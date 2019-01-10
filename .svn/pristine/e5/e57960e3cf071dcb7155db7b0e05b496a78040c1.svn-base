package com.nsu.dao.student.datadownload;

import com.nsu.bean.student.datadownload.DataDownloadViewBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 闫建宏
 * @Description:
 * @date 2017/8/3
 */
public interface DataDownloadDao {
    int getDataViewNum();
    List<DataDownloadViewBean> getDataViewByPage(@Param("startRow") int startRow,@Param("limitPage") int limitPage);
    List<DataDownloadViewBean> SearchData(String searchData);
    String getPathById(long id);
}
