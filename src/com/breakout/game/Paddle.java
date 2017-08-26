import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Paddle implements GameElementInterface{
	
	private int posX;
	private int posY;
	private int width;
	private int height;
	private Color paddleColor;
	
	
	public Paddle(int posX, int posY, int width, int height, Color paddleColor) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.paddleColor = paddleColor;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return posX;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return posY;
	}

	@Override
	public void setX(int posX) {
		// TODO Auto-generated method stub
		this.posX = posX;
	}

	@Override
	public void setY(int posY) {
		// TODO Auto-generated method stub
		this.posY = posY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void draw(Graphics g){
		g.setColor(paddleColor);				
		g.fillRect(posX, posY, width, height);
	}

	

//	public void checkBounds(int boundaryX){
//		if (posX < 0){
//			posX = 0;
//		}else {
//			moveLeft();
//		}
//		if (posX > boundaryX){
//			posX = boundaryX;
//		}else {
//			moveRight();
//		}
//	}
	
//	public void moveRight() {
//		posX += 20;
//	}
	
//	public void moveLeft() {
//		posY -= 20;
//	}
	
	
	
	
}
