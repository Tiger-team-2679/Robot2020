import cv2
import numpy as np
import socket
import sys
import rpcp


def initialize_connection_client(ip, port):
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((ip, port))
    print("connected")
    return client_socket


def initialize_connection_server(host_ip, port):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print('Socket created')
    sock.bind((host_ip, port))
    print('Socket bind complete')
    sock.listen(10)
    print('Socket now listening')
    conn, addr = sock.accept()
    print("connected")
    return conn


def recv_data(sock):
    data = sock.recv(1024)
    return data.decode()


def get_first_ascii_char(data):
    for x in data:
        if ord(x) >= ord('A') and ord(x) <= ord('Z'):
            return x


def send_data(client_socket, data):    # Serialize frame
    client_socket.sendall(data.encode())




