package com.example.tondeuse.controller;

import com.example.tondeuse.service.TondeuseService;
import com.example.tondeuse.service.model.Grille;
import com.example.tondeuse.service.model.Orientation;
import com.example.tondeuse.service.model.Position;
import com.example.tondeuse.service.model.Tondeuse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TondeuseControllerTest {

    public static final String URL = "http://localhost:";
    public static final String URI = "/tondeuse";

    @MockBean
    private TondeuseService tondeuseService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //Code mort si le fichier input.txt existe dans les ressources
//    @Test
//    void devrait_retourner_erreur_400_pour_fichier_manquant() {
//        //Given
//
//        //When
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + port + URI, String.class);
//
//        //Then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }

    @Test
    void devrait_retourner_200_avec_un_string_OK() {
        //Given

        //When
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + port + URI, String.class);

        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void devrait_retourner_resultat_position_avec_OK() {
        //Given
        Tondeuse tondeuse = new Tondeuse(new Position(1, 3, Orientation.North), new Grille(4, 4));
        doReturn(tondeuse).when(tondeuseService).calculerTrajectoire(any(), any(), any());

        //When
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL + port + URI, String.class);


        //Then
        String positionFinale = responseEntity.getBody();

        assertThat(positionFinale).isEqualTo("1 3 N 1 3 N");
    }
}
