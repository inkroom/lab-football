package cn.edu.nsu.lib.scheduled;

import cn.edu.nsu.lib.services.common.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/8/23
 * @Time 13:16
 * @Descorption 处理ip的定时任务
 */
@Component
@Lazy(false)
@EnableScheduling
public class IpScheduled implements SchedulingConfigurer {

    @Autowired
    private IpService service;


    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        log.debug("事件被触发**** 1 ");
        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
            public void run() {
                // 任务逻辑
//                log.debug("任务逻辑");
//                log.debug("dynamicCronTask is running...");
                try {
                    // TODO: 2017/9/17 ip清空未处理
//                    service.deleteIp(configService.getLongConfig(Constants.KEY_REDIS_DELETE_IP_INTERVAL));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Trigger() {
            public Date nextExecutionTime(TriggerContext triggerContext) {
//                log.debug("事件被触发");
                // 任务触发，可修改任务的执行周期

                try {
                    // TODO: 2017/9/17 修改时间
                    PeriodicTrigger trigger = new PeriodicTrigger(1L);
                    return trigger.nextExecutionTime(triggerContext);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return triggerContext.lastScheduledExecutionTime();
            }
        });
    }

}
