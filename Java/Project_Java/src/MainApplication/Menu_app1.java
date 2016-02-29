package MainApplication;

import java.io.*;
import com.sun.speech.freetts.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;

public class Menu_app1 extends JFrame {

    private static String name;
    private Color color;
    private static String VOICENAME = "kevin16";
    private javax.swing.JCheckBox tb0, tb1, tb2, tb3, tb4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JComboBox cb;
    private javax.swing.JButton b;
    private javax.swing.JScrollPane js;
    private DefaultListModel listmodel;
    private JPanel contentpane, drawpane, component;
    private int frameWidth = 1300, frameHeight = 800, avatarWidth = 322, avatarHeight = 455, avatarCurX = 0, avatarCurY = frameHeight - 350;
    private ButtonGroup bgroup;
    private String ImgLoc = "..\\..\\Images_Graphics\\";
    private String AudLoc = "..\\..\\Audio_wav\\";
    private String[] avatarImg = {ImgLoc+"jsk.gif", ImgLoc+"t1.gif", ImgLoc+"t2.gif", ImgLoc+"t3.gif", ImgLoc+"t4.gif"};
    private String[] songName = {AudLoc+"dyltp.wav", AudLoc+"ucv.wav", AudLoc+"EONS.wav", AudLoc+"oneandonly.wav", AudLoc+"I am a girl.wav"};
    Image background = Toolkit.getDefaultToolkit().createImage(ImgLoc+"wp.jpg");
    Image background1 = Toolkit.getDefaultToolkit().createImage(ImgLoc+"jms.jpg");
    private static String look1 = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private static String look2 = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    private static String look3 = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
    private static String look4 = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    private static String look5 = "javax.swing.plaf.metal.MetalLookAndFeel";
    public String songname, avatarname;
    UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();

    public String getName() {
        return name;
    }

    public Menu_app1(String n, Color color1) {
        name = n;
        setTitle("Menu_app");
        setBounds(12, 12, frameWidth, frameHeight);
        setResizable(false);
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // setContentPane(new JPanel()
        //{ protected void paintComponent(Graphics g) {super.paintComponent(g);g.drawImage(background, 0, 0, null);}});
        color = color1;
        initComponents();
        setVisible(true);

    }

    private void initComponents() {

        Icon icon = new ImageIcon(avatarImg[0]), icon2 = new ImageIcon(avatarImg[2]);
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setBounds(avatarCurX, avatarCurY, avatarWidth, avatarHeight);
        jLabel1.setIcon(icon);

        js = new javax.swing.JScrollPane();

        jList1 = new javax.swing.JList();

        bgroup = new ButtonGroup();
        tb0 = new JCheckBox("Avatar1");
        tb1 = new JCheckBox("Avatar2");
        tb2 = new JCheckBox("Avatar3");
        tb3 = new JCheckBox("Avatar4");
        tb4 = new JCheckBox("Avatar5");
        tb0.setSelected(true);
        tb0.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (tb0.isSelected()) {
                    jLabel1.setIcon(new ImageIcon(avatarImg[0]));
                }
                jLabel1.setBounds(avatarCurX, avatarCurY, avatarWidth, avatarHeight);
            }
        });
        tb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (tb1.isSelected()) {
                    jLabel1.setIcon(new ImageIcon(avatarImg[1]));
                }
                jLabel1.setBounds(avatarCurX, avatarCurY, avatarWidth, avatarHeight);
            }
        });
        tb2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (tb2.isSelected()) {
                    jLabel1.setIcon(new ImageIcon(avatarImg[2]));
                }
                jLabel1.setBounds(800, avatarCurY, avatarWidth, avatarHeight);
            }
        });
        tb3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (tb3.isSelected()) {
                    jLabel1.setIcon(new ImageIcon(avatarImg[3]));
                }
                jLabel1.setBounds(avatarCurX, avatarCurY, avatarWidth, avatarHeight);
            }
        });
        tb4.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (tb4.isSelected()) {
                    jLabel1.setIcon(new ImageIcon(avatarImg[4]));
                }
                jLabel1.setBounds(avatarCurX, avatarCurY, avatarWidth, avatarHeight);
            }
        });

        bgroup.add(tb0);
        bgroup.add(tb1);
        bgroup.add(tb2);
        bgroup.add(tb3);
        bgroup.add(tb4);

        String[] strings = {"song1", "song2", "song3", "song4", "song5"};

        jList1 = new JList(strings);
        jList1.setVisibleRowCount(3);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {

                switch (jList1.getSelectedIndex()) {
                    case 0:
                        songname = songName[0];
                        break;
                    case 1:
                        songname = songName[1];
                        break;
                    case 2:
                        songname = songName[2];
                        break;
                    case 3:
                        songname = songName[3];
                        break;
                    case 4:
                        songname = songName[4];
                        break;
                }
            }
        });

        String[] comboOptions = {"theme1", "theme2", "theme3", "theme4", "theme5"};

        cb = new JComboBox(comboOptions);
        cb.setToolTipText("Change Voice&Theme");
        cb.setSelectedIndex(-1);
        contentpane.setBackground(color);
        cb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int index = cb.getSelectedIndex();
                if (index == 0) {
                    try {
                        UIManager.setLookAndFeel(look1);
                    } catch (Exception ex) {
                    }
                    Thread t1 = new Thread() {
                        public void run() {
                            speak("You have selected the Windows Look And Feel");
                             }
                    };
                    t1.start();
                    dispose();
                    new Menu_app1(name, Color.BLACK);
                }

                if (index == 1) {
                    try {
                        UIManager.setLookAndFeel(look2);
                    } catch (Exception ex) {
                    }
                    Thread t1 = new Thread() {
                        public void run() {
                            speak("You have selected the Motif Look And Feel");
                        }
                    };
                    t1.start();
                    dispose();
                    new Menu_app1(name, Color.CYAN);
                }

                if (index == 2) {
                    try {
                        UIManager.setLookAndFeel(look3);
                    } catch (Exception ex) {
                    }
                    Thread t1 = new Thread() {
                        public void run() {speak("You have selected the Windows classic LookAndFeel");
                        }
                    };
                    t1.start();
                    dispose();
                    new Menu_app1(name, Color.GREEN);
                }

                if (index == 3) {
                    try {
                        UIManager.setLookAndFeel(look4);
                    } catch (Exception ex) {
                    }
                    Thread t1 = new Thread() {
                        public void run() {speak("You have selected the nimbus Look And Feel");
                        speak("my favorite theme  Good Choice!!");
                        speak("Good Choice!!");
                        speak("I repeat good choice!!");
                        }
                    };
                    t1.start();
                    dispose();
                    new Menu_app1(name, Color.RED);
                }

                if (index == 4) {
                    try {
                        UIManager.setLookAndFeel(look5);
                    } catch (Exception ex) {
                    }
                    Thread t1 = new Thread() {
                        public void run() {
                            speak("You have selected the metal Look And Feel");
                        }
                    };
                    t1.start();
                    dispose();
                    new Menu_app1(name, Color.YELLOW);
                }
            }
        });
        final JButton play_music = new JButton("Play Music");
        play_music.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jList1.getSelectedIndex() == -1) {
                    play_music.setEnabled(false);
                }
                new audio(true, songname);
                play_music.setEnabled(false);
            }
        });
        final JButton stop_music = new JButton("Stop Music");
        stop_music.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainApplication.audio.set_sPB(true);
                play_music.setEnabled(true);

            }
        });
        final JButton game_1 = new JButton(new ImageIcon(ImgLoc+"hg.png"));
        game_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speak("You chose the hunger game. Good Luck " + name);
                try {
                    new Game1Frame(name);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu_app1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Menu_app1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        game_1.setBounds(120, 80, 400, 300);
        game_1.setBackground(Color.black);
        final JButton game_2 = new JButton(new ImageIcon(ImgLoc+"brain.jpg"));
        game_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speak("You chose the number trick game. Good Luck " + name);
                try {
                    new Game2Frame(name);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu_app1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Menu_app1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        game_2.setBounds(550, 100, 300, 250);
        game_2.setBackground(Color.black);
        final JButton game_3 = new JButton(new ImageIcon(ImgLoc+"space.gif"));
        game_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speak("You chose the space travel. Good Luck " + name);
                Game3Frame.main(avatarImg);
            }
        });
        game_3.setBounds(900, 160, 180, 100);
        game_3.setBackground(Color.black);
        jList1.setToolTipText("Song Collection");
        JPanel grp = new JPanel();
        grp.setBounds(400, avatarCurY + 150, 350, 80);
        JPanel grp1 = new JPanel();
        grp1.setBounds(12, 12, 400, 50);
        JPanel grp2 = new JPanel();
        grp2.setBounds(1100, 12, 80, 33);
        //grp.setBackground(Color.red);
        grp.add(new JScrollPane(jList1));
        grp.add(play_music);
        grp.add(stop_music);
        grp1.add(tb0);
        grp1.add(tb1);
        grp1.add(tb2);
        grp1.add(tb3);
        grp1.add(tb4);
        grp2.add(cb);
        contentpane.add(grp2);
        jList1.setBounds(avatarCurX + 400, avatarCurY + 200, 100, 80);
        contentpane.add(game_1);
        contentpane.add(game_2);
        contentpane.add(game_3);
        contentpane.add(grp);
        contentpane.add(grp1);
        contentpane.add(jLabel1);

    }// </editor-fold>                        

    public static void speak(String msg) {
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);
        voice.allocate();
        try {
            voice.speak(msg);
        } catch (Exception e) {
        }
    }

    public static void main(String args[]) {
        new Menu_app1(name, Color.ORANGE);
    }
}
