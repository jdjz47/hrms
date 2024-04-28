package com.hrms.hrms_employee.controller;
import com.alibaba.excel.EasyExcel;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.hrms_employee.config.RegularRemote;
import com.hrms.hrms_employee.config.UploadRemote;
import com.hrms.hrms_employee.entity.*;
import com.hrms.hrms_employee.mapper.EmployeeMapper;
import com.hrms.hrms_employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
@RestController
@RequestMapping("/hrms_employee/employee")
public class EmployeeController {

    @Autowired
    EmployeeMapper mapper;
    @Autowired
    EmployeeService service;

    @PostMapping("hrDepartment")
    public R hrDepartmentInfo(HttpServletRequest request, @RequestBody PageUtils pageUtils) {
        R ok = R.Ok();
        HashMap data = ok.getData();
        //获取公司Id
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        data.put("name", doub[1]);
        //获取公司对应的员工信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("companyId", doub[0]);
        List<Employee> list1 = service.list(wrapper);
        Page<Employee> page = new Page(Integer.parseInt(pageUtils.getCurrent()), Integer.parseInt(pageUtils.getSize()));
        service.page(page, wrapper);
        List<Employee> list = page.getRecords();
        data.put("list", list);
        data.put("total", page.getTotal());
        //获取全职的集合
        List<Employee> collect = list1.stream().filter((p) ->
                p.getState() == 0
        ).collect(Collectors.toList());
        //获取兼职集合
        List<Employee> collect1 = list1.stream().filter((p) ->
                p.getState() == 1
        ).collect(Collectors.toList());
        //获取实习人数
        List<Employee> collect2 = list1.stream().filter((p) ->
                p.getState() == 2
        ).collect(Collectors.toList());
        System.out.println(collect.size() + ',' + collect1.size() + ',' + collect2.size());
        //全职
        data.put("full", collect.size());
        //兼职
        data.put("hold", collect1.size());
        //实习
        data.put("practice", collect2.size());
        //获取试用期集合
        List<Employee> collect3 = list1.stream().filter((p) ->
                p.getStage() == 0
        ).collect(Collectors.toList());
        //获取待离职的集合
        List<Employee> collect4 = list1.stream().filter((p) ->
                p.getStage() == 1
        ).collect(Collectors.toList());


        //获取正式的集合
        List<Employee> collect5 = list1.stream().filter((p) ->
                p.getStage() == 2
        ).collect(Collectors.toList());
        //试用期
        data.put("probation", collect3.size());
        //待离职
        data.put("resignation", collect4.size());
        //正式
        data.put("officially", collect5.size());
        ok.setData(data);
        return ok;
    }

    @Autowired
    RegularRemote regularRemote;

    @PostMapping("addEmployee")
    public R addEmployee(HttpServletRequest request,@RequestBody Employee employee) {
        System.out.println(employee);
        //将员工信息的在职状态由字符串转换为数字,已经岗位转态
        if (employee.getState() == null) {
            String stateTemp = employee.getStateTemp();
            switch (stateTemp) {
                case "全职":
                    employee.setState(0);
                    break;
                case "兼职":
                    employee.setState(1);
                    break;
                case "实习":
                    employee.setState(2);
                    break;
            }
        }
        if (employee.getStage() == null) {
            //处理员工的岗位阶段
            String stageTemp = employee.getStageTemp();
            System.out.println(stageTemp);
            switch (stageTemp) {
                case "试用期":
                    employee.setStage(0);
                    break;
                case "待离职":
                    employee.setStage(1);
                    break;
                case "正式":
                    employee.setStage(2);
                    break;
            }
        }
        boolean save;
        //为空则是由人事部模块直接的添加请求
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        employee.setCompanyId(Integer.parseInt(doub[0]));
        if (employee.getCompanyId() == null) {
            //如果不是则是由招聘部门发起的远程调用添加面试成功的人员
            R r = regularRemote.salarayInfi(employee);
            if (r.getData().get("msg") != null) {
                //返回错误
                return r;
            }
            String salary = (String) r.getData().get("salary");
            employee.setSalary(salary);
        }
        System.out.println(employee.getOperation());
        if (employee.getOperation().equals("1")) {
            //添加
            save = service.save(employee);
        } else {
            //修改员工数据
            save = service.updateById(employee);
        }
        String id = String.valueOf(employee.getCompanyId());
        if (save) {
            R ok = R.Ok();
            if (employee.getOperation().equals("1")) {
                ok.getData().put("msg", "添加成功");
            } else {
                ok.getData().put("msg", "修改成功");
            }
            return ok;
        } else {
            R error = R.error();
            error.getData().put("msg", "添加失败");
            if (employee.getOperation() == "1") {
                error.getData().put("msg", "添加失败");
            } else {
                error.getData().put("msg", "修改失败");
            }
            return error;
        }
    }
    @PostMapping("addEmployee_test")
    public R addEmployee(String ids,@RequestBody Employee employee) {
        System.out.println(employee);
        //将员工信息的在职状态由字符串转换为数字,已经岗位转态
        if (employee.getState() == null) {
            String stateTemp = employee.getStateTemp();
            switch (stateTemp) {
                case "全职":
                    employee.setState(0);
                    break;
                case "兼职":
                    employee.setState(1);
                    break;
                case "实习":
                    employee.setState(2);
                    break;
            }
        }
        if (employee.getStage() == null) {
            //处理员工的岗位阶段
            String stageTemp = employee.getStageTemp();
            System.out.println(stageTemp);
            switch (stageTemp) {
                case "试用期":
                    employee.setStage(0);
                    break;
                case "待离职":
                    employee.setStage(1);
                    break;
                case "正式":
                    employee.setStage(2);
                    break;
            }
        }
        boolean save;
        //为空则是由人事部模块直接的添加请求
        employee.setCompanyId(Integer.parseInt(ids));
        if (employee.getCompanyId() == null) {
            //如果不是则是由招聘部门发起的远程调用添加面试成功的人员
            R r = regularRemote.salarayInfi(employee);
            if (r.getData().get("msg") != null) {
                //返回错误
                return r;
            }
            String salary = (String) r.getData().get("salary");
            employee.setSalary(salary);
        }
        System.out.println(employee.getOperation());
        if (employee.getOperation().equals("1")) {
            //添加
            save = service.save(employee);
        } else {
            //修改员工数据
            save = service.updateById(employee);
        }
        String id = String.valueOf(employee.getCompanyId());
        if (save) {
            R ok = R.Ok();
            if (employee.getOperation().equals("1")) {
                ok.getData().put("msg", "添加成功");
            } else {
                ok.getData().put("msg", "修改成功");
            }
            return ok;
        } else {
            R error = R.error();
            error.getData().put("msg", "添加失败");
            if (employee.getOperation() == "1") {
                error.getData().put("msg", "添加失败");
            } else {
                error.getData().put("msg", "修改失败");
            }
            return error;
        }
    }
    //TODO:根据公司岗位和在职状态or岗位阶段来更加精确锁定需要编辑的员工信息
    @PostMapping("EditInfo")
    public R EditInfo(HttpServletRequest request, @RequestBody EmployeeVO employeeVO) {
        System.out.println(employeeVO.toString() + "测试");
        QueryWrapper wrapper = new QueryWrapper();
        switch (employeeVO.getSituation()) {
            case "全职":
                wrapper.eq("state", 0);
                break;
            case "兼职":
                wrapper.eq("state", 1);
                break;
            case "实习":
                wrapper.eq("state", 2);
                break;
            case "待离职":
                wrapper.eq("stage", 1);
                break;
            case "试用期":
                wrapper.eq("stage", 0);
                break;
            case "正式":
                wrapper.eq("stage", 2);
                break;
        }
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        wrapper.eq("companyId", doub[0]);
        wrapper.eq("department", employeeVO.getPosition());
        List<Employee> list = service.list(wrapper);
        R ok = R.Ok();
        ok.getData().put("list", list);
        return ok;
    }

    @PostMapping
    R EmployeeInfo() {

        return null;

    }

    //查询部门情况
    @PostMapping("PositionInfo")
    public R DepartmentInfo() {
        List<String> positionInfo = mapper.getDepartmentInfo();
        R ok = R.Ok();
        ok.getData().put("list", positionInfo);
        return ok;
    }

    //根据Id查询员工信息
    @PostMapping("EmployeeInfo")
    public R EmployeeInfo(@RequestBody Employee employee) {
        System.out.println(employee.getId());
        Employee byId = service.getById(employee.getId());
        R ok = R.Ok();
        HashMap data = ok.getData();
        data.put("employeeInfo", byId);
        return ok;
    }
    @Value("${spring.mail.username}")
    String username;
    @PostMapping("SendMail")
    public R SendMail(@RequestBody WrapperList lists) {
        List<TextVo> list = lists.getList();
        System.out.println(lists.toString()+"工资数据");
        List<Employee> employeeList = service.list(null);
        List<MailRequest> mailRequestList=new ArrayList<>();
        employeeList.stream().map(p -> {
            for (int i = 0; i < list.size(); i++) {
                MailRequest request = new MailRequest();
                System.out.println("测试"+i);
                if (p.getId()==list.get(i).getId()) {
                    request.setSendTo(p.getEmail());
                    request.setSubject(p.getName() + "工资条");
                    TextVo textVo = list.get(i);
                    request.setText("姓名:" + p.getName() + ",出勤天数:" + textVo.getNumber() + ",日薪资:" + textVo.getSalary() + ",奖金:" + textVo.getReward() + ",总工资" + textVo.getTotalWage() + ",实发工资" + textVo.getFact() + ",部门" + textVo.getDepartment());
                    request.setFilePath("X:\\Image\\mr.jpg");
                    sendMaillHTML(request);
                    mailRequestList.add(request);
                }
            }
            return true;
        }).forEach(System.out::println);
        System.out.println(mailRequestList.toString());
        R ok = R.Ok();
        ok.getData().put("msg","发送成功");
        return ok;
    }
    @Autowired
    JavaMailSender javaMailSender;
    public Boolean sendMaillHTML(MailRequest mailProperties){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom(username);
            // 邮件发送目标
            mimeMessageHelper.setTo(mailProperties.getSendTo());
            System.out.println(mailProperties.getSendTo());
            // 设置标题
            mimeMessageHelper.setSubject(mailProperties.getSubject());
            // 设置内容，并设置内容 html 格式为 true
            mimeMessageHelper.setText(mailProperties.getText(),true);
              //注意这里是你可以选择发生给每个人的图片,这里是地址因为本地电脑不同
              //mimeMessageHelper.addAttachment("mr.jpg", new File(mailProperties.getFilePath()));
            System.out.println(mimeMessage.toString()+"测试");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    static int n=1;
    @PostMapping("ImportData")
    public R ImportData(@RequestBody WrapperList list){
        System.out.println("这一步正在测试");
        System.out.println(list.getList());
        List<TextVo> list1 = list.getList();

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        //下载到自己的电脑本地文件夹中
        String filename = "E:/"+format.format(new Date())+n+"wage.xlsx";
        n++;
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(list1);
        return R.Ok();
    }
    @Autowired
    UploadRemote remote;
   
    @PostMapping("deleteEmployee")
    public R deleteEmployee(@RequestBody Employee employee,HttpServletRequest request){
        service.removeById(employee);
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
        return R.Ok();
    }
    @PostMapping("IndexCache")
    public R IndexCache(HttpServletRequest request) throws JsonProcessingException {
        String total = JwtUtils.getMemberIdByJwtToken(request);
        String doub[]=total.split(",");
            //查询数据库,如果list值为空就没有查询其他数据的必要了
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("companyId", doub[0]);
            List<Employee> list1 = service.list(wrapper);
            //获取全职的集合
            List<Employee> collect = list1.stream().filter((p) ->
                    p.getState() == 0
            ).collect(Collectors.toList());
            //获取兼职集合
            List<Employee> collect1 = list1.stream().filter((p) ->
                    p.getState() == 1
            ).collect(Collectors.toList());
            //获取实习人数
            List<Employee> collect2 = list1.stream().filter((p) ->
                    p.getState() == 2
            ).collect(Collectors.toList());
            System.out.println(collect.size() + ',' + collect1.size() + ',' + collect2.size());
            //获取试用期集合
            List<Employee> collect3 = list1.stream().filter((p) ->
                    p.getStage() == 0
            ).collect(Collectors.toList());
            //获取待离职的集合
            List<Employee> collect4 = list1.stream().filter((p) ->
                    p.getStage() == 1
            ).collect(Collectors.toList());
            //获取正式的集合
            List<Employee> collect5 = list1.stream().filter((p) ->
                    p.getStage() == 2
            ).collect(Collectors.toList());
            //设置互斥锁
            R ok = R.Ok();
            HashMap data = ok.getData();
            ObjectMapper mappers = new ObjectMapper();
            String s = mappers.writeValueAsString(list1);

            data.put("list", list1);

            data.put("full", collect.size());
            //兼职
            data.put("hold", collect1.size());
            //实习
            data.put("practice", collect2.size());
            //试用期
            data.put("probation", collect3.size());
            //待离职
            data.put("resignation", collect4.size());
            //正式
            data.put("officially", collect5.size());
            data.put("name", doub[1]);
            return ok;
    }
}