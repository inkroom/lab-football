package com.nsu.dao.core;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 *
 */
public interface RoleAuthorityDao {
	 public Map<String, Object> getRoleAuthority(@Param("roleCode")String roleCode ,@Param("url")String url) throws Exception;
}
