import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main implements ActionListener, MouseListener, KeyListener {

	static Main mainApp;
	JFrame jf;
	int WIDTH = 600;
	int HEIGHT = 600;
	Renderer panel;
	Timer timer;
	Rectangle car;
	ArrayList<Rectangle> cars;
	Random rand;
	int flag = 0;
	boolean started, gameover;
	int score;
	Image myCar, oppCar;
	int speed;

	Main() {
		jf = new JFrame("Car Game");
		panel = new Renderer();
		rand = new Random();

		timer = new Timer(20, this);

		car = new Rectangle((WIDTH / 2) - 25, HEIGHT - 120, 50, 80);
		cars = new ArrayList<>();

		myCar = Toolkit.getDefaultToolkit().getImage("C:\\Users\\shivs\\eclipse-workspace\\CarGame1\\Images\\gamecar2.png");
		oppCar = Toolkit.getDefaultToolkit().getImage("C:\\Users\\shivs\\eclipse-workspace\\CarGame1\\Images\\gamecar4.png");

		jf.add(panel);
		jf.addMouseListener(this);
		jf.addKeyListener(this);
		jf.setSize(WIDTH, HEIGHT);
		jf.setVisible(true);
		jf.setResizable(false);

		timer.start();
	}

	public void addCars(boolean b) {
//		int xi = rand.nextInt(100);
		int xi = rand.nextInt(250);
		int width = 50;
		int height = 80;
		if (b) {
			cars.add(new Rectangle(WIDTH / 4 + xi, -cars.size() * 150, width, height));
//			cars.add(new Rectangle(WIDTH / 4 + xi + 120, -(cars.size() - 1) * 150, width, height));
//			cars.add(new Rectangle(WIDTH / 4 + xi + 120, -(cars.size()) * 150, width, height));
		} else {
//			cars.add(new Rectangle(WIDTH / 4 + xi, -(cars.size()-1) * 150 , width, height));
			cars.add(new Rectangle(WIDTH / 4 + xi, -(cars.size() - 1) * 150, width, height));

//			cars.add(new Rectangle(WIDTH / 4 + xi + 120, -(cars.size() - 2) * 150, width, height));
//			cars.add(new Rectangle(WIDTH / 4 + xi + 120, -(cars.size() ) * 150, width, height));

		}
	}

	public void repaint(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.green.darker());
		g.fillRect(0, 0, WIDTH / 4, HEIGHT);
		g.fillRect((3 * WIDTH) / 4, 0, WIDTH / 4, HEIGHT);

		g.setColor(Color.red.darker().darker());
		g.fillRect((WIDTH / 4) - 20, 0, 20, HEIGHT);
		g.fillRect((3 * WIDTH) / 4, 0, 20, HEIGHT);

//		g.setColor(Color.red);
//		g.fillRect(car.x, car.y, car.width, car.height);
		g.drawImage(myCar, car.x, car.y, car.width, car.height, null);

		for (Rectangle rect : cars)
			paintCars(g, rect);

		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.white);
		if (!started)
			g.drawString("Click here to start!", 210, 100);
		if (gameover)
			g.drawString("GameOver! Your score is " + score, 170, 200);
		if (started && !gameover)
			g.drawString("Score : " + score, 10, 300);
		if (gameover) {
			started = false;
			cars.clear();
		}

	}

	public void paintCars(Graphics g, Rectangle rect) {
//		g.setColor(Color.blue);
//		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.drawImage(oppCar, rect.x, rect.y, rect.width, rect.height, null);

	}

	public static void main(String[] args) {
		mainApp = new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		speed = 3;
		if(score > 5 && score < 10)
		speed = 5;
		else if(score >= 10 && score < 15)
			speed = 7;
		else if(score >= 15 && score < 20)
			speed = 10;
		else 
			speed++;
		
		if (started) {
			for (Rectangle rect : cars)
				rect.y += speed;

			for (int i = 0; i < cars.size(); i++) {
				Rectangle rect = cars.get(i);
				if (rect.y + 150 > car.y + 150) {
					cars.remove(i);
//					flag++;
//					if ((flag % 2) == 0)
					addCars(false);
				}
			}
		}
		for (Rectangle rect : cars) {
			if (rect.y >= car.y) {
				score++;
//				System.out.println("1");
			}
			if (rect.intersects(car))
				gameover = true;

		}

		panel.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!started) {
			started = true;
			gameover = false;
			score = 0;
			addCars(true);
			addCars(true);
			addCars(true);
			addCars(true);
			addCars(true);
			addCars(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!started) {
			started = true;
			gameover = false;
			score = 0;
			addCars(true);
			addCars(true);
			addCars(true);
			addCars(true);
			addCars(true);
			addCars(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (car.x > 150)
				car.x -= 6;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (car.x < 394)
				car.x += 6;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
