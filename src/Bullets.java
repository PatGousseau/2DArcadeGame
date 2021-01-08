import java.text.DecimalFormat;

public class Bullets {
	private int numBullets;
	private double bulletX;
	private double bulletY;// = 0.23;
	private double radius = 0.008;
	private double bulletDiff;
	private int point = 0;
	
	
	public Bullets(double bulletX, double bulletY, double bulletDiff,int numBullets) {
		DecimalFormat format = new DecimalFormat("##.00");
		this.bulletX = bulletX;
		this.numBullets = numBullets;
		this.bulletDiff = bulletDiff;
		this.bulletY = bulletY;
	}
	
	public void setData(double bulletX, double bulletDiff,int numBullets) {
		this.bulletX = bulletX;
		this.numBullets = numBullets;
		this.bulletDiff = bulletDiff;
		
	}
	
	
	public int getNumBullets() {
		return numBullets;
	}
	
	public double getBulletDiff() {
		return bulletDiff;
	}
	
	public void drawBullets() {
		
		StdDraw.setPenColor(StdDraw.BLACK);
		
		switch(numBullets) {
		
		case 1:
			StdDraw.filledCircle(bulletX, bulletY, radius);
			break;
		case 2:
			StdDraw.filledCircle(bulletX, bulletY, radius);
			StdDraw.filledCircle(bulletX + bulletDiff, bulletY, radius);
			break;
		case 3:
			
			StdDraw.filledCircle(bulletX, bulletY, radius);
			StdDraw.filledCircle(bulletX + bulletDiff, bulletY, radius);
			StdDraw.filledCircle(bulletX + (bulletDiff*2), bulletY, radius);
			break;
		}
	}
	
		public void moveBullets(double speed) {
			
			this.bulletX -= speed;
		
				drawBullets();
			
		}
		
		
		public double getBulletX() {
			return bulletX;
		}
		
		public double getBulletY() {
			return bulletY;
		}
		
		public double getRadius() {
			return radius;
		}
		
		public String toString() {
			return "im a bullet";
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
		}
	
	
}
