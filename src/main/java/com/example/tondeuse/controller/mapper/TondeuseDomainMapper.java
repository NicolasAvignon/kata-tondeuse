package com.example.tondeuse.controller.mapper;

import com.example.tondeuse.controller.model.DonneesFichier;
import com.example.tondeuse.controller.model.DonneesTondeuse;
import com.example.tondeuse.service.model.Grille;
import com.example.tondeuse.service.model.Orientation;
import com.example.tondeuse.service.model.Position;
import com.example.tondeuse.service.model.Tondeuse;

import java.util.*;
import java.util.stream.Collectors;

public class TondeuseDomainMapper {
    public static final TondeuseDomainMapper MODELS_MAPPER = new TondeuseDomainMapper();

    private TondeuseDomainMapper() {
    }


    public DonneesFichier toDonneesFichier(String donnees) {
        String[] lignes = donnees.split("\r\n");
        Iterator<String> lignesIterator = Arrays.stream(lignes).iterator();
        Grille grille = toGrille(lignesIterator.next().split(" "));
        ArrayList<DonneesTondeuse> donneesTondeuses = new ArrayList<>();
        while(lignesIterator.hasNext()) {
            Position position = toPosition(lignesIterator.next().split(" "));
            donneesTondeuses.add(new DonneesTondeuse(position, lignesIterator.next()));

        }

        return new DonneesFichier(grille, donneesTondeuses);
    }

    private Position toPosition(String[] s) {
        Optional<Orientation> orientation = Orientation.findValueOf(s[3]);
        return new Position(Integer.valueOf(s[1]), Integer.valueOf(s[2]),orientation.get());
    }

    private Grille toGrille(String[] s) {
        return new Grille(Integer.valueOf(s[1]), Integer.valueOf(s[2]));
    }

    public String toResponse(List<Tondeuse> tondeuses) {
        return tondeuses.stream().map(tondeuse -> {
            return tondeuse.getPosition().toString();
        }).collect(Collectors.joining(" "));
    }
}
