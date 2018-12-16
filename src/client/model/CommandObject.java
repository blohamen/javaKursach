package client.model;

import java.io.Serializable;

public class CommandObject<T> implements Serializable {
    private String command;
    private T object;

    public CommandObject(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public CommandObject(String command, T object) {
        this.command = command;
        this.object = object;
    }
}
