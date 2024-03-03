package com.example.tondeuse.service.model;

public class Tondeuse {

    private Position position;
    private Grille grille;

    public Tondeuse(Position position, Grille grille) {
        this.position = position;
        this.grille = grille;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }


}
