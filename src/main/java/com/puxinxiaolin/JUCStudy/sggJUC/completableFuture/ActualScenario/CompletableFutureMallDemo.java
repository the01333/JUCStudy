package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture.ActualScenario;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @description: 大厂业务需求案例
 * @author: YCcLin
 * @date: 2024/12/28
 **/
public class CompletableFutureMallDemo {

    static ExecutorService threadPool;

    static {
        threadPool = Executors.newFixedThreadPool(5);
    }

    static List<NetMall> netMallList = Arrays.asList(
            new NetMall("jd"),
            new NetMall("taobao"),
            new NetMall("pdd"),
            new NetMall("dangdang"));

    /**
     * 1.1 正常写法
     * progress: List<NetMall> -> List<String>
     *
     * @param netMallList
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> netMallList, String productName) {
        return netMallList.stream()
                .map(p -> String.format(productName + " in %s price is %.2f", p.getNetMallName(), p.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    /**
     * 1.2 异步线程优化
     * progress: List<NetMall> -> List<CompletableFuture> -> List<String>
     *
     * @param netMallList
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> netMallList, String productName) {
        return netMallList.stream()
                .map(p -> CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        p.getNetMallName(), p.calcPrice(productName)), threadPool))
                .collect(Collectors.toList())
                .stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // 1.1 正常写法
        long start1 = System.currentTimeMillis();
        List<String> list1 = getPrice(netMallList, "mysql");
        for (String res : list1) {
            System.out.println(res);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("---costTime: " + (end1 - start1) + "ms");

        System.out.println("---------------------------------------");

        // 1.2 异步线程优化
        long start2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(netMallList, "redis");
        for (String s : list2) {
            System.out.println(s);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("---costTime: " + (end2 - start2) + "ms");

        threadPool.shutdown();
    }
}

@Getter
class NetMall {
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Accessors(chain = true)    // 链式编程支持
//class Student {
//    private Integer id;
//    private String name;
//    private String major;
//}
