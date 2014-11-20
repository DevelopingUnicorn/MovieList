package bl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigUtility {

    private String ptcf;
    private String lang, path;

    public ConfigUtility(String pathtocffile) {
        ptcf = pathtocffile;
    }

    public void createConfigFile(String lang, String ptm) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ptcf)));
            bw.write("Lang:=;" + lang);
            bw.append("\nPathToMovies:=;" + ptm);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(ConfigUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(ptcf)));

            String z = "";

            while ((z = br.readLine()) != null) {
                if (z.startsWith("Lang")) {
                    String[] spl = z.split(":=;");

                    lang = spl[1];
                } else if (z.startsWith("PathToMovies")) {
                    String[] spl = z.split(":=;");

                    path = spl[1].replace("\\", "/");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigUtility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLang() {
        return lang;
    }

    public String getPath() {
        return path;
    }
}
