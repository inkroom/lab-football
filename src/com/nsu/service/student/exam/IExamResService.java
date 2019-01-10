package com.nsu.service.student.exam;

import com.nsu.entity.UploadFiles;

/**
 * 考试资源服务
 * 考试所需的图片、音频、视频资源
 *
 * @author XueLong
 * @version V1.0
 * @InterfaceName: IExamResService
 * @Package com.nsu.service.student.exam
 * @Description: 考试资源服务
 * @date 2017/8/8 17:01
 */
public interface IExamResService {
    //通过类型和id获取资源
    public UploadFiles getResource(int type,long id);
}
