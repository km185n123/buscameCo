package com.buscame.oncor.buscame.Profile.Model;

import java.util.List;

public class Model extends Generic {

  private  List<Command> commands;

    public Model() {
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
