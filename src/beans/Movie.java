package beans;

import java.io.Serializable;

public class Movie implements Serializable{
    private String name, width, height, aspectratio, duration, filesize, fileextension;

    public Movie(String name, String width, String height, String aspectratio, String duration, String filesize, String fileextension) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.aspectratio = aspectratio;
        this.duration = duration;
        this.filesize = filesize;
        this.fileextension = fileextension;
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
    
    
}
