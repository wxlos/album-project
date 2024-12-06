import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.*;

public class GUI extends JFrame implements ActionListener {

    //navigation
    JButton viewAlbumsButton = new JButton("View Albums");
    JButton addAlbumsButton = new JButton("Add Album");
    JButton searchAlbumsButton = new JButton("<html>Search<br>Albums</html>");

    //back buttons
    JButton albumsBackButton = new JButton("Back");
    JButton addBackButton = new JButton("Back");
    JButton searchBackButton = new JButton("Back");

    //panels
    JPanel mainPanel = new JPanel();

    JPanel albumsPanel = new JPanel();
    JPanel addAlbumPanel = new JPanel();
    JPanel searchPanel = new JPanel();
    JPanel cardPanel = new JPanel();

    JTextArea showAlbums = new JTextArea();

    Clip clip;

    

    //card layout to switch
    CardLayout card1 = new CardLayout();

    AlbumCollection collection = new AlbumCollection();

    public GUI(){
        frame();

        setupMainPanel();
        albumsPanel();

    }

    //frame to set up gui
    void frame(){
        cardPanel.setLayout(card1);

        setupMainPanel();
        albumsPanel();
        addPanel();
        searchPanel();

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(albumsPanel, "Albums");
        cardPanel.add(searchPanel, "Search");
        cardPanel.add(addAlbumPanel, "Add");
        
        this.add(cardPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.setResizable(false);
        this.setVisible(true);
    }

    //first thing the user sees
    void setupMainPanel(){

        mainPanel.setLayout(null);
        this.setTitle("Welcome To Your Album Collection!");
        
        String filepath = "src/music/wild.au";
        PlayMusic(filepath);

        viewAlbumsButton.setBounds(20, 20, 100, 50);
        addAlbumsButton.setBounds(300, 20, 100, 50);
        searchAlbumsButton.setBounds(580, 20, 100, 50);

        mainPanel.add(viewAlbumsButton);
        mainPanel.add(addAlbumsButton);
        mainPanel.add(searchAlbumsButton);

        viewAlbumsButton.addActionListener(this);
        addAlbumsButton.addActionListener(this);
        searchAlbumsButton.addActionListener(this);

        JLabel textLabel = new JLabel("Album Collection", SwingConstants.CENTER);
        textLabel.setBounds(300, 500, 300, 150);
        textLabel.setFont(new Font("Serif", Font.BOLD, 16));
        textLabel.setForeground(Color.WHITE);
        mainPanel.add(textLabel);

        SwingUtilities.invokeLater(() -> {
            try {
                InputStream imageStream = getClass().getClassLoader().getResourceAsStream("download.jpeg");
                BufferedImage img = ImageIO.read(imageStream);
        
                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(mainPanel.getWidth(), mainPanel.getHeight(), Image.SCALE_SMOOTH));
        
                JLabel imageLabel = new JLabel(imageIcon);
                imageLabel.setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight()); // Set the image to fill the panel
                mainPanel.add(imageLabel);
        
                mainPanel.revalidate();
                mainPanel.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
    }

    //shows the albums that have been created
    void albumsPanel(){

        albumsPanel.setLayout(null);
        albumsPanel.removeAll();

        JButton sort = new JButton("Sort");
        sort.setBounds(300, 20, 100, 50);
        albumsPanel.add(sort);

        JButton delete = new JButton("Delete");
        delete.setBounds(580, 20, 100, 50);
        albumsPanel.add(delete);

        albumsBackButton.setBounds(20,20,100,50);
        albumsPanel.add(albumsBackButton);
        albumsBackButton.addActionListener(this);

        showAlbums.setEditable(false);
        showAlbums.setText(collection.getAllAlbumsDetails());
        showAlbums.setLineWrap(true);
        showAlbums.setWrapStyleWord(true);
        showAlbums.setOpaque(false);
        
        JScrollPane scrollAlbums = new JScrollPane(showAlbums);
        scrollAlbums.setBounds(100,100,400,400);
        scrollAlbums.setOpaque(false);
        scrollAlbums.getViewport().setOpaque(false);
        albumsPanel.add(scrollAlbums);

        showAlbums.setText(collection.getAllAlbumsDetails());
        albumsPanel.revalidate();
        albumsPanel.repaint();

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String deleteAlbum = JOptionPane.showInputDialog("Which album would you like to delete");

              if (deleteAlbum != null && !deleteAlbum.trim().isEmpty()){
                Album delete = collection.getAlbum(deleteAlbum);

                if(delete != null){
                    int confirmation = JOptionPane.showConfirmDialog(GUI.this,"You are deleting \n " + delete, "Delete album", JOptionPane.YES_NO_OPTION);

                    if (confirmation == JOptionPane.YES_OPTION){
                        collection.getAlbums().remove(delete);
                        showAlbums.setText(collection.getAllAlbumsDetails());
                        albumsPanel.revalidate();
                        albumsPanel.repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(GUI.this,"Can't find album", "Cant find album", JOptionPane.ERROR_MESSAGE);
                }
              }
              
            }
            
        });

        sort.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               collection.sortAlbums();
               showAlbums.setText(collection.getAllAlbumsDetails());
               albumsPanel.revalidate();
               albumsPanel.repaint();

            }
            
        });

        SwingUtilities.invokeLater(() -> {
            try {
                InputStream imageStream = getClass().getClassLoader().getResourceAsStream("guts.jpg");
                BufferedImage img = ImageIO.read(imageStream);
        
                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance( albumsPanel.getWidth(),  albumsPanel.getHeight(), Image.SCALE_SMOOTH));
        
                JLabel imageLabel = new JLabel(imageIcon);
                imageLabel.setBounds(0, 0,  albumsPanel.getWidth(),  albumsPanel.getHeight()); // Set the image to fill the panel
                albumsPanel.add(imageLabel);
        
                albumsPanel.revalidate();
                albumsPanel.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        albumsPanel.revalidate();
        albumsPanel.repaint();

    }


    //user is able to add their albums
    void addPanel(){
       addAlbumPanel.setLayout(null);

       addAlbumPanel.setBackground(Color.pink);
       SwingUtilities.invokeLater(() -> {
        try {
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("ari.jpg");
            BufferedImage img = ImageIO.read(imageStream);
    
            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance( addAlbumPanel.getWidth(),  addAlbumPanel.getHeight(), Image.SCALE_SMOOTH));
    
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(0, 0,  addAlbumPanel.getWidth(),  addAlbumPanel.getHeight()); // Set the image to fill the panel
            addAlbumPanel.add(imageLabel);
    
            addAlbumPanel.revalidate();
            addAlbumPanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
       
       JButton addAlbum = new JButton("Add Album");
       addAlbum.setBounds(580, 20, 100, 50);
       addAlbumPanel.add(addAlbum);

       addAlbumPanel.add(addBackButton);
       addBackButton.setBounds(20,20,100,50);
       addBackButton.addActionListener(this);

       addAlbum.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            String albumName = JOptionPane.showInputDialog("Enter Album Name: ");
            if (albumName == null || albumName.trim().isEmpty()) {
                return;
            }
    
            String artistName = JOptionPane.showInputDialog("Enter Artist Name: ");
            if (artistName == null || artistName.trim().isEmpty()) {
                return;
            }
    
            String genre = JOptionPane.showInputDialog("Enter Genre");
            if (genre == null || genre.trim().isEmpty()) {
                return;
            }
    
            int addSong = JOptionPane.showConfirmDialog(GUI.this, "Would you like to add a song to this album", "Add song", JOptionPane.YES_NO_OPTION);
            if (addSong == JOptionPane.CANCEL_OPTION) {
                return; 
            }
    
            String song = null;
            if (addSong == JOptionPane.YES_OPTION) {
                song = JOptionPane.showInputDialog(GUI.this, "Add a song!", "Add song", JOptionPane.QUESTION_MESSAGE);
                if (song == null || song.trim().isEmpty()) {
                    return;
                }
            }
    
            int favorite = JOptionPane.showConfirmDialog(GUI.this, "Would you like to favorite this album?", "Star", JOptionPane.YES_NO_OPTION);
            if (favorite == JOptionPane.CANCEL_OPTION) {
                return;
            }
    
            Album album = new Album(albumName, artistName, genre);
            if (song != null) {
                album.addSong(new Song(song, artistName));
            }
            
            if (favorite == JOptionPane.YES_OPTION) {
                album.setFavorite(true);
            }
        
           collection.addAlbum(album);
        
           System.out.println(album.albumDetails());
           showAlbums.setText(collection.getAllAlbumsDetails());

           card1.show(cardPanel, "Albums");
          
           albumsPanel.revalidate();
           albumsPanel.repaint();
           
        } 
       });
    }

    //user can search and find their albums
    void searchPanel(){
        searchPanel.setLayout(null);

        searchPanel.setBackground(Color.orange);

        searchBackButton.setBounds(20,20,100,50);
        searchPanel.add(searchBackButton);
        searchBackButton.addActionListener(this);

        JTextField searchAlbum = new JTextField();
        searchAlbum.setBounds(120, 20, 200, 20);
        searchPanel.add(searchAlbum);

        JButton search = new JButton("Search");
        search.setBounds(580, 20, 100, 50);
        searchPanel.add(search);

        JTextArea results = new JTextArea();
        results.setEditable(false);
        results.setBounds(120, 40, 300, 300);
        results.setOpaque(false);

        searchPanel.add(results);

        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               String name = searchAlbum.getText();
               Album album = collection.getAlbum(name);

               if(album != null){
                    results.setText(album.albumDetails());
               } else {
                    results.setText("Cannot find album " + name);
               }
            }
            
        });

        SwingUtilities.invokeLater(() -> {
            try {
                InputStream imageStream = getClass().getClassLoader().getResourceAsStream("bury.jpg");
                BufferedImage img = ImageIO.read(imageStream);
        
                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance( searchPanel.getWidth(),  searchPanel.getHeight(), Image.SCALE_SMOOTH));
        
                JLabel imageLabel = new JLabel(imageIcon);
                imageLabel.setBounds(0, 0,  searchPanel.getWidth(),  searchPanel.getHeight()); 
                searchPanel.add(imageLabel);
        
                searchPanel.revalidate();
                searchPanel.repaint();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    Clip currentClip = null;

    //music played for each panel
    void PlayMusic(String location){  
        new Thread(() -> {  
            try{  
                if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
                }

             File music = new File(location);  
                if (music.exists()){  
                 AudioInputStream audio = AudioSystem.getAudioInputStream(music);  
                 currentClip = AudioSystem.getClip();  
                 currentClip.open(audio);  
                 currentClip.start();  
                } else {  
                System.out.println("Error: File not found - " + music);  
                }  
             } catch (Exception e) {
            }  
        }).start();  
    }

    //switches between panels
    @Override
    public void actionPerformed(ActionEvent e) {
        
       if (e.getSource() == viewAlbumsButton){
            card1.show(cardPanel, "Albums");
            this.setTitle("Your Albums!!");
            PlayMusic("src/music/pretty.au");
       } else if (e.getSource() == addAlbumsButton){
            card1.show(cardPanel, "Add");
            this.setTitle("Add Your Albums!!");
            PlayMusic("src/music/intro.au");
       } else if (e.getSource() == searchAlbumsButton){
            card1.show(cardPanel, "Search");
            this.setTitle("Find Your Album!!");
            PlayMusic("src/music/dogs.au");
       } else if (e.getSource() == albumsBackButton || e.getSource() == addBackButton || e.getSource() == searchBackButton){
            card1.show(cardPanel, "Main");
            PlayMusic("src/music/wild.au");
       }
    }

}
    

    