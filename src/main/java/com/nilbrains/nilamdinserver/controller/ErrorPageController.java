/*
 * Copyright (c) 2021.
 * Author: NilBrains
 * Date: 2021/9/9 下午12:54
 */

package com.nilbrains.nilamdinserver.controller;

import com.nilbrains.nilamdinserver.entity.resp.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {
    @RequestMapping("/404")
    public ResponseResult page404() {
        return ResponseResult.ERROR_404();
    }

    @RequestMapping("/403")
    public ResponseResult page403() {
        return ResponseResult.ERROR_403();
    }

    @RequestMapping("/504")
    public ResponseResult page504() {
        return ResponseResult.ERROR_504();
    }

    @RequestMapping("/505")
    public ResponseResult page505() {
        return ResponseResult.ERROR_505();
    }

    @RequestMapping("/405")
    public ResponseResult page405() {
        return ResponseResult.ERROR_405();
    }
}
