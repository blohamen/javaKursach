package server.main;

import client.model.CommandObject;
import client.model.User;
import server.database.DatabaseHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private DatabaseHandler databaseHandler;

    public ClientHandler(Server server, Socket socket) {
        try {
            databaseHandler = new DatabaseHandler();
            this.socket = socket;
            this.server = server;
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    CommandObject<?> clientCommand;
                    while (true) {
                        try {
                            clientCommand = (CommandObject<?>) objectInputStream.readObject();
                            System.out.println(clientCommand.getCommand());
                            handleActions(clientCommand);
                            socket.close();
                        } catch (IOException e) {
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleActions(CommandObject<?> clientCommand) throws SQLException, ClassNotFoundException, IOException {
        switch (clientCommand.getCommand()) {
            case ServerConfig.AUTHORIZATION: {
                User userCreds = (User) clientCommand.getObject();
                objectOutputStream.writeObject(
                        databaseHandler.authorizate(
                                userCreds.getLogin(),
                                userCreds.getPassword()
                        )
                );
                break;
            }
            case ServerConfig.REGISTRATION : {
                User userCreds = (User) clientCommand.getObject();
                objectOutputStream.writeObject(
                        databaseHandler.insertEmployee(userCreds)
                );
                break;
            }

            case ServerConfig.GET_ALL_USERS: {
                objectOutputStream.writeObject(
                        databaseHandler.getAllUsers()
                );
                break;
            }
        }
    }
}