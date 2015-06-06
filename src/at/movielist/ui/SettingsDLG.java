package at.movielist.ui;

import at.movielist.bl.ConfigUtility;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class SettingsDLG extends javax.swing.JDialog {

    private ArrayList<String> pathsToMovies;
    private LinkedList<Image> iconlist = new LinkedList<>();
    private ResourceBundle resBundle;

    public SettingsDLG(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();

        try {
            ConfigUtility.getInstance().loadConfig();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occured! Maybe the file or path does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.pathsToMovies = new ArrayList<>();
        pathsToMovies.addAll(Arrays.asList(ConfigUtility.getInstance().getPropPaths()));

        listPathsToMovies.setModel(new DefaultListModel());
        listPathsToMovies.setListData(pathsToMovies.toArray(new String[pathsToMovies.size()]));
        listPathsToMovies.updateUI();

        cbLang.setModel(new DefaultComboBoxModel(new String[]{"English", "Deutsch", "Espaniol"}));

        switch (ConfigUtility.getInstance().getPropLang()) {
            case "de":
                setLang("de");
                cbLang.setSelectedIndex(1);
                break;
            case "en":
                setLang("en");
                cbLang.setSelectedIndex(0);
                break;
            case "es":
                setLang("es");
                cbLang.setSelectedIndex(2);
                break;
            default:
                setLang("en");
                cbLang.setSelectedIndex(0);
        }

        cbLang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String lang = cbLang.getSelectedItem().toString();
                switch (lang) {
                    case "Deutsch":
                        setLang("de");
                        break;
                    case "English":
                        setLang("en");
                        break;
                    case "Espaniol":
                        setLang("es");
                        break;
                    default:
                        setLang("en");
                }
            }
        });
        
        cbAutoSave.setSelected(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });

        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/at/movielist/resources/windowicon.small.png")).getImage());

        listPathsToMovies.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
//                    int index = list.locationToIndex(evt.getPoint());
                    int index = list.getSelectedIndex();
                    pathsToMovies.remove(index);
                    listPathsToMovies.setListData(pathsToMovies.toArray(new String[pathsToMovies.size()]));
                    listPathsToMovies.updateUI();
                    System.out.println(index);
                }
            }
        });

        this.setIconImages(iconlist);

        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(false);
        this.setResizable(true);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitel = new javax.swing.JLabel();
        btOk = new javax.swing.JButton();
        pnThings = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbCLUE = new javax.swing.JLabel();
        cbLang = new javax.swing.JComboBox();
        lbUEPM = new javax.swing.JLabel();
        btChoosePath = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPathsToMovies = new javax.swing.JList();
        cbAutoSave = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImages(null);

        lbTitel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbTitel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/settingsBig.png"))); // NOI18N
        lbTitel.setText("Settings");
        getContentPane().add(lbTitel, java.awt.BorderLayout.PAGE_START);

        btOk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btOk.setText("Change settings!");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFinish(evt);
            }
        });
        getContentPane().add(btOk, java.awt.BorderLayout.PAGE_END);

        pnThings.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        lbCLUE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCLUE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCLUE.setText("Language:");
        jPanel1.add(lbCLUE);

        cbLang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English" }));
        jPanel1.add(cbLang);

        lbUEPM.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUEPM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbUEPM.setText("Paths to movies:");
        jPanel1.add(lbUEPM);

        btChoosePath.setText("Choose folder");
        btChoosePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onChooseFolder(evt);
            }
        });
        jPanel1.add(btChoosePath);

        pnThings.add(jPanel1, java.awt.BorderLayout.NORTH);

        listPathsToMovies.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listPathsToMovies);

        pnThings.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        cbAutoSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbAutoSave.setText("Auto save to loaded movies to a file?");
        pnThings.add(cbAutoSave, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnThings, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onFinish(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFinish
        onFinish();
    }//GEN-LAST:event_onFinish

    private void onChooseFolder(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onChooseFolder
        JFileChooser fc = new JFileChooser();
        fc.setPreferredSize(new Dimension(700, 500));

        FileFilter directoryFilter = new FileFilter() {
            @Override
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
            pathsToMovies.add(fc.getSelectedFile().toString());
            listPathsToMovies.setListData(pathsToMovies.toArray(new String[pathsToMovies.size()]));
            listPathsToMovies.updateUI();
        }
    }//GEN-LAST:event_onChooseFolder


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChoosePath;
    private javax.swing.JButton btOk;
    private javax.swing.JCheckBox cbAutoSave;
    private javax.swing.JComboBox cbLang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCLUE;
    protected javax.swing.JLabel lbTitel;
    private javax.swing.JLabel lbUEPM;
    private javax.swing.JList listPathsToMovies;
    private javax.swing.JPanel pnThings;
    // End of variables declaration//GEN-END:variables

    public void setLang(String lang) {

        switch (lang) {
            case "de":
                resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.GERMAN);
                break;
            case "en":
                resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", Locale.ENGLISH);
                break;
            case "es":
                resBundle = ResourceBundle.getBundle("at.movielist.src.ResourceBundle", new Locale("es"));
                break;
        }

        // Lang support
        lbTitel.setText(resBundle.getString("settings_titel"));
        lbUEPM.setText(resBundle.getString("setup_path"));
        lbCLUE.setText(resBundle.getString("setup_chooseLang"));
        btOk.setText(resBundle.getString("setup_finish"));
        btChoosePath.setText(resBundle.getString("setup_choosePath"));
        // END lang support
    }

    /**
     * 
     * @return true if all went right; false on error
     */
    protected boolean onFinish() {
        
        if (pathsToMovies.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please select at least one path", "Attention", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
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

        try {
            ConfigUtility.getInstance().saveConfigToFile(lang, cbAutoSave.isSelected(), pathsToMovies.toArray(new String[pathsToMovies.size()]));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occured! Maybe the config file is missing.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SettingsDLG.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
        return true;
    }
}
