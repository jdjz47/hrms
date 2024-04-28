package com.hrms.hrms_advertise.config;

import com.hrms.hrms_advertise.entity.Advertiseinfo;
import com.hrms.hrms_advertise.entity.Employee;
import com.hrms.hrms_advertise.entity.R;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RemoteFallback implements RemoteEmployee{

    @Override
    public R addEmployee(String ids, Employee employee) {
        R error = R.error();
        error.getData().put("msg","添加失败,请稍后尝试");
        return error;
    }
}
