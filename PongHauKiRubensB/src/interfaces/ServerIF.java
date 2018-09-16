package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Rubens Santos Barbosa.
 * RMI - Pong Hau Ki
 * September 2018
 */
public interface ServerIF extends Remote {
    // used for connect in a Client with the Server    
    public boolean connect(ClientIF name) throws RemoteException;
    public void disconnect(String client) throws RemoteException;
    public String getEcho() throws RemoteException;
    public void setEcho(String message) throws RemoteException;
    public void sendMessage2chat(String name, String message) throws RemoteException;
    // send move of circles    
    public void sendMove(String piece) throws RemoteException;
    // send restart game
    public void sendRestart(String restart) throws RemoteException;
    // turn
    public boolean isMyTurn() throws RemoteException;
    public void setMyTurn(boolean myTurn) throws RemoteException;
}
