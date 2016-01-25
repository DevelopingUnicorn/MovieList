/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.movielist.tmdb;

import at.movielist.beans.TMDBMovie;
import at.movielist.bl.ConfigUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APItmdb {

    private static final String URL = "http://api.themoviedb.org/3/search/movie?api_key=dd3c14bcb799a290119b8e0628514721";
    private String imgBaseURL;
    private static Proxy proxy = null;
    private static HashMap<Integer, String> genres = new HashMap<Integer, String>();

    private static APItmdb INSTANCE = null;

    /**
     * Private Constructor because of Singleton
     */
    private APItmdb() {
        this.imgBaseURL = "";
    }

    /**
     * Returns an API Instance
     *
     * @return
     * @throws IOException
     */
    public static APItmdb getInstance() throws IOException {
        if (APItmdb.INSTANCE == null) {
            APItmdb.INSTANCE = new APItmdb();
        }
        return APItmdb.INSTANCE;
    }

    /**
     * Does a search inside the API from tMDb
     *
     * @param lang The language the result should be
     * @param query The name of the movie
     * @return An array of results
     * @throws Exception
     */
    public ArrayList<TMDBMovie> doSearch(String lang, String query) throws Exception {
        return formatJSON(makeTMDBRequest(lang, query));
    }

    /**
     * Sets the proxy settings (HTTP Proxy)
     *
     * @param address the host address
     * @param port the proxy port
     */
    public void setProxyUse(String address, int port) {
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, port));
    }

    /**
     * sets the proxy to use an username and password as well
     *
     * @param address the proxy host address
     * @param port the proxy port
     * @param user the username to auth
     * @param password the password to auth
     */
    public void setProxyUseWithAuth(String address, int port, final String user, final char[] password) {
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, port));

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }

    /**
     * Reset proxy to default (no proxy)
     */
    public void resetProxy() throws IOException {
        proxy = null;
        ConfigUtility.getInstance().saveConfigToFile("", "", "", 0, false);
    }

    /**
     * Fetch the IMG-URL used by tMDb and sets the variable
     *
     * @throws Exception
     */
    private void setBaseURL() throws Exception {
        String result = makeHTTPRequest("http://api.themoviedb.org/3/configuration?api_key=dd3c14bcb799a290119b8e0628514721");

        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(result);
        JSONObject obj2 = (JSONObject) obj.get("images");
        this.imgBaseURL = (String) obj2.get("base_url");
    }

    /**
     * Gets an img to the given URL (which is provided as normal information to
     * every movie)
     *
     * @param posterURL The URL to use
     * @return
     * @throws Exception
     */
    public URL getPoster(String posterURL) throws Exception {
        if (this.imgBaseURL.equals("") || this.imgBaseURL == null) {
            setBaseURL();
        }

        URL url = new URL(this.imgBaseURL + "/w300" + posterURL);
        return url;
    }

    /**
     * Gets the available Genres of TMDB and puts the in a HashMap with id as
     * key and name as value
     *
     * @throws Exception
     */
    public static void getGenres() throws Exception {
        String result = makeHTTPRequest("http://api.themoviedb.org/3/genre/movie/list?api_key=dd3c14bcb799a290119b8e0628514721");

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);

        JSONArray msg = (JSONArray) obj.get("genres");
        Iterator<JSONObject> iterator = msg.iterator();
        while (iterator.hasNext()) {
            JSONObject factObj = (JSONObject) iterator.next();
            genres.put(Integer.parseInt(factObj.get("id").toString()), factObj.get("name").toString());
        }

    }

    /**
     * Formats the JSON from the tMDb
     *
     * @param apiString The JSON
     * @return An array of results
     * @throws ParseException
     * @throws java.text.ParseException
     * @throws IOException
     * @throws Exception
     */
    private static ArrayList<TMDBMovie> formatJSON(String apiString) throws ParseException, java.text.ParseException, IOException, Exception {
        if (genres.isEmpty()) {
            getGenres();
        }

        ArrayList<TMDBMovie> list = new ArrayList<>();
        LinkedList<String> genrenames = new LinkedList<>();

        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(apiString);

        if (obj != null) {
            JSONArray msg = (JSONArray) obj.get("results");
            Iterator<JSONObject> iterator = msg.iterator();
            while (iterator.hasNext()) {
                JSONObject factObj = (JSONObject) iterator.next();
                Long id = (Long) factObj.get("id");
                String release_date = (String) factObj.get("release_date");

                Object va = factObj.get("vote_average");

                Double vote_average = 0.0;
                if (!va.toString().equals("0")) {
                    vote_average = (Double) factObj.get("vote_average");
                }

                Long vote_count = (Long) factObj.get("vote_count");
                String overview = (String) factObj.get("overview");
                String original_title = (String) factObj.get("original_title");
                String title = (String) factObj.get("title");
                String poster_path = (String) factObj.get("poster_path");

                JSONArray jar = (JSONArray) factObj.get("genre_ids");

                for (int i = 0; i < jar.size(); i++) {
                    int genreid = Integer.parseInt(jar.get(i).toString());
                    genrenames.add(genres.get(genreid));
                }

                if (release_date == null || release_date.equals("")) {
                    list.add(new TMDBMovie(
                            overview,
                            original_title,
                            title,
                            genrenames,
                            vote_average,
                            vote_count.doubleValue(),
                            new SimpleDateFormat("yyyy-MM-dd").parse("1111-11-11"),
                            id,
                            poster_path
                    ));
                } else {
                    list.add(new TMDBMovie(
                            overview,
                            original_title,
                            title,
                            genrenames,
                            vote_average,
                            vote_count.doubleValue(),
                            new SimpleDateFormat("yyyy-MM-dd").parse(release_date),
                            id,
                            poster_path
                    ));
                }
            }
        }

        return list;
    }

    /**
     * Get the HTTP result to the given URL
     *
     * @param url the Link to use
     * @return The result (HTML response)
     * @throws Exception
     */
    private static String makeHTTPRequest(String url) throws Exception {
        BufferedReader reader = null;

        try {
            URL usedURL = new URL(url);
            HttpURLConnection connection;

            if (proxy != null) {
                connection = (HttpURLConnection) usedURL.openConnection(proxy);
            } else {
                connection = (HttpURLConnection) usedURL.openConnection();
            }

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

    /**
     * Uses the tMDb URL to make a request on movies
     *
     * @param lang the language the result should be
     * @param query the movie name
     * @return
     * @throws Exception
     */
    private static String makeTMDBRequest(String lang, String query) throws Exception {
        String usedURL = APItmdb.URL + "&language=" + lang + "&query=" + URLEncoder.encode(query, "UTF-8");
        return makeHTTPRequest(usedURL);
    }
}
