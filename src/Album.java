import java.util.ArrayList;

//for album information
public class Album {
    String title;
    String artist;
    String genre;
    ArrayList<Song> songs;
    boolean isFavorite;

    public Album(String title, String artist, String genre){
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.songs = new ArrayList<>();
        this.isFavorite = false;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public String getGenre(){
        return genre;
    }

    public boolean isFavorite(){
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void addSong(Song song){
        songs.add(song);
    }
    
    public String albumDetails(){
        StringBuilder summary = new StringBuilder();
        if(isFavorite){
            summary.append("⭐ \n");
        }
        summary.append("Album: ").append(title).append("\n")
        .append("Musical Artist: ").append(artist).append("\n")
        .append("Music Genre: ").append(genre).append("\n");

        if(!songs.isEmpty()){
            summary.append("Songs:\n");
            for(Song song : songs){
                summary.append(song.toString());
            }
        } else {
            summary.append(", \nNo songs");
        }

        return summary.toString();
    }
    

    @Override
    public String toString(){
        return albumDetails();
    }
    
}
