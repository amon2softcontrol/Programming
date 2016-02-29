package MainApplication;

import static MainApplication.Menu_app1.speak;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class MainApp extends JFrame {
    
    JPanel p;
    JLabel aV;
    JPanel drawpane;
    private JTextField f ;
    private String name;
    
    public String getName(){return name;}
    static public GraphicsDevice gd;
    private String ImgLoc = "..\\..\\Images_Graphics";
    Image background = Toolkit.getDefaultToolkit().createImage("ImgLoc\\jms.jpg");
    Image avatar = Toolkit.getDefaultToolkit().createImage("ImgLoc\\jsk.gif");
       
    public MainApp(){
        
        setTitle("Project_3");
        setBounds(200,80,1000,600);
        setResizable(false);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );  
        setFocusable(true);
        requestFocusInWindow();
              
        p = (JPanel) getContentPane( );
        p.setLayout(null);
        JPanel k = new JPanel();
        JLabel jl = new JLabel(),jl1=new JLabel(),jl2 = new JLabel(),jl3=new JLabel(new ImageIcon(ImgLoc+"giphy.gif"));
        //System.out.println("user.dir: " + System.getProperty("user.dir"));
        jl3.setBounds(0,0,500,300);
        jl.setText("Enter Name: ");
        jl.setBounds(384, 300, 80, 24);
        jl1.setText("<c> copyright 2015.All Right Reserved");
        jl1.setBounds(380, 512, 500, 30);
        jl2.setBounds(284, 532, 600, 24);
        jl2.setText("Mahidol University International College, Department of Computer Engineering");
        f = new JTextField();
        f.setBounds(464, 300, 90, 24);
       
        JButton jb = new JButton();
        jb.setBounds(800, 400, 150, 100);
        jb.setBackground(Color.orange);
        jb.setIcon(new ImageIcon("ImgLoc\\next.jpg"));
        jb.setToolTipText("Next Button");
        jb.addMouseListener(new MouseAdapter()
        {
           public void mouseClicked(MouseEvent e)
           {
                try {
                   AddComponents();
               } catch (InterruptedException ex) {
                   Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
                   Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
               }
           }});
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Bye!Bye! "+name, "name entered!",JOptionPane.INFORMATION_MESSAGE);
            }}  );
        f.addCaretListener(new CaretListener(){
            public void caretUpdate(CaretEvent e) {
                name = f.getText();
            }
        });
        
        p.setBackground(Color.ORANGE);
        p.add(jl3);
        p.add(jl);
        p.add(jl1);
        p.add(jl2);
        p.add(f);
        p.add(jb);
        
        //this.requestFocusInWindow();
        setVisible(true);
        
    }

    public static void main(String[] args) {
    GraphicsEnvironment gv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    gd = gv.getDefaultScreenDevice();
    gd.setFullScreenWindow(new MainApp());
        }
    
    public void AddComponents() throws InterruptedException, IOException
    {
             Thread t1 = new Thread(){
            public void run(){
                    speak("Welcome " + name + " to the most anticipated game of the year!");
                    speak("Choose any game from the above or play with other settings and options");
            }
        };
        t1.start();
             this.dispose();
             gd.setFullScreenWindow(new Menu_app1(name,Color.ORANGE));
    }

}
