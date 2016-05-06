package at.movielist.bl;

import at.movielist.beans.Movie;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import at.lib.mediainfo.MediaInfo;
import at.movielist.ui.MainUI;
import java.util.Locale;
import java.util.ResourceBundle;

public class MovieLoaderWorker extends SwingWorker<LinkedList<Movie>, Movie> {

    private String[] paths;
    private LinkedList<Movie> liste = new LinkedList<>();
    private JProgressBar loading;
    private JLabel lb;
    private JProgressBar pbLoad;
    private MainUI mui;
    private UtilityClass uc = new UtilityClass();
    private SimpleDateFormat durationFormat = new SimpleDateFormat("k'h' mm'mn'");
    private ResourceBundle resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);
    private MediaInfo mi = new MediaInfo();

    private FileFilter videoAfolder = new FileFilter() {
        @Override
        public boolean accept(File file) {
            for (String extension : okFileExtensions) {
                if (file.getName().toLowerCase().endsWith(extension) || file.isDirectory()) {
                    return true;
                }
            }
            return false;
        }
    };

    private FileFilter fileOnly = new FileFilter() {
        @Override
        public boolean accept(File file) {
            for (String extension : okFileExtensions) {
                if (file.getName().toLowerCase().endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }
    };

    private String prog1, prog2;

    private static final String[] okFileExtensions
            = new String[]{".mkv", ".avi", ".mp4", ".ogg", ".flv", ".3gp", ".iso", ".img", ".vob", ".ts", ".mpg", ".m2ts"};

    /**
     * Constructor
     *
     * @param paths
     * @param d
     * @param mui
     * @param loc
     */
    public MovieLoaderWorker(String[] paths, JProgressBar pb, JLabel xoy, MainUI mui, Locale loc) {
        this.paths = paths;
        this.loading = pb;
        this.lb = xoy;
        this.mui = mui;
        this.resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", loc);

        prog1 = resBundle.getString("progress_string_1");
        prog2 = resBundle.getString("progress_string_2");

//        pbLoad.setVisible(true);
    }

    /**
     * Sets the list
     *
     * @param liste
     */
    public void setListe(LinkedList<Movie> liste) {
        this.liste = liste;
    }

    /**
     * Returns the List
     *
     * @return
     */
    public LinkedList<Movie> getListe() {
        return liste;
    }

    /**
     * Walks through all paths and loads the movies0
     *
     * @return A list with the movies found with some information
     * @throws Exception
     */
    @Override
    protected LinkedList<Movie> doInBackground() throws Exception {
        loading.setVisible(true);
        for (String path : paths) {
            File folder = new File(path);
            File[] dirListing = folder.listFiles();

            int length = dirListing.length;

            String xfy = "";
            double inc = 1000000 / length;

            for (int i = 0; i < length; i++) {
                if (!isCancelled()) {
                    StringBuilder sb = new StringBuilder();
                    xfy = sb.append(prog1).append(" ").append((i + 1)).append(" ").append(prog2).append(" ").append(length).toString();
                    lb.setText(xfy);

                    if (dirListing[i].isDirectory()) {
                        isADirectory(dirListing[i], dirListing[i].getName());
                    } else {
                        isAFile(dirListing[i]);
                    }

                    loading.setValue(loading.getValue() + (int) inc);
                } else {
                    i = length;
                }
            }
        }

        lb.setText("");
        loading.setVisible(false);
        return liste;
    }

    /**
     * After finished it sets the list of movies and if AutoSave is set to true
     * all the movies will be saved
     */
    @Override
    protected void done() {
        mui.setList(liste, true);

        if (liste.size() > 0) {
            String things = uc.getSizeAndNumberOfFiles(liste, resBundle.getLocale());
            mui.getLbThings().setText(things);
        }
    }

    /**
     * Creates an Movie-Object with all the neccessary information
     *
     * @param fname the file name (of the movie)
     * @param numberOfFiles how many files have been found
     * @param f The files found
     */
    private void createMovie(String fname, int numberOfFiles, boolean isAFile, File... f) {
        double filesize = 0.0;
        double kib = 0.0;
        String gibmiborkib = "GiB";

        mi.open(f[0]);

        String size = "";
        String name = "";
        if (isAFile) {
            for (String ext : okFileExtensions) {
                if (fname.endsWith(ext)) {
                    name = fname.replace(ext, "");
                }
            }
        } else {
            name = fname;
        }

        String width = mi.get(MediaInfo.StreamKind.Video, 0, "Width", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        String height = mi.get(MediaInfo.StreamKind.Video, 0, "Height", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        String dar = mi.get(MediaInfo.StreamKind.Video, 0, "DisplayAspectRatio/String", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        String duration = mi.get(MediaInfo.StreamKind.General, 0, "Duration/String", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        String extension = mi.get(MediaInfo.StreamKind.General, 0, "FileExtension", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);

        if (numberOfFiles == 1) {
            size = mi.get(MediaInfo.StreamKind.General, 0, "FileSize/String2", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            mi.close();
        } else if (numberOfFiles > 1) {
            mi.close();

            for (File file : f) {
                if (file.isFile()) {
                    mi.open(file);
                    String[] spl = mi.get(MediaInfo.StreamKind.General, 0, "FileSize/String2", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name).split("\\s+");

                    if (spl[1].equals("MiB")) {
                        filesize += (Double.parseDouble(spl[0]) / 1024.0);
                    } else if (spl[1].equals("GiB")) {
                        filesize += Double.parseDouble(spl[0]);
                    } else {
                        kib += Double.parseDouble(spl[0]);
                        if (kib >= 1024) {
                            filesize += 1 / 1024;
                            kib -= 1024;
                        }
                    }
                }
            }

            if (filesize < 1.0) {
                gibmiborkib = "MiB";
                filesize *= 1024.0;
            }

            size = String.format("%4.2f %s", filesize, gibmiborkib);
            size = size.replaceAll(",", ".");
            mi.close();
        }

        liste.add(new Movie(name, width, height, dar, duration, size, extension, numberOfFiles, f[0].getParent(), f[0].getAbsolutePath(), isAFile));
    }

    /**
     * Checks if the File-Objects is actually a file and has an appropriate file
     * extension
     *
     * @param f The File-Object
     */
    public void isAFile(File f) {
        for (String ext : okFileExtensions) {
            if (f.getName().endsWith(ext)) {
                createMovie(f.getName(), 1, true, f);
            }
        }
    }

    /**
     * Checks if the File-Object is a directory and loads the movie information
     * from the first one found
     *
     * @param f The file
     * @param fname the name of the file
     */
    public void isADirectory(File f, String fname) {
        File[] videofilesInFolder = f.listFiles(videoAfolder);

        int filesonlylength = f.listFiles(fileOnly).length;
        int length = videofilesInFolder.length;

        for (int i = 0; i < length; i++) {
            if (videofilesInFolder[i].isDirectory()) {
                isADirectory(videofilesInFolder[i], fname);
            } else {
                if (filesonlylength == 1) {
                    createMovie(fname, 1, false, videofilesInFolder[i]);
                } else if (filesonlylength > 1) {
                    createMovie(fname, filesonlylength, false, f.listFiles(fileOnly));
                    if (filesonlylength == length) {
                        i = length;
                    }
                }

            }
        }

    }
}
