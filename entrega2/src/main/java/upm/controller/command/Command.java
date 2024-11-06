package upm.controller.command;

public abstract class Command {
    public abstract String apply(String[] params);
    public abstract String toStringCommand();
}

