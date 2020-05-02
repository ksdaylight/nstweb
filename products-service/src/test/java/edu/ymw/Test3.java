package edu.ymw;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test3 {


        public static void main (String[]args){
            int port = 0;
            int defaultPort = 8011;
            if (0 == port) {
                Future<Integer> future = ThreadUtil.execAsync(() -> {
                    int p = 0;
                    System.out.printf("请于5秒钟内输入端口号, 推荐  %d ,超过5秒将默认使用 %d \n", defaultPort, defaultPort);
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        String strPort = scanner.nextLine();
                        if (!NumberUtil.isInteger(strPort)) {
                            System.err.println("只能是数字");
                            continue;
                        } else {
                            p = Convert.toInt(strPort);
                            scanner.close();
                            break;
                        }
                    }
                    return p;
                });
                try {
                    port = future.get(5, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    port = defaultPort;
                }
            }
            System.out.println(port);
        }
    }
