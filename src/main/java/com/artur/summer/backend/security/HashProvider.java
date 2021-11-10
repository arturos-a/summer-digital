package com.artur.summer.backend.security;

public interface HashProvider {
    byte[] getHash(String data);
}
