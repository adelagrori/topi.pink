package movingBall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MovingBallWithSparks extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    
    private int ballX = 400;
    private int ballY = 300;
    private int ballRadius = 30;
    private int ballDX = 4; 
    private int ballDY = 3; 

 
    private ArrayList<Spark> sparks = new ArrayList<>();
    private Random random = new Random();

    public MovingBallWithSparks() {
   
        for (int i = 0; i < 50; i++) {
            sparks.add(new Spark());
        }

        
        Timer timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
       
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        
        g2d.setColor(Color.YELLOW);
        for (Spark spark : sparks) {
            g2d.fillOval(spark.x, spark.y, spark.size, spark.size);
        }

        
        g2d.setColor(Color.MAGENTA);
        g2d.fillOval(ballX - ballRadius, ballY - ballRadius, ballRadius * 2, ballRadius * 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        ballX += ballDX;
        ballY += ballDY;

       
        if (ballX - ballRadius < 0 || ballX + ballRadius > getWidth()) {
            ballDX = -ballDX;
        }

        
        if (ballY - ballRadius < 0 || ballY + ballRadius > getHeight()) {
            ballDY = -ballDY;
        }

       
        for (Spark spark : sparks) {
            spark.update(ballX, ballY, getWidth(), getHeight());
        }

       
        repaint();
    }

  
    private class Spark {
        int x, y;
        int size;
        int speedX, speedY;

        public Spark() {
            resetPosition();
        }

        private void resetPosition() {
            x = random.nextInt(WIDTH);
            y = random.nextInt(HEIGHT);
            size = random.nextInt(5) + 2; 
            speedX = random.nextInt(5) - 2; 
            speedY = random.nextInt(5) - 2;
        }

        public void update(int targetX, int targetY, int boundsW, int boundsH) {
            if (x < targetX) x += random.nextInt(3);
            else x -= random.nextInt(3);

            if (y < targetY) y += random.nextInt(3);
            else y -= random.nextInt(3);

           
            x += speedX;
            y += speedY;

           
            if (x < 0 || x > boundsW || y < 0 || y > boundsH) {
                resetPosition();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Topi Pink me Xixa");
        MovingBallWithSparks panel = new MovingBallWithSparks();

        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}