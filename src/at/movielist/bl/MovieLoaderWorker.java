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
import at.movielist.bl.ConfigUtility;
import at.movielist.bl.UtilityClass;
import at.movielist.ui.MainUI;
import at.movielist.ui.ProgressbarDLG;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieLoaderWorker extends SwingWorker<LinkedList<Movie>, Movie> {

    private String[] paths;
    private LinkedList<Movie> liste = new LinkedList<>();
    private JProgressBar loading;
    private JLabel lb;
    private ProgressbarDLG dlg;
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
    private static final String[] filesToIgnore
            = new String[]{"ds_store", ".nfo", ".mp3"};

    public MovieLoaderWorker(String[] paths, ProgressbarDLG d, MainUI mui, Locale loc) {
        this.paths = paths;
        this.loading = d.getProgBar();
        this.lb = d.getLabel();
        this.dlg = d;
        this.mui = mui;
        this.resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", loc);

        prog1 = resBundle.getString("progress_string_1");
        prog2 = resBundle.getString("progress_string_2");

        dlg.setVisible(true);
    }

    public void setListe(LinkedList<Movie> liste) {
        this.liste = liste;
    }

    public LinkedList<Movie> getListe() {
        return liste;
    }

    @Override
    protected LinkedList<Movie> doInBackground() throws Exception {
        for (String path : paths) {
            File folder = new File(path);
            File[] dirListing = folder.listFiles();

            dlg.setMovieWorker(this);
            int length = dirListing.length;

            String xfy = "";
            double inc = 1000000 / length;

            for (int i = 0; i < length; i++) {
                StringBuilder sb = new StringBuilder();
                xfy = sb.append(prog1).append(" ").append((i + 1)).append(" ").append(prog2).append(" ").append(length).toString();
                lb.setText(xfy);

                if (dirListing[i].isDirectory()) {
                    isADirectory(dirListing[i], dirListing[i].getName());
                } else {
                    isAFile(dirListing[i]);
                }

                loading.setValue(loading.getValue() + (int) inc);
            }
        }
        return liste;
    }

    @Override
    protected void done() {
        mui.setList(liste, false);

        if (liste.size() > 0) {
            String things = uc.getSizeAndNumberOfFiles(liste, resBundle.getLocale());
            mui.getLbThings().setText(things);
        }

        dlg.dispose();
        try {
            if (ConfigUtility.getInstance().isPropAutoSafe()) {
                mui.safeMovies(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(MovieLoaderWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createMovie(String fname, int numberOfFiles, File... f) {
        double filesize = 0.0;
        String gibormib = "GiB";

        mi.open(f[0]);

        String size = "";
        String name = fname;
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
                    } else {
                        filesize += Double.parseDouble(spl[0]);
                    }
                }
            }

            if (filesize < 1.0) {
                gibormib = "MiB";
                filesize *= 1024.0;
            }

            size = String.format("%4.2f %s", filesize, gibormib);
            size = size.replaceAll(",", ".");
            mi.close();
        }

        liste.add(new Movie(name, width, height, dar, duration, size, extension, numberOfFiles, f[0].getParent()));
    }

    public void isAFile(File f) {
        for (String ext : okFileExtensions) {
            if (f.getName().endsWith(ext)) {
                createMovie(f.getName(), 1, f);
            }
        }
    }

    public void isADirectory(File f, String fname) {
        File[] videofilesInFolder = f.listFiles(videoAfolder);

        int filesonlylength = f.listFiles(fileOnly).length;
        int length = videofilesInFolder.length;

        for (int i = 0; i < length; i++) {
            if (videofilesInFolder[i].isDirectory()) {
                isADirectory(videofilesInFolder[i], fname);
            } else {
                if (filesonlylength == 1) {
                    createMovie(fname, 1, videofilesInFolder[i]);
                } else if (filesonlylength > 1) {
                    createMovie(fname, filesonlylength, f.listFiles(fileOnly));
                    if (filesonlylength == length) {
                        i = length;
                    }
                }

            }
        }

    }
}
