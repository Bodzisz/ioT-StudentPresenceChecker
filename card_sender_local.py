import time
from datetime import datetime, timedelta
import paho.mqtt.client as mqtt
import atexit

isReaded = False
card_rfid = ""

# The terminal ID - can be any string.
terminal_id = "T0"
# The broker name or IP address.
broker = "localhost"
# broker = "127.0.0.1"
# broker = "10.0.0.1"

# The MQTT client.
client = mqtt.Client()

def call_worker(worker_name):
    client.publish("test/rfid", worker_name)

def connect_to_broker():
    # Connect to the broker.
    client.connect(broker)
    # Send message about conenction.
    call_worker("Client connected")
    print("Client connected")


def disconnect_from_broker():
    # Send message about disconenction.
    call_worker("Client disconnected")
    # Disconnet the client.
    client.disconnect()
    print("DISCONNECTED")

def rfidRead():
    global isReaded, card_rfid
    card_rfid = input()
    return True


if __name__ == "__main__":
    # buzzer(False)
    connect_to_broker()
    atexit.register(disconnect_from_broker)
    buzzed = False
    buzzing = False
    start_time = datetime.now()
    buzz_time = 1
    reads=[False,False, False, False, False, False]
    while True:
        read = rfidRead()
        reads.append(read)
        reads.pop(0)
        #print(read)
        if True in reads and not buzzed:
            curr_date = datetime.now()
        
            start_time = datetime.now()
            message = f'{card_rfid}'
            print(message)
            call_worker(str(card_rfid))
