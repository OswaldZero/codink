package rmi;

import javax.naming.Name;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/15
 */
public class Server implements Runnable {
    public void server() throws AlreadyBoundException, RemoteException, MalformedURLException {

        //底层其实创建了stub和skeleton
        Service service = new ServiceImpl();

        //创建RMI注册表并绑定端口
        Registry registry = LocateRegistry.createRegistry(11111);

        //将服务注册绑定到注册表
        Naming.bind("rmi://localhost:11111/service",service);

        System.out.println("服务器已开始运行");
    }

    @Override
    public void run() {
        try {
            server();
            while (true);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
