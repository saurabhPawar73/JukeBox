package Interfaces;

import Implementations.PodcastImpl;
import PojoClasses.Podcasts;

import java.util.List;

public interface PodcastInterface
{
    List<Podcasts> displayAllPodcasts();
    void sortPodcasts();
    PodcastImpl searchPodcast();
}
