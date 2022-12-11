package com.albi.advent.controller;

import com.albi.advent.service.AdventServiceImport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.albi.advent.controller.AdventControllerImport.ADVENT_BASE_URL;


@RestController
@RequestMapping(ADVENT_BASE_URL)
@RequiredArgsConstructor
public class AdventControllerImport {

    private final AdventServiceImport adventServiceImport;

    public static final String ADVENT_BASE_URL = "/advent";

    @GetMapping("{year}/{day}")
    public ResponseEntity<String> getAventInputs(
            @PathVariable Integer year,
            @PathVariable Integer day
    ) {
        String listImported = adventServiceImport.getImputBasedOnYearAndDay(year, day);
        return ResponseEntity.ok().body(listImported);

    }

}
