package at.movielist.beans;

import at.movielist.bl.ConfigUtility;
import at.movielist.dal.APItmdb;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Movie implements Serializable {

    private final String width, height, aspectratio, duration, filesize, fileextension;
    private String name, path;
    private final int numberoffiles;
    transient private ResourceBundle resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);
    private ArrayList<TMDBMovie> possibleMatches;
    private Object m;

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
        possibleMatches = new ArrayList<>();
    }

    public ArrayList<TMDBMovie> getPossibleMatches() {
        return possibleMatches;
    }

    public void setPossibleMatches(ArrayList<TMDBMovie> possibleMatches) {
        this.possibleMatches = possibleMatches;
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

    public String getClosestMatch() throws IOException, Exception {
        if (this.possibleMatches.isEmpty()) {
            ArrayList<TMDBMovie> list = APItmdb.getInstance().doSearch(ConfigUtility.getInstance().getPropLang(), this.name);
            if (list.isEmpty()) {
                return "<br><center><h1>" + resBundle.getString("main_information_TMDB_header") + "</h1><hr noshade width='80%' ><table width='100%' style='font-size:12px' ><h2>" + resBundle.getString("main_information_TMDB_nothingFound") + "</h2>";
            } else {
                return list.get(0).toHTMLString();
            }
        } else {
            return this.possibleMatches.get(0).toHTMLString();
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
