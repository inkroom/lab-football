package cn.nsu.edu.web.four.services.team.Impl;

import cn.nsu.edu.web.four.beans.player.Player;
import cn.nsu.edu.web.four.beans.teams.PageQueryBean;
import cn.nsu.edu.web.four.beans.teams.PlayerCondition;
import cn.nsu.edu.web.four.daos.jdbc.team.PlayerMapper;
import cn.nsu.edu.web.four.services.team.PlayerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyong
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: TODO
 * @date ${date} ${time}
 */
@Service("playerServiceImpl1")
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List<Player> findPlayersByconditions(@Param("playerCondition") PlayerCondition playerCondition,Integer orgId) throws Exception {
        //获得总数据
//        int rows = playerMapper.selectCountsByCondition(playerCondition);
//        if (rows>0){
//            //总条数添加到playerCondition中
//            playerCondition.setTotalRows(rows);
//            //设置当前页
//            playerCondition.setCurrentPage(playerCondition.getCurrentPage());
//            //设置每一页的条数
//            playerCondition.setPageSize(playerCondition.getPageSize());
//            //获取当前分页的数据
//            List<Player> players = playerMapper.selectPlayerByPage(playerCondition);
//            playerCondition.setItems(players);
//        }
        Integer classes = playerCondition.getClasses();
        Integer grade = playerCondition.getGrade();
        String name = playerCondition.getName();
        return playerMapper.selectPlayersByconditions(name,grade,classes,orgId);

    }

    @Override
    public Player findPlayerByPlayerId(Integer idPlayer) {
       return playerMapper.selectPlayerById(idPlayer);

    }

    @Override
    public List<Player> findPlayerByPlayerIds(@Param("ids") List<Integer> ids) {
        return playerMapper.selectPlayerByIds(ids);

    }

    @Override
    public List<Integer> findAllPlayerIds() {
        return playerMapper.selectAllPlayerIds();
    }
}
