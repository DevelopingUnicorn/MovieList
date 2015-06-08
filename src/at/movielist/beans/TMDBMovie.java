/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.movielist.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Manu
 */
public class TMDBMovie {

    private final String overview, title;
    private final Double voteAverage, voteCount;
    private final Date releaseDate;
    private final Long id;

    private final transient ResourceBundle resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);

    public TMDBMovie(String overview, String originalTitle, Double voteAverage, Double voteCount, Date releaseDate, Long id) {
        this.overview = overview;
        this.title = originalTitle;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    public String toHTMLString() {
        StringBuilder result = new StringBuilder();
        
        result.append("<br><center><h1>").append(resBundle.getString("main_information_TMDB_header")).append("</h1><hr noshade width='80%' ><table width='100%' style='font-size:12px' >");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_id")).append(":</strong></td><td width='50%' >").append(id).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_overview")).append(":</strong></td><td width='50%' >").append(overview).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_releaseDate")).append(":</strong></td><td width='50%' >").append(getReleaseDate()).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_title")).append(":</strong></td><td width='50%' >").append(title).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_voteAverage")).append(":</strong></td><td width='50%' >").append(voteAverage).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_voteCount")).append(":</strong></td><td width='50%' >").append(voteCount).append("</td></tr>");

        result.append("</table></center></body>");

        return result.toString();
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Double getVoteCount() {
        return voteCount;
    }

    public String getReleaseDate() {
        return new SimpleDateFormat("YYYY-MM-DD").format(releaseDate);
    }

    public Long getId() {
        return id;
    }

}
