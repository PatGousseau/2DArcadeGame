
public class DoubleJump {

	private double doubleJumpX;
	private double doubleJumpY;
	private double doubleJumpRadius = 0.012;
	
	public DoubleJump(double doubleJumpX,double doubleJumpY) {
		this.doubleJumpX = doubleJumpX;
		this.doubleJumpY = doubleJumpY;
	}
	
	public void drawDoubleJump() {
		StdDraw.setPenColor(StdDraw.CYAN);
		StdDraw.filledCircle(doubleJumpX,doubleJumpY , doubleJumpRadius);
	}
	
	public void moveDoubleJ(double speed) {
		doubleJumpX -= speed;
		drawDoubleJump();
	}
	
	public double getDoubleJumpX() {
		return doubleJumpX;
	}
	
	public double getDoubleJumpY() {
		return doubleJumpY;
	}
	
	public double getRadius() {
		return doubleJumpRadius;
	}
}
