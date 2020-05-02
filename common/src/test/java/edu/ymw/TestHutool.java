package edu.ymw;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class TestHutool {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TestHutool.class);
    public static void main(String[] args) {
        String dateStr = "2012-12-12 12:12:12";
        Date date = DateUtil.parse(dateStr);
        System.out.println(date);
        log.error("Something else is wrong heresdgasdg ");
        log.info("test1");
        log.debug("test2");
        log.trace("test3");
    }
}
