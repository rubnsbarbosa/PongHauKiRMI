import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import interfaces.ServerIF;
import interfaces.ClientIF;
import javax.swing.JButton;
import javax.swing.JTextArea;
import ponghauki.Buttons;
import ponghauki.MoveButtonsCircle;

/**
 * Created by Rubens Santos Barbosa. 
 * RMI - Pong Hau Ki 
 * September 2018
 */
public class ClientService extends UnicastRemoteObject implements ClientIF {

    private ServerIF server;
    private String name = null;
    private JTextArea textArea;
    MoveButtonsCircle circle;
    Buttons pOneOrange = null;
    Buttons pTwoOrange = null;
    Buttons pOneYellow = null;
    Buttons pTwoYellow = null;

    private JButton p1Orange;
    private JButton p1Yellow;
    private JButton p2Orange;
    private JButton p2Yellow;

    public ClientService(ServerIF s, String n, JTextArea ta, JButton p1o, JButton p2o, JButton p1y, JButton p2y) throws RemoteException {
        this.server = s;
        this.name = n;
        this.textArea = ta;
        server.connect(this);

        this.p1Orange = p1o;
        this.p2Orange = p2o;
        this.p1Yellow = p1y;
        this.p2Yellow = p2y;

        this.circle = new MoveButtonsCircle();

        setBallsPositions();
    }

    private void setBallsPositions() {
        pOneOrange = new Buttons(p1Orange, 0);
        pTwoOrange = new Buttons(p2Orange, 1);
        pOneYellow = new Buttons(p1Yellow, 2);
        pTwoYellow = new Buttons(p2Orange, 3);

        circle.setPlayerOrangeOne(pOneOrange);
        circle.setPlayerOrangeTwo(pTwoOrange);
        circle.setPlayerYellowOne(pOneYellow);
        circle.setPlayerYellowTwo(pTwoYellow);
    }

    @Override
    public void onIncomingMessage(String message) throws RemoteException {
        this.textArea.append(message);
    }

    @Override
    public void onIncomingMovement(String piece) throws RemoteException {
        switch (piece) {
            case "P1ORANGE":
                circle.movePlayerOrangeOne();
                break;
            case "P2ORANGE":
                circle.movePlayerOrangeTwo();
                break;
            case "P1YELLOW":
                circle.movePlayerYellowOne();
                break;
            case "P2YELLOW":
                circle.movePlayerYellowTwo();
                break;
            default:
                break;
        }
    }

    @Override
    public void onIncomingRestart(String restart) throws RemoteException {
        switch (restart) {
            case "RESTART":
                setInitialPositions();
                break;
            default:
                break;
        }
    }

    private void setInitialPositions() {
        pOneOrange.startingPositionOrangeOne(p1Orange);
        pTwoOrange.startingPositionOrangeTwo(p2Orange);
        pOneYellow.startingPositionYellowOne(p1Yellow);
        pTwoYellow.startingPositionYellowTwo(p2Yellow);
    }

}
