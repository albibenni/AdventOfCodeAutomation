package com.albi.advent.service;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class AdventServiceImport {

    public List<String> getImputBasedOnYearAndDay(Integer year, Integer day) throws IOException {
        fetchAdventURLBasedOnYearAndDay(year, day);

        return List.of("el1", year.toString(), day.toString());
    }

    private List<String> fetchAdventURLBasedOnYearAndDay(Integer year, Integer day) {

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
                "User-Agent": "github.com/tomfran/advent-of-code-setup reddit:u/fran-sch, discord:@tomfran#5786"
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

            System.out.println("IOException: "+ioe);
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
