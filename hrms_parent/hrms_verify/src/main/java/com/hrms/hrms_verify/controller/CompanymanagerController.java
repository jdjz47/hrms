package com.hrms.hrms_verify.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrms.hrms_verify.entity.Companymanager;
import com.hrms.hrms_verify.entity.JwtUtils;
import com.hrms.hrms_verify.entity.R;
import com.hrms.hrms_verify.service.CompanymanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/hrms_verify/companymanager")
public class CompanymanagerController {
    @Autowired
    CompanymanagerService service;
    @PostMapping("login")
    public R loginHandle(@RequestBody Companymanager companymanager,HttpServletResponse response) {
        //处理用户登录,成功后返回JWT字符串
        //查询数据库如果存在并且密码正确则登录成功
        System.out.println("测试成功");
        Boolean b = service.loginVerify(companymanager.getAccount(), companymanager.getPassword());
        if(b) {
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("account", companymanager.getAccount());
            List list = service.list(wrapper);
            Companymanager o =(Companymanager)list.get(0);
            R ok = R.Ok();
            String jwtToken = JwtUtils.getJwtToken(String.valueOf(o.getId()), o.getName());
            //将JWT字符串存储到Cookie中传送到前端
            ok.getData().put("msg","登录成功");
            ok.getData().put("token",jwtToken);
            return ok;
        }else{
            R error = R.error();
            error.getData().put("msg","账号或者密码错误请重试");
            return error;
        }
        }
    @GetMapping("logins")
    public R loginHandle(String account) {
        //TODO:处理用户登录,成功后返回JWT字符串
        R ok = R.Ok();
        ok.getData().put("accountAndPassword",account);
        return ok;
    }
    @PostMapping("register")
    public R registerHandle(@RequestBody Companymanager companymanager){
    QueryWrapper wrapper=new QueryWrapper();
    wrapper.eq("name",companymanager.getName());
        List list = service.list(wrapper);
        if(list.size()>0){
            //说明存在这个公司的账号
            R error = R.error();
            error.getData().put("msg","注册失败");
            return error;
        }else{
            //不存在该公司账号进行注册
            service.save(companymanager);
            R ok = R.Ok();
            ok.getData().put("msg","注册成功");
            return ok;
        }
    }
}

