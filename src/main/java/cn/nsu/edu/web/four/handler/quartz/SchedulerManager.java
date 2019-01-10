package cn.nsu.edu.web.four.handler.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/10
 * @Time 16:05
 * @Descorption
 */
public class SchedulerManager {

    private Logger log = LoggerFactory.getLogger(getClass());

    private Scheduler scheduler;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    private ArrayList<Map<String, String>> keys;

    private static final String KEY_NAME = "name";
    private static final String KEY_GROUP = "group";

    public SchedulerManager(){
        keys = new ArrayList<>();
    }

    public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        scheduler = schedulerFactoryBean.getScheduler();

    }

    /**
     * 添加任务
     *
     * @param c     对应的class
     * @param name  name
     * @param group group
     * @param cron  cron表达式
     */
    public void addJob(Class<? extends Job> c, String name, String group, String cron) {

        if (scheduler == null)
            scheduler = schedulerFactoryBean.getScheduler();
        try {

            scheduler.scheduleJob(createJobDetail(name, group, c), createCronTrigger(name, group, cron));
            log.debug("添加任务 name = " + name + "  group = " + group + "  class = " + c.getName());

            addKeys(name, group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JobDetail createJobDetail(String name, String group, Class<? extends Job> c) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(c);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setGroup(group);
        jobDetailFactoryBean.setName(name);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }

    private Trigger createCronTrigger(String name, String group, String cron) {

        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setCronExpression(cron);
        cronTriggerFactoryBean.setName(name);
        cronTriggerFactoryBean.setGroup(group);
        try {
            cronTriggerFactoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cronTriggerFactoryBean.getObject();
    }

    public void deleteJob(String name, String group) {
        try {
            log.debug("删除任务 name = " + name + "  group = " + group + "  result = " + scheduler.deleteJob(JobKey.jobKey(name, group)));
            deleteKeys(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void addKeys(String name, String group) {
        Map<String, String> map = new HashMap<>();
        map.put(KEY_NAME, name);
        map.put(KEY_GROUP, group);

        keys.add(map);
    }

    private void deleteKeys(String name, String group) {
        for (Map<String, String> map : keys) {
            if (map.get(KEY_NAME).equals(name) && map.get(KEY_GROUP).equals(group)) {
                keys.remove(map);
                break;
            }
        }
    }

    public void updateCron(String name, String group, String cron) {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(name, group));
            Class<? extends Job> c = jobDetail.getJobClass();
            log.debug("更新任务 name = " + name + "  group = " + group);
            deleteJob(name, group);
            addJob(c, name, group, cron);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String name, String group) {
        try {
            return scheduler.checkExists(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void standBy(String name, String group) {
        try {
            scheduler.pauseJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void resume(String name, String group) {
        try {
            scheduler.resumeJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public List<JobDetail> getAllJob() {
        ArrayList<JobDetail> details = new ArrayList<>(keys.size());
        for (Map<String, String> map : keys) {
            try {
                details.add(scheduler.getJobDetail(JobKey.jobKey(map.get(KEY_NAME), map.get(KEY_GROUP))));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        return details;
    }

    public List<Trigger> getAllTrigger() {
        ArrayList<Trigger> details = new ArrayList<>(keys.size());
        for (Map<String, String> map : keys) {
            try {
                details.add(scheduler.getTrigger(TriggerKey.triggerKey(map.get(KEY_NAME), map.get(KEY_GROUP))));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        return details;
    }

}
