/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 *
 * @author Peter
 */
public class Music extends Thread {
    
    private Media[] playlist;
    private boolean[] goodmedia;
    private String backormusic="";
    private MediaPlayer player;
    private int n;
    
    public Music(boolean musicornot){
        if (musicornot)
            backormusic = "/music/";
        else
            backormusic = "/backgroundmusic/";

        n=new File(System.getProperty("user.dir").replace('\\', '/') + backormusic).listFiles().length;
        playlist=new Media[n];
        goodmedia=new boolean[n];

        File folder = new File(System.getProperty("user.dir").replace('\\', '/') + backormusic);
        File[] listOfFiles = folder.listFiles();
        //System.out.println(n);
        int good=0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (file.getName().endsWith(".mp3") || file.getName().endsWith(".wav")){
                    goodmedia[good]=true;
                    playlist[good]=new Media("file:///"+System.getProperty("user.dir").replace('\\', '/') + backormusic + file.getName());
                    good++;
                }else{
                    goodmedia[good]=false;
                }
                    
                //System.out.println(file.getName());
            }
        }
    }
    
    @Override
    public void run(){
        
        Random randomgen = new Random();
        int randomnum = randomgen.nextInt(playlist.length);
        //System.out.println(randomnum);
        
        player=new MediaPlayer(playlist[randomnum]);
        player.play();
        
        player.setOnEndOfMedia(this);
        player.setVolume(1);
        
    }
    
    public void stopmusic(){
        //TODO ezt ird at mert vagy 3 secig megakad a program ahol all
        player.setOnEndOfMedia(null);
        for(int i=10000;i>=0;i--){
            player.setVolume(i/10000.0);
            //System.out.println(i);
            /*try {
                this.sleep(16);
            } catch (InterruptedException ex) {
                Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }
    
    /*public Runnable aaa(){
        System.out.print("hiba");
        return this;
    }*/
    
    public void ChangeDirectory(boolean musicornot){
        //ide jon egy kod ami alapjan az alapertelmezett konyvtarakat le lehet cserelni
        //ha megvan a lecsereles akkor ujra inicializalnunk kell a dolgokat
    }
    
    public void NextTrack(){
        player.setOnPaused(this);
        player.pause();
    }
    
    public void SetVolume(int mus, int eff){
        if(mus>=0){
            player.setVolume(mus);
        } 
        if(eff>=0){
            //ez billenti az efektes dolgot
        }
    }
    
}
