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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manu
 */
public class TMDBMovie implements Serializable {

    private final String overview, original_title, title;
    private final Double voteAverage, voteCount;
    private final Date releaseDate;
    private final Long id;
    private final String poster_url;
    private String posterPathOnFilesystem = "";

    transient private ResourceBundle resBundle;

    public TMDBMovie(String overview, String original_title, String title, Double voteAverage, Double voteCount, Date releaseDate, Long id, String poster_url) {
        this.overview = overview;
        this.original_title = original_title;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.releaseDate = releaseDate;
        this.id = id;
        this.poster_url = poster_url;

        resource();
    }

    public String toHTMLString() {
        StringBuilder result = new StringBuilder();

        result.append("<br><center><h1>").append(resBundle.getString("main_information_TMDB_header")).append("</h1><hr noshade width='80%' >");
        if (!this.posterPathOnFilesystem.equals("")) {
            result.append("<img src='file:").append(this.posterPathOnFilesystem).append("' /><br>");

        }
        result.append("<strong style='color:#00bda5;font-size:14px'>").append(resBundle.getString("main_information_TMDB_overview")).append("</strong>");
        result.append("<p width='100%' style='font-size:12px'>").append(overview).append("</p></center><br>");
        result.append("<table width='100%' style='font-size:12px' >");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_releaseDate")).append(":</strong></td><td width='50%' >").append(getReleaseDate()).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_orititle")).append(":</strong></td><td width='50%' >").append(original_title).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_voteAverage")).append(":</strong></td><td width='50%' >").append(voteAverage).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_voteCount")).append(":</strong></td><td width='50%' >").append(voteCount).append("</td></tr>");

        result.append("</table></center></body>");

        return result.toString();
    }

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
        return new SimpleDateFormat("dd.MM.yyyy").format(releaseDate);
    }

    public String getReleaseYear() {
        return new SimpleDateFormat("yyyy").format(releaseDate);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setPosterPathOnFilesystem(String posterPathOnFilesystem) {
        this.posterPathOnFilesystem = posterPathOnFilesystem;
    }

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
