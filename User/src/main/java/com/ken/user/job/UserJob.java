package com.ken.user.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserJob implements Job {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") ;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String time = sdf.format( new Date());
        System.out.println(time +" "+ this.getClass().getName() );
    }
}
