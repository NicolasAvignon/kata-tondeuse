package com.example.tondeuse.controller.model;

import com.example.tondeuse.service.model.Grille;
import com.example.tondeuse.service.model.Position;

import java.util.List;

public class DonneesFichier {
    private Grille grille;

    private List<DonneesTondeuse> donneesTondeuse;

    public DonneesFichier(Grille grille, List<DonneesTondeuse> donneesTondeuse) {
        this.grille = grille;
        this.donneesTondeuse = donneesTondeuse;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public List<DonneesTondeuse> getDonneesTondeuse() {
        return donneesTondeuse;
    }

    public void setDonneesTondeuse(List<DonneesTondeuse> donneesTondeuse) {
        this.donneesTondeuse = donneesTondeuse;
    }
}
