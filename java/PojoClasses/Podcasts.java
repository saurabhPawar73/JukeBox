package PojoClasses;

public class Podcasts
{
    private int podcast_id;
    private String podcastName;
    private String podcast_duration;
    private int podcast_episode;
    private String podcast_filePath;


    public int getPodcast_id() {
        return podcast_id;
    }

    public void setPodcast_id(int podcast_id) {
        this.podcast_id = podcast_id;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getPodcast_duration() {
        return podcast_duration;
    }

    public void setPodcast_duration(String podcast_duration) {
        this.podcast_duration = podcast_duration;
    }

    public int getPodcast_episode() {
        return podcast_episode;
    }

    public void setPodcast_episode(int podcast_episode) {
        this.podcast_episode = podcast_episode;
    }

    public String getPodcast_filePath() {
        return podcast_filePath;
    }

    public void setPodcast_filePath(String podcast_filePath) {
        this.podcast_filePath = podcast_filePath;
    }



    public Podcasts(int podcast_id, String podcastName, String podcast_duration, int podcast_episode, String podcast_filePath) {
        this.podcast_id = podcast_id;
        this.podcastName = podcastName;
        this.podcast_duration = podcast_duration;
        this.podcast_episode = podcast_episode;
        this.podcast_filePath = podcast_filePath;
    }

    @Override
    public String toString() {
        return "Podcasts{" +
                "podcast_id=" + podcast_id +
                ", podcastName='" + podcastName + '\'' +
                ", podcast_duration='" + podcast_duration + '\'' +
                ", podcast_episode='" + podcast_episode + '\'' +
                ", podcast_filePath='" + podcast_filePath + '\'' +
                '}';
    }
}
