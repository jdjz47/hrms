package com.hrms.hrms_employee.mapper;

import com.hrms.hrms_employee.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    public List<String> getDepartmentInfo();
}
