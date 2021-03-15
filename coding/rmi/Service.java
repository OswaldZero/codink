package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/15
 */
public interface Service extends Remote, Serializable {
    public User remote(User user)throws RemoteException;
}
