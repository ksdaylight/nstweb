package edu.ymw;
public class Test {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TestHutool.class);
    public static void main(String[] args) {
        Common common = new Common();
        common.setName("hello你好1");
        common.say();
        log.error("Something else is wrong here");
    }
    public void  test(){
        System.out.println("asgsadg");

    }
}

