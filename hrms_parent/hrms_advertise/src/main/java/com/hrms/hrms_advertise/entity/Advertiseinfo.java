package com.hrms.hrms_advertise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Advertiseinfo对象", description="")
public class Advertiseinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司Id")
    @TableField("companyId")
    private Integer companyId;

    @ApiModelProperty(value = "员工名词")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "应聘的公司岗位")
    private String position;

    @ApiModelProperty(value = "员工性别")
    private String sex;

    @ApiModelProperty(value = "员工年龄")
    private String age;

    @ApiModelProperty(value = "学历如:大专,本科,研究生,博士")
    @TableField("eduBackground")
    private String eduBackground;

    @ApiModelProperty(value = "工作年限")
    private String experience;

    @ApiModelProperty(value = "手机号码")
    private String email;

    @ApiModelProperty(value = "毕业学校")
    private String school;

    @ApiModelProperty(value = "所学专业")
    private String speciality;

    @ApiModelProperty(value = "工作经历:例如在*****公司工作")
    private String undergo;

    @ApiModelProperty(value = "面试状态")
    private String state;

    @ApiModelProperty(value = "意愿部门")
    private String department;
}
