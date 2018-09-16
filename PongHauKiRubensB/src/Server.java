import java.rmi.Naming;

/**
 * Created by Rubens Santos Barbosa. 
 * RMI - Pong Hau Ki September 2018
 */
public class Server {

    public static void main(String args[]) {
        try {           
            ServerService obj = new ServerService();
            Naming.rebind("//localhost/PongHauKiRMI", obj);
            System.out.println("Servidor Registrado!");
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }

}

/*
na pasta src 

Terminal 1 - server
$ javac ServerService.java
$ javac Server.java
// stubs
$ rmic ServerService

Terminal 2 - deixando o servidor de nomes no ar
$ rmiregistry

Terminal 1 - server
$ java Server

 */
