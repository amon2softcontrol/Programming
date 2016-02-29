package MainApplication;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import static javax.swing.JComponent.WHEN_FOCUSED;
import sun.audio.*;
import javax.swing.Timer;
import static oracle.jrockit.jfr.JFR.get;
//String buf="";
// buf=String.format();


public class Game1Frame extends JFrame {

    // components
    private JPanel contentpane;
    private JLabel nameLabel,j;
    private static String name;
    private JPanel drawpane,grp;
    private cartLabel cL;
    private ImageIcon playerImg0, playerImg1, playerImg2, playerImg21,playerImg3, playerImg4,playerImg41, obstracleImg;
    private JTextField text;
    private Game1Frame c;
    public int ii = 0;
    private boolean forward = true;
    private int speed = 500, direction = 0;
    private int frameWidth = 1000, frameHeight = 750;
    private int obstracleWidth = 150, obstracleHeight = 120;
    private int playerWidth = 200, playerHeight = 300;
    private int playerCurX = 0, playerCurY = 480, obstracleCurY;
    private String ImgLoc = "C:\\Users\\AMON\\Desktop\\MUIC_Study_Content\\Programing\\Programing_Paradiagm\\Codings\\NetBeansProjects\\Project_Java\\Images_Graphics\\";
    private int score;
    private String[] obst = {ImgLoc+"banana.gif", ImgLoc+"Cherry.gif", ImgLoc+"grapes.gif", ImgLoc+"orange.gif", ImgLoc+"letterS.gif", ImgLoc+"letterM.gif", ImgLoc+"letterF.gif", ImgLoc+"scorpio.gif", ImgLoc+"obstracle1.gif"};

    public int getScore() {
        return score;
    }
    private boolean Ispause = false;

    private void setspeed(int s) {
        speed = s;
    }

    private void setdirection(String s) {
        if (s == "forward") {
            direction = 0;
        } else {
            direction = 1;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Game1Frame(name);
    }

    public Game1Frame(String s) throws InterruptedException, IOException {
        setTitle("Game");
        setBounds(180, 10, frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        AddComponents();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(contentpane, "You made: " + score + " points", "Result", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ImgLoc+"jrk.png"));
            }
        });
       
        name = s;
        setVisible(true);
        createPlayerThread();
        createDropThreads();

    }
    Image background = Toolkit.getDefaultToolkit().createImage(ImgLoc+"jms.png");

    public void AddComponents() {
        drawpane = new JPanel() {
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }

        };
        drawpane.setLayout(null);

        playerImg0 = new ImageIcon(ImgLoc+"unknown-23.gif");
        playerImg1 = new ImageIcon(ImgLoc+"unknown-231.gif");
        playerImg2 = new ImageIcon(ImgLoc+"oldman_right.gif");
        playerImg21= new ImageIcon(ImgLoc+"oldman_left.gif");
        playerImg3 = new ImageIcon(ImgLoc+"giphy(2).gif");
        playerImg4 = new ImageIcon(ImgLoc+"man_right2.gif");
        playerImg41= new ImageIcon(ImgLoc+"man_left2.gif");

        cL = new cartLabel(playerImg1);

        Action goLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (cL.getIcon() == playerImg0) {
                    cL.setIcon(playerImg1);
                }
                if (cL.getIcon() == playerImg2) {
                    cL.setIcon(playerImg21);
                }
                if (cL.getIcon() == playerImg4) {
                    cL.setIcon(playerImg41);
                }
                setdirection("backward");
            }
        };
        Action goRight = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (cL.getIcon() == playerImg1) {
                    cL.setIcon(playerImg0);
                }
                 if (cL.getIcon() == playerImg21) {
                    cL.setIcon(playerImg2);
                }
                  if (cL.getIcon() == playerImg41) {
                    cL.setIcon(playerImg4);
                }
                setdirection("forward");
            }
        };

        Action pause = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Ispause = true;
                          
                PauseMethod();
                  }
        };
        Action resume = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Ispause = false;
                //PauseMethod();              
            }
        };

        Action refresh = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    createDropThreads();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game1Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        contentpane.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "doSomething1");
        contentpane.getActionMap().put("doSomething1", goLeft);
        contentpane.getInputMap().put(KeyStroke.getKeyStroke("P"), "doSomething2");
        contentpane.getActionMap().put("doSomething2", pause);
        contentpane.getInputMap().put(KeyStroke.getKeyStroke("R"), "doSomething3");
        contentpane.getActionMap().put("doSomething3", resume);
        contentpane.getInputMap().put(KeyStroke.getKeyStroke("F5"), "doSomething4");
        contentpane.getActionMap().put("doSomething4", refresh);
        contentpane.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "doSomething");
        contentpane.getActionMap().put("doSomething", goRight);

        cL.setBounds(playerCurX, playerCurY, playerWidth, playerHeight);
        drawpane.add(cL);
        text = new JTextField("" + score, 5);
        text.setToolTipText("Current Score!");
        text.setFont(new Font("Tahoma", Font.BOLD + Font.ITALIC, 14));
        text.setEditable(false);

        grp = new JPanel();
        nameLabel = new JLabel(name);
        grp.setBounds(0, 0, 400, 50);
        grp.setBackground(new Color(200, 200, 150));
        grp.add(nameLabel);
        grp.add(text);
        contentpane.add(grp, BorderLayout.NORTH);
        contentpane.add(drawpane, BorderLayout.CENTER);

    }

    private void createPlayerThread() throws InterruptedException {

        Thread PlayerThread = new Thread() {
            public void run() {

                while (true) {
                    if (direction == 0) {
                        switch (speed) {
                            case 500:
                                playerCurX += 12;
                            case 300:
                                playerCurX += 6;
                            case 100:
                                playerCurX += 3;
                        }
                    }
                    if (direction == 1) {
                        switch (speed) {
                            case 500:
                                playerCurX -= 12;
                            case 300:
                                playerCurX -= 6;
                            case 100:
                                playerCurX -= 3;

                        }
                    }

                    if ((playerCurX + playerWidth) > drawpane.getWidth()) {
                        playerCurX = 0;
                    }
                    if (playerCurX < 0) {
                        playerCurX = drawpane.getWidth() - playerWidth;
                    }
                    cL.setLocation(playerCurX, playerCurY);
                    repaint();
                    validate();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }

            } // end method run
        }; // end thread creation with anonymous class
        PlayerThread.start();
    }

    private void createDropThreads() throws InterruptedException {

        class a extends Thread {

            private int obstracleCurY;

            public a(String name, int i) {
                super(name);
                obstracleCurY = i;
            }

            public void run() {
                Random r = new Random();
                int ObstracleCurX = r.nextInt(900);
                int randomDrop = r.nextInt(obst.length);
                JLabel obstracleLabel = new JLabel(new ImageIcon(obst[randomDrop]));
                obstracleLabel.setBounds(ObstracleCurX, 0, obstracleWidth, obstracleHeight);
                drawpane.add(obstracleLabel);
                while ((obstracleCurY != frameHeight)) {
                    if(!Ispause)try{wait();}catch(Exception e){}
                    if (obstracleCurY >= (playerCurY)) {
                        if (ObstracleCurX >= playerCurX && ObstracleCurX <= (playerImg0.getIconWidth() + playerCurX)) {
                            switch (randomDrop) {
                                case 4: {
                                    cL.setIcon(playerImg2);
                                    setspeed(100);
                                }
                                break;
                                case 5: {
                                    cL.setIcon(playerImg4);
                                    setspeed(300);
                                }
                                break;
                                case 6: {
                                    cL.setIcon(playerImg1);
                                    setspeed(500);
                                }
                                break;
                                case 7: {
                                    score--;
                                    dispose();
                                }
                                break;
                                case 8: {
                                    score--;
                                    dispose();
                                }
                                break;
                                default:
                                    updateScore();
                            }

                            drawpane.remove(obstracleLabel);
                        }
                    }
                    obstracleCurY++;
                    if (obstracleCurY == frameHeight) {
                        try {
                            createDropThreads();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Game1Frame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    obstracleLabel.setLocation(ObstracleCurX, obstracleCurY);
                    repaint();
                    try {
                        Thread.sleep(12);
                    } catch (InterruptedException ex) {
                    }
                }
                drawpane.remove(obstracleLabel);
                repaint();
                validate();

            }
        }
        new a("", 40).start();
        new a("", 0).start();

    }

    synchronized private void updateScore() {
        score++;
        text.setText("" + score);
    }

    synchronized private void PauseMethod() {
        if(Ispause){ try{wait(10000);}catch(Exception e){}}
     }

    class cartLabel extends JLabel {

        public cartLabel(ImageIcon i) {
            setIcon(i);
        }
    }

}
