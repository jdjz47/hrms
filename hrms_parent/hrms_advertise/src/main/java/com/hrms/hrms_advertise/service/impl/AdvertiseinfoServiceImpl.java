package com.hrms.hrms_advertise.service.impl;

import com.hrms.hrms_advertise.entity.Advertiseinfo;
import com.hrms.hrms_advertise.mapper.AdvertiseinfoMapper;
import com.hrms.hrms_advertise.service.AdvertiseinfoService;
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
public class AdvertiseinfoServiceImpl extends ServiceImpl<AdvertiseinfoMapper, Advertiseinfo> implements AdvertiseinfoService {

    @Override
    public Boolean deleteAdvertiseInfo(Integer id) {
        boolean b = this.removeById(id);
        return b;
    }

    @Override
    public List<String> selectEdu(String id) {
        List<String> list = this.baseMapper.selectEdus(Integer.parseInt(id));
        return list;
    }

    @Override
    public List<String> selectDepartment(String id) {
        List<String> list = this.baseMapper.selectDepartments(Integer.parseInt(id));
        return list;
    }

    @Override
    public List<String> selectPosition(String id) {
        List<String> list = baseMapper.selectPositions(Integer.parseInt(id));
        return list;
    }

}
