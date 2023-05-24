package Implementations;

import Interfaces.PlaylistInterface;
import PlaySong.PlayAudio;
import PlaySong.PlaySong;
import PojoClasses.ConnectionToDatabase;
import PojoClasses.Playlists;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistImpl implements PlaylistInterface {
    ConnectionToDatabase connection=new ConnectionToDatabase();
    Scanner sc=new Scanner(System.in);
    @Override
    public List<Playlists> displayAllPlaylists() {
        List<Playlists> playlists=new ArrayList<>();
        Connection con=connection.getConnectionDatabase();
        String displayQuery="select * from playlist";
        try {
            PreparedStatement pst=con.prepareStatement(displayQuery);
            ResultSet displaySet=pst.executeQuery();
            ResultSetMetaData rsmd=displaySet.getMetaData();
            int column= rsmd.getColumnCount();
            Playlists playlists1;

            while (displaySet.next()) {
                int playlist_id=displaySet.getInt(1);
                String playlist_name=displaySet.getString(2);
                String user_id=displaySet.getString(3);
                playlists1=new Playlists(playlist_id, playlist_name, user_id);
                playlists.add(playlists1);
                for (int i = 1; i <=column; i++) {
                    if (i > 1)
                        System.out.println(", ");
                    String value = displaySet.getString(i);
                    System.out.println(rsmd.getColumnName(i) + " " + value);
                }
                System.out.println("");
            }
            System.out.println("enter playlist id to play ");
            int pid=sc.nextInt();
            String playquery="select file_path from songs where p_id=?";
            PreparedStatement pst2=con.prepareStatement(playquery);
            pst2.setInt(1,pid);
            ResultSet playSet=pst2.executeQuery();
            String songfilePath="";
            while (playSet.next())
            {

                songfilePath=playSet.getString(1);
                PlaySong playSong=new PlaySong();
                playSong.readAudio(songfilePath,1);
            }
            }
        catch (SQLException sqlException)
        {
            throw new RuntimeException();
        }
        return playlists;
    }

    @Override
    public void playSongFromPlaylist() {
        Connection con=connection.getConnectionDatabase();


        System.out.println("select any one playlist\n" +
                "1. Hindi Songs\n" +
                "2. Marathi Songs\n" +
                "3. English Songs\n" +
                "4. South Songs\n" +
                "5. your playlist\n" +
                "6. Roll Back");
        int choice= sc.nextInt();
            switch (choice){
                case 1:
                    String playHindiSong = "select file_path from songs where p_id=1 and song_name=?";
                    System.out.println("enter name of Song");
                    sc.nextLine();
                    String songName = sc.nextLine();
                    try {

                            PreparedStatement songSt = con.prepareStatement(playHindiSong);
                            songSt.setString(1, songName);
                            ResultSet songSet = songSt.executeQuery();
                            String path = "";
                            while (songSet.next()) {
                                path = songSet.getString(1);
                                PlaySong playSong1 = new PlaySong();
                                playSong1.readAudio(path, 1);
                            }
                        }
                        catch (SQLException e)
                        {
                            throw new RuntimeException();
                        }
                        break;
                case 2:
                    String playMarathiSong = "select file_path from songs where p_id=2 and song_name=?";
                    System.out.println("enter name of Song");
                    sc.nextLine();
                    String songNameM = sc.nextLine();
                    try {

                        PreparedStatement songSt = con.prepareStatement(playMarathiSong);
                        songSt.setString(1, songNameM);
                        ResultSet songSetM = songSt.executeQuery();
                        String pathM = "";
                        while (songSetM.next()) {
                            pathM = songSetM.getString(1);
                            PlaySong playSong1 = new PlaySong();
                            playSong1.readAudio(pathM, 1);
                        }
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException();
                    }
                    break;

                case 3:
                    String playEngSong = "select file_path from songs where p_id=3 and song_name=?";
                    System.out.println("enter name of Song");
                    sc.nextLine();
                    String songNameE = sc.nextLine();
                    try {

                        PreparedStatement songSt = con.prepareStatement(playEngSong);
                        songSt.setString(1, songNameE);
                        ResultSet engSongSet = songSt.executeQuery();
                        String engPath = "";
                        while (engSongSet.next()) {
                            engPath= engSongSet.getString(1);
                            PlaySong playSong1 = new PlaySong();
                            playSong1.readAudio(engPath, 1);
                        }
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException();
                    }
                    break;
                case 4:
                    String playSouthSong = "select file_path from songs where p_id=4 and song_name=?";
                    System.out.println("enter name of Song");
                    sc.nextLine();
                    String songNameS= sc.nextLine();
                    try {

                        PreparedStatement songSt = con.prepareStatement(playSouthSong);
                        songSt.setString(1, songNameS);
                        ResultSet songSetS = songSt.executeQuery();
                        String pathS = "";
                        while (songSetS.next()) {
                            pathS = songSetS.getString(1);
                            PlaySong playSong1 = new PlaySong();
                            playSong1.readAudio(pathS, 1);
                        }
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException();
                    }
                    break;
                case 5:
                    System.out.println("enter your playlist id");
                    int id=sc.nextInt();
                    String pidQuery="select file_path from songs where p_id=?";
                    try {
                        PreparedStatement psPid=con.prepareStatement(pidQuery);
                        psPid.setInt(1,id);
                        ResultSet setpid=psPid.executeQuery();

                        if(setpid.next()==false)
                            System.out.println("no such playlist");
                        String path1="";
                        while (setpid.next()){
                            path1=setpid.getString(1);
                            PlaySong playSong2=new PlaySong();
                            playSong2.readAudio(path1,1);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                case 6:
                    UserImpl user=new UserImpl();
                    try {
                        user.welcomeScreen();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


            }

        }


    @Override
    public void addSongToAPlaylist() {
        System.out.println("enter id for your playList");
        int id=sc.nextInt();
        System.out.println("Please give a name to your Playlist");
        String newPlaylist=sc.next();
        Connection con=connection.getConnectionDatabase();
        try {
            String insertPLaylist = "insert into playlist values(?, ?, null)";
            PreparedStatement plst = con.prepareStatement(insertPLaylist);
            plst.setInt(1,id);
            plst.setString(2,newPlaylist);
            int newRow=plst.executeUpdate();
                int i=1;
                while(i==1) {
                    System.out.println("enter the song id you want to add in your playlist");
                    int songId = sc.nextInt();
                    String queryid = "update songs set p_id=? where song_id=?";
                    PreparedStatement updStatement = con.prepareStatement(queryid);
                    updStatement.setInt(1, id);
                    updStatement.setInt(2, songId);
                    int rowUpd = updStatement.executeUpdate();
                    System.out.println("enter 1 to add more");
                    i = sc.nextInt();
                }

                UserImpl user=new UserImpl();
                user.welcomeScreen();

        }
        catch (SQLException sqlException)
        {
            throw new RuntimeException();
        }
    }
}
