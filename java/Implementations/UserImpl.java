package Implementations;

import Interfaces.UserInterface;
import PojoClasses.ConnectionToDatabase;
import PojoClasses.Playlists;
import PojoClasses.Songs;

import java.sql.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements UserInterface {
    Scanner sc=new Scanner(System.in);
    Connection con=null;
SongsImpl songs=new SongsImpl();

    ConnectionToDatabase connection=new ConnectionToDatabase();
    PlaylistImpl playlist=new PlaylistImpl();
    PodcastImpl podcastImpl=new PodcastImpl();
    public void welcomeScreen() throws SQLException {
        System.out.println("Welcome to Jukebox....choose any 1\n" +
                "1.Songs\n" +
                "2. Playlists\n" +
                "3. Podcasts\n" +
                "4. exit");
                   int choice=sc.nextInt();
                switch (choice)
                {
                    case 1:
                        System.out.println("Choose one among the following\n" +
                                "1. Display all songs\n" +
                                "2. Sort Songs\n" +
                                "3. Search a song");
                            int songChoice=sc.nextInt();
                        List<Songs> songList;
                            if(songChoice==1)
                           songList=songs.displayAllSongs();
                            else if (songChoice==2) {
                                songList=songs.sortedSongs();
                            }
                            else if (songChoice==3){
                                System.out.println("press 1 to search song by ID\n" +
                                        "press 2 to search song by name");
                                int searchSongby=sc.nextInt();
                                if (searchSongby==1)
                                songs.searchSongbyId();
                                else {
                                    try {
                                        songs.searchSongByName();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                            break;
                    case 2:
                        System.out.println("Choose any one\n" +
                                "1. display all playlists\n" +
                                "2. search and play a song in a playlist \n" +
                                "3. create a new playlist");
                        int playlistoption=sc.nextInt();
                        if(playlistoption==1){
                            List<Playlists> playlists=playlist.displayAllPlaylists();
                        } else if (playlistoption==2) {
                            playlist.playSongFromPlaylist();
                        }else if (playlistoption==3)
                        {
                            playlist.addSongToAPlaylist();
                            System.out.println("press 1 to to add another song");
                        }
                        else
                            System.exit(0);
                        break;
                    case 3:
                        System.out.println("Choose any one\n" +
                                "1. display all podcasts\n" +
                                "2. Sort Podcasts\n" +
                                "3. Search Podcasts");
                        int podOption=sc.nextInt();
                        if(podOption==1)
                            podcastImpl.displayAllPodcasts();
                        else if (podOption==2) {
                            podcastImpl.sortPodcasts();
                        } else if (podOption==3) {
                            podcastImpl.searchPodcast();
                        }
                        else
                            System.out.println("invalid selection");

                    case 4:
                        System.exit(0);



                }

    }
    @Override
    public boolean createAccount()
    {
        boolean flag=false;
        try{
        System.out.println("please enter a username");
        String userName=sc.next();
        System.out.println("please enter your password");
        String password=sc.next();
        System.out.println("please enter your mobile number");
        String mobileNo=sc.next();
        Connection con=connection.getConnectionDatabase();
        String query="insert into userinfo(user_name,user_password,mob_no) values(?,?,?)";
        PreparedStatement psobj= null;
        psobj = con.prepareStatement(query);

            psobj.setString(1, userName);
            psobj.setString(2, password);
            psobj.setString(3, mobileNo);
                UserImpl user=new UserImpl();
                int data1=psobj.executeUpdate();
                if(data1>0){
                    System.out.println("Account Created Success");
                    user.welcomeScreen();
                flag=true;}
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

    @Override
    public boolean login()
    {
        System.out.println("enter your username");
        String userName=sc.next();
        System.out.println("enter your password");
        String password=sc.next();
        try{
            Connection con=connection.getConnectionDatabase();
            String query="select * from userinfo where user_name= ? and user_password= ?";

            PreparedStatement statement=con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet set=statement.executeQuery();
            if(set.next()){
                System.out.println("login successfull");
                UserImpl user=new UserImpl();
                user.welcomeScreen();}
            else
                System.out.println("no such user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
