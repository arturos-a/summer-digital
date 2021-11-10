package com.artur.summer.backend.security.impl;

import com.artur.summer.backend.security.HashProvider;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class Sha256HashProvider implements HashProvider {
    @Override
    public byte[] getHash(String data) {
        return DigestUtils.sha256Hex(data).getBytes(StandardCharsets.UTF_8);
    }
}
