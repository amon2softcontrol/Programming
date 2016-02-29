try:
    import urllib.request as urllib2
except ImportError:
    import urllib2
import requests,json
import time
import kivy
kivy.require('1.0.9')
from kivy.app import App
from kivy.properties import NumericProperty
from kivy.uix.button import Button

#buildozer

data1 = []
data = ""
path = "LineLog.txt"
logIt = open(path,'a+')

def fillData1(attr):
    global data1,data
    data1.append(data[attr])

def LineProfile():

    global data
    
    headers = {
        'Authorization': 'Bearer VLcoQzsaY1EDhPbUaFsux4vCWjKBFMtVrCQib8B5Po38kURteqoL094//4QiHYUc5AXBj7k3LpKWTMLLhvbNdk2BRNzTC+ZVs0qj3zcyvJxTjxh837wE8yAZ3MPD1dx6fFeI5mew9jTwEC2txM3k0WaJVKly+BFdfGdzL73njOw=',
        }
    wp = requests.get('https://api.line.me/v1/profile',headers=headers)

    data = wp.json() ##data = wp.text  ##data = json.load(wp.text)
        
    fillData1('displayName')
    fillData1('statusMessage')
    fillData1('pictureUrl')
    fillData1('mid')

    data = "\n\t%s\'s Profile printed on kivy GUI.\n Current Time: %s"%(data1[0],time.asctime())
    printWrite('\n'*5)
    printWrite('Name: ',data1[0])
    printWrite('Status: ',data1[1])
    printWrite('Picture URL: ',data1[2])
    printWrite('MID: ',data1[3])
    printWrite('\n'*5)
    


def printWrite(text,*param):
    global data
    if param:
        a = "%s %s\n\n"%(text,param[0])
        logIt.write(a)
##        a = text.join(param) #or a = "\t%s %s\n\n"%(text,param)
    else:
        a = text
    data += a
    
class TestApp(App):
    count = NumericProperty(0)
    def build(self):
        self.bind(count=self.update_widget)
        self.btn = Button(text=data,font_size='20dp')
        self.btn.bind(on_press=self.increment)
        return self.btn

    def increment(self, button):
        self.count +=2

    def update_widget(self,app,value):
        self.btn.text = 'Hello {}'.format(self.count)

        
    
if __name__ == '__main__':
    LineProfile()
    print('\t\t...you have 2 seconds')
    logIt.close()
    TestApp().run()
    time.sleep(2)
##    exit()
    
    
    
    
    
##
##url = 'https://api.line.me/v1/profile'
##req = urllib2.Request(url, data, {'Content-Type': 'application/json'})
##f = urllib2.urlopen(req)
##for x in f:
##    print(x)
##f.close()



