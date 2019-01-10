package com.nsu.dao.organization;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrgLoginDao {
    public Map<String, Object> findUser(Map<String, Object> map) throws Exception;

    public void insertTime(Map<String, Object> map);


    public  void updateMobileInfo(Map<String, Object> map);

    public  Map<String,Object> mobileFindUser(String username);
     /*
      * @Description: TODO(手机查询机构的所有赛事)
      * @methodName
      * @param
      * @return     返回类型
      * @throws
      */
    public List<Map<String,Object>> mobileFindAllComp(String org_id);

   /*
    * @Description: TODO(手机查询赛事下有哪些赛程)
    * @methodName
    * @param
    * @return     返回类型
    * @throws
    */
    public  List<Map<String, Object>> mobileFindRaceDetails(@Param(value = "com_id") Object com_id,
                                                     @Param(value = "org_id") String org_id);

    /*
     * @Description: TODO(机构名，机构电话)
     * @methodName
     * @param
     * @return     返回类型
     * @throws
     */
     public Map<String,Object> findMobileInfo(String username);

     /*
      * @Description: TODO(查询球队名)
      * @methodName
      * @param
      * @return     返回类型
      * @throws
      */
    public Object findTeamName(Object team_id);
}
