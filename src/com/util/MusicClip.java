package com.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicClip {
    static AudioInputStream audioInputStream;
    static  Clip clip;
    // Function for music clip
    public static void music(){

        try{
            audioInputStream = AudioSystem.getAudioInputStream(MusicClip.class.getResource("/Files/musicClip.wav"));
            clip =AudioSystem.getClip();
            clip.open(audioInputStream);
            play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void play(){
        clip.start();
    }

    public static void stop(){
        clip.stop();
    }
}
