import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound() {
        soundURL[0] = getClass().getResource("/Sfx/INGAME_THEME_SHORT.wav");
        soundURL[1] = getClass().getResource("/Sfx/WRITINGSFXSHORT.wav");
        soundURL[2] = getClass().getResource("/Sfx/KNIGHT_WALKING_SFX.wav");
        soundURL[3] = getClass().getResource("/Sfx/MONSTER_WALKING_SFX.wav");
        soundURL[4] = getClass().getResource("/Sfx/KILL_SFX.wav");
        soundURL[5] = getClass().getResource("/Sfx/GAME_OVER_SFX.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
