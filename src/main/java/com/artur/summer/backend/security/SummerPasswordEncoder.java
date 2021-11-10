package com.artur.summer.backend.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;

@Slf4j
public class SummerPasswordEncoder extends AbstractPasswordEncoder {
    private final String ANCHOR_SYMBOL = ":";

    @Override
    protected byte[] encode(CharSequence rawPassword, byte[] salt) {
        log.info("Пока не реализовано");
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        int i = rawPassword.toString().indexOf(ANCHOR_SYMBOL);
        if (i < 0) {
            log.error("Отсуствует значение соли");
            return false;
        }
        String salt = rawPassword.toString().substring(0, i);
        String hashedPassword = rawPassword.toString().substring(i + 1);
        return DigestUtils.sha256Hex(salt + encodedPassword).equals(hashedPassword);
    }
}
