package com.example.tondeuse.service.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Enumeration pour l'orientation => orientation initiale, orientation gauche, orientation droite
 */
public enum Orientation {

    North("N", "W", "E"),
    South("S", "E", "W"),
    West("W", "S", "N"),
    East("E", "N", "S");

    private final String initiale;
    private final String gauche;
    private final String droite;

    private static final Map<String, Orientation> INITIALE = Arrays.stream(Orientation.values())
            .collect(Collectors.toMap(Orientation::getInitiale, Function.identity()));

    Orientation(String initiale, String gauche, String droite) {
        this.initiale = initiale;
        this.gauche = gauche;
        this.droite = droite;
    }


    public String getInitiale() {
        return initiale;
    }

    public String getGauche() {
        return gauche;
    }

    public String getDroite() {
        return droite;
    }

    public Orientation setGauche() {
        return setValueOf(gauche);
    }

    public Orientation setDroite() {
        return setValueOf(droite);
    }

    public Orientation setValueOf(String initiale) {
        Optional<Orientation> firstOrientation = Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getInitiale().equals(initiale))
                .findAny();

        return firstOrientation.orElse(this);
    }

    public static Optional<Orientation> findValueOf(String initiale) {
        if (initiale == null || INITIALE.get(initiale) == null) {
            return Optional.of(Orientation.North);
        }
        return Optional.of(INITIALE.get(initiale));
    }


}
