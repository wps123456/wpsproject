package com.wps.studyplatform.generator.mapper;

import com.wps.studyplatform.generator.entity.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}