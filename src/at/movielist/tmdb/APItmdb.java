/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.movielist.tmdb;

import at.movielist.beans.TMDBMovie;
import java.awt.Image;
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
import javax.imageio.ImageIO;
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
    private String imgBaseURL;

    private static APItmdb INSTANCE = null;

    private APItmdb() {
        this.imgBaseURL = "";
    }

    public static APItmdb getInstance() throws IOException {
        if (APItmdb.INSTANCE == null) {
            APItmdb.INSTANCE = new APItmdb();
        }
        return APItmdb.INSTANCE;
    }

    public ArrayList<TMDBMovie> doSearch(String lang, String query) throws Exception {
        return formatJSON(makeTMDBRequest(lang, query));
    }

    public void setProxyUse() {
        System.setProperty("java.net.useSystemProxies", "true");
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("snimaa11", "Raffii123".toCharArray());
            }
        });
    }

    private void setBaseURL() throws Exception {
        String result = makeHTTPRequest("http://api.themoviedb.org/3/configuration?api_key=dd3c14bcb799a290119b8e0628514721");

        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(result);
        JSONObject obj2 = (JSONObject) obj.get("images");
        this.imgBaseURL = (String) obj2.get("base_url");

        System.out.println("Base_URL:\t" + this.imgBaseURL);
    }

    public URL getPoster(String posterURL) throws Exception {
        if (this.imgBaseURL.equals("") || this.imgBaseURL == null) {
            setBaseURL();
        }

        URL url = new URL(this.imgBaseURL + "/original" + posterURL);
        return url;
    }

    private static ArrayList<TMDBMovie> formatJSON(String apiString) throws ParseException, java.text.ParseException, IOException, Exception {
        ArrayList<TMDBMovie> list = new ArrayList<>();

        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(apiString);

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
            String title = (String) factObj.get("title");
            String poster_path = (String) factObj.get("poster_path");

            list.add(new TMDBMovie(
                    overview,
                    original_title,
                    title,
                    vote_average,
                    vote_count.doubleValue(),
                    new SimpleDateFormat("yyyy-MM-dd").parse(release_date),
                    id,
                    poster_path
            ));
        }

        return list;
    }

    private static String makeHTTPRequest(String url) throws Exception {
        BufferedReader reader = null;

        try {
            URL usedURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) usedURL.openConnection();

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

    private static String makeTMDBRequest(String lang, String query) throws Exception {
        String usedURL = APItmdb.URL + "&language=" + lang + "&query=" + URLEncoder.encode(query, "UTF-8");
        System.out.println(usedURL);
        return makeHTTPRequest(usedURL);
    }
}
