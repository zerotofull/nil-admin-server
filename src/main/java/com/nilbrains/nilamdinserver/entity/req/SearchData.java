package com.nilbrains.nilamdinserver.entity.req;

import lombok.Data;

@Data
public class SearchData {
    private Integer current = 1;
    private Integer size = 10;
    private String key = "";
}
