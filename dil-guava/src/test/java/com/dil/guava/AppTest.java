package com.dil.guava;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.net.InternetDomainName;
import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class AppTest {

    /**
     * 功能：布隆过滤器
     * 文档：https://github.com/google/guava/wiki/HashingExplained#bloomfilter
     */
    @Test
    public void test_BloomFilter() {
        BloomFilter<String> bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                1000,
                0.01);

        // 向布隆过滤器中添加元素
        bloomFilter.put("apple");
        bloomFilter.put("banana");
        bloomFilter.put("orange");

        // 检查元素是否存在于布隆过滤器中
        System.out.println(bloomFilter.mightContain("apple"));   // true
        System.out.println(bloomFilter.mightContain("banana"));  // true
        System.out.println(bloomFilter.mightContain("orange"));  // true
        System.out.println(bloomFilter.mightContain("grape"));   // false

        // 输出布隆过滤器的统计信息
        log.info("Expected FPP: {}", bloomFilter.expectedFpp());
        log.info("Number of Inserted Elements: {}", bloomFilter.approximateElementCount());
    }

    /**
     * 功能：域名截取
     * 文档：https://github.com/google/guava/wiki/InternetDomainNameExplained
     */
    @Test
    public void test_InternetDomainName() {
        InternetDomainName owner = InternetDomainName.from("mail.google.com").topPrivateDomain();
        log.info("测试结果：{}", owner.topPrivateDomain());
    }

    /**
     * 功能：并发回调
     * 文档：https://github.com/google/guava/wiki/ListenableFutureExplained
     */
    @Test
    public void test_ListenableFuture() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> explosion = executorService.submit(() -> "finished");

        ExecutorService callBackService = Executors.newFixedThreadPool(1);
        Futures.addCallback(explosion, new FutureCallback<String>() {
            public void onSuccess(String explosion) {
                System.out.println("onSuccess");
                countDownLatch.countDown();
            }

            public void onFailure(Throwable thrown) {
                System.out.println("onFailure");
                countDownLatch.countDown();
            }
        }, callBackService);

        countDownLatch.await();
    }

}
