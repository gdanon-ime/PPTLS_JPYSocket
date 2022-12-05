import random
import jpysocket
import socket

# Variaveis do servidor
HOST = 'localhost'
PORT = 12345
OptionsList = ["Pedra", "Papel", "Tesoura", "Lagarto", "Spock"]

# Definindo a conexao do socket
Psoc = socket.socket()
Psoc.connect((HOST,PORT))
print("Socket Is Connected....")

loop = 0
while(loop < 15):
    # Enviando a mensagem
    msgsend = jpysocket.jpyencode(str(random.sample(OptionsList, 1)[0]))
    Psoc.send(msgsend)

    # Recebendo a mensagem
    msgrecv = Psoc.recv(1024)
    msgrecv = jpysocket.jpydecode(msgrecv)
    print("Game: ",msgrecv)
    loop = loop + 1


msgrecv = Psoc.recv(1024)
msgrecv = jpysocket.jpydecode(msgrecv)
print(msgrecv)

msgrecv = Psoc.recv(1024)
msgrecv = jpysocket.jpydecode(msgrecv)
print(msgrecv)

Psoc.close()
print("Connection Closed.")