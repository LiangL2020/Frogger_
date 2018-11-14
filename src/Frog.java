import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by liang lu on 2/13/17.
 */
public class Frog extends Sprite {

    public Frog(){
        super(500, 550, NORTH);
        setSpeed(getBoundingRectangle().height);
        setPic("frog1.png", NORTH);
    }

    @Override
    public void update() {
        super.update();


        //This makes a hopping sound when the frog is told to move.
        try {
            // open the sound file as a Java input stream
            String hop = "res/hop.wav";
            InputStream in = new FileInputStream(hop);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error loading sound file.");
        }

    }
}
