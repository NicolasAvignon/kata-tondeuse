package com.example.tondeuse.controller;

import com.example.tondeuse.controller.mapper.TondeuseDomainMapper;
import com.example.tondeuse.controller.model.DonneesFichier;
import com.example.tondeuse.service.TondeuseService;
import com.example.tondeuse.service.model.Tondeuse;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class TondeuseController {

    private static final TondeuseDomainMapper tondeuseDomainMapper = TondeuseDomainMapper.MODELS_MAPPER;
    private final TondeuseService tondeuseService;

    public TondeuseController(TondeuseService tondeuseService) {
        this.tondeuseService = tondeuseService;
    }

    @GetMapping("/tondeuse")
    public ResponseEntity<String> executionTondeuses() throws IOException {

        URL resource = getClass().getClassLoader().getResource("input.txt");
        //Si le fichier input.txt n'existe pas dans le projet
        if (resource == null)
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        File file = new File(resource.getFile());
        String donnees = FileUtils.readFileToString(file, "UTF-8");
        DonneesFichier donneesFichier = tondeuseDomainMapper.toDonneesFichier(donnees);
        List<Tondeuse> tondeuses = donneesFichier.getDonneesTondeuse().stream()
                .map(donneesTondeuse ->
                        tondeuseService.calculerTrajectoire(
                                donneesTondeuse.getPosition(),
                                donneesFichier.getGrille(),
                                donneesTondeuse.getActions())
                ).toList();
        String response = tondeuseDomainMapper.toResponse(tondeuses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
