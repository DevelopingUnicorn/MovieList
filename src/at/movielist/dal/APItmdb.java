/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.movielist.dal;

import at.movielist.beans.TMDBMovie;
import at.movielist.bl.ConfigUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Manu
 */
public class APItmdb {

    private static final String URL = "http://api.themoviedb.org/3/search/movie?api_key=dd3c14bcb799a290119b8e0628514721";

    private static APItmdb INSTANCE = null;

    public APItmdb() {
    }

    public static APItmdb getInstance() throws IOException {
        if (APItmdb.INSTANCE == null) {
            APItmdb.INSTANCE = new APItmdb();
        }
        return APItmdb.INSTANCE;
    }

    public static void main(String[] args) {
        try {
            APItmdb.getInstance().setProxyUse();

            ArrayList<TMDBMovie> list = formatJSON(makeHTTPRequest("de", "age of ultron"));

            for (TMDBMovie movie : list) {
                System.out.println(movie.toHTMLString());
            }

        } catch (IOException ex) {
            Logger.getLogger(APItmdb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(APItmdb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<TMDBMovie> doSearch(String lang, String query) throws Exception
    {
        return formatJSON(makeHTTPRequest(lang, query));
    }
    
    public void setProxyUse() {
        System.setProperty("java.net.useSystemProxies", "true");
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kolmaa11", "Rafitau7".toCharArray());
            }
        });
    }

    private static ArrayList<TMDBMovie> formatJSON(String apiString) throws ParseException, java.text.ParseException {
        ArrayList<TMDBMovie> list = new ArrayList<>();

        JSONParser parser = new JSONParser();

//        JSONArray array = (JSONArray) parser.parse(apiString);
        JSONObject obj = (JSONObject) parser.parse(apiString);
//        System.out.println("total_results:\t" + obj.get("total_results"));

        JSONArray msg = (JSONArray) obj.get("results");
        Iterator<JSONObject> iterator = msg.iterator();
        while (iterator.hasNext()) {
            JSONObject factObj = (JSONObject) iterator.next();
            Long id = (Long) factObj.get("id");
            String release_date = (String) factObj.get("release_date");
            Double vote_average = (Double) factObj.get("vote_average");
            Long vote_count = (Long) factObj.get("vote_count");
            String overview = (String) factObj.get("overview");
            String original_title = (String) factObj.get("original_title");

//            System.out.println("ID:\t" + id);
//            System.out.println("release_date:\t" + release_date);
//            System.out.println("vote_average:\t" + vote_average);
//            System.out.println("vote_count:\t" + vote_count);
//            System.out.println("overview:\t" + overview);
//            System.out.println("original_title:\t" + original_title);
            list.add(new TMDBMovie(
                    overview,
                    original_title,
                    vote_average,
                    vote_count.doubleValue(),
                    new SimpleDateFormat("YYYY-mm-DD").parse(release_date),
                    id
            ));
        }

//        return result.toString();
        return list;
    }

    private static String makeHTTPRequest(String lang, String query) throws Exception {

        String usedURL = APItmdb.URL + "&language=" + lang + "&query=" + URLEncoder.encode(query, "UTF-8");
        System.out.println(usedURL);

        BufferedReader reader = null;

        try {
            URL url = new URL(usedURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);
            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
