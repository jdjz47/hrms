package com.hrms.hrms_verify.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hrms.hrms_verify.entity.Companymanager;
import com.hrms.hrms_verify.mapper.CompanymanagerMapper;
import com.hrms.hrms_verify.service.CompanymanagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
@Service
public class CompanymanagerServiceImpl extends ServiceImpl<CompanymanagerMapper, Companymanager> implements CompanymanagerService {

    @Override
    public Boolean loginVerify(String account, String password) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("account",account);
        wrapper.eq("password",password);
        List list = this.list(wrapper);
        if(list.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
