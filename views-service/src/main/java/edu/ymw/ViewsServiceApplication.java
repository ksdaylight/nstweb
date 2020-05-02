package edu.ymw;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

@SpringBootApplication
@EnableEurekaClient
public class ViewsServiceApplication {
    public static void main(String[] args) {
        int port = 0;
        int[] defaultPort = {8041,8042};
        int eurekaServerPort = 8761;
        if (NetUtil.isUsableLocalPort(eurekaServerPort)) {
            System.err.printf("检查到端口%d 未启用，判断 eureka 服务器没有启动，本服务无法使用，故退出%n", eurekaServerPort);
            System.exit(1);
        }

        if (null != args && 0 != args.length) {
            for (String arg : args) {
                if (arg.startsWith("port=")) {
                    String strPort = StrUtil.subAfter(arg, "port=", true);
                    if (NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }

        if (0 == port) {
            for(int i = 0; i < defaultPort.length; i++)
            {
                port = defaultPort[i];
                if (!NetUtil.isUsableLocalPort(port)) {
                    System.err.printf("端口%d被占用了，无法启动,尝试下一个端口%n", port);
                    if(i== defaultPort.length-1)
                    {
                        System.err.printf("暂无合适端口，启动失败%n");
                        System.exit(1);
                    }
                }
                else
                    break;

            }
        }

        System.out.printf("即将启动的端口号为%d %n", port);
        new SpringApplicationBuilder(ViewsServiceApplication.class).properties("server.port=" + port).run(args);
    }

}
