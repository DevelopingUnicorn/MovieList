package at.movielist.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Movie implements Serializable {

    private final String width, height, aspectratio, duration, filesize, fileextension;
    private String name, path;
    private final int numberoffiles;
    transient private ResourceBundle resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);
    private boolean DBmatch = false;

    private String t_overview, t_original_title, t_title;
    private Double t_voteAverage = 0.0, t_voteCount;
    private String t_releaseDate, t_releaseYear = "1";
    private Long t_id;
    private String t_posterPathOnFilesystem;
    private LinkedList<String> t_genres;

    public Movie(String name, String width, String height, String aspectratio, String duration, String filesize, String fileextension, int numberoffiles, String path) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.aspectratio = aspectratio;
        this.duration = duration;
        this.filesize = filesize;
        this.fileextension = fileextension;
        this.numberoffiles = numberoffiles;
        this.path = path;
    }

    public boolean getDBMatch() {
        return DBmatch;
    }

    public void setMatch(boolean match) {
        this.DBmatch = match;
    }

    public void setResBundle(Locale loc) {
        this.resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", loc);
    }

    public String getName() {
        return name;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getAspectratio() {
        return aspectratio;
    }

    public String getDuration() {
        return duration;
    }

    public String getFilesize() {
        return filesize;
    }

    public String getFileextension() {
        return fileextension;
    }

    public int getNumberoffiles() {
        return numberoffiles;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    //tMDb vars
    public String getT_overview() {
        return t_overview;
    }

    public void setT_overview(String t_overview) {
        this.t_overview = t_overview;
    }

    public String getT_original_title() {
        return t_original_title;
    }

    public void setT_original_title(String t_original_title) {
        this.t_original_title = t_original_title;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public Double getT_voteAverage() {
        return t_voteAverage;
    }

    public void setT_voteAverage(Double t_voteAverage) {
        this.t_voteAverage = t_voteAverage;
    }

    public Double getT_voteCount() {
        return t_voteCount;
    }

    public void setT_voteCount(Double t_voteCount) {
        this.t_voteCount = t_voteCount;
    }

    public String getT_releaseDate() {
        return t_releaseDate;
    }

    public void setT_releaseDate(String t_releaseDate) {
        this.t_releaseDate = t_releaseDate;
    }

    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public String getT_posterPathOnFilesystem() {
        return t_posterPathOnFilesystem;
    }

    public void setT_posterPathOnFilesystem(String t_posterPathOnFilesystem) {
        this.t_posterPathOnFilesystem = t_posterPathOnFilesystem;
    }

    public LinkedList<String> getT_genres() {
        return t_genres;
    }

    public void setT_genres(LinkedList<String> t_genres) {
        this.t_genres = t_genres;
    }

    public String getT_releaseYear() {
        return t_releaseYear;
    }

    public void setT_releaseYear(String t_releaseYear) {
        this.t_releaseYear = t_releaseYear;
    }

    //END

    public String toHTMLString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<br><center><h1>").append(name).append("</h1><hr noshade width='80%' ><table width='100%' style='font-size:12px' >");
        sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_duration")).append(":</strong></td><td>").append(duration).append("</td></tr>");

        if (width.equals("") && height.equals("")) {
            sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_resolution")).append(":</strong></td><td width='50%' >DVD</td></tr>");
        } else {
            if (Integer.parseInt(width) >= 1280) {
                sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_resolution")).append(":</strong></td><td width='50%' >").append(width).append("x").append(height).append("  HD</td></tr>");
            } else {
                sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_resolution")).append(":</strong></td><td width='50%' >").append(width).append("x").append(height).append("  SD</td></tr>");
            }

        }

        sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_dar")).append(":</strong></td><td width='50%' >").append(aspectratio).append("</td></tr>");
        sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_fileext")).append(":</strong></td><td width='50%' >").append(fileextension).append("</td></tr>");
        sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_filesize")).append(":</strong></td><td width='50%' >").append(filesize).append("</td></tr>");
        sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_numboffiles")).append(":</strong></td><td width='50%' >").append(numberoffiles).append("</td></tr>");
        sb.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_path")).append(":</strong></td><td width='50%' >").append(path).append("</td></tr>");
        sb.append("</table></center>");

        return sb.toString();
    }

    public String toTMDBString() {
        StringBuilder result = new StringBuilder();

        result.append("<br><center><h1>").append(resBundle.getString("main_information_TMDB_header")).append("</h1><hr noshade width='80%' >");
        if (!this.t_posterPathOnFilesystem.equals("")) {
            result.append("<img src='file:").append(this.t_posterPathOnFilesystem).append("' /><br>");

        }

        result.append("<strong style='color:#00bda5;font-size:14px'>").append(resBundle.getString("main_information_TMDB_overview")).append("</strong>");
        result.append("<p width='100%' style='font-size:12px'>").append(t_overview).append("</p></center><br>");
        result.append("<table width='100%' style='font-size:12px' >");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_releaseDate")).append(":</strong></td><td width='50%' >").append(t_releaseDate).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_orititle")).append(":</strong></td><td width='50%' >").append(t_original_title).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_genre")).append(":</strong></td><td width='50%' >");
        result.append("<ul>");

        LinkedList<String> tmp = new LinkedList<>();
        for (String gen : t_genres) {
            if (!tmp.contains(gen)) {
                tmp.add(gen);
                result.append("<li>").append(gen).append("</li>");
            }
        }

        result.append("</ul></td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_voteAverage")).append(":</strong></td><td width='50%' >").append(t_voteAverage).append("</td></tr>");
        result.append("<tr><td width='50%' ><strong style='color:#00bda5'>").append(resBundle.getString("main_information_TMDB_voteCount")).append(":</strong></td><td width='50%' >").append(t_voteCount).append("</td></tr>");

        result.append("</table></center></body>");

        return result.toString();
    }

    public String getMatch() {
        if (DBmatch == false) {
            StringBuilder sb = new StringBuilder("<br><center><h1>");
            sb.append(resBundle.getString("main_information_TMDB_header")).append("</h1><hr noshade width='80%' ><table width='100%' style='font-size:12px' ><h2>").append(resBundle.getString("main_information_TMDB_nothingFound")).append("</h2>");
            return sb.toString();
        } else {
            return toTMDBString();
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
