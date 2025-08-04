//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.applet.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LearningGraphics extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    //instance variables
    private AudioClip Sound;
    private int WIDTH;
    private int HEIGHT;
    private int rX, rY, rW, rH, rX2, rY2, rW2, rH2;
    private int cX, cY, diam, cVx, cVy, cX2, cY2, diam2, cVx2, cVy2;
    private int life1, life2;
    private int score1, score2;
    private int goRX, goRY, goW, goH;
private boolean game;
    //Default Constructor
    public LearningGraphics() {
        //initializing instance variables
        //Player 1 stuff
        WIDTH = 1000;
        HEIGHT = 500;
        rX = 20;
        rY = 180;
        rW = 25;
        rH = 100;

        cX = 500;
        cY = 300;
        diam = 70;
        cVx = 5;
        cVy = 5;
        score1 = 0;
        life1 = 3;

        //Player 2 Stuff
        rX2 = 950;
        rY2 = 180;
        rW2 = 25;
        rH2 = 100;
        score2 = 0;
        life2 = 3;
        
        //Game over 
        goRX = 0;
        goRY = 0;
        goW = WIDTH;
        goH = HEIGHT;
        game=false;
        
        Sound = Applet.newAudioClip(this.getClass().getResource("yippee-147032.wav"));
        //Setting up the GUI
        JFrame gui = new JFrame(); //Makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure that the program can close
        gui.setTitle("Learning Graphics"); //Title of the game, can be changed later
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes so that the gui can't be resized
        gui.getContentPane().add(this); //Adding this class to the gui

        gui.pack(); //Packs everything togehter
        gui.setLocationRelativeTo(null); //Makes so that the gui opens in the center of the screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this); //Saying that this object will listen to the keyboard
        gui.addMouseListener(this); //Makes the object listen to the mouse
        gui.addMouseMotionListener(this); //Makes the object acknowledges when the mouse moves
    }
//This method will acknowledge user input

    public boolean gameOver()
    {
        if (life1 == 0) {
            String user = JOptionPane.showInputDialog("Player 1 lost, do you want to play again?");
            if (user.equalsIgnoreCase("Yes")) {
                life1 = 3;
                life2 = 3;
                score1 = 0;
                score2 = 0;
                game = false;
            } if(user.equalsIgnoreCase("No")) {
                life1 = 3;
                life2 = 3;
                score1 = 0;
                score2 = 0;
                game= true;
            }
        }
        if (life2 == 0) {
            String user = JOptionPane.showInputDialog("Player 2 lost, do you want to play again?");
            if (user.equalsIgnoreCase("Yes")) {
                life1 = 3;
                life2 = 3;
                score1 = 0;
                score2 = 0;
                game= false;
            } if(user.equalsIgnoreCase("No")) {
                life1 = 3;
                life2 = 3;
                score1 = 0;
                score2 = 0;
               game= true;              
            }
        }
        return game;
    }
    
    public void play() {
        int curX = cX;
        if (curX <= 0) {
            life1 -= 1;
            score2 += 1;
            return;
        } else if (curX + diam >= 1000) {
            life2 -= 1;
            score1 += 1;
            return;
        }

        if (life1 == 0) {
            gameOver();
        }
        if (life2 == 0) {
            gameOver();
        }
    }

    public double distance(int x1, int y1, int x2, int y2) {
        int xD = x2 - x1;
        int yD = y2 - y1;
        double xSq = Math.pow(xD, 2);
        double ySq = Math.pow(yD, 2);
        double dis = Math.sqrt(xSq + ySq);
        return dis;
    }

    public boolean detectCollisionL() {
        boolean output = false;
        int radius, centerX, centerY;
        int nextX, nextY;
        nextX = cX + cVx;
        nextY = cY + cVy;
        radius = diam / 2;
        centerX = (2 * nextX + diam) / 2;
        centerY = (2 * nextY + diam) / 2;

        for (int x = rX; x <= rX + rW; x++) //If the ball goes through the rectangle, make sure that the boolean statement is x<=rX+rH
        {
            for (int y = rY; y <= rY + rH; y++) //boolean is supposed to be y<=rY+rH
            {
                if (distance(x, y, centerX, centerY) < radius) {
                    output = true;
                }
            }
        }
        return output;
    }

    public boolean detectCollisionR() {
        boolean output = false;
        int radius, centerX, centerY;
        int nextX, nextY;
        nextX = cX + cVx;
        nextY = cY + cVy;
        radius = diam / 2;
        centerX = (2 * nextX + diam) / 2;
        centerY = (2 * nextY + diam) / 2;

        for (int x = rX2; x <= rX2 + rW2; x++) //If the ball goes through the rectangle, make sure that the boolean statement is x<=rX+rH
        {
            for (int y = rY2; y <= rY2 + rH2; y++) //boolean is supposed to be y<=rY+rH
            {
                if (distance(x, y, centerX, centerY) < radius) {
                    output = true;
                }
            }
        }
        return output;
    }

    public void keyPressed(KeyEvent e) {
//getting the key pressed
        int key1 = e.getKeyCode();
        System.out.println(key1);
        //moving the rectangle
        if (key1 == 38) //Up arrow
        {
            rY2 -= 10;
        } else if (key1 == 40) //Down arrow
        {
            rY2 += 10;
        }

        int key2 = e.getKeyCode();
        System.out.println(key2);
        if (key2 == 87) //W - Up
        {
            rY -= 10;
        } else if (key2 == 83) //S - Down
        {
            rY += 10;
        }
    }
//All your UI drawing goes in here

    public void paintComponent(Graphics g) {
        Graphics2D g2d;
        g2d = (Graphics2D)g;
        //Drawing a Blue Rectangle to be the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //Drawing Hello World!! at the center of the GUI
        Font f = new Font("Times New Roman", Font.BOLD, 50);
        g.setFont(f);
        g.setColor(Color.WHITE);
        g.drawString("Pong", WIDTH - 560, HEIGHT - 400);
        //Drawing the user-controlled rectangle
        //Player 1 Rectangle
        g.setColor(Color.GRAY);
        g.fillRect(rX, rY, rW, rH);

        //Drawing the autonomous circle
        g.setColor(Color.WHITE);
        g.fillOval(cX, cY, diam, diam);

        //Player 2 Rectangle
        g.setColor(Color.GRAY);
        g.fillRect(rX2, rY2, rW2, rH2);

        //PLayer 1 Score + Lives
        Font p1Score = new Font("Times New Roman", Font.BOLD, 20);
        g.setFont(p1Score);
        g.setColor(Color.YELLOW);
        g.drawString("Player 1", 5, 20);
        g.setColor(Color.GREEN);
        g.drawString("Score: " + score1, 5, 40);

        Font p1Lifes = new Font("Times New Roman", Font.BOLD, 20);
        g.setFont(p1Lifes);
        g.setColor(Color.RED);
        g.drawString("Lives Left: " + life1, 5, 60);

        //Player 2 Score + Lives
        Font p2Score = new Font("Times New Roman", Font.BOLD, 20);
        g.setFont(p2Score);
        g.setColor(Color.CYAN);
        g.drawString("Player 2", WIDTH-80, HEIGHT-480);
        g.setColor(Color.PINK);
        g.drawString("Score: " + score2, WIDTH - 80, HEIGHT-460);
      
        Font p2Lifes = new Font("Times New Roman", Font.BOLD, 20);
        g.setFont(p2Lifes);
        g.setColor(Color.RED);
        g.drawString("Lives Left: " + life2, WIDTH - 115, HEIGHT - 440);

        Font controls = new Font("Bodoni", Font.ITALIC, 15);
        g.setFont(controls);
        g.setColor(Color.YELLOW);
        g.drawString("Player 1 Controls: W-Up, S-Down", WIDTH-610, HEIGHT-40);
        g.setColor(Color.cyan);
        g.drawString("Player 2 Controls: Up Arrow, Down Arrow", WIDTH-630, HEIGHT-20);
        //Game Over
        if(game)
        {
            cX=-1000;
            cY = -1000;
            g.setColor(Color.WHITE);
            g.fillRect(0,0, WIDTH, HEIGHT);
            Font endMessage = new Font("Arial", Font.BOLD, 40);
            g.setFont(endMessage);
            g.setColor(Color.BLACK);
            g.drawString("Game Over", WIDTH-600, HEIGHT -450);
            g.drawString("Thank you for playing!!", WIDTH - 700, HEIGHT - 400);
            //adding my image 
            ImageIcon Smiling = new ImageIcon(LearningGraphics.class.getResource("Smiling.jpg"));
            g2d.drawImage(Smiling.getImage(), WIDTH-620, HEIGHT-390, 300, 300, null);
            Sound.play(); //Its not playing the sound properly but I find it goofy how it is
        }
    }

    public void loop() {
//making the autonomous circle move
        cX += cVx;
        cY += cVy;
//handling when the circle collides with the edges
        int nextX, nextY;
        nextX = cX + cVx;
        nextY = cY + cVy;
        if (nextY + diam > HEIGHT) //hits the bottom border
        {
            cVy *= -1;
        }
        if (nextX + diam > WIDTH) //hits the right border
        {
            cVx *= -1;
        }
        if (nextY < 0) //hits the top border
        {
            cVy *= -1;
        }
        if (nextX < 0) //hits the left border
        {
            cVx *= -1;
        }
//handling the collision of the circle with the rectangle
        boolean col = detectCollisionL();
        boolean col2 = detectCollisionR();
        if (col || col2) {
            cVx *= -1;
            cVy *= -1;
        }
        play();
        
//Do not write below this
        repaint();
    }
//These methods are required by the compiler.
//You might write code in these methods depending on your goal.

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void start(final int ticks) {
        Thread gameThread = new Thread() {
            public void run() {
                while (true){
                    loop();
                    try {
                        Thread.sleep(1000 / ticks);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        gameThread.start();
    }

    public static void main(String[] args) {
        LearningGraphics g = new LearningGraphics();
        g.start(60);
    }
}
