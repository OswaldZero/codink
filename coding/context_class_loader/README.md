# 线程上下文类加载器
> 之所以提出是因为java的spi机制导致的，因为这些接口被当做java核心包的一部分，所以应当是由根加载器加载的，
> 但是由于其实现是第三方厂商实现的，因此其实现是无法由根加载器加载的，因此TCCL便出现了，我们以JDBC中的一个例子为例
> DriverManager里面的静态代码块里有loadInitialDrivers()，实际上这个方法里面有ServiceLoader.load(Driver.class)一段代码
> 这个ServiceLoader是jdk1.6提出来的目的是为了按照类路径下的jar包的META-INF下的services下的文件找到实现类
> 这个其实是针对spi机制提出来的一种解决方案。再看ServiceLoader.load(Driver.class)里面

```java
public static <S> ServiceLoader<S> load(Class<S> service) {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    return ServiceLoader.load(service, cl);
}
```

> 因为线程上下文是在运行时获得的，所以这里很显然是将这个实现类的加载交给了系统类加载器（应用类加载器）
