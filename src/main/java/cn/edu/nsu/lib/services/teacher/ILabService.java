package cn.edu.nsu.lib.services.teacher;

import cn.edu.nsu.lib.bean.teacher.LabEntity;

import java.util.List;

public interface ILabService {
    /**
     * 获取实验室详细信息列表
     * @param t_id 教师id
     * @return
     */
    public List<LabEntity> findLabList(String t_id) throws Exception;
}
