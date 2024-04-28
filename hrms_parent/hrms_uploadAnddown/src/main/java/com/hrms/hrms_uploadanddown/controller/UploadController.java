package com.hrms.hrms_uploadanddown.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrms.hrms_uploadanddown.entity.*;
import com.hrms.hrms_uploadanddown.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author czp
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/hrms_uploadAnddown/upload")
public class UploadController {
    @Autowired
    UploadService service;
    //添加
    @PostMapping("addFile")
    public R addFile(@RequestBody Upload upload){
    service.save(upload);
    return R.Ok();
    }
    @PostMapping("getList")
    public R getList(@RequestBody Map map, HttpServletRequest request){
        QueryWrapper wrapper=new QueryWrapper();
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        String dateTime =(String) map.get("dateTime");
        wrapper.eq("create_time",dateTime);
        wrapper.eq("companyId",doub[0]);
        List<Upload> list = service.list(wrapper);
        R ok = R.Ok();
        ok.getData().put("list",list);
        return ok;
    }
}