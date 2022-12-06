package com.albi.advent.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Service
public class AdventServiceImport {

    public List<String> getImputBasedOnYearAndDay(Integer year, Integer day) throws IOException {
        fetchAdventURLBasedOnYearAndDay(year, day);

        return List.of("el1", year.toString(), day.toString());
    }

    private List<String> fetchAdventURLBasedOnYearAndDay(Integer year, Integer day) throws IOException {
        URLConnection urlConnection = new URL("https://adventofcode.com/2022/day/1/input").openConnection();
//        String response = urlConnection.getResponseMessage();
//        System.out.println(response);
        return null;
    }
}
