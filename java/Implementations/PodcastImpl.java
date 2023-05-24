package Implementations;

import Interfaces.PodcastInterface;
import PlaySong.PlaySong;
import PojoClasses.ConnectionToDatabase;
import PojoClasses.Podcasts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PodcastImpl implements PodcastInterface {

    ConnectionToDatabase con=new ConnectionToDatabase();
    Scanner sc=new Scanner(System.in);

    @Override
    public List<Podcasts> displayAllPodcasts()
    {
        List<Podcasts> podcastsList=new ArrayList<>();
        Connection connection=con.getConnectionDatabase();
        try{
            String displayQuery="select * from podcasts";
            PreparedStatement  pstdisplay=connection.prepareStatement(displayQuery);
            ResultSet displaySet=pstdisplay.executeQuery();
            Podcasts pod;
            ResultSetMetaData rsmdDisplay=displaySet.getMetaData();
            int countColumns=rsmdDisplay.getColumnCount();
            while (displaySet.next()){
                int podcastId=displaySet.getInt(1);
                String podcastName=displaySet.getString(2);
                String podcastDuration=displaySet.getString(3);
                int podcastEpisode=displaySet.getInt(4);
                String podcastFilePath=displaySet.getString(5);
                pod=new Podcasts(podcastId, podcastName, podcastDuration, podcastEpisode, podcastFilePath);
                podcastsList.add(pod);
                for (int i = 1; i <= countColumns; i++) {
                    if (i > 1)
                        System.out.println(", ");
                    String value = displaySet.getString(i);
                    System.out.println(rsmdDisplay.getColumnName(i) + " " + value);
                }
                System.out.println("");
            }
        }
        catch(SQLException sqlException){
            throw new RuntimeException();
        }

        return null;
    }

    @Override
    public void sortPodcasts() {
        List<Podcasts> podcastsList=new ArrayList<>();
        Connection connection=con.getConnectionDatabase();
        try{
            String sortQuery="select * from podcasts order by podcast_name";
            PreparedStatement  pstsort=connection.prepareStatement(sortQuery);
            ResultSet sortedSet=pstsort.executeQuery();
            ResultSetMetaData rsmdSort=sortedSet.getMetaData();
            int countColumns=rsmdSort.getColumnCount();
            while (sortedSet.next()){
                for (int i = 1; i <= countColumns; i++) {
                    if (i > 1)
                        System.out.println(", ");
                    String value = sortedSet.getString(i);
                    System.out.println(rsmdSort.getColumnName(i) + " " + value);
                }
                System.out.println("");
            }
            }
        catch (SQLException sqlException){
            throw new RuntimeException();
        }

    }

    @Override
    public PodcastImpl searchPodcast() {
        Connection connection=con.getConnectionDatabase();
        System.out.println("enter podcast ID to play");
        int podcastId=sc.nextInt();
        try{
        String searchQuery="select podcast_filePath from podcasts where podcast_id=?";
        PreparedStatement seacrhSt=connection.prepareStatement(searchQuery);
        seacrhSt.setInt(1,podcastId);
        ResultSet setPod=seacrhSt.executeQuery();
        String podPath="";
        while(setPod.next()){
            podPath=setPod.getString(1);
            PlaySong playSong=new PlaySong();
            playSong.readAudio(podPath,1);
        }
        }
        catch (SQLException sqlException)
        {
            throw new RuntimeException();
        }
        return null;
    }
}
