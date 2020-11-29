public class TestGracefulShutdown02 {
    //验证 Java 线程被强制关闭时是否会执行 shutdownHook 中的方法
    //如果控制台打印出 is closing. 则表明会
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }));

        while (true) {
            //do nothing
        }
    }


    public static void close(){
        System.out.println("is closing.");
    }
}
