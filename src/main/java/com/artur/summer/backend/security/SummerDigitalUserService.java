package com.artur.summer.backend.security;

import com.artur.summer.backend.constants.ClientStatus;
import com.artur.summer.backend.model.ClientInfo;
import com.artur.summer.backend.repository.ClientInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component("summer")
public class SummerDigitalUserService implements UserDetailsService {

    private ClientInfoRepository clientInfoRepository;
    private Long secretLifeTimeDays;

    public SummerDigitalUserService(ClientInfoRepository clientInfoRepository, @Value("${app.secret-lifetime}") Long secretLifeTimeDays) {
        this.clientInfoRepository = clientInfoRepository;
        this.secretLifeTimeDays = secretLifeTimeDays;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientInfo clientInfo = clientInfoRepository.findAllByLogin(username);
        if (clientInfo == null) {
            throw new UsernameNotFoundException("Login " + username + " is not found");
        }
        return new CustomUserDetails(clientInfo, secretLifeTimeDays);
    }

    static final class CustomUserDetails extends ClientInfo implements UserDetails {

        private static final List<GrantedAuthority> ROLE_USER = Collections
                .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));
        private Long secretLifeTimeDays;

        CustomUserDetails(ClientInfo clientInfo, Long secretLifeTimeDays) {
            super(clientInfo);
            this.secretLifeTimeDays = secretLifeTimeDays;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return ROLE_USER;
        }

        @Override
        public String getPassword() {
            return getSecretHash();
        }

        @Override
        public String getUsername() {
            return getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return !ClientStatus.BLOCKED.equals(getStatus());
        }

        @Override
        public boolean isCredentialsNonExpired() {
            LocalDateTime secretDate = getSecretDate() != null ? getSecretDate() : LocalDateTime.now();
            return LocalDateTime.now().isBefore(secretDate.plusDays(secretLifeTimeDays));
        }

        @Override
        public boolean isEnabled() {
            return ClientStatus.ACTIVE.equals(getStatus());
        }

    }
}
