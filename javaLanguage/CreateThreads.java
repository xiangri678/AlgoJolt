package com.algo.jolt.javaLanguage;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateThreads {
    public static void main(String[] args) {
        // 法 1：继承 Thread 类
        NewThread1 nt1 = new NewThread1();
        nt1.start();

        // 法 2：Runnable 接口
        // 2.1 创建类，实现 Runnable 接口
        Runnable r1 = new MyRunnable();
        Thread nt2 = new Thread(r1);
        nt2.start();

        // 2.2 匿名内部类写法 1
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    log.info("子线程 3" + i);
                }
            }
        };
        Thread nt3 = new Thread(r2);
        nt3.start();

        // 2.3 匿名内部类写法 2
        new Thread(new Runnable() {
            @Override
            public void run(){
                for (int i = 0; i < 5; i++) {
                    log.info("子线程 4" + i);
                }
            }
        }).start();

        // 2.4 匿名内部类写法 3
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.info("子线程 5" + i);
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            log.info("主线程" + i);
        }

        // 3 Callable 接口
        Callable<String> cs1 = new MyCallable(5);
        FutureTask<String> ft1 = new FutureTask<>(cs1); // 不是函数式接口，无法用 Lambda 进一步化简
        Thread nt4=new Thread(ft1);
        nt4.start();
    }
}

// 创建子类，继承 Thread 类
@Slf4j
class NewThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("子线程 1" + i);
        }
    }
}

// 实现Runnable接口
@Slf4j
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("子线程2" + i);
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

// 实现Callable接口
@Slf4j
class MyCallable implements Callable<String> {
    private int n;

    public MyCallable(int n) {
        this.n = n;
    }

    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            log.info("子线程 6" + i);
            sum += i;
        }
        
        return "子线程 6 可以返回值" + sum;
    }

}


