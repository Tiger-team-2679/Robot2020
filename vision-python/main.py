import communicator as comm
import processor
import cv2
import rpcp

IP = "10.10.2.8"
PORT = 8089

def reset_main():
    sock = comm.initialize_connection_server(IP, PORT)
    return sock


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
    camera = cv2.VideoCapture(0)
    sock = reset_main()
    while True:
        try:
            recieved_data = comm.recv_data(sock)
        except Exception as msg:
            print("problem with recieving data")
            reset_main()
            continue

        #processing
        ret, frame = camera.read()
        returned_values = handle_request(recieved_data, sock, frame)
        #cv2.imshow("cam", frame)


        #send_answer
        answer = rpcp.rpcp_protocol(returned_values)
        try:
            print(answer)
            send_data(sock, answer)
            print("sent data")
        except Exception as msg:
            reset_main()
            print("couldn't send data")

    cap.release()




if __name__ == '__main__':
    main()