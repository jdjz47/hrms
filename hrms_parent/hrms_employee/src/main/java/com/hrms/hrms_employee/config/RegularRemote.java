package com.hrms.hrms_employee.config;

import com.hrms.hrms_employee.entity.Employee;
import com.hrms.hrms_employee.entity.R;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "hrms-regular",fallback = SalaryRemote.class,url = "localhost:8087")
public interface RegularRemote {
    @PostMapping("/hrms_regular/regular/salaryInfo")
    public R salarayInfi(@RequestBody Employee advertiseinfo);
}
