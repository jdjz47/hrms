package com.hrms.hrms_verify.service;

import com.hrms.hrms_verify.entity.Companymanager;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
public interface CompanymanagerService extends IService<Companymanager> {

    Boolean loginVerify(String account, String password);
}
