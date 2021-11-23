package com.nilbrains.nilamdinserver.utils;

public interface Constants {

    interface DB {
        String FLAG_TRUE = "1";
        String FLAG_FALSE = "0";
        String NULL = "";
        String DEFALT_PASS = "123456";
    }

    interface FILE_TYPE {
        String IMAGE = "image";
    }

    interface TimeValue {
        int MIN = 60;
        int HOUR = MIN * 60;
        int HOUR_2 = 2 * HOUR;
        int DAY = 24 * HOUR;
        long MONTH = 30 * DAY;
    }

    interface Setting {
        String HEADER_AUTH = "auth";
        String HEAD_URL_START = "https://cravatar.cn/avatar/";
        String URL = "https://data.nilbrains.com/auth/check/";
    }

}
