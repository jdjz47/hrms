package com.hrms.hrms_employee.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {

    //设置表头名称
    @ExcelProperty("id")
    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("出勤情况")
    private String number;

    @ExcelProperty("日薪资")
    private String salary;

    @ExcelProperty("奖金")
    private String reward;

    @ExcelProperty("总工资")
    private String totalWage;

    @ExcelProperty("实发工资")
    private String fact;

    @ExcelProperty("所属部门")
    private String department;

}
