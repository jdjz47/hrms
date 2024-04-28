package com.hrms.hrms_advertise.service;

import com.hrms.hrms_advertise.entity.Advertiseinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author czp
 * @since 2022-10-05
 */
public interface AdvertiseinfoService extends IService<Advertiseinfo> {

    Boolean deleteAdvertiseInfo(Integer id);

    List<String> selectEdu(String id);

    List<String> selectDepartment(String id);

    List<String> selectPosition(String id);
}