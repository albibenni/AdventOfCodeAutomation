package com.albi.advent.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventServiceImport {

    public List<String> getImputBasedOnYearAndDay(Integer year, Integer day) {


        return List.of("el1", year.toString(), day.toString());
    }
}
