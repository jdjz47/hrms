package com.hrms.hrms_regular.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class R {
    //返回结果类JSON格式,需要属性
//返回结果必须是固定的,所以私有化构造函数
    private R() {}
    @ApiModelProperty(value = "是否成功返回")
    private Boolean success;
    @ApiModelProperty(value = "返回状态码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private HashMap data=new HashMap();
    public static R Ok(){
    //外部不能创建对象,只能内部创建
        R r=new R();
        r.setSuccess(true);
        r.setCode(ResultCode.Success);
        r.setMessage("成功");
        return r;
    }
    public static R error(){
    R r=new R();
    r.setSuccess(false);
    r.setCode(ResultCode.Error);
    r.setMessage("失败");
    return r;
    }
}
