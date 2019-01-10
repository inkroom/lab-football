package cn.nsu.edu.web.four.daos.jdbc.player;

import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerDao {

    //通过主键查询单个球员信息
    Player selectPlayerById(@Param("idPlayer") Integer id) throws Exception;

    //通过用户名查询单个球员信息
    Player selectPlayerByUserName(@Param("userName") String userName) throws Exception;

    //通过身份证号查询单个球员信息
    Player selectPlayerByIdCard(@Param("idCard") String idCard) throws Exception;

    //查询未删除的单个球员信息
    Player selectPlayerByIdStatus(@Param("idPlayer") Integer idPlayer,@Param("status")Integer status) throws Exception;

    //通过球员ID查询球员姓名
    String selectOrgNameById(@Param("idPlayer") Integer idPlayer) throws  Exception;

    //通过球员ID查询球队名称
    List<String> selectTeamNameById(@Param("playerId") Integer playerId) throws Exception;

    //删除球员信息（只是添加已删除的状态码）
    int addStateById(@Param("idPlayer") Integer id,@Param("status") int status) throws Exception;

    //添加单个球员信息：注册使用
    int insertPlayer(Player player) throws Exception;

    //添加图片路径
    int updatePlayerImageById(@Param("idPlayer") Integer idPlayer,@Param("headPic") String headPic) throws Exception;

    //编辑更新单个球员信息
    int updatePlayerById(Player player) throws Exception;
}
