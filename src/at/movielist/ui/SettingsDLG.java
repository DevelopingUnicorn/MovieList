package at.movielist.ui;

import at.movielist.bl.ConfigUtility;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SettingsDLG extends javax.swing.JDialog {

    private String pathtomovies;
    private ConfigUtility cu;
    private LinkedList<Image> iconlist = new LinkedList<Image>();
    private ResourceBundle resBundle;

    public SettingsDLG(java.awt.Frame parent, boolean modal, String ptm, ConfigUtility c) {
        super(parent, modal);
        initComponents();

        cu = c;
        cu.getConfig();

        switch (cu.getLang()) {
            case "de":
                language("de");
                break;
            case "en":
                language("en");
                break;
            case "es":
                language("es");
                break;
            default:
                language("en");
        }

        cbLang.setModel(new DefaultComboBoxModel(new String[]{"English", "Deutsch", "Espaniol"}));
        cbLang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String lang = cbLang.getSelectedItem().toString();

                switch (lang) {
                    case "Deutsch":
                        language("de");
                        break;
                    case "English":
                        language("en");
                        break;
                    case "Espaniol":
                        language("es");
                        break;
                    default:
                        language("en");
                }
            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });

        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.small.png")).getImage());

        this.setIconImages(iconlist);

        pathtomovies = ptm;
        lbPath.setText(ptm);

        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitel = new javax.swing.JLabel();
        lbVers = new javax.swing.JLabel();
        pnThings = new javax.swing.JPanel();
        lbCLUE = new javax.swing.JLabel();
        cbLang = new javax.swing.JComboBox();
        lbUEPM = new javax.swing.JLabel();
        pnPath = new javax.swing.JPanel();
        lbPath = new javax.swing.JLabel();
        btChoosePath = new javax.swing.JButton();
        btOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImages(null);

        lbTitel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/settingsBig.png"))); // NOI18N
        lbTitel.setText("Settings");
        getContentPane().add(lbTitel, java.awt.BorderLayout.PAGE_START);

        lbVers.setText("v1.0b");
        getContentPane().add(lbVers, java.awt.BorderLayout.PAGE_END);

        pnThings.setLayout(new java.awt.GridLayout(5, 0));

        lbCLUE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCLUE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCLUE.setText("Language:");
        pnThings.add(lbCLUE);

        cbLang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English" }));
        pnThings.add(cbLang);

        lbUEPM.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUEPM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbUEPM.setText("Path to movies:");
        pnThings.add(lbUEPM);

        pnPath.setLayout(new java.awt.GridLayout(1, 2));

        lbPath.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pnPath.add(lbPath);

        btChoosePath.setText("Choose folder");
        btChoosePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onChooseFolder(evt);
            }
        });
        pnPath.add(btChoosePath);

        pnThings.add(pnPath);

        btOk.setText("Change settings!");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFinish(evt);
            }
        });
        pnThings.add(btOk);

        getContentPane().add(pnThings, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onFinish(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFinish

        String lang = cbLang.getSelectedItem().toString();

        switch (lang) {
            case "Deutsch":
                lang = "de";
                break;
            case "English":
                lang = "en";
                break;
            case "Espaniol":
                lang = "es";
                break;
            default:
                lang = "en";
        }

        cu.createConfigFile(lang, pathtomovies);

        this.dispose();
    }//GEN-LAST:event_onFinish

    private void onChooseFolder(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onChooseFolder
        JFileChooser fc = new JFileChooser();
        fc.setPreferredSize(new Dimension(700, 500));

        FileFilter directoryFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Movie Folder";
            }
        };

        fc.setFileFilter(directoryFilter);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            pathtomovies = fc.getSelectedFile().toString();
            lbPath.setText(pathtomovies);
        }
    }//GEN-LAST:event_onChooseFolder


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChoosePath;
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox cbLang;
    private javax.swing.JLabel lbCLUE;
    private javax.swing.JLabel lbPath;
    private javax.swing.JLabel lbTitel;
    private javax.swing.JLabel lbUEPM;
    private javax.swing.JLabel lbVers;
    private javax.swing.JPanel pnPath;
    private javax.swing.JPanel pnThings;
    // End of variables declaration//GEN-END:variables

    public void language(String lang) {

        if (lang.equals("de")) {
            resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.GERMAN);
        } else if (lang.equals("en")) {
            resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);
        } else if (lang.equals("es")) {
            resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", new Locale("es"));
        }

        // Lang support
        lbTitel.setText(resBundle.getString("setup_titel"));
        lbUEPM.setText(resBundle.getString("setup_path"));
        lbCLUE.setText(resBundle.getString("setup_chooseLang"));
        btOk.setText(resBundle.getString("setup_finish"));
        btChoosePath.setText(resBundle.getString("setup_choosePath"));
        // END lang support
    }
}
