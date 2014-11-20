package ui;

import bl.ConfigUtility;
import bl.MovieListModel;
import bl.MovieLoader;

public class MainUI extends javax.swing.JFrame {

    private MovieListModel mlm = new MovieListModel();
    private MovieLoader ml;
    private String userdocs, pathtomovies;
    private ConfigUtility cu;
    
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
        ml = new MovieLoader(pathtomovies);
        
        mlm.setList(ml.getMovies());
        liMovies.updateUI();
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

        miOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fileopen.png"))); // NOI18N
        miOpen.setText("Open MovieList File");
        meFile.add(miOpen);

        mbBar.add(meFile);

        setJMenuBar(mbBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane epInfos;
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
}
