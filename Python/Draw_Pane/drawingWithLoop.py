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
def main():
    panel = Drawing(300,300)
    panel.set_background('black')

    for i in range(0,200,20):
        panel.canvas.create_rectangle(50+i,220-i,250,240-i,outline='red',fill='yellow')
    
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
        
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Check Caller ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#   
if __name__ == '__main__':
   main()
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ xxxxxXxxXxxxxxxx ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
