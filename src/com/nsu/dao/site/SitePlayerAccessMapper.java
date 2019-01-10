package com.nsu.dao.site;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author LuLongShan
 * @Date 2017年 04月13日 2017/4/13 15:50
 * @package com.nsu.dao.site
 */
public interface SitePlayerAccessMapper {

    List<Map<String, String>> getPlayerByAId(Integer A_ID);

    Integer updatePlayerStatus(@Param("AId") Long A_ID,@Param("Status")Integer status);
}
