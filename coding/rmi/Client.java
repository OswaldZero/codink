package rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/15
 */
public class Client {
    public Service service;
    public void remoteInvoke() throws RemoteException, NotBoundException, MalformedURLException {

//            Registry registry = LocateRegistry.getRegistry("8888");
            //客户端到根据url到注册表中查找,得到存根stub
            Service service = (Service) Naming.lookup("rmi://localhost:11111/service");
            //调用
            User user = new User();
            user.setName("wyh");
            user.setAge(20);

            User userChange = service.remote(user);
            System.out.println(userChange);

    }

    public static void main(String[] args) throws AlreadyBoundException, RemoteException, MalformedURLException, NotBoundException {
        Server server = new Server();
        Thread thread = new Thread(server);
        thread.start();

        Client client = new Client();
        client.remoteInvoke();
    }
}
