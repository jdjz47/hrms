package com.hrms.hrms_advertise.controller;

import com.hrms.hrms_advertise.entity.JwtUtils;
import com.hrms.hrms_advertise.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hrms_advertise")
public class AdvertiseTestController {
    @GetMapping("test001")
    public R test0888(HttpServletRequest request) {
        R ok = R.Ok();
        ok.getData().put("msg","Hello World");
        return ok;
    }
}
