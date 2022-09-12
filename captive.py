import requests
import ipaddress
import sched, time
import daemon
import configparser
from appdirs import *

config = configparser.ConfigParser()
appname = "ResidsssConnectR76"
appauthor = "Rhadamanthe76KIN220"

s = sched.scheduler(time.time, time.sleep)

dir = user_data_dir(appname, appauthor)
config_file = dir + "/config.ini"
credentials = dir + "/credentials.ini"

try:
    with open(credentials, "r") as credentials_file:
        content = credentials_file.read()
        username, password = content.split(":")

    config.read(config_file)
    interval = config["DEFAULT"]["interval"]


except FileNotFoundError:
    import os
    os.makedirs(dir, exist_ok=True)

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

    config["DEFAULT"] = {"interval":interval}
    with open(config_file, 'w') as configfile:
        config.write(configfile)


def check_and_connect():
    google = 'http://google.com'

    r = requests.get(google)
    ip_or_domain = r.url.split(":")[1].strip("/")

    try:
        # http://10.254.0.254:1000/
        ipaddress.ip_address(ip_or_domain)

        url, magic = r.url.split("?")
        url = url.split("fgtauth")[0]

        content = {"magic":magic, "username":username, "password":password}
        connect = requests.post(url, data=content)


    except ValueError as e:
        print("still  connected")
        # whait and restart

    s.enter(int(interval), 1, check_and_connect)

with daemon.DaemonContext():
    s.enter(1, 1, check_and_connect)
    s.run()
