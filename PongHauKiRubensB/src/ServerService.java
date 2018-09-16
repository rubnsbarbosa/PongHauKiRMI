import bean.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.ServerIF;
import interfaces.ClientIF;

/**
 * Created by Rubens Santos Barbosa. 
 * RMI - Pong Hau Ki September 2018
 */
public class ServerService extends UnicastRemoteObject implements ServerIF {

    private ServerIF server;
    private ArrayList<ClientIF> clients;
    User user;
    private String message;
    private short FLAG = 9000;
    private boolean myTurn;

    ServerService() throws RemoteException {
        super();
        System.out.println("Servidor criado!");
        clients = new ArrayList<ClientIF>();
        user = new User();
    }

    public boolean addNewUser(ClientIF client) throws RemoteException {
        if (clients.isEmpty()) { // no one connected            
            user.setMyTurn(false); // set player orange with: player 2
            setMyTurn(false);
            setEcho("Você joga com as bolinhas laranjas e aguarda o movimento do openente.");
            this.clients.add(client);
            FLAG = 9001;
            return true; // connection ok
        } else if (!clients.isEmpty() && FLAG == 9000) {
            user.setMyTurn(true); // set player yellow with: player 1
            setMyTurn(true);
            setEcho("Você joga com as bolinhas amarelas e inicia a partida.");
            this.clients.add(client);
            return true;
        }
        FLAG = 9000;
        return false;
    }

    @Override
    public synchronized boolean connect(ClientIF client) throws RemoteException {
        return this.addNewUser(client);
    }

    @Override
    public synchronized void disconnect(String client) throws RemoteException {
        clients.remove(client);

        // Message 2 send
        String message = client + ": desistiu e vc venceu por WO!";

        int i = 0;
        while (i < clients.size() - 1) {
            clients.get(i++).onIncomingMessage(message);
        }

    }

    @Override
    public void sendMessage2chat(String name, String message) throws RemoteException {
        String msg = name + ": " + message + "\n";
        broadcastMessage(msg);
    }

    private synchronized void broadcastMessage(String message) throws RemoteException {
        try {
            int i = 0;
            while (i < clients.size() - 1) {
                clients.get(i++).onIncomingMessage(message);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getEcho() throws RemoteException {
        return message;
    }

    @Override
    public void setEcho(String message) throws RemoteException {
        this.message = message;
    }

    @Override
    public void sendMove(String piece) throws RemoteException {
        try {
            int i = 0;
            while (i < clients.size() - 1) {
                clients.get(i++).onIncomingMovement(piece);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendRestart(String restart) throws RemoteException {
        try {
            int i = 0;
            while (i < clients.size() - 1) {
                clients.get(i++).onIncomingRestart(restart);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isMyTurn() throws RemoteException {
        return myTurn;
    }

    @Override
    public void setMyTurn(boolean myTurn) throws RemoteException {
        this.myTurn = myTurn;
    }

}
