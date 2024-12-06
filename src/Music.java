public abstract class Music {
    String title;
    
    public Music(String title){
        this.title = title;

    }

    public String getTitle(){
        return title;
    }

    public abstract String getDetails();
}
