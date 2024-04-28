package com.hrms.hrms_employee.config;

import com.hrms.hrms_employee.entity.R;
import com.hrms.hrms_employee.entity.Upload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="hrms-uploadAnddown",url = "localhost:8088")
public interface UploadRemote {
    @PostMapping("/hrms_uploadAnddown/upload/addFile")
    public R addFile(@RequestBody Upload upload);
}