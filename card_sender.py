import time
import RPi.GPIO as GPIO
from config import *  # pylint: disable=unused-wildcard-import
from mfrc522 import MFRC522
from datetime import datetime, timedelta
import board
import neopixel
import paho.mqtt.client as mqtt
import atexit

isReaded = False
MIFAREReader = MFRC522()
pixels = neopixel.NeoPixel(
        board.D18, 8, brightness=1.0/32, auto_write=False)
card_rfid = ""

# The terminal ID - can be any string.
terminal_id = "T0"
# The broker name or IP address.
broker = "10.108.33.23"
# broker = "127.0.0.1"
# broker = "10.0.0.1"

# The MQTT client.
client = mqtt.Client()

def call_worker(worker_name):
    client.publish("test/rfid", worker_name + "." + terminal_id,)

def connect_to_broker():
    # Connect to the broker.
    client.connect(broker, keepalive=60)
    client.loop_start()
    # Send message about conenction.
    call_worker("Client connected")


def disconnect_from_broker():
    # Send message about disconenction.
    call_worker("Client disconnected")
    # Disconnet the client.
    client.disconnect()
    client.loop_stop()
    print("DISCONNECTED")


def ledGreen():
    pixels.fill((0, 255, 0))

def ledRed():
    pixels.fill((255, 0, 0))

def buzzer(state):
    GPIO.output(buzzerPin, not state)

def rfidRead():
    global isReaded, MIFAREReader, card_rfid
    (status, TagType) = MIFAREReader.MFRC522_Request(MIFAREReader.PICC_REQIDL)
    if status == MIFAREReader.MI_OK:
        (status, uid) = MIFAREReader.MFRC522_Anticoll()
        if status == MIFAREReader.MI_OK:
            if not isReaded:
                num = 0
                for i in range(0, len(uid)):
                    num += uid[i] << (i*8)
                #print(f"Card read UID: {uid} > {num}")
                card_rfid = num
                isReaded = True
            return True
        else:
            isReaded = False
    else:
        isReaded = False
    return False


if __name__ == "__main__":
    # buzzer(False)
    connect_to_broker()
    atexit.register(disconnect_from_broker)
    buzzed = False
    buzzing = False
    start_time = datetime.now()
    buzz_time = 1
    reads=[False,False, False, False, False, False]
    ledRed()
    while True:
        read = rfidRead()
        reads.append(read)
        reads.pop(0)
        #print(read)
        pixels.show()
        if True in reads and not buzzed:
            curr_date = datetime.now()
            buzzed = True
            buzzing = True
            start_time = datetime.now()
            buzzer(True)
            message = f'{card_rfid}'
            print(message)
            call_worker(str(card_rfid) + " " + str(start_time))
            #print(f"Date: {curr_date} ")
        if buzzing:
            if start_time + timedelta(seconds=buzz_time) < datetime.now():
                buzzer(False)
                buzzing = False
        if True not in reads and buzzed:
            buzzed = False
        if True not in reads:
            ledRed()
        else:
            ledGreen()
