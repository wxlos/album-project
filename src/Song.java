//keeps track of songs
public class Song extends Music {
    String artist;
    boolean isFavorited;
   
    public Song(String title, String artist){
        super(title);
        this.artist = artist;
        this.isFavorited = false;
    }

    public String getArtist(){
        return artist;
    }

    public boolean isFavorited(){
        return isFavorited;
    }

    void favorited(){
        this.isFavorited = true;
    }

    void unFavorited(){
        this.isFavorited = false;
    }

    @Override
    public String getDetails(){
        return "Song names: " + "-" + getTitle() + "Artist: " + artist + (isFavorited ? " (Favorited)" : "");
    }

    @Override
    public String toString(){
        return "Song names: \n" +  "-" + getTitle()  + (isFavorited ? " (Favorited)" : "");

    }
}
