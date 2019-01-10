package cn.edu.nsu.lib.services.admin;

import cn.edu.nsu.lib.bean.admin.db.DbPrize;
import cn.edu.nsu.lib.bean.admin.db.DbPrize;
import cn.edu.nsu.lib.enums.Result;

import java.util.ArrayList;

/**
 * Created by 王振科 on 2017/9/26.
 */
public interface IAdmin_Prize_Service {
    /**
     * 判断 通过获奖审核是否成功
     * @param notice_id
     * @return
     */
    public Result Passcheck_Service(int notice_id, int Lab_id, boolean is_checked)throws Exception;

    /**
     * 不通过获奖审核
     * 直接删除数据库该条数据
     *
     * @param prize_id
     * @param Lab_id
     * @return
     */
    public Result Nopass_Service(int prize_id, int Lab_id)throws Exception;

    /**
     * 判断 删除获奖信息是否成功
     *
     * @param prize_id
     * @param notice_id
     * @return
     */
    public Result Deletewinner_Service(int prize_id, int notice_id) throws Exception;

    /**
     * 奖项主界面获取奖项信息
     * @param Lab_id
     * @return
     */
    public ArrayList<DbPrize> toprize(int Lab_id) throws Exception;
}
