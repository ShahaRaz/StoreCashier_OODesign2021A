package main.model.store;

public interface Command {
    void execute();
    void undo();
}
