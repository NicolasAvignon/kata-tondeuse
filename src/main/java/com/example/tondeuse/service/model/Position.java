package com.example.tondeuse.service.model;

public class Position {

    private int x;
    private int y;
    private Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public Position() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void rotationGauche() {
        orientation = orientation.setGauche();
    }

    public void rotationDroite() {
        orientation = orientation.setDroite();
    }

    public void avancer(Grille grille) {
        switch (orientation) {
            case North:
                if (y < grille.getY())
                    y += 1;
                break;
            case West:
                if (x > 0)
                    x -= 1;
                break;
            case South:
                if (y > 0)
                    y -= 1;
                break;
            case East:
                if (x < grille.getX())
                    x += 1;
                break;
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation.getInitiale();
    }
}
