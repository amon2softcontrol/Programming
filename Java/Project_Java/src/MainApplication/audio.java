package MainApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import static javax.swing.JFrame.*;

public class audio extends JFrame{

  AudioFormat audioFormat;
  AudioInputStream audioInputStream;
  SourceDataLine sourceDataLine;
  static boolean stopPlayback;
  public static void set_sPB(boolean ss){stopPlayback=ss;}
  
  public static void main(String args[]){
    new audio();
  }
  
  public audio(){}
  public audio(boolean p,String s)
  {         if(p==true)
            {
                playAudio(s);stopPlayback = false;
            }
  }
  
  private void playAudio(String fn) {
      
 try{
      File soundFile = new File(fn);
      audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      audioFormat = audioInputStream.getFormat();
      System.out.println(audioFormat);
      sourceDataLine =(SourceDataLine)AudioSystem.getLine(new DataLine.Info(SourceDataLine.class,audioFormat));

      new PlayThread().start();
      
    }catch (Exception e) {
          e.printStackTrace();
          System.exit(0);
    }
  }

class PlayThread extends Thread{
    
  byte tempBuffer[] = new byte[10000];
  
  public void run(){
    try{
        
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      int cnt;
      
      while((cnt= audioInputStream.read(tempBuffer,0,tempBuffer.length)) != -1&& stopPlayback == false)
      {
        if(cnt > 0)
        {
                 sourceDataLine.write(tempBuffer, 0, cnt);
        }
      }
    
    }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
}

}