package MainApplication;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import static javax.swing.JComponent.WHEN_FOCUSED;
import sun.audio.*;
import javax.swing.Timer;
//String buf="";
// buf=String.format();
//event.getSource();

public class Game2Frame extends JFrame {
    // components
  private JPanel contentpane,drawpane;
  private int frameWidth=800,frameHeight=800;
  private static String VOICENAME = "kevin16";
  private static String name;
  private boolean box1=false,box2=false,box3=false,box4=false;
  private int numberGuess;
  private String numberIm;
  private String ImgLoc = "C:\\Users\\AMON\\Desktop\\MUIC_Study_Content\\Programing\\Programing_Paradiagm\\Codings\\NetBeansProjects\\Project_Java\\Images_Graphics\\";
  Image background = Toolkit.getDefaultToolkit().createImage("wp.jpg");
  private String[] numberImg={ImgLoc+"n0.jpg",ImgLoc+"n1.jpg",ImgLoc+"n2.jpg",ImgLoc+"n3.jpg",ImgLoc+"n4.jpg",ImgLoc+"n5.jpg",ImgLoc+"n6.png",ImgLoc+"n7.jpg",ImgLoc+"n8.jpg",ImgLoc+"n9.jpg",ImgLoc+"n10.jpg",ImgLoc+"n11.jpg",ImgLoc+"n12.jpg",ImgLoc+"n13.jpg",ImgLoc+"n14.jpg",ImgLoc+"n15.jpg"};
    public static void main(String[] args) throws InterruptedException, IOException {
        new Game2Frame(name);
    }

    public Game2Frame(String s) throws InterruptedException, IOException {
        setTitle("Game");
        setBounds(120,10, frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(contentpane, "That was too easy to guess", "Result", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(ImgLoc+"jrk.png"));
            }
        });
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(null);
        AddComponents();
       
        name =s;
        setVisible(true);
      
    }
  

    public void AddComponents() {
       
        //drawpane.setBounds(0, 0, frameWidth, frameHeight);
        
        speak("Welcome "+name+" Choose any number between 0-15");
        speak("Now is it in this box");
       int reply = JOptionPane.showConfirmDialog( null,"11 8 14 9 12 15 13 10", "An Inane Question",
                JOptionPane.YES_NO_OPTION);
       
        if (reply == JOptionPane.YES_OPTION)   box1=true;
        else box1=false;
        speak("is it in this box");
        int reply1 = JOptionPane.showConfirmDialog( null,"4 12 15 5 13 6 14 7", "An Inane Question",
                JOptionPane.YES_NO_OPTION);
       
        if (reply1 == JOptionPane.YES_OPTION)   box2=true;
        else box2=false;
        speak("is it in this box");
        int reply2 = JOptionPane.showConfirmDialog( null,"10 6 2 7 14 3 11 15", "An Inane Question",
                JOptionPane.YES_NO_OPTION);
       
        if (reply2 == JOptionPane.YES_OPTION)   box3=true;
        else box3=false;
        speak("is it in this box");
        int reply3 = JOptionPane.showConfirmDialog( null,"11 1 5 3 9 13 7 15", "An Inane Question",
                JOptionPane.YES_NO_OPTION);
       
        if (reply3 == JOptionPane.YES_OPTION)   box4=true;
        else box4=false;
        
        doTheGuessing();
        drawpane = new JPanel(){
             protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }
        };
        drawpane.setLayout(null);
        drawpane.setBounds(0, 0, frameWidth, frameHeight);
        JButton number1 = new JButton(new ImageIcon(ImgLoc+"banana1.gif"));
        number1.setBounds(0,0, frameWidth,frameHeight);
        number1.setBackground(Color.BLACK);
        //numberIm=numberImg[12];
        number1.setRolloverIcon(new ImageIcon(numberIm));
        drawpane.add(number1);
        contentpane.add(number1);
       
        speak("hover over button to see what i guessed");
        
        
        
       
    }

    
    public static void speak(String msg)
    {
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);
        voice.allocate();
        try {
              voice.speak(msg);
        } catch (Exception e) {
        }
    }
    
        public void doTheGuessing(){
            
            if(box1==true&&box2==true&&box3==true&&box4==true){numberGuess =15;numberIm=numberImg[15];}
            if(box1==false&&box2==false&&box3==false&&box4==false){numberGuess =0;numberIm=numberImg[0];}
            if(box1==false&&box2==false&&box3==false&&box4==true){numberGuess =1;numberIm=numberImg[1];}
            if(box1==false&&box2==false&&box3==true&&box4==false){numberGuess =2;numberIm=numberImg[2];}
            if(box1==false&&box2==false&&box3==true&&box4==true){numberGuess =3;numberIm=numberImg[3];}
            if(box1==false&&box2==true&&box3==false&&box4==false){numberGuess =4;numberIm=numberImg[4];}
            if(box1==false&&box2==true&&box3==false&&box4==true){numberGuess =5;numberIm=numberImg[5];}
            if(box1==false&&box2==true&&box3==true&&box4==false){numberGuess =6;numberIm=numberImg[6];}
            if(box1==false&&box2==true&&box3==true&&box4==true){numberGuess =7;numberIm=numberImg[7];}
            if(box1==true&&box2==false&&box3==false&&box4==false){numberGuess =8;numberIm=numberImg[8];}
            if(box1==true&&box2==false&&box3==false&&box4==true){numberGuess =9;numberIm=numberImg[9];}
            if(box1==true&&box2==false&&box3==true&&box4==false){numberGuess =10;numberIm=numberImg[10];}
            if(box1==true&&box2==false&&box3==true&&box4==true){numberGuess =11;numberIm=numberImg[11];}
            if(box1==true&&box2==true&&box3==false&&box4==false){numberGuess =12;numberIm=numberImg[12];}
            if(box1==true&&box2==true&&box3==false&&box4==true){numberGuess =13;numberIm=numberImg[13];}
            if(box1==true&&box2==true&&box3==true&&box4==false){numberGuess =14;numberIm=numberImg[14];}
            
        }

        
}
