package at.movielist.ui;

import at.movielist.beans.TMDBMovie;
import at.movielist.tmdb.APItmdb;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FetchedMoviesDLG extends javax.swing.JDialog {

    private int selMatch = 0;
    private URL poster = null;
    private final ArrayList<TMDBMovie> matches;

    public FetchedMoviesDLG(java.awt.Frame parent, boolean modal, ArrayList<TMDBMovie> matches, String moviename) {
        super(parent, modal);
        initComponents();
        this.setSize(400, 400);
        this.setResizable(false);

        this.setLocationRelativeTo(parent);

        this.matches = matches;

        LinkedList<String> arr = new LinkedList<>();

        for (TMDBMovie tm : matches) {
            arr.add(tm.getOriginalTitle() + "  -  (" + tm.getReleaseYear() + ")");
        }

        liSelMovies.setListData(arr.toArray());
        lbForMovie.setText(moviename);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUe = new javax.swing.JLabel();
        btNext = new javax.swing.JButton();
        pn = new javax.swing.JPanel();
        spListe = new javax.swing.JScrollPane();
        liSelMovies = new javax.swing.JList();
        lbForMovie = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbUe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbUe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/at/movielist/resources/fetchdlg.png"))); // NOI18N
        lbUe.setText("Select one of the matches");
        getContentPane().add(lbUe, java.awt.BorderLayout.PAGE_START);

        btNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btNext.setText("Next");
        btNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onNext(evt);
            }
        });
        getContentPane().add(btNext, java.awt.BorderLayout.PAGE_END);

        pn.setLayout(new java.awt.BorderLayout());

        liSelMovies.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        liSelMovies.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        spListe.setViewportView(liSelMovies);

        pn.add(spListe, java.awt.BorderLayout.CENTER);

        lbForMovie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbForMovie.setText("MovieName");
        pn.add(lbForMovie, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(pn, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onNext(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onNext
        selMatch = liSelMovies.getSelectedIndex();
        try {
            this.poster = APItmdb.getInstance().getPoster(matches.get(selMatch).getPoster_url());
        } catch (Exception ex) {
            Logger.getLogger(FetchedMoviesDLG.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_onNext


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btNext;
    private javax.swing.JLabel lbForMovie;
    private javax.swing.JLabel lbUe;
    private javax.swing.JList liSelMovies;
    private javax.swing.JPanel pn;
    private javax.swing.JScrollPane spListe;
    // End of variables declaration//GEN-END:variables
    public int getSelMatch() {
        return selMatch;
    }

    public URL getPoster() {
        return poster;
    }
    
}
