from drawing import *
import sys
import contextlib
import random

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Global Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
panel = []
x_axis,y_axis,mode,speed,size = 0,0,0,5,2
stop,width,height = None,None,None
color,randomHouse = [],[]
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#


#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Drawer Functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
def draw_Init(sizeX,sizeY,bg,m):
    global panel,mode,width,height
    width = sizeX
    height = sizeY
    mode = m
    panel = Drawing(sizeX,sizeY)
    panel.set_background(bg)

    if mode !=4:
        if mode != 3: panel.bind('<KeyPress>',onKeyPress)
        else : drawRandom_Animation(panel)
    exercise_4_2(panel,sizeX,sizeY,bg)
    
def drawUpdate_imagePane(panel):
    #Initially x,y axes values are Zero. So when called from drawStatic or , just a
    #non-movable image is displayed
    panel.canvas.create_rectangle(5+x_axis,5+y_axis, 36+x_axis, 276+y_axis,outline="red",fill="yellow")
    panel.canvas.create_polygon(20+x_axis, 50+y_axis, 20+x_axis, 100+y_axis, 70+x_axis, 75+y_axis,95+x_axis,115+y_axis,fill="blue")
    panel.canvas.create_line(10+x_axis, 120+y_axis, 20+x_axis, 160+y_axis, 30+x_axis, 120+y_axis, 40+x_axis, 175+y_axis)
    panel.canvas.create_rectangle(95+x_axis, 120+y_axis, 395+x_axis,3+y_axis)

def drawRandom_Moving(panel):
    #Initially x,y axes values are Zero. So when called from drawInit , just a
    #non-movable image is displayed
    print('Jai Radhekrishna')
    for i in range(5):
        randomPositionSize()
        draw_JRK(i)
        panel.sleep(80)
        

def drawRandom_Animation(panel):
    while(1):
        Var1  = randomPositionSize(1)
        Var2  = randomPositionSize(1)
        Var3  = randomPositionSize(1)
        Var4  = randomPositionSize(1)
        Var5  = randomPositionSize(1)

        for i in range(40):
            panel.clear()
            draw_JRK((i%5),(i*Var1[2])+Var1[0],(i*Var1[2])+Var1[1],Var1[3],i)
            draw_JRK((i%5),(-(i*Var2[2]))+Var2[0],(i*Var2[2])+Var2[1],Var2[3],i)
            draw_JRK((i%5),(i*Var3[2])+Var3[0],(i*Var3[2])+Var3[1],Var3[3],i)
            draw_JRK((i%5),(-(i*Var4[2]))+Var4[0],(i*Var4[2])+Var4[1],Var4[3],i)
            panel.sleep(80)
    
def draw_JRK(i,*didi):
    if didi:
        print(didi)
        x_axis = didi[0]
        y_axis = didi[1]
        size = didi[2]
  
    panel.canvas.create_rectangle((5*size)+x_axis,(5*size)+y_axis, (36*size)+x_axis, (276*size)+y_axis,outline=color[i],fill=color[i+1])
    panel.canvas.create_polygon((20*size)+x_axis, (50*size)+y_axis, (20*size)+x_axis, (100*size)+y_axis, (70*size)+x_axis, (75*size)+y_axis,(95*size)+x_axis,(115*size)+y_axis,fill=color[i+2])
    panel.canvas.create_line((10*size)+x_axis, (120*size)+y_axis, (20*size)+x_axis, (160*size)+y_axis, (30*size)+x_axis, (120*size)+y_axis, (40*size)+x_axis, (175*size)+y_axis)
    panel.canvas.create_rectangle((95*size)+x_axis, (120*size)+y_axis, (395*size)+x_axis,(3*size)+y_axis)
    
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
    


#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Key Listener ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
def onKeyPress(event):

    global x_axis,y_axis,panel,speed,stop
            
    try:
        while True:
            ch = event.char
            if ch == chr(68):    #if pressed 'D' move left
                x_axis = x_axis + speed
                x_axis = adjust(x_axis,width)                    
                panel.set_background("red")
                stop = False
                print([ch,x_axis,'Background changed to Red'])
                
            elif ch == chr(65): #if pressed 'A' move right
                x_axis = x_axis - speed
                x_axis = adjust(x_axis,width)                    
                panel.set_background("orange")
                stop = False
                print([ch,x_axis,'Background changed to Orange']);
                
            elif ch == chr(87):  #if pressed 'W' move up
                y_axis = y_axis - speed
                y_axis = adjust(y_axis,height)                    
                panel.set_background("brown")
                stop = False
                print([ch,y_axis,'Background changed to Brown']);
                
            elif ch == chr(83): #if pressed 'S' move down
                y_axis = y_axis + speed
                y_axis = adjust(y_axis,height)                    
                panel.set_background("green")
                stop = False
                print([ch,y_axis,'Background changed to Green']);
                
            elif ch == chr(32): #if pressed ' ' go to start point
                print('space entered')
                panel.set_background("white")
                x_axis = 0
                y_axis = 0
                stop = True

            elif ch == chr(93): #if pressed ']' increase speed
                speed = speed + 3
                if speed > 112:
                    speed = 112
                print('speed increased to',speed)
                break;

            elif ch == chr(91): #if pressed '[' decrease speed
                speed = speed - 3
                if speed < 0 :
                    speed = 0
                print('speed decreased to',speed)
                break;
            if mode == 1 and not(stop):
                panel.clear()
                drawUpdate_imagePane(panel)
            elif mode == 2 and not(stop):
                panel.clear()
                drawRandom_Moving(panel)
               
            panel.sleep(10)
            
        print('\nout of while loop\n')
            
    except (KeyboardInterrupt, EOFError):
        pass
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#


def adjust(x,y):
    if x>y:
        return 0
    elif x<0:
        return y
    else:
        return x

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Random functions ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
def randomPositionSize(*gg):

    global x_axis,y_axis,speed,size,stop,color,randomHouse

    randomHouse.clear()
    x_axis = random.randint(1,300)
    y_axis = random.randint(1,300)
    speed = random.randint(1,5)
    size = random.randint(1,2)
    i = random.randint(1,5)

    if i == 1:
        color = ["yellow","dark slate blue","coral","yellow green","navajo white","medium orchid","dark salmon"]
    if i == 2:
        color = ["dark slate blue","coral","yellow green","navajo white","dark olive green","hot pink","purple"]
    if i == 3:
        color = ["dark slate blue","coral","yellow green","dark olive green","navajo white","AntiqueWhite4","bisque2"]
    if i == 4:
        color = ["yellow green","navajo white","dark olive green","dark slate blue","coral","PeachPuff4","royal blue"]
    if i == 5:
        color = ["dark slate blue","dark olive green","yellow green","navajo white","coral","dark slate blue","snow"]

    if gg:
        randomHouse.append(x_axis)
        randomHouse.append(y_axis)
        randomHouse.append(speed)
        randomHouse.append(size)
        return randomHouse
    
   
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#





#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Local Main ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
def main_draw(sizeX,sizeY,bg):
    global panel
    panel = Drawing(sizeX,sizeY);
    panel.set_background(bg);
    panel.canvas.create_line(100, 300,200,200,fill="blue")
    panel.sleep(800)
    panel.canvas.create_line(200, 200,300,300,fill="blue")
    panel.sleep(800)
    panel.canvas.create_line(140, 260,260,260,fill="magenta")
    panel.sleep(800)
    panel.clear()
   
    for i in range(20):
        panel.canvas.create_polygon(20 * i, 50, 20 * i,100,20* i + 50, 75,fill="green")
        panel.canvas.create_line(50, 150-(i*i), 100, 100-(i*i), 150, 150-(i*i),fill="blue")
        panel.canvas.create_line(70, 130-(i*i), 130, 130-(i*i),fill="magenta")
        panel.sleep(80)
        panel.clear()

    for i in range(20):
        panel.canvas.create_line(5*i, 15*i,10*i,10*i,fill="blue")
        panel.canvas.create_line(sizeX-(10*i)+75, sizeY-(10*i)+75,sizeX-(15*i)+75,sizeY-(15*i)+75,fill="brown")
        panel.canvas.create_line(sizeX-(7*i)-21, 13*i, sizeX-(13*i)-21,13*i,fill="red")
        panel.sleep(80)
        if i < 19:
            panel.clear()
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Circle Exercise ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#   
def exercise_4_2(panel,x,y,color):
    x = x/2
    y = y/2
    center_x = x
    while True:
        panel.canvas.create_oval(x-150,y-150,x+150,y+150,fill=color)
        doIt(panel,x,y,center_x)
        panel.sleep(30)
        panel.clear()

def doIt(panel,x,y,center_x):
    while True:
        panel.canvas.create_line(x-1,y-1,x+2,y+2,fill="blue")
        hor_ver = random.randint(1,2)
        if hor_ver ==1:
            xx = random.randint(-3,3)
            x = x + xx
        else :
            yy = random.randint(-3,3)
            y = y + yy
        if checkBorder(x,y,center_x):
            break;
        panel.sleep(1)
    
def checkBorder(n,m,x):
    base = abs(n-x)
    height = abs(m-x)
    border = ((base**2) + (height**2))**0.5
    print('base = %d \t height = %d \t border = %d '%(base,height,border))
    if border > (x-10):
        return True        
    return False;

#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
        
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Check Caller ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#   
if __name__ == '__main__':
    print("This works on 400x400 pane: effect finished ", main_draw(400,400,"white"))
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
