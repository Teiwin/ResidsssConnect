import configparser
import re
import sched
import time

import daemon
import requests
from appdirs import user_data_dir

config = configparser.ConfigParser()
# noinspection SpellCheckingInspection
appname = "ResidsssConnectR76"
appauthor = "Rhadamanthe76KIN220"

s = sched.scheduler(time.time, time.sleep)

directory = user_data_dir(appname, appauthor)
config_file = directory + "/config.ini"
credentials = directory + "/credentials.ini"

try:
    with open(credentials, "r") as credentials_file:
        content = credentials_file.read()
        username, password = content.split(":")

    config.read(config_file)
    interval = config["DEFAULT"]["interval"]

except FileNotFoundError:
    import os

    os.makedirs(directory, exist_ok=True)

    # credentials
    username = input("username: ")
    password = input("password: ")
    password_conf = input("repeat password: ")
    while password != password_conf:
        print("passwords do not match!")
        password = input("password: ")
        password_conf = input("repeat password: ")

    with open(credentials, "w") as credentials_file:
        credentials_file.write(username + ":" + password)

    # config
    interval = input("connectivity check period (sec): ")
    while not interval.isnumeric():
        print("please enter a correct number of sec!")
        interval = input("connectivity check period (sec): ")

    config["DEFAULT"] = {"interval": interval}
    with open(config_file, 'w') as configfile:
        config.write(configfile)


def check_and_connect():
    google = 'http://google.com'

    r = requests.get(google)

    match = re.search(r'http://(\d+\.\d+\.\d+\.\d+):?\d*/fgtauth\?([a-z0-9]+)', r.text)

    if match:
        # http://10.254.0.254:1000/

        magic = match.group(2)
        url = match.group(1)

        data = {"magic": magic, "username": username, "password": password}
        requests.post(url, data=data)

    else:
        print("still  connected")
    # wait and restart
    s.enter(int(interval), 1, check_and_connect)


with daemon.DaemonContext():
    s.enter(1, 1, check_and_connect)
    s.run()
