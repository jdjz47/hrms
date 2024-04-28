package com.hrms.hrms_advertise.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.hrms_advertise.config.RemoteEmployee;
import com.hrms.hrms_advertise.config.RemoteSalary;
import com.hrms.hrms_advertise.entity.*;
import com.hrms.hrms_advertise.service.AdvertiseinfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
@RestController
@RequestMapping("/hrms_advertise/advertiseinfo")
public class AdvertiseinfoController {
    @Autowired
    AdvertiseinfoService service;

    @GetMapping("test001")
    public R test0888(HttpServletRequest request) {
        R ok = R.Ok();
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        ok.getData().put("msg", doub[0]);
        return ok;
    }


    @PostMapping("AdvertiseInfo")
    public R AdvertiseInfo(HttpServletRequest request) {
        R ok = R.Ok();
        HashMap data = ok.getData();
        //获取公司账号对应的Id用户查询相关信息
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        System.out.println("测试中名字"+doub);
        data.put("name", doub[1]);
        //获取员工信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("companyId", doub[0]);
        List<Advertiseinfo> list = service.list(wrapper);
        data.put("list", list);
        ok.setData(data);
        return ok;
    }

    @PostMapping("SingleInfo")
    public R SingleInfo(@RequestBody Advertiseinfo advertiseinfo) {
        R ok = R.Ok();
        HashMap data = ok.getData();
        Advertiseinfo byId = service.getById(advertiseinfo.getId());
        data.put("personInfo", byId);
        return ok;
    }

    @Autowired
    RemoteSalary salarys;
    @Autowired
    RemoteEmployee employee;

    @PostMapping("EmployeeInfo")
    public R EmployeeInfo(HttpServletRequest request, @RequestBody Advertiseinfo advertiseinfo) {
        System.out.println(advertiseinfo);
        //新增员工信息
        Employee employeeInfo = new Employee();
        BeanUtils.copyProperties(advertiseinfo, employeeInfo);
        employeeInfo.setId(null);
        //TODO:以部门和公司Id作为参数查询改公司的部门对应的工资
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        Advertiseinfo advertiseinfo1 = new Advertiseinfo();
        advertiseinfo1.setDepartment(advertiseinfo.getDepartment());
        advertiseinfo1.setCompanyId(Integer.parseInt(doub[0]));
        System.out.println(advertiseinfo + "," + advertiseinfo1);
        R salary = salarys.getSalary(advertiseinfo1);
        if (salary.getData().get("salary") == null && salary.getData().get("msg").equals("工资获取失败")) {
            return salary;
        }
        String sal = (String) salary.getData().get("salary");
        employeeInfo.setSalary(sal);
        employeeInfo.setDepartment(advertiseinfo.getDepartment());
//        switch (advertiseinfo.getPosition()){
//            case "业务经理":
//                employeeInfo.setDepartment("市场营销部");
//                employeeInfo.setSalary("5000");
//                break;
//            case "后端开发"  :
//                employeeInfo.setDepartment("研发部");
//                employeeInfo.setSalary("8000");
//                break;
//        }
        employeeInfo.setStage(0);
        employeeInfo.setState(2);
        employeeInfo.setOperation("1");
        employeeInfo.setCompanyId(Integer.parseInt(doub[0]));
        System.out.println(employeeInfo);
        R r = employee.addEmployee(doub[0],employeeInfo);
        System.out.println(r.getData().get("msg") + "测试中");
        if (!r.getData().get("msg").equals("添加失败,请稍后尝试")) {
            //调用成功
            service.deleteAdvertiseInfo(advertiseinfo.getId());
        }
        return r;
    }

    @PostMapping("addInterview")
    public R addInterview(@RequestBody IdVo idVo, HttpServletRequest request) {
        Integer[] array = idVo.getArray();
        System.out.println(array);
        for (int i = 0; i < array.length; i++) {
            Advertiseinfo advertiseinfo = new Advertiseinfo();
            advertiseinfo.setId(array[i]);
            advertiseinfo.setState("1");
            service.updateById(advertiseinfo);
        }
        R ok = R.Ok();
        HashMap data = ok.getData();
        return AdvertiseInfo(request);
    }

    @PostMapping("addAdvertise")
    public R addAdvertise(HttpServletRequest request, @RequestBody Advertiseinfo advertiseinfo) {
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        advertiseinfo.setState("0");
        advertiseinfo.setCompanyId(Integer.parseInt(doub[0]));
        System.out.println(advertiseinfo.getState()+"测试状态");
        boolean save = service.save(advertiseinfo);
        R ok = R.Ok();
        if (save) {
            ok.getData().put("msg", "添加员工成功");
        } else {
            ok.getData().put("msg", "添加员工失败");
        }
        return ok;
    }

    @PostMapping("AdvertiseRemove/{id}")
    public R EmployeeRemove(@PathVariable("id") Integer id) {
        service.removeById(id);
        R ok = R.Ok();
        HashMap data = ok.getData();
        data.put("msg", "删除人员成功");
        return ok;
    }

    @PostMapping("deleteAdvertise")
    public R deleteAdvertise(HttpServletRequest request,@RequestBody IdVo idVo) {
        System.out.println(idVo);
        Integer[] array = idVo.getArray();
        List<Integer> integers = Arrays.asList(array);
        boolean b = service.removeByIds(integers);
        if (b) {
            R ok = R.Ok();
            HashMap data = ok.getData();
            QueryWrapper wrapper=new QueryWrapper();
            String total = JwtUtils.getMemberIdByJwtToken(request);
            String doub[]=total.split(",");
           wrapper.eq("companyId",doub[0]);
            List<Advertiseinfo> list = service.list(wrapper);
            data.put("list", list);
            data.put("msg", "删除成功");
            return ok;
        } else {
            R error = R.error();
            HashMap data = error.getData();
            List<Advertiseinfo> list = service.list(null);
            data.put("list", list);
            data.put("msg", "删除失败");
            return error;
        }
    }

    @PostMapping("condition/{id}")
    public R condition(@PathVariable("id") String id,HttpServletRequest request) {
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        List<String> list = null;
        switch (id) {
            case "1":
                list = service.selectEdu(doub[0]);
                break;
            case "2":
                list = service.selectDepartment(doub[0]);
                break;
            case "3":
                list = service.selectPosition(doub[0]);
                break;
        }
        R ok = R.Ok();
        HashMap data = ok.getData();
        data.put("list", list);
        return ok;
    }

    @PostMapping("ConditionQuery")
    public R ConditionQuery(@RequestBody ConditionVo conditionVo,HttpServletRequest request){
        System.out.println(conditionVo.toString());
        QueryWrapper wrapper=new QueryWrapper();
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        if(!StringUtils.isEmpty(conditionVo.getEduBackground())){
            wrapper.eq("eduBackground",conditionVo.getEduBackground());
        }
        if(!StringUtils.isEmpty(conditionVo.getDepartment())){
            wrapper.eq("department",conditionVo.getDepartment());
        }
        if(!StringUtils.isEmpty(conditionVo.getPosition())){
            wrapper.eq("position",conditionVo.getPosition());
        }
        wrapper.eq("companyId",doub[0]);
        Page<Advertiseinfo> page=new Page<Advertiseinfo>(conditionVo.getCurrent(),conditionVo.getSize());
        service.page(page,wrapper);
        R ok = R.Ok();
        HashMap data = ok.getData();
        data.put("list",page.getRecords());
        data.put("total",page.getTotal());
        return ok;
    }
    @PostMapping("EditAdvertiseInfo/{id}")
    public R EditAdvertiseInfo(@PathVariable("id") String id){
        Advertiseinfo byId = service.getById(id);
        R ok = R.Ok();
        ok.getData().put("info",byId);
        return ok;
    }
    @PostMapping("EditAdvertiseHandle")
    public R EditAdvertiseHandle(@RequestBody Advertiseinfo advertiseinfo){
        System.out.println(advertiseinfo);
        service.updateById(advertiseinfo);
        R ok = R.Ok();
        ok.getData().put("msg","更新成功");
        return ok;
    }
}