package at.movielist.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtility {

    private String propLang, propProxyUsername, propProxyPassword, propProxyHost;
    private String[] propPaths;
    private int propProxyPort;
    private boolean propAutoSave, propSavePosters, propProxyUseAuthenticate;
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

    private final String pathToImages = new StringBuilder()
            .append(userdocs)
            .append(File.separator)
            .append("posters").toString();

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

    /**
     * Saves all the config to the file
     *
     * @param lang Property; Language
     * @param proxyUsername Property; Username to use with the HTTP Proxy
     * @param proxyPassword Property; Password for HTTP Proxy
     * @param proxyHost Property; Host for Proxy
     * @param autoSave Property; Wheter the loaded movies should be
     * automatically saved to an file
     * @param proxyPort Property; Port for Proxy
     * @param savePosters Propery; Wheter the posters should be saved
     * automatically
     * @param proxyUseAuthenticate Property; Wheter to use username and password
     * to authenticate with the HTTP Proxy
     * @param pathToMovies Property; All paths (divided by an ';') to the movies
     * to be loaded
     * @throws IOException
     */
    public void saveConfigToFile(String lang, String proxyUsername, String proxyPassword, String proxyHost, int proxyPort, boolean autoSave, boolean savePosters, boolean proxyUseAuthenticate, String[] pathToMovies)
            throws IOException {

        properties.setProperty("Lang", lang.equals("") ? "en" : lang);
        properties.setProperty("AutoSave", autoSave ? "true" : "false");
        properties.setProperty("SavePosters", savePosters ? "true" : "false");
        properties.setProperty("ProxyUsername", proxyUsername);
        properties.setProperty("ProxyPassword", proxyPassword);
        properties.setProperty("ProxyHost", proxyHost);
        properties.setProperty("ProxyPort", proxyPort + "");
        properties.setProperty("ProxyUseAuthenticate", proxyUseAuthenticate ? "true" : "false");

        String allPaths = "";
        for (int i = 0; i < pathToMovies.length - 1; i++) {
            allPaths += pathToMovies[i] + ";";
        }
        allPaths += pathToMovies[pathToMovies.length - 1];

        properties.setProperty("Paths", allPaths);
        outStream = new FileOutputStream(pathToConfFile);
        properties.store(outStream, null);
        loadConfig();
    }

    /**
     * Saves the general config to the file
     *
     * @param lang
     * @param autoSave
     * @param savePosters
     * @param pathToMovies
     * @throws IOException
     */
    public void saveConfigToFile(String lang, boolean autoSave, boolean savePosters, String[] pathToMovies) throws IOException {
        saveConfigToFile(lang, "", "", "", 0, autoSave, savePosters, false, pathToMovies);
    }

    /**
     * Saves all config regarding the proxy to the file
     *
     * @param proxyUsername
     * @param proxyPassword
     * @param proxyHost
     * @param proxyPort
     * @param proxyUseAuthenticate
     * @throws IOException
     */
    public void saveConfigToFile(String proxyUsername, String proxyPassword, String proxyHost, int proxyPort, boolean proxyUseAuthenticate) throws IOException {
        saveConfigToFile(propLang, proxyUsername, proxyPassword, proxyHost, proxyPort, propAutoSave, propSavePosters, proxyUseAuthenticate, propPaths);
    }

    /**
     * Loads the config and sets the variables
     *
     * @throws IOException
     */
    public void loadConfig() throws IOException {
        this.propLang = properties.getProperty("Lang", "en");
        this.propProxyUsername = properties.getProperty("ProxyUsername", "");
        this.propProxyPassword = properties.getProperty("ProxyPassword", "");
        this.propProxyHost = properties.getProperty("ProxyHost", "");
        this.propProxyPort = Integer.parseInt(properties.getProperty("ProxyPort", "0"));
        this.propAutoSave = Boolean.valueOf(properties.getProperty("AutoSave"));
        this.propSavePosters = Boolean.valueOf(properties.getProperty("SavePosters"));
        this.propProxyUseAuthenticate = Boolean.valueOf(properties.getProperty("ProxyUseAuthenticate"));

        try {
            this.propPaths = properties.getProperty("Paths").split(";");
        } catch (NullPointerException ex) {
            this.propPaths = new String[0];
        }

        if (propSavePosters) {
            File f = new File(this.pathToImages);
            if (!f.exists() || !f.isDirectory()) {
                f.mkdir();
            }
        }
    }

    public static ConfigUtility getInstance() throws IOException {
        if (ConfigUtility.INSTANCE == null) {
            ConfigUtility.INSTANCE = new ConfigUtility();
        }
        return ConfigUtility.INSTANCE;
    }

    public String getPropProxyUsername() {
        return propProxyUsername;
    }

    public String getPropProxyPassword() {
        return propProxyPassword;
    }

    public String getPropProxyHost() {
        return propProxyHost;
    }

    public int getPropProxyPort() {
        return propProxyPort;
    }

    public boolean isPropProxyUseAuthenticate() {
        return propProxyUseAuthenticate;
    }

    /**
     * Checks if the ConfigFile exists
     *
     * @return true if it exists, otherwise false
     * @throws IOException
     */
    public boolean isFileExisting() throws IOException {
        return !ConfigUtility.getInstance().properties.isEmpty();
    }

    public String getPropLang() {
        return propLang;
    }

    public String[] getPropPaths() {
        return propPaths;
    }

    public boolean isPropAutoSave() {
        return propAutoSave;
    }

    public String getUserdocs() {
        return userdocs;
    }

    public String getPathToConfFile() {
        return pathToConfFile;
    }

    public boolean isPropSavePosters() {
        return propSavePosters;
    }

    public String getPathToImages() {
        return pathToImages;
    }
}
