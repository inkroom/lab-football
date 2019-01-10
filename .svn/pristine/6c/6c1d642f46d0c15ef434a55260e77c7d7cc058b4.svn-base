package com.nsu.service.student.exam.Impl;

import com.nsu.dao.student.exam.IExamResDao;
import com.nsu.entity.UploadFiles;
import com.nsu.service.student.exam.IExamResService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 考试资源服务
 * 考试所需的图片、音频、视频资源
 *
 * @author XueLong
 * @version V1.0
 * @ClassName: IExamResServiceImpl
 * @Package com.nsu.service.student.exam.Impl
 * @Description: 考试资源服务
 * @date 2017/8/8 17:11
 */
@Service
public class ExamResServiceImpl implements IExamResService {

    //考试资源持久层对象
    @Resource
    IExamResDao examResDao;

    /**
     * 通过类型和id获取资源
     * 考试所需的图片、音频、视频资源
     *
     * @param type 文件类型
     * @param id   文件id
     * @return 资源文件
     * @Title: getResource
     * @Description: 通过类型和id获取资源
     * @author XueLong
     * @date 2017 -08-08 17:18:06
     */
    @Override
    public UploadFiles getResource(int type, long id) {
        return examResDao.getFileByTypeAndID(type,id);
    }
}
