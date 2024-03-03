package com.example.tondeuse.service.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Action {
    Droite('D'),
    Gauche('G'),
    Avance('A');

    private Character code;

    private static final Map<Character, Action> ACTIONS = Arrays.stream(Action.values())
            .collect(Collectors.toMap(Action::getCode, Function.identity()));

    Action(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }

    public static Optional<Action> findValueOf(Character code) {
        if (code == null || ACTIONS.get(code) == null) {
            return Optional.empty();
        }
        return Optional.of(ACTIONS.get(code));
    }
}
