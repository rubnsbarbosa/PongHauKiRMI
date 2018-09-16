package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Rubens Santos Barbosa.
 * RMI - Pong Hau Ki
 * September 2018
 */
public interface ClientIF extends Remote {
    // Server send message 2 cliente
    public void onIncomingMessage(String message) throws RemoteException;
    public void onIncomingMovement(String message) throws RemoteException;
    public void onIncomingRestart(String restart) throws RemoteException;
}
