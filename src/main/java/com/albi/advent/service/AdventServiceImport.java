package com.albi.advent.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Permission;
import java.util.List;

@Service
public class AdventServiceImport {

    private static final String FILE_URL = "https://adventofcode.com/2022/day/1/input";

    private static final String FILE_NAME = "/import.txt";

    //TODO: valid until 2024
    private static final String SESSION_ID = "53616c7465645f5fa42d7d28f63c8cf51756637c84cc6bfb6abf78f8f46927a7b0d93935b53acba2d83ba4ad4d13b66aba09321cb404f2a96c10f79d927340f6";

    public List<String> getImputBasedOnYearAndDay(Integer year, Integer day) {
        fetchAdventURLBasedOnYearAndDay(year, day);

        return List.of("el1", year.toString(), day.toString());
    }

    private void fetchAdventURLBasedOnYearAndDay(Integer year, Integer day) {
        InputStream in;
        try {
            URL url = new URL(FILE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Connection", "keep-alive");
            con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
            con.setRequestProperty("Cookie","session="+SESSION_ID);
            in = con.getInputStream();
            Files.copy(in,Paths.get(FILE_NAME),
                    StandardCopyOption.REPLACE_EXISTING);
            Permission permission = con.getPermission();
            System.out.println(permission.toString());
            System.out.println(con.getResponseCode());
            String responseMessage = con.getResponseMessage();
            System.out.println(responseMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //not working method
    private List<String> fetchAdventBasicURLBasedOnYearAndDay(Integer year, Integer day) {

        //  Step 1:  Start creating a few objects we'll need.
        URL url;
        InputStream is = null;
        DataInputStream dis;
        String s;
        try {
            // Step 2:  Create the URL.
            //------------------------------------------------------------//
            // Note: Put your real URL here, or better yet, read it as a
            // command-line arg, or read it from a file.
            url = new URL("https://adventofcode.com/2022/day/1/input");

            /*
            Add:
            session: 53616c7465645f5fa42d7d28f63c8cf51756637c84cc6bfb6abf78f8f46927a7b0d93935b53acba2d83ba4ad4d13b66aba09321cb404f2a96c10f79d927340f6
                espires in 2024
            SESSION_ID_FILE = "session.cookie"
            SESSION = get_session_id(SESSION_ID_FILE)
            HEADERS = {
                "User-Agent": "github.com/albibenni/AdventOfCodeAutomation"
            }
            COOKIES = {"session": SESSION}
             */
            // Step 3:  Open an input stream from the url.
            is = url.openStream(); //IO exception


            // Step 4:                                                     //
            //-------------------------------------------------------------//
            // Convert the InputStream to a buffered DataInputStream.      //
            // Buffering the stream makes the reading faster; the          //
            // readLine() method of the DataInputStream makes the reading  //
            // easier.                                                     //
            //-------------------------------------------------------------//

            dis = new DataInputStream(new BufferedInputStream(is));
            // Step 5:                                                    //
            //------------------------------------------------------------//
            // Now just read each record of the input stream, and print   //
            // it out.  Note that it's assumed that this problem is run   //
            // from a command-line, not from an application or applet.    //
            //------------------------------------------------------------//

            while ((s = dis.readLine()) != null) {
                System.out.println(s);
            }
        } catch (MalformedURLException mue) {

            System.out.println("MalformedURLException: " + mue);
            mue.printStackTrace();
            System.exit(1);

        } catch (IOException ioe) {

            System.out.println("IOException: " + ioe);
            ioe.printStackTrace();
            System.exit(1);

        } finally {

            //---------------------------------//
            // Step 6:  Close the InputStream  //
            //---------------------------------//

            try {
                is.close();
            } catch (IOException ioe) {

            }
        }
        return null;
    }


}
