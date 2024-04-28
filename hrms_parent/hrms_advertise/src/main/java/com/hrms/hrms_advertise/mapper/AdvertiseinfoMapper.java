package com.hrms.hrms_advertise.mapper;

import com.hrms.hrms_advertise.entity.Advertiseinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface AdvertiseinfoMapper extends BaseMapper<Advertiseinfo> {
    public List<String> selectEdus(@Param("id") Integer id);
    public List<String> selectDepartments(@Param("id") Integer id);
    public List<String> selectPositions(@Param("id") Integer id);
}
