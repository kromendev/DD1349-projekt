import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class contains methods and handles all sounds 
 * in the game.
 * 
 * @author Husein Hassan
 * @version 2025-05-08
 */
public class Sound {
    Clip clip;
    URL soundURL[] = new URL[10];

    /**
     * Creates a sound object with all the sounds available to it.
     * 
     * @throws Exception if a there's an invalid path.
     */
    public Sound() {
        try {
            soundURL[0] = getClass().getResource("/Sfx/INGAME_THEME_SHORT.wav");
            soundURL[1] = getClass().getResource("/Sfx/WRITINGSFXSHORT.wav");
            soundURL[2] = getClass().getResource("/Sfx/KNIGHT_WALKING_SFX.wav");
            soundURL[3] = getClass().getResource("/Sfx/MONSTER_WALKING_SFX.wav");
            soundURL[4] = getClass().getResource("/Sfx/KILL_SFX.wav");
            soundURL[5] = getClass().getResource("/Sfx/GAME_OVER_SFX.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets which sound file to use for an object.
     * 
     * @param i index for soundURL array.
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * plays sound.
     */
    public void play() {
        clip.start();
    }

    /**
     * loops sound forever.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * stops sound from playing.
     */
    public void stop() {
        clip.stop();
    }
}
