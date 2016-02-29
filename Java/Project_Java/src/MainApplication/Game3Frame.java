package MainApplication;
import static MainApplication.Game3Frame.frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Game3Frame extends JPanel 
{
        Spaceship ship;
        ArrayList asteroids= new ArrayList();
        private int x;
        Game3Frame temp;
        static JFrame frame;
        scoreboard scoreb=new scoreboard();

	public Game3Frame()
	{
		setBounds(0,25,1100,575);
                setBackground(Color.black);
                setLayout(null);
                
                addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
                            if(e.getKeyCode()==e.VK_UP)
                                shootlaser();
			}

			public void keyPressed(KeyEvent e) {
				ship.keyPressed(e);
			}
		});


                
	}
        public void addscoreboard(){ getParent().add(scoreb);}
    
	public static void main(String[] args) 
	{
		frame= new JFrame("Asteroid game");
                frame.getContentPane().setLayout(null);
                Game3Frame game= new Game3Frame();
                
                frame.setBounds(120,40, 1100, 600);
                frame.setResizable(false);
		frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
                
                game.addspaceship();
                frame.add(game);
                game.addscoreboard();
               // frame.getContentPane().repaint();
               // game.repaint();
                 frame.setVisible(true);
                game.requestFocus();
                game.createasteroids();

                
	}
        public void addspaceship ()
        {
            ship= new Spaceship(this);
            add(ship);
        }
        public void shootlaser()
        {
             x=ship.getX();
            Thread l= new Thread()
            {
                public void run(){
                    laserbeam pewpew=new laserbeam(x+58,ship.getY());
                    add(pewpew);
                    outerloop:
                    while (true)
                    {
                        pewpew.minusy();
                        try{sleep(3);}catch(Exception e){}
                        pewpew.setLocation(x+58,pewpew.gety());
                        
                        for ( int i=0;i<asteroids.size();i++)
                        {
                            asteroid a=(asteroid)asteroids.get(i);
                            if (pewpew.getBounds().intersects(a.getBounds()))
                                {
                                        scoreb.add();
                                        remove(a);
                                        asteroids.remove(a);
                                        remove(pewpew);
                                        repaint();
                                        break outerloop;
                                }
                            
                        }
                        if (pewpew.gety()<getY())
                        {remove(pewpew);
                            repaint();
                            break;}
                    }
                }
            };
            l.start();
        }
         public void createasteroids()
        {
            temp=this;
                Thread creation = new Thread(){
                    public void run()
                    {
                        
                        while (true)
                        {
                            
                            Thread ast= new Thread(){
                                public void run()
                                {
                                    asteroid a = new asteroid(temp);
                                    temp.add(a);
                                    asteroids.add(a);
                                    while (true)
                                    {
                                        a.plusY();
                                        a.setLocation(a.getx(), a.gety());
                                        try {sleep(10);} catch(Exception e){}
                                        if (a.gety()>temp.getHeight()-70)
                                        {remove(a);
                                        asteroids.remove(a);
                                            repaint();
                                            break;}
                                        if (a.getBounds().intersects(ship.getBounds()))
                                        {
                                            scoreb.minus();
                                            remove(a);
                                            asteroids.remove(a);
                                            repaint();
                                            break;
                                        }
                                    }
                                }
                            };
                            ast.start();
                            try {sleep(750);}catch (Exception e){}
                            }
                        
                        }
                
                };
                creation.start();

        }
         
      
}


class Spaceship extends JLabel implements KeyListener
{
	private int curX  = 100, curY   ;
	private int width = 115,  height = 100;
	private ImageIcon spaceship ;
        private String ImgLoc = "C:\\Users\\AMON\\Desktop\\MUIC_Study_Content\\Programing\\Programing_Paradiagm\\Codings\\NetBeansProjects\\Project_Java\\Images_Graphics\\";

	public Spaceship(Game3Frame game)				
	{ 
                spaceship = new ImageIcon(ImgLoc+"spaceship.png");
                setIcon(spaceship);
                curY=game.getHeight()-height-20;
		setBounds(curX, curY, width, height);
	}
       

	public void keyPressed(KeyEvent e)       { 
           
                if (e.getKeyCode()==e.VK_LEFT)
                {
                    if (curX>getParent().getX()-50)
                    {curX-=10;
                    setLocation(curX,curY);}
                    else
                    {
                        
                    }
                }
                if (e.getKeyCode()==e.VK_RIGHT)
                {
                    if (curX<getParent().getWidth()-70){
                    curX+=10;
                    setLocation(curX,curY);}
                    else
                    {
                        
                    }
                }
              
           
        
        } 
         public void keyReleased(KeyEvent e)
         {
         }
         public void keyTyped(KeyEvent e) {}
         
         public int getX(){return curX;}
         public int getY(){return curY;}

       
}

class scoreboard extends JPanel
{
    private JTextField scoretracker, lifetracker;
    int life=10, score=0;
    public scoreboard()
    {
        setBounds(0,0,550,25);
        scoretracker= new JTextField("" + score, 5);
        scoretracker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scoretracker.setEditable(false);
        lifetracker= new JTextField("" + life, 5);
        lifetracker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lifetracker.setEditable(false);
        add(scoretracker);
        add(lifetracker);
    }
     public int getscore(){return score;}
     public int getlife(){return life;}
     public void add(){score++; scoretracker.setText(""+score);}
     public void minus(){life--;lifetracker.setText(""+life); 
      if(life==0){ JOptionPane.showMessageDialog(this, "You made: "+ score, "Game Over!", JOptionPane.ERROR_MESSAGE);
                    frame.dispose();
      }
     }
    
}

class laserbeam extends JLabel 
{
    private int curX , curY   ;
    private int width = 25,  height = 50;
    private String ImgLoc = "C:\\Users\\AMON\\Desktop\\MUIC_Study_Content\\Programing\\Programing_Paradiagm\\Codings\\NetBeansProjects\\Project_Java\\Images_Graphics\\";
    private ImageIcon laser=new ImageIcon(ImgLoc+"laser.png") ;
    public laserbeam(int x, int y)
    {
        curX=x; curY=y;
        setIcon(laser);
        setBounds(curX,curY,width,height);
    }
    public void minusy(){
        curY--;
    }
    public int gety(){
        return curY;
    }
    
}
class asteroid extends JLabel 
{
    private int curX , curY   ;
    private Random r= new Random();
    private int width = 110,  height = 95;
    private String ImgLoc = "C:\\Users\\AMON\\Desktop\\MUIC_Study_Content\\Programing\\Programing_Paradiagm\\Codings\\NetBeansProjects\\Project_Java\\Images_Graphics\\";    private ImageIcon asteroid=new ImageIcon(ImgLoc+"asteroid.png") ;
    public asteroid(Game3Frame game)
    {
        curX=r.nextInt(game.getWidth()-120);
        curY=game.getY();
        setIcon(asteroid);
        setBounds(curX,curY,width,height);
    }
    public void plusY(){curY++;}
    public int gety(){return curY;}
    public int getx(){return curX;}
    
}