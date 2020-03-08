import communicator as comm
import processor
import cv2
import rpcp
import led as led

IP = "10.26.79.10"
PORT = 8089

def handle_request(data, sock, frame):
    req = comm.get_first_ascii_char(data)
    if req in requests_functions:
        print("entered func")
        return requests_functions[req](sock, frame)
    return [-1]


def handle_reflector(sock, frame):
    values = processor.detect_reflectors(frame)
    values = rpcp.rpcp_protocol(values)
    comm.send_data(sock, values)

#dict must be entered after handleRequst function
requests_functions = {"R": handle_reflector}
def main():
    led.red()

    source = 0
    cap = cv2.VideoCapture(source)
    if cap is None or not cap.isOpened():
           print('Error: unable to open video source: ', source)
           exit(0)

    led.orange()

    sock = comm.initialize_connection_server(IP, PORT)

    led.green()

    while True:
        try:
            received_data = comm.recv_data(sock)
        except Exception as msg:
            print("Warning: data receiving failed")
            continue

        #processing
        ret, frame = cap.read()
        returned_values = handle_request(received_data, sock, frame)
        #cv2.imshow("cam", frame)

        #send_answer
        answer = rpcp.rpcp_protocol(returned_values)
        try:
            print(answer)
            send_data(sock, answer)
            print("sent data")
        except Exception as msg:
            print("Warning: data sending failed")
            continue

    cap.release()

if __name__ == '__main__':
    main()