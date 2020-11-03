package com.shengq.covid19;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class test {
    public static void main(String[] args) {
        Set<String> number = new HashSet<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                AtomicInteger atomicInteger = new AtomicInteger();
                atomicInteger.set(0);
                while (true){
                    atomicInteger.getAndAdd(1);
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,18);
                    char[] c = uuid.toCharArray();
                    String str="";
                    for (int ii=0;ii<c.length;ii++){
                        if (c[ii]>='a'&&c[ii]<='z'){
                            c[ii] = (char) ((c[ii]-'a')+'0');
                        }
                        str = str + c[ii];
                    }
                    System.out.println("执行");
                    number.add(str);
                    if (atomicInteger.get()>100000) break;
                }
            }
        });
        thread.start();
        while (true){
            if (!thread.isDaemon()){
                number.forEach(System.out::println);
                break;
            }
            System.out.println("线程状态1："+thread.isAlive());
            System.out.println("线程状态2："+thread.isDaemon());
            System.out.println("线程状态3："+thread.isInterrupted());
        }


    }

}
