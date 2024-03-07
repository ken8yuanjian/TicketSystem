package com.ken.user.thread;


import com.ken.common.cache.MyCache;
import lombok.Data;

import java.util.Date;

@Data
public class RedisRWThread implements Runnable {

    private MyCache myCache;
    public RedisRWThread(MyCache myCache){
        this.myCache = myCache;
    }

    @Override
    public void run()  {
        System.gc();
        for (int i=0;i<10;++i){
            System.out.println("暂停："+i + "秒");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }
        for (int i=0;i<10;++i){
            Date date = new Date();
            long expire =100;
            if (0 != i)
                expire = myCache.getExpire("date");
            myCache.setForValue("date",date);
            myCache.expire("date",expire);
            System.out.println("写入时间:"+date.toString());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
            }
            date = myCache.getForValue("date");
            System.out.println("读入时间:"+date.toString());
            System.out.println("TTL:"+ String.valueOf( myCache.getExpire("date")) );

        }
    }
}
