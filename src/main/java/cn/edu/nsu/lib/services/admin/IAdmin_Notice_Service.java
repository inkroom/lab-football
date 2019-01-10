package cn.edu.nsu.lib.services.admin;

import cn.edu.nsu.lib.bean.admin.Notice;
import cn.edu.nsu.lib.enums.Result;

import java.util.ArrayList;

/**
 * Created by 王振科 on 2017/9/26.
 */
public interface IAdmin_Notice_Service {
//    通过session里的实验室id查询公告

    /**
     * 通过实验室id，返回当前实验室的所有公告
     * @param Lab_id
     * @return
     */
    public ArrayList<Notice> toNotice_Service(int Lab_id) throws Exception;

    /**
     * 通过公告id删除公告
     * @param Notice_id
     * @return
     */
    public Result Deletenotice_Service(int Notice_id, int Lab_id) throws Exception;
}
