package PojoClasses;

public class Songs
{
    private int songId;
    private String songName;
    private String songDuration;
    private String artist;
    private String language;
    private String SongfilePath;
    private int pid;

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSongfilePath() {
        return SongfilePath;
    }

    public void setSongfilePath(String songfilePath) {
        SongfilePath = songfilePath;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Songs(int songId, String songName, String songDuration, String artist, String language, String songfilePath, int pid) {
        this.songId = songId;
        this.songName = songName;
        this.songDuration = songDuration;
        this.artist = artist;
        this.language = language;
        SongfilePath = songfilePath;
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", songDuration='" + songDuration + '\'' +
                ", artist='" + artist + '\'' +
                ", language='" + language + '\'' +
                ", SongfilePath='" + SongfilePath + '\'' +
                ", pid=" + pid +
                '}';
    }
}
