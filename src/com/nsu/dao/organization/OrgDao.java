package com.nsu.dao.organization;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrgDao {
	Map<String, Object> orgLogin(@Param("name")String name,@Param("pwd")String pwd);
}
