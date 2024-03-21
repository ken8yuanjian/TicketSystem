package com.ken.user;

import com.ken.user.job.UserJob;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication

public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);

        /*try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //创建触发器
            SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                    .startAt(new Date(System.currentTimeMillis()+5000) )
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule().
                                    withIntervalInSeconds(3).withRepeatCount(100))
                    .build();
            //创建任务
            JobDetail job = JobBuilder.newJob( UserJob.class ).build();

            scheduler.scheduleJob(job,trigger);

            scheduler.start();
            //scheduler.shutdown();
        }catch (SchedulerException e){

        }*/

    }
}
