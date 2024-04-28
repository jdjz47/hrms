package com.hrms.hrms_employee.entity;

import lombok.Data;

@Data
public class PageUtils {
    //当前的页面数
    String current;
    //当前查询多少行
    String size;
}