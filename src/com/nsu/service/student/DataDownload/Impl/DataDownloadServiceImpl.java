package com.nsu.service.student.DataDownload.Impl;

import com.nsu.bean.student.datadownload.DataDownloadViewBean;
import com.nsu.dao.student.datadownload.DataDownloadDao;
import com.nsu.service.student.DataDownload.DataDownloadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 闫建宏
 * @Description:
 * @date 2017/8/2
 */
@Service
public class DataDownloadServiceImpl implements DataDownloadService{
    @Resource
    DataDownloadDao dataDownloadDao;
    @Override
    public int getDataViewNum() {
        return dataDownloadDao.getDataViewNum();
    }

    @Override
    public List<DataDownloadViewBean> getDataViewByPage(int startRow, int limitPage) {
        return dataDownloadDao.getDataViewByPage(startRow,limitPage);
    }

    @Override
    public List<DataDownloadViewBean> SearchData(String searchData) {
        searchData = "%"+searchData+"%";
        return dataDownloadDao.SearchData(searchData);
    }

    @Override
    public String getPathById(long id) {
        return dataDownloadDao.getPathById(id);
    }


}
