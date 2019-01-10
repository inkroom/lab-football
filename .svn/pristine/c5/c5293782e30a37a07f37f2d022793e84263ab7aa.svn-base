package cn.nsu.edu.web.four.services.player;

import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :王新璋
 * @Description: 球员的业务接口
 * @date :10.13 2018/3/16
 */


public interface PlayerService {


    //通过主键ID查询单个球员信息通过球员编号
    public Player selectPlayerById(Integer id) ;

    //通过用户名查询单个球员信息通过球员编号
    public Player selectPlayerByUserName(String userName) ;

    //通过身份证号查询单个球员信息检验是否占用
    public Player selectPlayerByIdCard(String idCard) ;

    //查询未删除的球员信息
    public Player selectPlayerByIdStatus(@Param("idPlayer") Integer idPlayer,@Param("status") Integer status);

    //添加球员信息（注册用）
    public int insertPlayer(Player player) ;

    //删除球员信息（只是添加已删除的状态码）
    public  int  addStateById(Integer playid,Integer status) ;

    //编辑球员信息
    public int updatePlayerById(Player player) ;

    //添加图片路径
    public int updatePlayerImageById(Integer id,String headPic) ;

    //通过球员ID查询球员姓名
    public String selectOrgNameById(@Param("idPlayer") Integer idPlayer);

    //通过球员ID查询球队名称
    List<String> selectTeamNameById(@Param("playerId") Integer playerId);


}
