package com.algo.jolt.javaLanguage;

import java.util.concurrent.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorServiceDemo {
    public static void main(String[] args) {
        // 线程池创建方法 1,7个参数
        ExecutorService pool = new ThreadPoolExecutor(3, 5,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        // 线程池创建方法 2，调工具类的静态方法
        ExecutorService pool2 = Executors.newFixedThreadPool(3);

        // 执行 Runnable 任务
        Runnable target = new MyRunnableForPool();
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);

        // 执行 Callable 任务
        Future<String> f1 = pool2.submit(new MyCallableForPool(5));
        Future<String> f2 = pool2.submit(new MyCallableForPool(5));
        Future<String> f3 = pool2.submit(new MyCallableForPool(5));
        Future<String> f4 = pool2.submit(new MyCallableForPool(5));
        Future<String> f5 = pool2.submit(new MyCallableForPool(5));
        try {
            log.info(f1.get());
            log.info(f2.get());
            log.info(f3.get());
            log.info(f4.get());
            log.info(f5.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 实现Runnable接口
@Slf4j
class MyRunnableForPool implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("name" + Thread.currentThread().getName() + "子线程" + i);
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
class MyCallableForPool implements Callable<String> {
    private int n;

    public MyCallableForPool(int n) {
        this.n = n;
    }

    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }

        return "name" + Thread.currentThread().getName() + "Callable 子线程可以返回值" + sum;
    }

}