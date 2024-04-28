package com.hrms.hrms_regular.entity;

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
 * @since 2022-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Regular对象", description="")
public class Regular implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    //这就是部门
    private String name;

    private String salary;

    @TableField("companyId")
    private String companyId;

    private String reward;

    @TableField(exist = false)
    private Integer size;

    @TableField(exist = false)
    private Integer current;

}