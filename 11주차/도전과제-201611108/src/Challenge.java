import java.awt.*;
import javax.swing.*;
//공을 Ball 클래스로 모델링한다.
class Ball {
	//공의 속성은 위치, 크기, 속도이다.
	private int x = 100;
	private int y = 100;
	private int size = 30;
	private int xSpeed = 10;
	private int ySpeed = 10;
	//공을 화면에 그려주는 메소드이다.
	public void draw(Graphics g) {
		// frame 사이즈가 다른 경우 아래 코드로 범위 구분이 가능합니다.
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, MyPanel.BOARD_WIDTH, MyPanel.BOARD_HEIGHT);
		// 원 그리기
		g.setColor(Color.RED);
		g.fillOval(x, y, size, size);
	}
	public void update() {
		x += xSpeed;
		y += ySpeed;
		if (x >= Challenge.BOARD_WIDTH - size || x <= 0) {
			xSpeed = -xSpeed;
		}
		if (y >= Challenge.BOARD_HEIGHT - size || y <= 0) {
			ySpeed = -ySpeed;
		}
	}
}
//공의 새로운 위치를 계산한다. 공이 벽에 부딪치면 반사되도록 한다.
public class Challenge extends JPanel {
	static final int BOARD_WIDTH = 600;
	static final int BOARD_HEIGHT = 300;
	private Ball ball = new Ball();
	public Challenge() {
		this.setBackground(Color.YELLOW);
		Runnable task = () -> {
			while (true) {
				ball.update();
				//람다식을 이용하여서 Runnable 객체를 생성한다. 무한루프를 돌면서 공의 위치를 변경하고 화면에 다시 그린다. 50 밀리초동안 쉰다.
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException ignore) {
				}
			}
		};
		new Thread(task).start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball.draw(g);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(Challenge.BOARD_WIDTH, Challenge.BOARD_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(new Challenge());
	}
}