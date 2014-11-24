package ui;

import beans.Movie;
import bl.ConfigUtility;
import bl.MovieCompare;
import bl.MovieListModel;
import bl.MovieLoader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainUI extends javax.swing.JFrame {

    private MovieListModel mlm = new MovieListModel();
    private MovieLoader ml;
    private String userdocs, pathtomovies;
    private ConfigUtility cu;
    private LinkedList<Movie> movielist = new LinkedList<Movie>();

    private String pathtoconf;

    public MainUI(String ud) {
        initComponents();
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);

        epInfos.setContentType("text/html");

        userdocs = ud;
        pathtoconf = userdocs + "\\movielist.conf";

        cu = new ConfigUtility(pathtoconf);

        liMovies.setModel(mlm);

        cu.getConfig();
        pathtomovies = cu.getPath();
        
        liMovies.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    printInformation(liMovies.getSelectedIndex());
                }
            }
        });
    }

    private void printInformation(int selectedIndex) {
        Movie m = movielist.get(selectedIndex);
        
        StringBuilder sb = new StringBuilder();
        sb.append("<body style='font-family:Arial;font-size:14pt'><center><h1 style='color:#00d76f'>Movie Information</h1></center>");
        sb.append("<hr width='80%' />");
        sb.append("<br><center><table>");
        sb.append("<tr><td><strong style='color:#00bda5'>Titel:</strong></td><td>").append(m.getName()).append("</td></tr>");
        sb.append("<tr><td><strong style='color:#00bda5'>Duration:</strong></td><td>").append(m.getDuration()).append("</td></tr>");
        sb.append("<tr><td><strong style='color:#00bda5'>Resolution:</strong></td><td>").append(m.getWidth()).append("x").append(m.getHeight()).append("</td></tr>");
        sb.append("<tr><td><strong style='color:#00bda5'>Display Aspect Ratio:</strong></td><td>").append(m.getAspectratio()).append("</td></tr>");
        sb.append("<tr><td><strong style='color:#00bda5'>Fileextension:</strong></td><td>").append(m.getFileextension()).append("</td></tr>");
        sb.append("<tr><td><strong style='color:#00bda5'>Filesize:</strong></td><td>").append(m.getFilesize()).append("</td></tr>");
        sb.append("</table></center></body>");

        epInfos.setText(sb.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLeft = new javax.swing.JPanel();
        lbThings = new javax.swing.JLabel();
        pnListe = new javax.swing.JPanel();
        spList = new javax.swing.JScrollPane();
        liMovies = new javax.swing.JList();
        pnRight = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        epInfos = new javax.swing.JEditorPane();
        mbBar = new javax.swing.JMenuBar();
        meFile = new javax.swing.JMenu();
        miOpen = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        pnLeft.setLayout(new java.awt.BorderLayout());

        lbThings.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pnLeft.add(lbThings, java.awt.BorderLayout.PAGE_END);

        pnListe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnListe.setLayout(new java.awt.BorderLayout());

        liMovies.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spList.setViewportView(liMovies);

        pnListe.add(spList, java.awt.BorderLayout.CENTER);

        pnLeft.add(pnListe, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnLeft);

        pnRight.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N
        pnRight.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(epInfos);

        pnRight.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnRight);

        mbBar.setBackground(new java.awt.Color(255, 255, 255));

        meFile.setText("File");
        meFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        miOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        miOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fileopen.png"))); // NOI18N
        miOpen.setText("Open MovieList File");
        meFile.add(miOpen);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/loadmovies.png"))); // NOI18N
        jMenuItem1.setText("Load Movies");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onLoadMovies(evt);
            }
        });
        meFile.add(jMenuItem1);

        mbBar.add(meFile);

        setJMenuBar(mbBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onLoadMovies(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onLoadMovies
        ProgressbarDLG pd = new ProgressbarDLG(this, false);
        pd.setVisible(true);
        
        ml = new MovieLoader(pathtomovies, pd);
        
        loadMovies();
    }//GEN-LAST:event_onLoadMovies

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane epInfos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbThings;
    private javax.swing.JList liMovies;
    private javax.swing.JMenuBar mbBar;
    private javax.swing.JMenu meFile;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnListe;
    private javax.swing.JPanel pnRight;
    private javax.swing.JScrollPane spList;
    // End of variables declaration//GEN-END:variables

    private void loadMovies() {          
        ml.getMovies(this);
    }
    
    public void setList(LinkedList<Movie> liste)
    {
        movielist = liste;
        
        Collections.sort(movielist, new MovieCompare());
        
        System.out.println(""+movielist.size());
        
        mlm.setList(movielist);
        liMovies.updateUI();
    }
}
