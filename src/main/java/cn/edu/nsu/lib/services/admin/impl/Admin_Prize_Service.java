package cn.edu.nsu.lib.services.admin.impl;

import cn.edu.nsu.lib.bean.admin.db.DbLab;
import cn.edu.nsu.lib.bean.admin.db.DbPrize;
import cn.edu.nsu.lib.bean.admin.db.DbStudent;
import cn.edu.nsu.lib.dao.admin.IAdmin_Prize_DAO;
import cn.edu.nsu.lib.enums.Result;
import cn.edu.nsu.lib.services.admin.IAdmin_Prize_Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by 王振科 on 2017/10/5.
 */
@Service
public class Admin_Prize_Service implements IAdmin_Prize_Service{
    @Autowired
    private IAdmin_Prize_DAO prize_dao;
    private Log log = LogFactory.getLog(Admin_Prize_Service.class);

    @Override
    public Result Passcheck_Service(int prize_id,int Lab_id,boolean is_checked)throws Exception {
        int count = prize_dao.updateprize_byid(prize_id,Lab_id,is_checked);
        if(count == 1){
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @Override
    public Result Nopass_Service(int prize_id, int Lab_id)throws Exception {
        return Deletewinner_Service(prize_id,Lab_id);
    }

    @Override
    public Result Deletewinner_Service(int prize_id, int Lab_id)throws Exception {
        int count = prize_dao.deletedateprize_byid(prize_id,Lab_id);
        if(count == 1){
            return Result.SUCCESS;
        }
        return Result.FAIL;
    }

    @Override
    public ArrayList<DbPrize> toprize(int Lab_id)throws Exception {
        ArrayList<DbPrize> list = prize_dao.getstuprize_byid(Lab_id);
            //遍历插入信息
            for (DbPrize dbPrize :
                    list) {
                //根据实验室id插入实验室的名字
                DbLab db_lab = prize_dao.getlab_byid(dbPrize.getLab_id());
                dbPrize.setLab_name(db_lab.getName());
                //根据id插入获奖人的名字
                DbStudent student = prize_dao.getownname_byid(dbPrize.getOwner());
                dbPrize.setOwner_name(student.getName());
            }
        return list;
    }
}
