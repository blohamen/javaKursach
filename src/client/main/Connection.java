package client.main;

import server.main.ServerConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection extends ServerConfig {
    protected Socket clientSocket;
    protected ObjectOutputStream objectOutputStream;
    protected ObjectInputStream objectInputStream;
    protected void connect() {
        try {
            clientSocket = new Socket(SERVER_IP, PORT);
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void closeConnection(){
        try {
            clientSocket.close();
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
