package at.movielist.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtility {

    private String propLang;
    private String[] propPaths;
    private boolean propAutoSafe;
    private final Properties properties;
    private FileOutputStream outStream;

    private static ConfigUtility INSTANCE = null;

    private final String userdocs = new StringBuilder()
            .append(System.getProperty("user.home"))
            .append(File.separator)
            .append("Documents")
            .append(File.separator)
            .append("MovieList").toString();

    private final String pathToConfFile = new StringBuilder()
            .append(userdocs)
            .append(File.separator)
            .append("movielist.properties").toString();

    private ConfigUtility() throws IOException {
        FileInputStream fi;
        try {
            fi = new FileInputStream(pathToConfFile);
        } catch (FileNotFoundException ex) {
            // File does not exist ==> create it!
            System.out.println("Create config file");
            File fileProp = new File(pathToConfFile);
            fileProp.createNewFile();
            fi = new FileInputStream(pathToConfFile);
        }
        outStream = null;
        properties = new Properties();
        properties.load(fi);
    }

    public void saveConfigToFile(String lang, boolean autoSafe, String[] pathToMovies) throws IOException {

        properties.setProperty("Lang", lang.equals("") ? "en" : lang);
        properties.setProperty("AutoSafe", autoSafe ? "true" : "false");

        String allPaths = "";
        for (int i = 0; i < pathToMovies.length - 1; i++) {
            allPaths += pathToMovies[i] + ";";
        }
        allPaths += pathToMovies[pathToMovies.length - 1];

        properties.setProperty("Paths", allPaths);
        outStream = new FileOutputStream(pathToConfFile);
        properties.store(outStream, null);
    }

    public void loadConfig() throws IOException {

        this.propLang = properties.getProperty("Lang", "en");
        this.propAutoSafe = Boolean.valueOf(properties.getProperty("AutoSafe"));

        try {
            this.propPaths = properties.getProperty("Paths").split(";");
        } catch (NullPointerException ex) {
            this.propPaths = new String[0];
        }
    }

    public static ConfigUtility getInstance() throws IOException {
        if (ConfigUtility.INSTANCE == null) {
            ConfigUtility.INSTANCE = new ConfigUtility();
        }
        return ConfigUtility.INSTANCE;
    }

    public String getPropLang() {
        return propLang;
    }

    public String[] getPropPaths() {
        return propPaths;
    }

    public boolean isPropAutoSafe() {
        return propAutoSafe;
    }

    public String getUserdocs() {
        return userdocs;
    }

    public String getPathToConfFile() {
        return pathToConfFile;
    }

}
