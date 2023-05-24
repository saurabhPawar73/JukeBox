package Interfaces;

import Implementations.SongsImpl;
import PojoClasses.Songs;

import java.util.List;

public interface SongsInterface {
    List<Songs> displayAllSongs();
    List<Songs> sortedSongs();

    Songs searchSongbyId();
}
