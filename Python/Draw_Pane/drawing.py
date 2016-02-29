import atexit
import sys
import time

if (sys.version_info >= (3,0)):
    from tkinter import *
else:
    from Tkinter import *

'''
Author : Marty Stepp
Version: 2009/10/21
'''

class Drawing(Tk):
    def __init__(self, width=500, height=500, background="white"):
        Tk.__init__(self)
        self.width = width
        self.height = height
        self.title("Drawing")
        self.canvas = Canvas(self, width = width + 1, height = height + 1)
        self.canvas["bg"] = background
        self.canvas.pack({"side": "top"})
        self.wm_resizable(0, 0)
        self.update()
    
        # hack - runs mainloop on exit if not interactive
        if not hasattr(sys, 'ps1'):
            self.install_mainloop_hack()

    def install_mainloop_hack(self):
        # for everything but idle
        atexit.register(self.mainloop)

        # hack just for idle:
        # flush_stdout is called immediately after code execution - intercept
        # this call, and use it to call mainloop
        try:
            import idlelib.run
            def mainloop_wrap(orig_func):
                def newfunc(*a, **kw):
                    self.mainloop()
                    idlelib.run.flush_stdout = orig_func
                    return orig_func(*a, **kw)
                return newfunc
            idlelib.run.flush_stdout = mainloop_wrap(idlelib.run.flush_stdout)
        except ImportError:
            pass

    def clear(self):
        self.canvas.create_rectangle(0, 0, self.width + 2, self.height + 2, \
                outline=self.canvas["bg"], fill=self.canvas["bg"])
    
    def set_background(self, color):
        self.canvas["bg"] = color
    
    def sleep(self, ms):
        try:
            self.update()
            time.sleep(ms / 1000.0)
            self.update()
        except Exception:
            pass
