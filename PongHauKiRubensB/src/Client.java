import java.rmi.Naming;
import interfaces.ServerIF;

/**
 * Created by Rubens Santos Barbosa.
 * RMI - Pong Hau Ki
 * September 2018
 */
public class Client {

    public static void main(String args[]) throws Exception {
        ServerIF server = (ServerIF)Naming.lookup("rmi://localhost/PongHauKiRMI");
        ClientChatGame frame = new ClientChatGame(server);
        frame.setVisible(true);
        System.out.println("Objeto Localizado!");     
        
    }

}

