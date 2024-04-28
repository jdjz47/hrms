package com.hrms.hrms_advertise.entity;

import lombok.Data;

@Data
public class ConditionVo {
    //每页多少行
    Integer size;
    //当前多少页
    Integer current;
    //学历
    String eduBackground;
    //部门
    String department;
    //岗位
    String position;

}
