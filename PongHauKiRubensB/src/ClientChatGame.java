import bean.User;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;
import interfaces.ServerIF;
import interfaces.ClientIF;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * Created by Rubens Santos Barbosa.
 * RMI - Pong Hau Ki September 2018
 */
public class ClientChatGame extends javax.swing.JFrame {

    private String name;
    private ServerIF stub;
    private ClientIF client;

    public ClientChatGame(ServerIF stub) throws RemoteException {
        super();
        initComponents();
        this.stub = stub;

        surrenderCloseWindow();
        // update messages in textArea
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    private void mOrangeOne() throws RemoteException {
        try {
            if (!stub.isMyTurn() == false) {
                JOptionPane.showMessageDialog(null, "Aguarde sua vez de jogar");
            } else {
                String piece = "P1ORANGE";
                stub.sendMove(piece);
                stub.setMyTurn(true);
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void mOrangeTwo() {
        try {
            if (!stub.isMyTurn() == false) {
                JOptionPane.showMessageDialog(null, "Aguarde sua vez de jogar");
            } else {
                String piece = "P2ORANGE";
                stub.sendMove(piece);
                stub.setMyTurn(true);
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void mYellowOne() {
        try {
            if (stub.isMyTurn() == true) {
                String piece = "P1YELLOW";
                stub.sendMove(piece);
                stub.setMyTurn(false);
            } else {
                JOptionPane.showMessageDialog(null, "Aguarde sua vez de jogar");
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void mYellowTwo() {
        try {
            if (stub.isMyTurn() == true) {
                String piece = "P2YELLOW";
                stub.sendMove(piece);
                stub.setMyTurn(false);
            } else {
                JOptionPane.showMessageDialog(null, "Aguarde sua vez de jogar");
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void btnSendMessage() throws RemoteException {
        // get message of TextField
        String textInput = txt2send.getText();
        // check if text is empty
        if (!textInput.isEmpty()) {
            // try send a mensagem typed
            try {
                stub.sendMessage2chat(name, textInput);
                // clean textField
                txt2send.setText("");
                txt2send.requestFocus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void disabledFields() {
        this.btnConnect.setEnabled(false);
        this.txtName.setEditable(false);
        this.textArea.setEnabled(true);
        this.txt2send.setEnabled(true);
        this.btnSend.setEnabled(true);
    }

    private void loginUser() throws RemoteException {
        name = txtName.getText();
        User user = new User();

        if (!name.isEmpty()) {
            disabledFields();
            try {
                this.client = new ClientService(stub, name, textArea, p1Orange, p2Orange, p1Yellow, p2Yellow);
                this.stub.connect(client);
                user.setName(name);
            } catch (RemoteException ex) {
                Logger.getLogger(ServerIF.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, user.getName() + ", bem vindo(a) ao jogo Pong-Hau-Ki! " + this.stub.getEcho());
        }
    }

    private void btnConnect() throws RemoteException {
        loginUser();
    }

    private void btnSurrender() {
        try {
            this.stub.disconnect(name);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        System.exit(0);
    }

    public void surrenderCloseWindow() throws RemoteException {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    stub.disconnect(name);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientChatGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
    }

    private void restartActionPerformed() throws RemoteException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String restart = "RESTART";
                try {
                    stub.sendRestart(restart);

                } catch (RemoteException re) {
                    re.printStackTrace();
                }
            }
        }).start();
        JOptionPane.showMessageDialog(this, "Você perdeu a partida!");
    }

    private boolean myTurn;
    public void setMyTurn(boolean myTurn) throws RemoteException {
        this.myTurn = myTurn;
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pong_hau_ki = new javax.swing.JLabel();
        rocket = new javax.swing.JLabel();
        p1Orange = new javax.swing.JButton();
        p2Orange = new javax.swing.JButton();
        p1Yellow = new javax.swing.JButton();
        p2Yellow = new javax.swing.JButton();
        no = new javax.swing.JLabel();
        no1 = new javax.swing.JLabel();
        no2 = new javax.swing.JLabel();
        no3 = new javax.swing.JLabel();
        no4 = new javax.swing.JLabel();
        ux = new javax.swing.JLabel();
        restart = new javax.swing.JButton();
        surrender = new javax.swing.JButton();
        lb_restart = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        txt2send = new javax.swing.JTextField();
        img_chat = new javax.swing.JLabel();
        btnSend = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pong Hau Ki");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(970, 520));
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 60, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));

        jPanel2.setBackground(new java.awt.Color(15, 191, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pong_hau_ki.setFont(new java.awt.Font("Lucida Grande", 1, 26)); // NOI18N
        pong_hau_ki.setForeground(new java.awt.Color(255, 255, 255));
        pong_hau_ki.setText("Pong-Hau-Ki");
        jPanel2.add(pong_hau_ki, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 190, 40));

        rocket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/rocket.png")));
        jPanel2.add(rocket, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 70));

        p1Orange.setBackground(new java.awt.Color(15, 191, 255));
        p1Orange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleOrange-2.png")));
        p1Orange.setBorderPainted(false);
        p1Orange.setContentAreaFilled(false);
        p1Orange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    mOrangeOne();
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientChatGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jPanel2.add(p1Orange, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 84, 84));

        p2Orange.setBackground(new java.awt.Color(15, 191, 255));
        p2Orange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleOrange-2.png")));
        p2Orange.setBorderPainted(false);
        p2Orange.setContentAreaFilled(false);
        p2Orange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOrangeTwo();
            }
        });
        jPanel2.add(p2Orange, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 100, 84, 84));

        p1Yellow.setBackground(new java.awt.Color(15, 191, 255));
        p1Yellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleYellow-2.png")));
        p1Yellow.setBorderPainted(false);
        p1Yellow.setContentAreaFilled(false);
        p1Yellow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mYellowOne();
            }
        });
        jPanel2.add(p1Yellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 84, 84));

        p2Yellow.setBackground(new java.awt.Color(15, 191, 255));
        p2Yellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleYellow-2.png")));
        p2Yellow.setBorderPainted(false);
        p2Yellow.setContentAreaFilled(false);
        p2Yellow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mYellowTwo();
            }
        });
        jPanel2.add(p2Yellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 320, 84, 84));

        no.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleZ.png")));
        jPanel2.add(no, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 207, 75, 75));

        no1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleZ.png")));
        jPanel2.add(no1, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 106, 75, 75));

        no2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleZ.png")));
        jPanel2.add(no2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 106, 75, 75));

        no3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleZ.png")));
        jPanel2.add(no3, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 326, 75, 75));

        no4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/circleZ.png")));
        jPanel2.add(no4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 326, 75, 75));

        ux.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/UX.png")));
        jPanel2.add(ux, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 410, 250));

        restart.setBackground(new java.awt.Color(15, 191, 255));
        restart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/restart.png")));
        restart.setBorderPainted(false);
        restart.setContentAreaFilled(false);
        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    restartActionPerformed();
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientChatGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jPanel2.add(restart, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 70, 70));

        surrender.setBackground(new java.awt.Color(15, 191, 255));
        surrender.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        surrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/surrender.png")));
        surrender.setBorderPainted(false);
        surrender.setContentAreaFilled(false);
        surrender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSurrender();
            }
        });
        jPanel2.add(surrender, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 6, 70, 70));

        lb_restart.setFont(new java.awt.Font("Lucida Grande", 1, 14));
        lb_restart.setForeground(new java.awt.Color(255, 255, 255));
        lb_restart.setText("Reiniciar");
        jPanel2.add(lb_restart, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 76, -1, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Desistir");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 76, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 0, 610, 500));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 0, 280, -1));

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        textArea.setEnabled(false);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 40, 300, 420));

        txt2send.setEnabled(false);
        getContentPane().add(txt2send, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 459, 234, 40));

        img_chat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/chat.png"))); // NOI18N
        getContentPane().add(img_chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, 40));

        btnSend.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        btnSend.setText("Enviar");
        btnSend.setEnabled(false);
        btnSend.setOpaque(true);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnSendMessage();
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientChatGame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        getContentPane().add(btnSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 459, 70, 40));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 0, 170, 40));

        btnConnect.setText("Conectar");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnConnect();
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientChatGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        getContentPane().add(btnConnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 100, 40));

        pack();
    }
    
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel img_chat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_restart;
    private javax.swing.JLabel no;
    private javax.swing.JLabel no1;
    private javax.swing.JLabel no2;
    private javax.swing.JLabel no3;
    private javax.swing.JLabel no4;
    private javax.swing.JButton p1Orange;
    private javax.swing.JButton p1Yellow;
    private javax.swing.JButton p2Orange;
    private javax.swing.JButton p2Yellow;
    private javax.swing.JLabel pong_hau_ki;
    private javax.swing.JButton restart;
    private javax.swing.JLabel rocket;
    private javax.swing.JButton surrender;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField txt2send;
    private javax.swing.JTextField txtName;
    private javax.swing.JLabel ux;
    
}
