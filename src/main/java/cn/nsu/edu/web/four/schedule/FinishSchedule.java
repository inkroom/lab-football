package cn.nsu.edu.web.four.schedule;

import cn.nsu.edu.web.four.services.referee.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 赛程结束清理定时任务
 */
public class FinishSchedule extends TimerTask {

    private Logger log = LoggerFactory.getLogger(getClass());
    private Integer schId;

    private String directory;

    private long delay;

    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Autowired
    private ReportService service;

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    public void start() {
        Date now = new Date();
        Timer timer = new Timer();
        timer.schedule(this, delay);
        log.info("开始定时任务，将在" + new Date(now.getTime() + delay) + " 时删除文件夹" + directory);
    }

    @Override
    public void run() {
        log.info("开始删除文件夹" + directory);
        //遍历删除图片
        File dir = new File(directory);
        File files[] = dir.listFiles();
        int count = 0;
        if (files != null) {
            for (File file : files) {
                if (file.delete()) {
                    count++;
                }
            }
            if (count != files.length) {
                log.error(dir + "下" + (files.length - count) + "个文件删除失败");
            } else {
                if (!dir.delete()) {
                    log.error(dir + "文件夹删除失败");
                }
            }
        }
        //删除数据
        service.deleteMessage(schId);
    }
}
