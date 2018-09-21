package com.buscame.oncor.buscame.Profile.Model;


public class Command extends Generic {

   private  String commandCodeActive;
   private  String commandCodeDesactive;
   private  boolean active;
   private String image;

    public Command() {
    }

    public String getCommandCodeActive() {
        return commandCodeActive;
    }

    public void setCommandCodeActive(String commandCodeActive) {
        this.commandCodeActive = commandCodeActive;
    }

    public String getCommandCodeDesactive() {
        return commandCodeDesactive;
    }

    public void setCommandCodeDesactive(String commandCodeDesactive) {
        this.commandCodeDesactive = commandCodeDesactive;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
