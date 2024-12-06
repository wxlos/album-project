import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//manages the albums the user creates
public class AlbumCollection {
    ArrayList<Album> albums;
    
    public AlbumCollection(){
        albums = new ArrayList<>();
    }

    void addAlbum(Album album){
        albums.add(album);
    }

    ArrayList<Album> getAlbums(){
        return albums;
    }

    public Song getSong(String title){
        for (Album album : albums){
            for (Song song : album.getSongs()){
                if (song.getTitle().equals(title)){
                    return song;
                }
            }
        }
        return null;
    }

    void sortAlbums(){
        Collections.sort(albums, new Comparator<Album>(){

            @Override
            public int compare(Album o1, Album o2) {
               return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
    }

    public Album getAlbum(String title){
        for (Album album : albums){
            if (album.getTitle().equals(title)){
                return album;
            }
        }
        return null;
    }

    public String getAllAlbumsDetails(){
        if(albums.isEmpty()){
            return "You have no albums";
        }

        StringBuilder albumDetails = new StringBuilder();
        for (Album album : albums){

            if (album.isFavorite()){
                albumDetails.append("⭐ \n");
            }
            albumDetails.append("Album Title: ").append(album.getTitle()).append("\n")
            .append("Musical Artist: ").append(album.getArtist()).append("\n")
            .append("Music Genre: ").append(album.getGenre()).append("\n");

            if (!album.getSongs().isEmpty()){
                albumDetails.append("Songs: \n");
                for (Song song : album.getSongs()){
                    albumDetails.append(song.getTitle());
                } 
            }
        }
        return albumDetails.toString();
    }
}
