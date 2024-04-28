package com.hrms.hrms_advertise;

import com.hrms.hrms_advertise.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @GetMapping("/test")
    public R testp(HttpServletRequest request){
        R ok=R.Ok();
        ok.getData().put("msg","test");
        return ok;
    }
}
