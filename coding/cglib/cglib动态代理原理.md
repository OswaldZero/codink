# cglic原理
    该原理大致也是差不多的，只不过他的创建的代理类并不是通过继承Enhancer类（相对于Proxy）而得到的方法拦截器对象（相对于InvocationHandler），
    而是在data.newInstance(argumentTypes, arguments, callbacks)这个方法时自动生成的，给代理类强行加入了一个private的MethodInterceptor属性。

    