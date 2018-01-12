package mygame;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SiXFOiL on 13.07.2017.
 */
public class Player implements Runnable{

    private String path = "/sounds/";
    private String name;
    private boolean playing;
    private int timeIndex;
    private int volume;
    private Thread t;

    public Player(String name, int volume, boolean longTrack) {
        this.name = name;
        this.volume = volume;
        playing = longTrack;
        timeIndex = longTrack ? 10000000 : 250;
        t = new Thread(this);
        t.setPriority(10);
        t.start();

    }

    synchronized void startPlay() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        InputStream stream = this.getClass().getResourceAsStream(path + name);
        InputStream bufferedStream = new BufferedInputStream(stream);
        AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedStream);
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        FloatControl vc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        vc.setValue(volume);
        do {
            clip.setFramePosition(0);
            clip.start();
            do {
                Thread.sleep(clip.getMicrosecondLength() / timeIndex);
            }while (playing);
            clip.stop();
        }while (playing);
        clip.close();
        t.stop();
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Thread getT() {
        return t;
    }

    @Override
    public void run() {
        try {
            startPlay();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
