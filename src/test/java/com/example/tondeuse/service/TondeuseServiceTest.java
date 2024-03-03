package com.example.tondeuse.service;

import com.example.tondeuse.service.model.Grille;
import com.example.tondeuse.service.model.Orientation;
import com.example.tondeuse.service.model.Position;
import com.example.tondeuse.service.model.Tondeuse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TondeuseServiceTest {

    private TondeuseService tondeuseService;

    @BeforeEach
    void setup() {
        tondeuseService = new TondeuseService();
    }

    @Test
    void devrait_retourner_tondeuse_vide() {
        //Given
        Position position = new Position(0, 0, Orientation.North);

        //When
        String actions = "A";
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, new Grille(), actions);

        //Then
        assertThat(tondeuse).isNotNull();
    }

    @Test
    void devrait_retourner_tondeuse_position_initiale_0_0() {
        //Given
        Position position = new Position(0, 0, Orientation.North);

        //When
        String actions = "G";
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, new Grille(), actions);

        //Then
        assertThat(tondeuse.getPosition())
                .hasFieldOrPropertyWithValue("x", 0)
                .hasFieldOrPropertyWithValue("y", 0);
    }

    @Test
    void devrait_retourner_tondeuse_position_initiale_0_1() {
        //Given
        Position position = new Position(0, 1, Orientation.North);
        //When
        String actions = "G";
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, new Grille(), actions);

        //Then
        assertThat(tondeuse.getPosition())
                .hasFieldOrPropertyWithValue("x", 0)
                .hasFieldOrPropertyWithValue("y", 1);
    }

    @Test
    void devrait_retourner_grille_5x5_pour_tondeuse() {
        //Given
        Grille grille = new Grille(5, 5);
        Position position = new Position(0, 0, Orientation.North);
        String actions = "A";
        //When
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, grille, actions);

        //Then
        assertThat(tondeuse.getGrille())
                .hasFieldOrPropertyWithValue("x", 5)
                .hasFieldOrPropertyWithValue("y", 5);
    }

    @Test
    void devrait_avoir_orientation_initiale_Nord() {
        //Given
        Position position = new Position(0, 0, Orientation.North);
        //When
        String actions = "A";
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, new Grille(), actions);

        //Then
        assertThat(tondeuse.getPosition().getOrientation()).isEqualTo(Orientation.North);
    }

    @Test
    void devrait_pivoter_a_gauche_en_partnat_du_nord() {
        //Given
        Position position = new Position(0, 0, Orientation.North);
        String actions = "G";

        //When
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, new Grille(), actions);

        //Then
        assertThat(tondeuse.getPosition().getOrientation()).isEqualTo(Orientation.West);

    }

    @Test
    void devrait_pivoter_a_droite_en_partnat_de_l_est() {
        //Given
        Position position = new Position(0, 0, Orientation.East);
        String actions = "D";

        //When
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, new Grille(), actions);

        //Then
        assertThat(tondeuse.getPosition().getOrientation()).isEqualTo(Orientation.South);
    }

    @Test
    void devrait_avancer_de_0_0_vers_0_1_en_partant_du_nord() {
        //Given
        Position position = new Position(0, 0, Orientation.North);
        String actions = "A";
        Grille grille = new Grille(1, 1);

        //When
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, grille, actions);

        //Then
        assertThat(tondeuse.getPosition())
                .hasFieldOrPropertyWithValue("x", 0)
                .hasFieldOrPropertyWithValue("y", 1);
        assertThat(tondeuse.getPosition().getOrientation()).isEqualTo(Orientation.North);
    }

    @Test
    void devrait_avancer_de_0_0_vers_2_0_en_partant_de_est() {
        //Given
        Position position = new Position(0, 0, Orientation.East);
        String actions = "AA";
        Grille grille = new Grille(2, 2);

        //When
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, grille, actions);

        //Then
        assertThat(tondeuse.getPosition())
                .hasFieldOrPropertyWithValue("x", 2)
                .hasFieldOrPropertyWithValue("y", 0);
        assertThat(tondeuse.getPosition().getOrientation()).isEqualTo(Orientation.East);
    }

    @Test
    void devrait_avancer_de_5_5_vers_0_0_en_partant_du_sud() {
        //Given
        Position position = new Position(0, 5, Orientation.South);
        String actions = "AAAAA";
        Grille grille = new Grille(5, 5);

        //When
        Tondeuse tondeuse = tondeuseService.calculerTrajectoire(position, grille, actions);

        //Then
        assertThat(tondeuse.getPosition())
                .hasFieldOrPropertyWithValue("x", 0)
                .hasFieldOrPropertyWithValue("y", 0);
        assertThat(tondeuse.getPosition().getOrientation()).isEqualTo(Orientation.South);
    }
}
