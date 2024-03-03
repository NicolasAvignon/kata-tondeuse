package com.example.tondeuse.service;

import com.example.tondeuse.service.model.Action;
import com.example.tondeuse.service.model.Grille;
import com.example.tondeuse.service.model.Position;
import com.example.tondeuse.service.model.Tondeuse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class TondeuseService {
    public Tondeuse calculerTrajectoire(Position positionInitiale, Grille grille, String actions) {
        Tondeuse tondeuse = new Tondeuse(positionInitiale, grille);

        List<Character> actionsCharacter = actions.chars().mapToObj(c -> (char) c).toList();
        actionsCharacter.forEach(actionCharacter -> {
            Optional<Action> valueOf = Action.findValueOf(actionCharacter);
            valueOf.ifPresent(action -> changerTrajectoire(tondeuse, action));
        });

        return tondeuse;
    }

    private Consumer<? super Action> changerTrajectoire(Tondeuse tondeuse, Action action) {
        switch (action) {
            case Gauche -> tondeuse.getPosition().rotationGauche();
            case Droite -> tondeuse.getPosition().rotationDroite();
            case Avance -> tondeuse.getPosition().avancer(tondeuse.getGrille());
        }
        return null;
    }
}
