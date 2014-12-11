package ui;

import bl.ConfigUtility;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SettingsDLG extends javax.swing.JDialog {

    private String pathtomovies;
    private ConfigUtility cu;
    private LinkedList<Image> iconlist = new LinkedList<Image>();

    public SettingsDLG(java.awt.Frame parent, boolean modal, String ptm, ConfigUtility c) {
        super(parent, modal);
        initComponents();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });

        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.large.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.medium.png")).getImage());
        iconlist.add(new ImageIcon(this.getClass().getResource("/resources/windowicon.small.png")).getImage());

        this.setIconImages(iconlist);

        pathtomovies = ptm;
        lbPath.setText(ptm);
        cu = c;

        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUe = new javax.swing.JLabel();
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

        lbUe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbUe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/settingsBig.png"))); // NOI18N
        lbUe.setText("Settings");
        getContentPane().add(lbUe, java.awt.BorderLayout.PAGE_START);

        lbVers.setText("v1.0a");
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
        } else {
            JOptionPane.showMessageDialog(this, "Error!", "No folder choosen!", 0);
        }
    }//GEN-LAST:event_onChooseFolder


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChoosePath;
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox cbLang;
    private javax.swing.JLabel lbCLUE;
    private javax.swing.JLabel lbPath;
    private javax.swing.JLabel lbUEPM;
    private javax.swing.JLabel lbUe;
    private javax.swing.JLabel lbVers;
    private javax.swing.JPanel pnPath;
    private javax.swing.JPanel pnThings;
    // End of variables declaration//GEN-END:variables

}
