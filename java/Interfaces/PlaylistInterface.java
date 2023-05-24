package Interfaces;

import Implementations.PlaylistImpl;
import PojoClasses.Playlists;

import java.sql.SQLException;
import java.util.List;

public interface PlaylistInterface {
  List<Playlists> displayAllPlaylists();

  void playSongFromPlaylist() throws SQLException;


  void addSongToAPlaylist();


}
