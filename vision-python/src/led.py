from sense_hat import SenseHat

sense = SenseHat()

def red():
    sense.clear((255,0,0))

def green():
    sense.clear((0,255,0))

def orange():
    sense.clear((255,165,0))