package com.hrms.hrms_employee.service.impl;

import com.hrms.hrms_employee.entity.Employee;
import com.hrms.hrms_employee.mapper.EmployeeMapper;
import com.hrms.hrms_employee.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
