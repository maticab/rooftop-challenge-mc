package com.rooftopmc.challenge.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class CustomUtils {
    public static String hashForTextanalizer(String text, int chars) {
        return DigestUtils.md5DigestAsHex((text + chars).getBytes(StandardCharsets.UTF_8));
    }
}
