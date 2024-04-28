package com.hrms.hrms_employee.entity;

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
@ApiModel(value="Employee对象", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "员工薪资")
    private String salary;

    @ApiModelProperty(value = "公司部门")
    private String department;

    @ApiModelProperty(value = "公司岗位")
    private String position;

    @ApiModelProperty(value = "在职状态比如:全职 0,兼职 1,实习2")
    private Integer state;

    @ApiModelProperty(value = "试用期:  0  , 代理职  : 1 , 正式 : 2）默认正式")
    private Integer stage;

    @ApiModelProperty(value = "临时存储使用的,因为前端的组件只能传递字符串无法转换为0,1,2")
    @TableField(exist = false)
    String stateTemp;

    @ApiModelProperty(value = "原因同上")
    @TableField(exist = false)
    String stageTemp;

    @ApiModelProperty(value = "手机号码")
    private String email;

    @ApiModelProperty(value = "对应的公司管理的Id")
    @TableField("companyId")
    private Integer companyId;

    @ApiModelProperty(value = "判断添加还是修改的操作")
    @TableField(exist = false)
    private String operation;

    String dayTime;

}
