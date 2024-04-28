package com.hrms.hrms_advertise.config;

import com.hrms.hrms_advertise.entity.Advertiseinfo;
import com.hrms.hrms_advertise.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "hrms-regular",fallback = RemoteSalaryFallback.class,url = "localhost:8087")
public interface RemoteSalary {
    @PostMapping("/hrms_regular/regular/salaryInfo")
    public R getSalary(@RequestBody Advertiseinfo advertiseinfo);
}