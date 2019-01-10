package com.nsu.dao.student.exam;

import com.nsu.entity.UploadFiles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 考试资源持久层
 * 考试所需的图片、音频、视频资源
 *
 * @author qingyi xuelongqy@foxmail.com
 * @version V1.0
 * @InterfaceName: IExamResDao
 * @Package com.nsu.dao.student.exam
 * @Description: 考试资源持久层
 * @date 2017/8/8 17:12
 */
public interface IExamResDao {
    //通过类型和id获取资源
    @Select(
            "SELECT * FROM upload_files WHERE FILE_TYPE = #{type} AND U_ID = #{id}"
    )
    public UploadFiles getFileByTypeAndID(@Param("type") int type, @Param("id") long id);
}
