package com.wps.studyplatform.sqlcollect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wps.studyplatform.sqlcollect.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Title SysRoleMapper
 * @Description
 * @auther wps
 * @Date 2020/7/219:58
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

   public IPage<SysRole> queryRoleList(Page page, Map<String,Object> params);

    List<UserPositionBean> queryPositionByUserId(@Param("userId") Long userId);

    public List<String> queryPermsByUserId(@Param("userId") Long userId,@Param("delFlag") String delFlag,@Param("status") String status);

    IPage<RespSmartAppApply> myApply(Page page,Map<String,Object> params);

    boolean batchUpdate(@Param("params") Map<String,Object> params);

    int updateBatch(Map<String, Object> map1);

    int maxMenuId(@Param("parentId") long parentId);

 /**
  * 查询映射明城是否存在
  */
    boolean selectMapperName(@Param("randomString") String randomString);
 /**
  * 查询服务下相应状态的总和
  * serviceId 能力id
  * status 响应状态
  * isEqual 状态的使用条件 true为= ；false为！=
  */
  Integer queryStatusSumByServiceId(@Param("serviceId") Long serviceId, @Param("status") String status,@Param("isEqual") boolean isEqual);

  int selectCountByDate(@Param("yesterday")Timestamp yesterday,
                        @Param("today") Timestamp today,
                        @Param("serviceId") Long serviceId);
  List<APIAlarmInfoModel> findAlarmDetails(@Param("pageSize") int pageSize,@Param("offset") int offset);

 /**
  * api添加熔断模板
  */
  void insert (APIHysBean bean);
 /**
  * 更新熔断模板
  */
 void update (APIHysBean bean);

 void batchDeleteByIds(List<String> apiIds);
}
