package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/15
 */
public class ServiceImpl extends UnicastRemoteObject implements Service, Serializable {

    private static final long serialVersionUID = 1L;
    protected ServiceImpl() throws RemoteException {
    }


    /**
     * 远程方法
     * @param user 用户
     * @return 修改后的实体类对象
     */
    @Override
    public User remote(User user)throws RemoteException{
        System.out.println("UserAge: "+user.getAge());
        System.out.println("UserName: " + user.getName());
        user.setAge(18);
        user.setName("cx");
        return user;
    }

}
