import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.InterruptedException;
import java.util.concurrent.TimeUnit;
import java.util.List;

/**
 * Created by liang lu on 2/13/17.
 */

public class FroggerMain extends JPanel {

    //instance fields for the general environment
    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    private Timer timer;
    private boolean[] keys;

    //instance fields for frogger.
    private Sprite frog;
    private ArrayList<Sprite> obstacles;
    private ArrayList<Sprite> helpers;
    private JButton re = new JButton("Restart");
    private int count = 5;
    private int level = 1;
//    private int gen = 0;


    public FroggerMain(){

        keys = new boolean[512]; //should be enough to hold any key code.
        //TODO: initialize the instance fields.

        frog = new Frog();
        //TODO: init obstacles arraylist
        obstacles = new ArrayList<Sprite>();
        helpers = new ArrayList<Sprite>();

//        re.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frog.setLoc(new Point(500, 550));
//                frog.setDir(Sprite.NORTH);
//                count = 5;
//                timer.start();
////                repaint();
//            }
//        });
//        add(re);
//        re.setBounds(300, 300, 100, 100);
        re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frog = new Frog();
                frog.setLoc(new Point(500, 550));
                frog.setDir(Sprite.NORTH);
                count = 5;
                level = 1;
                for (Sprite sp : obstacles)
                    sp.setSpeed(5 + level);
                for (Sprite he : helpers)
                    he.setSpeed(5 + level);
                timer.start();
                grabFocus();
//                repaint();
            }
        });
        add(re);



        //TODO: add obstacles - cars and stuff
//        RedCar one = new RedCar(100, 400, Sprite.EAST);
//        obstacles.add(one);
//        RedCar two = new RedCar(200, 400, Sprite.EAST);
//        obstacles.add(two);
//        RedCar three = new RedCar(900, 300, Sprite.WEST);
//        obstacles.add(three);
//        RedCar four = new RedCar(800, 300, Sprite.WEST);
//        obstacles.add(four);
//        ChristmasCar three = new ChristmasCar(900, 300, Sprite.WEST);
//        obstacles.add(three);
//        ChristmasCar four = new ChristmasCar(800, 300, Sprite.WEST);
//        obstacles.add(four);
        //Cars obstacles
        int chrisX = 0;
        int chrisY = 400;
        for (int i = 0; i < 7; i++) {
            obstacles.add(new ChristmasCar(chrisX, chrisY, Sprite.EAST));
            chrisX += 200;
        }
        int redX = 0;
        int redY = 500;
        for (int i = 0; i < 10; i++) {
            obstacles.add(new RedCar(redX, redY, Sprite.EAST));
            redX += 150;
        }
        int truckX = 0;
        int truckY = 450;
        for (int i = 0; i < 3; i++) {
            obstacles.add(new Truck(truckX, truckY, Sprite.WEST));
            truckX += 500;
        }
        int yelX = 0;
        int yelY = 350;
        for (int i = 0; i < 4; i++) {
            obstacles.add(new YellowCar(yelX, yelY, Sprite.WEST));
            yelX += 350;
        }

        //logs and turtles helpers
        int slogX = 0;
        int slogY = 250;
        for (int i = 0; i < 4; i++) {
            helpers.add(new SLog(slogX, slogY, Sprite.EAST));
            slogX += 360;
        }

        int mlogX = 0;
        int mlogY = 200;
        for (int i = 0; i < 4; i++) {
            helpers.add(new MLog(mlogX, mlogY, Sprite.EAST));
            mlogX += 375;
        }

        int ttX = 0;
        int ttY = 150;
        for (int j = 0; j < 20; j++) {
            if(j % 4 != 0){
                helpers.add(new Turtle(ttX, ttY, Sprite.WEST));
                ttX += 35;
            }else{
                ttX += 135;
            }
        }

//        int turX = 0;
//        int turY = 150;
//        for (int i = 0; i < 1; i++) {
//            helpers.add(new Turtle(turX, turY, Sprite.WEST));
//            turX += 100;
//        }
//        int tuX = 32;
//        int tuY = 150;
//        for (int i = 0; i < 1; i++) {
//            helpers.add(new Turtle(tuX, tuY, Sprite.WEST));
//            tuX += 100;
//        }
//        int tX = 64;
//        int tY = 150;
//        for (int i = 0; i < 1; i++) {
//            helpers.add(new Turtle(tX, tY, Sprite.WEST));
//            tX += 100;
//        }

        int llogX = 0;
        int llogY = 100;
        for (int i = 0; i < 3; i++) {
            helpers.add(new LLog(llogX, llogY, Sprite.EAST));
            llogX += 400;
        }

        int logX = 0;
        int logY = 50;
        for (int i = 0; i < 3; i++) {
            helpers.add(new MLog(logX, logY, Sprite.WEST));
            logX += 500;
        }


        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //move the frog
                if(keys[KeyEvent.VK_W] && frog.getLoc().y > 0){
                    frog.setDir(Sprite.NORTH);
                    frog.update();
                    keys[KeyEvent.VK_W] = false; //probably.  Makes 1 move per button press.
                }
                if(keys[KeyEvent.VK_A] && frog.getLoc().x > 0){
                    frog.setDir(Sprite.WEST);
                    frog.update();
                    keys[KeyEvent.VK_A] = false; //probably.  Makes 1 move per button press.
                }
                if(keys[KeyEvent.VK_S] && frog.getLoc().y < FRAMEHEIGHT - 3 * (frog.getBoundingRectangle().getHeight())){
                    frog.setDir(Sprite.SOUTH);
                    frog.update();
                    keys[KeyEvent.VK_S] = false; //probably.  Makes 1 move per button press.
                }
                if(keys[KeyEvent.VK_D] && frog.getLoc().x < FRAMEWIDTH - 3 * (frog.getBoundingRectangle().getWidth())){
                    frog.setDir(Sprite.EAST);
                    frog.update();
                    keys[KeyEvent.VK_D] = false; //probably.  Makes 1 move per button press.
                }
                //TODO: implement other directions (DONE)

                //TODO: update each obstacle (DONE)

                for(Sprite ob: obstacles){
                    ob.update();
                }

                for(Sprite he: helpers){
                    he.update();
                }

                //TODO: check for collisions - frog vs obstacles
                //intersect and dieeeeee
                for(Sprite ob: obstacles){
                    if(frog.intersects(ob)){
                        frog.setLoc(new Point(500, 550));
                        frog.setDir(Sprite.NORTH);
                        //TODO: set how many lives each get
                        count--;
                    }
                }

                if(frog.getLoc().y < 300 && frog.getLoc().y > 40){ //in the water
                    boolean dead = true;
                    for(Sprite he: helpers){
                        if (frog.intersects(he)) {
                            dead = false;
                            if(he.getDir() == Sprite.EAST)
                                frog.getLoc().translate(he.getSpeed(), 0);
                            else if(he.getDir() == Sprite.WEST)
                                frog.getLoc().translate(-he.getSpeed(), 0);

                        }
                    }
                    if(dead){
                        frog.setLoc(new Point(500, 550));
                        frog.setDir(Sprite.NORTH);
                        count --;
                    }
                }
                if(frog.getLoc().y < 50) {
                    level++;
                    for (Sprite sp : obstacles)
                        sp.setSpeed(5 + level);
                    for (Sprite he : helpers)
                        he.setSpeed(5 + level);
//                    count = 5;
//                    repaint();
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException ex) {
//                        Thread.currentThread().interrupt();
//                    }
//                    repaint();
                    frog.setLoc(new Point(500, 550));
                }
                repaint(); //always the last line.  after updating, refresh the graphics.
            }
        });
        timer.start();


        /*
        You probably don't need to modify this keyListener code.
         */
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {/*intentionally left blank*/ }

            //when a key is pressed, its boolean is switch to true.
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            //when a key is released, its boolean is switched to false.
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
            }
        });


    }

//    public int getGen(){return gen;}

    //Our paint method.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Backgrounds
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 300, getWidth(), getHeight()); //ROAD
        g2.setColor(new Color(150, 231, 121));
        g2.fillRect(0, 300, getWidth(), 37);
        g2.fillRect(0, 537, getWidth(), getHeight()); //Grass
        g2.setColor(new Color(142, 191, 255));
        g2.fillRect(0, 0, getWidth(), 300); //WATER
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(0, 487, getWidth(), 487);
        g2.drawLine(0, 437, getWidth(), 437);
        g2.drawLine(0, 387, getWidth(), 387); //white lines
        g2.setColor(Color.BLACK);

        //TODO: draw all the obstacles.
        for (Sprite ob : obstacles) {
            ob.draw(g2);
        }

        for (Sprite he : helpers) {
            he.draw(g2);
        }

        frog.draw(g2);


        //words
        g2.setColor(Color.WHITE);
        Font font = new Font("Verdana", Font.ITALIC, 20);
        g2.setFont(font);
        g2.setStroke(new BasicStroke(2));
        g2.drawString("You have " + count + " lives left.", 720, getHeight() - 30);

//        if (frog.getLoc().getY() < 50) {
////            g2.setFont(font);
//            g2.setStroke(new BasicStroke(4));
//            g2.drawString("You have completed level " + level + " !!", 15, 100);
//            timer.stop();
//
//
//        }
        g2.setFont(font);
        g2.setStroke(new BasicStroke(4));
        g2.drawString("Level: " + level, 900, 25);

        Font font1 = new Font("Monospaced", Font.PLAIN, 20);
            if (count == 0) {
            g2.setFont(font1);
//            g2.setStroke(new BasicStroke(4));
                g2.drawString("You have lost the level!!", 15, 100);
                g2.drawString("Click Restart to play again!!", 15, 150);
                timer.stop();
            }

    }

    //sets ups the panel and frame.  Probably not much to modify here.
    public static void main(String[] args) {
        JFrame window = new JFrame("Frog the Frogger");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        FroggerMain panel = new FroggerMain();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}