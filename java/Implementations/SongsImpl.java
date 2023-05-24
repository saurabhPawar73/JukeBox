package Implementations;


import Interfaces.SongsInterface;
import PlaySong.PlaySong;
import PojoClasses.ConnectionToDatabase;
import PojoClasses.Songs;

import javax.sql.rowset.RowSetWarning;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SongsImpl implements SongsInterface {
    ConnectionToDatabase con = new ConnectionToDatabase();
    Scanner sc=new Scanner(System.in);


    @Override
    public List<Songs> displayAllSongs() {
        List<Songs> songsList = new ArrayList<>();
        Connection connection = con.getConnectionDatabase();
        try {
            String query = "select * from songs";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Songs song;
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCOunt = rsmd.getColumnCount();
            while (rs.next()) {
                int songId = rs.getInt(1);
                String songName = rs.getString(2);
                String songDuration = rs.getString(3);
                String artist = rs.getString(4);
                String language = rs.getString(5);
                String songfilePath = rs.getString(6);
                int pid = rs.getInt(7);
                song = new Songs(songId, songName, songDuration, artist, language, songfilePath, pid);
                songsList.add(song);
                for (int i = 1; i <= columnCOunt; i++) {
                    if (i > 1)
                        System.out.println(", ");
                    String value = rs.getString(i);
                    System.out.println(rsmd.getColumnName(i) + " " + value);
                }
                System.out.println("");
            }


        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
        return songsList;
    }

    @Override
    public List<Songs> sortedSongs() {
        List<Songs> sortedSongsList = new ArrayList<>();
        try {
            Connection connection = con.getConnectionDatabase();
            String query = "select * from songs order by song_name";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet set = ps.executeQuery();
            ResultSetMetaData rsmd = set.getMetaData();
            int ColumnCount = rsmd.getColumnCount();
            while (set.next()) {
                for (int i = 1; i <= ColumnCount; i++) {
                    if (i > 1)
                        System.out.println(", ");
                    String value = set.getString(i);
                    System.out.println(rsmd.getColumnName(i) + " " + value);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Songs searchSongbyId() {

        Connection connection = con.getConnectionDatabase();
        System.out.println("enter the song id of a song you want to play");
        String word = "select * from songs where song_id=?";
        int songId= sc.nextInt();
        try {
            PreparedStatement ps2 = connection.prepareStatement(word);
            ps2.setInt(1,songId);
            ResultSet songSet = ps2.executeQuery();
            ResultSetMetaData rsmdSong = songSet.getMetaData();
            int count = rsmdSong.getColumnCount();
            while (songSet.next()) {
                for (int i = 1; i <=count; i++) {
                    if (i > 1)
                        System.out.println(", ");
                    String value = songSet.getString(i);
                    System.out.println(rsmdSong.getColumnName(i) + " " + value);
                }
                System.out.println("");
            }
            System.out.println("press 1 to play the song");
            int songPlay=sc.nextInt();
            if(songPlay==1)
            {
                String query="select file_path from songs where song_id=?";
                PreparedStatement ps3=connection.prepareStatement(query);
                ps3.setInt(1,songId);
                ResultSet playSongSet=ps3.executeQuery();
                while (playSongSet.next())
                {
                    String filepath=playSongSet.getString(1);
                    PlaySong playSearchedSong=new PlaySong();
                    playSearchedSong.readAudio(filepath,1);
                }
            }


        }
    catch(SQLException e)

    {
        throw new RuntimeException(e);
    }
        return null;

 }

        public void searchSongByName() throws SQLException {
            Connection connection=con.getConnectionDatabase();
            try
            {
                String query="select * from songs where song_name=?";
                System.out.println("enter a song name");
                String name= sc.nextLine();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                ResultSet rs=preparedStatement.executeQuery();
                ResultSetMetaData rsmd=rs.getMetaData();
                int column= rsmd.getColumnCount();
                System.out.println("Here are the details of song you searched");
                String songPath="";
                String query2="select file_path from songs where song_name=?";
                PreparedStatement ps2=connection.prepareStatement(query2);
                ps2.setString(1,name);
                ResultSet set=ps2.executeQuery();
                while (rs.next()) {
                    for (int i = 1; i <=column; i++) {
                        if (i > 1)
                            System.out.println(", ");
                        String value = rs.getString(i);
                        System.out.println(rsmd.getColumnName(i) + " " + value);
                    }
                    System.out.println("");
                }

                    while (set.next())
                    {
                        songPath=set.getString(1);

                        PlaySong playSong=new PlaySong();
                        playSong.readAudio(songPath, 1);
                    }
                }
            catch (SQLException sqlException)
            {
                throw new RuntimeException();
            }


            }
}


