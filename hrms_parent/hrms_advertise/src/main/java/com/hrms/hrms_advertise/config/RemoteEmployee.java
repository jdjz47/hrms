package com.hrms.hrms_advertise.config;

import com.hrms.hrms_advertise.entity.Employee;
import com.hrms.hrms_advertise.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Component
@FeignClient(name = "hrms-employee",url="localhost:8082",fallback = RemoteFallback.class)
public interface RemoteEmployee {

    @PostMapping("/hrms_employee/employee/addEmployee_test")
    public R addEmployee(@RequestParam("ids") String ids, @RequestBody Employee employee);

}
