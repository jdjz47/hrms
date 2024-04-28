package com.hrms.hrms_advertise.config;

import com.hrms.hrms_advertise.entity.Advertiseinfo;
import com.hrms.hrms_advertise.entity.R;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
@Component
public class RemoteSalaryFallback implements RemoteSalary{
    @Override
    public R getSalary(@RequestBody Advertiseinfo advertiseinfo) {
        R error = R.error();
        HashMap data = error.getData();
        data.put("msg","工资获取失败");
        return error;
    }
}
