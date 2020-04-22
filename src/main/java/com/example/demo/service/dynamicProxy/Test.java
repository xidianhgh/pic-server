package com.example.demo.service.dynamicProxy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.Comparator;

/**
 * Created by MI on 2019/8/21.
 */
public class Test {
    //JDK InvocationHandler聚合，cglib继承
    public static void main(String[] args) throws Exception {
        // maxPoolSize io密集型 核数*2  cpu密集型 核数+1
        int cpuCoreNum = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU核数：" + cpuCoreNum);
        test4();
    }

    //实例化对象的四种方法
    public static void test4() throws Exception {
        //反射实例化对象
        Class clazz = Class.forName("com.example.demo.service.dynamicProxy.Germany");
        Constructor constructor = clazz.getConstructor(String.class);
//         Germany germany= (Germany) clazz.newInstance();
        Germany germany = (Germany) constructor.newInstance("yes");
        System.out.println(germany.sayName());

        //实现Cloneable 调用clone方法
        Chinese chinese = new Chinese();
        chinese.setName("clone Chinese");
        Chinese chineseCloned = (Chinese) chinese.clone();
        System.out.println(chineseCloned.sayHello());

        //反序列化得到对象
        FileOutputStream fos = new FileOutputStream("d:/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(germany);
        oos.close();
        fos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));
        Germany a5 = (Germany) ois.readObject();
        ois.close();
        System.out.println("a5: " + a5);
    }

    public static void test3() {

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        //lambda表达式
        Comparator<Integer> comparator2 = (a, b) -> a - b;
        System.out.println(comparator.compare(1, 20));

        People japanese = () -> {
            System.out.println("xx");
            return "Japanese say hello";
        };

        System.out.println(japanese.sayHello());

    }

    public static void test2() {
        Germany germany = new Germany();
        MyLogHandler logHandler = new MyLogHandler(germany);
        MyTransactionHandler transactionHandler = new MyTransactionHandler(logHandler);
        transactionHandler.sayHello();
    }

    public static void test1() {
        People people = (People) new PeopleInvocationHandle().getInstance(new Chinese());
//        People people= (People) Proxy.newProxyInstance(MyAnnotation.class.getClassLoader(),Germany.class.getInterfaces(),new PeopleInvocationHandle());

        people.sayHello();
    }
}
