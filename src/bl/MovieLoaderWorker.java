package bl;

import beans.Movie;
import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import mediainfo.MediaInfo;
import ui.ProgressbarDLG;

public class MovieLoaderWorker extends SwingWorker<LinkedList<Movie>, Movie> {

    private String path;
    private LinkedList<Movie> liste = new LinkedList<Movie>();
    private Movie folderdummy = new Movie("Folders", "", "", "", "", "", "");
    private Movie filedummy = new Movie("Files", "", "", "", "", "", "");
    private JProgressBar loading;
    private JLabel lb;
    private ProgressbarDLG dlg;

    private static final String[] okFileExtensions
            = new String[]{"mkv", "avi", "mp4", "ogg", "flv", "3gp"};

    public MovieLoaderWorker(String path, ProgressbarDLG d) {
        this.path = path;
        this.loading = d.getProgBar();
        this.lb = d.getLabel();
        this.dlg = d;
    }

    public LinkedList<Movie> getListe() {
        return liste;
    }

    @Override
    protected LinkedList<Movie> doInBackground() throws Exception {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        MediaInfo mi = new MediaInfo();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            String xfy = "Loading Movie " + (i + 1) + " from " + listOfFiles.length;
            double inc = 100 / listOfFiles.length;
            lb.setText(xfy);
            if (listOfFiles[i].isDirectory()) {
                File moviefolder = new File(listOfFiles[i].getAbsolutePath());
                File[] listoffilesinmoviefolder = moviefolder.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        for (String extension : okFileExtensions) {
                            if (file.getName().toLowerCase().endsWith(extension)) {
                                return true;
                            }
                        }
                        return false;
                    }
                });
                if (listoffilesinmoviefolder != null) {
                    if (listoffilesinmoviefolder.length >= 1) {
                        File movie = listoffilesinmoviefolder[0];
                        mi.open(movie);
                        String name = listOfFiles[i].getName();
                        String width = mi.get(MediaInfo.StreamKind.Video, 0, "Width", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                        String height = mi.get(MediaInfo.StreamKind.Video, 0, "Height", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                        String dar = mi.get(MediaInfo.StreamKind.Video, 0, "DisplayAspectRatio/String", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                        String duration = mi.get(MediaInfo.StreamKind.General, 0, "Duration/String", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                        String size = mi.get(MediaInfo.StreamKind.General, 0, "FileSize/String2", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                        String extension = mi.get(MediaInfo.StreamKind.General, 0, "FileExtension", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                        liste.add(new Movie(name, width, height, dar, duration, size, extension));
                        mi.close();
                    }
                }
            } else if (listOfFiles[i].isFile()) {
                mi.open(listOfFiles[i]);
                String name = listOfFiles[i].getName();
                String width = mi.get(MediaInfo.StreamKind.Video, 0, "Width", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                String height = mi.get(MediaInfo.StreamKind.Video, 0, "Height", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                String dar = mi.get(MediaInfo.StreamKind.Video, 0, "DisplayAspectRatio/String", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                String duration = mi.get(MediaInfo.StreamKind.General, 0, "Duration/String", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                String size = mi.get(MediaInfo.StreamKind.General, 0, "FileSize/String2", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                String extension = mi.get(MediaInfo.StreamKind.General, 0, "FileExtension", MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
                liste.add(new Movie(name, width, height, dar, duration, size, extension));
                mi.close();
            }
            loading.setValue(loading.getValue() + (int) inc);
            Thread.sleep(2000);
        }

        return liste;
    }
  
    @Override
    protected void done() {
        dlg.dispose();
    }
}
