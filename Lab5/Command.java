package command;

import exception.ResourceException;

public interface Command {
    void execute() throws ResourceException;
}