package com.albi.advent.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AdventServiceImport {

    //TODO: session Cookie valid until 2024
    private static final String SESSION_ID = "53616c7465645f5fa42d7d28f63c8cf51756637c84cc6bfb6abf78f8f46927a7b0d93935b53acba2d83ba4ad4d13b66aba09321cb404f2a96c10f79d927340f6";

    public String getImputBasedOnYearAndDay(Integer year, Integer day) {
        return fetchAdventURLBasedOnYearAndDay(year, day);
    }

    public String fetchAdventURLBasedOnYearAndDay(Integer year, Integer day) {
        final String FILE_URL = "https://adventofcode.com/"+year+"/day/"+day+"/input";
        InputStream in;
        try {
            URL url = new URL(FILE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
            con.setRequestProperty("Cookie","session="+SESSION_ID);
            in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            return IOUtils.toString(in, encoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
