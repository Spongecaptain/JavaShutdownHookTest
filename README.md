# README

TestGracefulShutdown01 用于验证 Java 线程正常退出时是否会运行 shutdownHook 中的方法。

TestGracefulShutdown02 用于验证 Java 线程被强制关闭时是否会执行 shutdownHook 中的方法。

TestGracefulShutdown03 用于验证 Java 线程调用 System#exit 时是否会执行 shutdownHook 中的方法。

测试步骤如下：

1.编译 java 源代码

```bash
# 通过如下方式编译代码

cd src

javac TestGracefulShutdown01.java
javac TestGracefulShutdown02.java
javac TestGracefulShutdown03.java
```

2.运行字节码进行测试1

```bash
# 利用 java 命令来进行测试，当前路径为 src

java TestGracefulShutdown01
is closing.
```

这证明了在 Java 线程正常退出时，JVM 会负责运行 shutdownHook 中的方法。

3.测试强制关闭线程的情况下 shutdownHook 中的方法是否会被执行

```bash
# 利用 kill 命令来关闭线程

java TestGracefulShutdown02

# 在另一个 bash 窗口利用 jps 得到 TestGracefulShutdown02 类的线程 ID
jps
67923 Jps
67878 TestGracefulShutdown02
# 然后利用 kill 命令关闭线程

kill 67878

# 可以见到原 bash 窗口输出如下内容
is closing.
```

可见 kill 命令能够触发 JVM 的 shutdownHook 机制中的方法。

如果我们换一个命令：kill -9，那么可以发现控制台并不会打印出 "is closing."。可见，kill -9 命令不能触发 JVM 的 shutdownHook 机制中的方法。

下面我们使用 Ctrl+C 的方式来关闭该线程，检测这种方式关闭线程是否会触发 JVM 的 shutdownHook 机制中的方法。

```bash
java TestGracefulShutdown02
# 按 Ctrl + C
^Cis closing.
```

可见，Ctrl+C 的方式也会触发 JVM 的 shutdownHook 机制。

4.最后测试 System#exit 方法是否会触发 shutdownHook 机制

```bash
java TestGracefulShutdown03
is closing.
```

可见 System#exit 方法会触发 shutdownHook 机制。