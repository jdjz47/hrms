package com.hrms.hrms_regular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.hrms_regular.entity.*;
import com.hrms.hrms_regular.service.RegularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author czp
 * @since 2022-10-18
 */
@RestController
@RequestMapping("/hrms_regular/regular")
public class RegularController {
    @Autowired
    RegularService service;
    @PostMapping("salaryInfo")
    public R salaryInfo(@RequestBody Advertiseinfo advertiseinfo){
        System.out.println(advertiseinfo.getDepartment());
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("companyId",advertiseinfo.getCompanyId());
        wrapper.eq("name",advertiseinfo.getDepartment());
        List<Regular> list = service.list(wrapper);
        Regular regular = list.get(0);
        String salary = regular.getSalary();
        R ok = R.Ok();
        ok.getData().put("salary",salary);
        return ok;
    }
    @PostMapping("salaryEmployeeInfo")
    public R salaryInfo(@RequestBody Employee employee){
        System.out.println(employee.getDepartment());
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("companyId",employee.getCompanyId());
        wrapper.eq("name",employee.getDepartment());
        List<Regular> list = service.list(wrapper);
        Regular regular = list.get(0);
        String salary = regular.getSalary();
        R ok = R.Ok();
        ok.getData().put("salary",salary);
        return ok;
    }
    @PostMapping("departmentInfo")
    public R departmentInfo(HttpServletRequest request){
        QueryWrapper wrapper=new QueryWrapper();
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        wrapper.eq("companyId",doub[0]);
        List<Regular> list = service.list(wrapper);
        R ok = R.Ok();
        HashMap data = ok.getData();
        data.put("list",list);
        return ok;
    }
    @PostMapping("InfoAll")
    public R InfoAll(HttpServletRequest request,@RequestBody Regular regular){
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        Integer current = regular.getCurrent();
        Integer size = regular.getSize();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("companyId",doub[0]);
        Page page=new Page(current,size);
        service.page(page,wrapper);
        R ok = R.Ok();
        HashMap data = ok.getData();
        data.put("list",page.getRecords());
        data.put("total",page.getTotal());
        return ok;
    }
    @PostMapping("addRegular")
    public R addRegular(@RequestBody Regular regular,HttpServletRequest request){
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        regular.setCompanyId(doub[0]);
        boolean save = service.save(regular);
        if(save){
            R ok = R.Ok();
            ok.getData().put("msg","添加成功");
            return ok;
        }else{
            R error = R.error();
            error.getData().put("msg","添加失败");
            return error;
        }
    }
    @PostMapping("deleteStanded")
    public R deleteStanded(@RequestBody Regular regular){
        boolean b = service.removeById(regular.getId());
       return R.Ok();
    }
    @PostMapping("getInfo")
    public R getInfo(@RequestBody Regular regular){
        Regular byId = service.getById(regular.getId());
        R ok = R.Ok();
        HashMap data = ok.getData();
        data.put("info",byId);
        return ok;
    }
    @PostMapping("updateRegular")
    public R updateRegular(@RequestBody Regular regular){
    service.updateById(regular);
    return R.Ok();
    }
}