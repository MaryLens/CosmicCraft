package com.example.cosmiccraft.patterns.behavioral;

public class AdminCommandInvoker {
    private AdminCommand command;

    public void setCommand(AdminCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
