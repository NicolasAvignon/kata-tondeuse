package com.example.tondeuse.controller.model;

import com.example.tondeuse.service.model.Position;

public class DonneesTondeuse {
    private Position position;
    private String actions;

    public DonneesTondeuse(Position position, String actions) {
        this.position = position;
        this.actions = actions;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
}
