package cn.nsu.edu.web.four.services.player.impl;

import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.Team;
import cn.nsu.edu.web.four.daos.jdbc.player.PlayerDao;
import cn.nsu.edu.web.four.dto.ctv.MessageDto;
import cn.nsu.edu.web.four.services.player.PlayerService;
import cn.nsu.edu.web.four.utils.encrypt.Md5EncryptUtil;
import cn.nsu.edu.web.four.utils.string.ParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author :王新璋
 * @Description: 球员的业务实现类
 * @date :10.13 2018/3/16
 */

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    //添加球员信息（注册用）
    public int insertPlayer(Player player){
        try {
            String password=player.getPassword();
            String[] result=Md5EncryptUtil.parseMd5WithSalt(password);
            player.setPassword(result[0]);
            player.setSalt(result[1]);
            return playerDao.insertPlayer(player);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


    //通过主键ID查询单个球员信息通过球员编号
    @Override
    public Player selectPlayerById(Integer id){
        try {
            return playerDao.selectPlayerById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    //通过用户名查询单个球员信息通过球员编号
    @Override
    public Player selectPlayerByUserName(String userName) {
        try {
            return playerDao.selectPlayerByUserName(userName);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    //通过身份证号查询单个球员信息
    @Override
    public Player selectPlayerByIdCard(String idCard) {
        try {
            return playerDao.selectPlayerByIdCard(idCard);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


    //删除球员信息（只是添加已删除的状态码）
    public  int  addStateById(Integer id,Integer status){
        try {
            return playerDao.addStateById(id,status);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


    //编辑球员信息
    @Override
    public int updatePlayerById(Player player){
        try {
            String password=player.getPassword();
            String[] result=Md5EncryptUtil.parseMd5WithSalt(password);
            player.setPassword(result[0]);
            player.setSalt(result[1]);
            return playerDao.updatePlayerById(player);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    //添加图片路径
    @Override
    public int updatePlayerImageById(Integer id,String headPic) {
        try {
            return playerDao.updatePlayerImageById(id,headPic);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    //查询球队名称
    @Override
    public List<String> selectTeamNameById(Integer playerId) {
        try {
            return playerDao.selectTeamNameById(playerId);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    //查询机构名称
    @Override
    public String selectOrgNameById(Integer idPlayer) {
        try {
            return playerDao.selectOrgNameById(idPlayer);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    //查询单个未删除的球员信息
    @Override
    public Player selectPlayerByIdStatus(Integer id, Integer status){
        try {
            return playerDao.selectPlayerByIdStatus(id,status);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

}
