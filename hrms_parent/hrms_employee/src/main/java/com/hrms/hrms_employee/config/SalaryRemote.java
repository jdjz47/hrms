package com.hrms.hrms_employee.config;



import com.hrms.hrms_employee.entity.Employee;
import com.hrms.hrms_employee.entity.R;
import org.springframework.stereotype.Component;

@Component
public class SalaryRemote implements RegularRemote{
    @Override
    public R salarayInfi(Employee advertiseinfo) {
        String s=new String("添加失败,请稍后尝试");
        R error = R.error();
        error.getData().put("msg",s);
        return error;
    }
}
