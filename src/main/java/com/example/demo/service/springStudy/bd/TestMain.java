package com.example.demo.service.springStudy.bd;

import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by MI on 2019/10/17.
 */
public class TestMain {
    public static void test1() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext();
        //GenericApplicationContext does not support multiple refresh attempts: just call 'refresh' once
        ac.register(AppConfig.class);

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.getPropertyValues().add("name", "zl");
        rootBeanDefinition.getPropertyValues().add("country", "Brazil");

        rootBeanDefinition.setBeanClass(BeanService.class);

        ac.registerBeanDefinition("xxx", rootBeanDefinition);
        ac.addBeanFactoryPostProcessor(new LubanBeanFactoryPostProcessor());
        ac.refresh();
        System.out.println(ac.getBean("xxx"));
    }

    public static void main(String[] args) throws Exception {

        test1();
    }

    public static void test5() throws ExecutionException, InterruptedException {
        //有返回值的线程
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(5);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.initialize();
        Future<Integer> future = threadPoolTaskExecutor.submit(new MyThread());
        Future<Integer> future2 = threadPoolTaskExecutor.submit(new MyThread());

        int num = 1024;
        // future.get()建议放在最后
        System.out.println(future.get() + num);

    }

    private static class MyThread implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName()+" I am Callable");
            return 1024;
        }
    }

    public static void test4() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    public static void test3() {
//        List<String> list= Collections.synchronizedList(new ArrayList<>());

        List<String> list = new CopyOnWriteArrayList<>();
//        List<String> list=new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(8));
                System.out.println(list);
            }, String.valueOf(i)).start();

        }
    }

    public static void test2() {
        String pkg = "com.example.demo.service.springStudy.bd";
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AppConfig.class);


        ClassPathBeanDefinitionScanner customScanner = new ClassPathBeanDefinitionScanner(ac);
        customScanner.addIncludeFilter(new AnnotationTypeFilter(MyAnnotation.class));
        int k = customScanner.scan(pkg);
        System.out.println(k);
    }




}
