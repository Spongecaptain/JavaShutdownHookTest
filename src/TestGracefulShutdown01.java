public class TestGracefulShutdown01 {
    //验证 Java 线程正常退出时是否会运行 shutdownHook 中的方法
    //如果控制台打印出 is closing. 则表明会
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }));
    }


    public static void close(){
        System.out.println("is closing.");
    }
}
