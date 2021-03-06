import java.awt.*;

/**
 * Created by student on 2/8/18.
 */
public class MLog extends Sprite{


    private int corX, corY, rec;

    public MLog(int corX, int corY, int rec){
        super(corX, corY, rec);
        setPic("logMedium.png", rec);
        this.rec = rec;
    }

    public int getCorX(){
        return corX;
    }

    public int getCorY(){
        return corY;
    }

    @Override
    public void update(){
        setSpeed(6);
        super.update();

        if(getLoc().x > 1200)
            setLoc(new Point(-200, getLoc().y));
        if(getLoc().x < -200)
            setLoc(new Point(1200, getLoc().y));
    }
}
