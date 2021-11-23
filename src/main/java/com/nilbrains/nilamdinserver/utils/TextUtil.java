package com.nilbrains.nilamdinserver.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TextUtil {
    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }

    public static String ganRandomNum() {
        return Math.floor(Math.random() * 999 + 1000) + "";
    }

    public static String ganToken() {
        return DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
    }
}
