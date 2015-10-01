/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.movielist.beans;

import at.movielist.bl.ConfigUtility;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TMDBMovie implements Serializable {

    private final String overview, original_title, title;
    private final Double voteAverage, voteCount;
    private final Date releaseDate;
    private final Long id;
    private final String poster_url;
    private String posterPathOnFilesystem = "";
    private LinkedList<String> genres;

    transient private ResourceBundle resBundle;

    /**
     * Constructor
     * 
     * @param overview
     * @param original_title
     * @param title
     * @param genres
     * @param voteAverage
     * @param voteCount
     * @param releaseDate
     * @param id
     * @param poster_url 
     */
    public TMDBMovie(String overview, String original_title, String title, LinkedList<String> genres, Double voteAverage, Double voteCount, Date releaseDate, Long id, String poster_url) {
        this.overview = overview;
        this.original_title = original_title;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.releaseDate = releaseDate;
        this.id = id;
        this.poster_url = poster_url;
        this.genres = genres;

        resource();
    }

    /**
     * Get & Set
     * 
     * @return 
     */
    public String getPoster_url() {
        return poster_url;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Double getVoteCount() {
        return voteCount;
    }

    public String getReleaseDate() {
        if (new SimpleDateFormat("dd.MM.yyyy").format(releaseDate).equals("11.11.1111")) {
            return "no Date";
        } else {
            return new SimpleDateFormat("dd.MM.yyyy").format(releaseDate);
        }
    }

    public String getReleaseYear() {
        if (new SimpleDateFormat("dd.MM.yyyy").format(releaseDate).equals("11.11.1111")) {
            return "no Date";
        } else {
            return new SimpleDateFormat("yyyy").format(releaseDate);
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPathOnFilesystem() {
        return posterPathOnFilesystem;
    }

    public LinkedList<String> getGenres() {
        return genres;
    }
    //END
    

    /**
     * Sets the Posterpath on filesystem
     * 
     * @param posterPathOnFilesystem 
     */
    public void setPosterPathOnFilesystem(String posterPathOnFilesystem) {
        this.posterPathOnFilesystem = posterPathOnFilesystem;
    }

    /**
     * Language support
     */
    private void resource() {
        try {
            // Lang support
            Locale currentLocal = Locale.ENGLISH;

            switch (ConfigUtility.getInstance().getPropLang()) {
                case "de":
                    currentLocal = Locale.GERMAN;
                    break;
                case "es":
                    currentLocal = new Locale("es");
                    break;
            }

            resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", currentLocal);
        } catch (IOException ex) {
            Logger.getLogger(TMDBMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
