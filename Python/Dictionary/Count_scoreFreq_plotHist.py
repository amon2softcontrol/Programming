from drawing import *

count = [0]*101

def main0():
    global count
    score = open("score1.txt")
    space = 0
    info = "scores from score1.txt"

    panel = Drawing(350,300)
    panel.set_background('black')
    panel.canvas.create_line(30,20,30,260,fill="brown")
    panel.canvas.create_line(30,260,320,260,fill="brown")
    panel.canvas.create_text(150,40,text=info,fill="skyblue")
    

    find_number(score)
    
    for i in range(90,101):
        draw(panel,count,i,space)
        space = space+25
            
def draw(panel,count,i,j):
        panel.canvas.create_rectangle(30+j,260-(25*count[i]),30+(j+25),260,outline='red',fill='yellow')
        panel.canvas.create_text(30+(j+10),280,text=i,fill="white")
        panel.canvas.create_text(30+(j+5),260-(25*count[i])-10,text=count[i],fill="white")
        if i<100:
            panel.canvas.create_text(20,260-(25*(i-90))-10,text=i-90,fill="white")

def find_number(score):
    global count
    for line in score:
        a = line.split()
        for i in a:
             count[int(i)]=count[int(i)]+1
            
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Check Caller ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#   
if __name__ == '__main__':
   main0() 
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
