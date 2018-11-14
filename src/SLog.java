import java.awt.*;

/**
 * Created by student on 2/7/18.
 */
public class SLog extends Sprite{

    private int corX, corY, rec;

    public SLog(int corX, int corY, int rec){
        super(corX, corY, rec);
        setPic("logShort.png", rec);
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
//        setSpeed(6);
        super.update();

        if(getLoc().x > 1200)
            setLoc(new Point(-200, getLoc().y));
        if(getLoc().x < -200)
            setLoc(new Point(1200, getLoc().y));
    }

}
