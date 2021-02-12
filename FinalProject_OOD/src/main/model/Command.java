package main.model;

public interface Command {
    void execute();
    void undo();
}
