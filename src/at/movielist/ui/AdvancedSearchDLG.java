package at.movielist.ui;

import at.movielist.tmdb.APItmdb;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdvancedSearchDLG extends javax.swing.JDialog {

    /**
     * Membervars
     */
    private static HashMap<Integer, String> genres = new HashMap<Integer, String>();
    private MainUI mui;

    /**
     * Constructor
     *
     * @param parent
     * @param modal
     */
    public AdvancedSearchDLG(java.awt.Frame parent, boolean modal, ResourceBundle res) {
        super(parent, modal);
        initComponents();
        
        mui = (MainUI) parent;

        this.setTitle("Advanced Search");
        this.setSize(300, 100);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        this.btSearch.setText(res.getString("main_search_lable"));
        this.lbGenre.setText(res.getString("main_information_TMDB_genre"));
        
        LinkedList<String> genrenames = new LinkedList<String>();
        cbGenre.removeAllItems();
        cbGenre.addItem(res.getString("genre_all"));

        try {
            APItmdb.getGenres(res.getLocale().getLanguage());
            genres = APItmdb.returnGenres();

            for (Map.Entry<Integer, String> entry : genres.entrySet()) {
                genrenames.add(entry.getValue());
            }

            Collections.sort(genrenames);

            for (String s : genrenames) {
                cbGenre.addItem(s);
            }

        } catch (Exception ex) {
            Logger.getLogger(AdvancedSearchDLG.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        lbGenre = new javax.swing.JLabel();
        cbGenre = new javax.swing.JComboBox();
        btSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnMain.setLayout(new java.awt.BorderLayout());

        lbGenre.setText("Genre:");
        pnMain.add(lbGenre, java.awt.BorderLayout.WEST);

        cbGenre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnMain.add(cbGenre, java.awt.BorderLayout.CENTER);

        btSearch.setText("Search");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSearch(evt);
            }
        });
        pnMain.add(btSearch, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(pnMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onSearch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSearch
        String genre = cbGenre.getSelectedItem().toString();
        
        mui.AdvancedSearch(genre);
        this.dispose();
    }//GEN-LAST:event_onSearch

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSearch;
    private javax.swing.JComboBox cbGenre;
    private javax.swing.JLabel lbGenre;
    private javax.swing.JPanel pnMain;
    // End of variables declaration//GEN-END:variables
}
