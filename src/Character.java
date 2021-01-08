import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Character {

	private static double halfHeight;
	private static double halfWidth;
	public static double bodyX = 0.1;
	private static double bodyY;
	private static int score = 0;
	private static int numDJ = 0;
	boolean point = false;
	boolean avoidBullet;
	private static double speed = 0.005;
	private static int DJGot = 99;
	static boolean addedAlready = false;
	int sumPoints = 0;
	public static double characterSpeed = 0.00015;
	boolean heDead = false;
	int jumpSpeed = 50;
	double jumpHeight = 0.125;
	double bottom = 0.24;
	
	public Character(double bodyY, double halfHeight) {
		this.bodyY = bodyY;
		this.halfHeight = halfHeight;
		this.halfWidth = halfHeight/2;
	}
	
	public void stopGame(Bullets[] allBullets) {
		StdDraw.clear(StdDraw.RED);
		StdDraw.text(0.5, 0.8, "You lose!");
		while(true) {
			
			StdDraw.clear(StdDraw.RED);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5, 0.8, "You lose!");
			StdDraw.text(0.5, 0.7, "score:"+ getScore());
			StdDraw.text(0.5, 0.6, "Press 's' to save your score and check out the leaderboard");
			drawCharacter(bodyX, bodyY, halfHeight,halfWidth);
			for(int i = 0; i < allBullets.length; i++) {
				allBullets[i].drawBullets();
			}
			StdDraw.show(1);
			
			
			
			
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				break;
			}
		}
		
	}
	
	public boolean moveRight(double characterSpeed, Bullets[] allBullets) {
		
		this.bodyX += characterSpeed;
		drawCharacter(bodyX, bodyY, halfHeight, halfWidth);
		if(isHit(allBullets,halfHeight, halfWidth,bodyY)){

			heDead = true;
			
		}
		
		return heDead;
	}
	
	public boolean moveLeft(double characterSpeed,Bullets[] allBullets) {
		
		
		bodyX -= characterSpeed;
		drawCharacter(bodyX, bodyY, halfHeight, halfWidth);
		if(isHit(allBullets,halfHeight, halfWidth,bodyY)){
			heDead = true;
			
		}
		return heDead;
	}
	
	public boolean isOnX(Bullets bullet, Character guy) {
		boolean isOn = false;
		if(guy.getBodyX() >= bullet.getBulletX() && guy.getBodyX() <= (bullet.getBulletX() + (bullet.getNumBullets() * bullet.getBulletDiff()))) {
	
			isOn = true;
		}
		return isOn;
		
	}
	
	public boolean gotPoint(Bullets[] allBullets,  Character guy, double halfHeight, double halfWidth) {
		boolean yes = false;

		for(int i =0; i< 89; i++){
			
			
			if(isOnX(allBullets[i], guy) ) {
				if(!(guy.isHit(allBullets,halfHeight, halfWidth,bodyY-(halfWidth/2)))){
	
					allBullets[i].setPoint(1);
					
					yes = true;
				}
			}
		}
		return yes;
	}
	
	public boolean isHit(Bullets[] allBullets, double halfHeight, double halfWidth, double bodyY) {
		
		halfWidth/=2;
		halfHeight/=2;
		boolean hit = false;
		//System.out.println(bodyY+halfHeight);
		for(int i = 0; i < allBullets.length; i++) {
			if((bodyX + halfWidth) >= (allBullets[i].getBulletX() - allBullets[i].getRadius()) && (bodyX - halfWidth) <= (allBullets[i].getBulletX() + (allBullets[i].getBulletDiff() * (allBullets[i].getNumBullets()-1))) ) {
				
				if((allBullets[i].getBulletY() >= (bodyY- halfHeight) && allBullets[i].getBulletY() <= (bodyY+ halfHeight))){
					System.out.println(bodyY);
					hit = true;
					
					
				}
			}
		}
		return hit;
	}
	
public boolean gotDoubleJ(ArrayList<DoubleJump> allDoubleJumps) {
		
		boolean gotIt = false;
		
		for(int i = 0; i < allDoubleJumps.size(); i++) {
			if((bodyX + halfWidth) >= (allDoubleJumps.get(i).getDoubleJumpX() - allDoubleJumps.get(i).getRadius()) && (bodyX - halfWidth) <= (allDoubleJumps.get(i).getDoubleJumpX() + (allDoubleJumps.get(i).getRadius())) ) {
				if((allDoubleJumps.get(i).getDoubleJumpY() >= (bodyY- halfHeight) && allDoubleJumps.get(i).getDoubleJumpY() <= (bodyY+ halfHeight))){
					gotIt = true;
					allDoubleJumps.remove(i);
					DJGot = i;
				}
			}
		}
		return gotIt;
	}

	
	
	public int howManyPoints(Bullets[] allBullets) {
		sumPoints = 0;
		for(int i = 0; i < allBullets.length; i++) {
			sumPoints += allBullets[i].getPoint();
			
		}
		return sumPoints;
	}
	
	public double getBodyY() {
		return bodyY;
	}
	
	public double getHalfHeight() {
		return halfHeight;
	}
	
	public double getHalfWidth() {
		return halfWidth;
	}
	
	public double getBodyX() {
		return bodyX;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getScore() {
		return score;
	}
	
	public boolean getAvoidBullet() {
		return avoidBullet;
	}
	
	public int getNumDJ() {
		return numDJ;
	}
	
	public int getDJGot() {
		return DJGot;
	}
	
	public boolean getCanUsed() {
		 return canUse;
	}
	
	public void drawCharacter(double bodyX, double bodyY, double halfHeight, double halfWidth) {
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(bodyX, bodyY, "stand.png", halfWidth , halfHeight);
		
		
	}
	
	public void drawJump(double bodyX, double bodyY, double halfHeight, double halfWidth) {

		StdDraw.picture(bodyX, bodyY, "jump.png", halfWidth, halfHeight);
		
		
	}
	
	public void drawCrouch(double bodyX, double bodyY, double halfHeight, double halfWidth) {
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.picture(bodyX, bodyY, "down.png", halfWidth, halfHeight);
		
		
	}
	
	public void useDoubleJ(Character guy, Bullets[] allBullets, ArrayList<DoubleJump> allDoubleJumps,String background) {
		double tempBodyY = guy.getBodyY();
		double top = tempBodyY +0.001;
	
		if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
			numDJ--;
			
			jump(guy,allBullets,allDoubleJumps,0.3,tempBodyY,background);
			
		
		}
	}
	
	boolean canUse = true;
	public  boolean jump(Character guy, Bullets[] allBullets,ArrayList<DoubleJump> allDoubleJumps, double top, double jumpStart, String background) {
		
		StdDraw.clear();
		int time = 0;
		guy.drawCharacter(bodyX, bodyY, halfHeight,halfWidth );
		boolean heDead = false;
		boolean gotDoubleJ = false;
		avoidBullet = false;
		
		
		do {
		
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, background, 1, 1);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5, 0.7, "score: "+ guy.howManyPoints(allBullets));
			StdDraw.text(0.5, 0.9, "Number of double jumps left: "+ guy.getNumDJ());
			time++;
			bodyY = jumpStart - jumpHeight/(Math.pow(jumpSpeed/2, 2))*time*(time-jumpSpeed);
			guy.drawJump(bodyX, bodyY, halfHeight,halfWidth);
			guy.gotPoint(allBullets, guy,halfHeight,halfWidth);

			if(gotDoubleJ(allDoubleJumps)) {
				gotDoubleJ = true;
			}
			if(isHit(allBullets,halfHeight, halfWidth,bodyY)){
				heDead = true;
				break;
			}
			
			else {
				for(int j = 0; j < allBullets.length; j++) {
					allBullets[j].moveBullets(speed);
				}
				
				
				for(int j = 0; j < allDoubleJumps.size(); j++) {
					
					allDoubleJumps.get(j).moveDoubleJ(speed);
					
				}
				if(canUse) {
					if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
							if(numDJ > 0) {
								canUse = false;
								
								useDoubleJ(guy, allBullets, allDoubleJumps,background);
								break;
						}
					}
				}
				

			}
			
			StdDraw.show(1);
		}
		
		while(bodyY>bottom);
		
	
		//move this in the loop 
		if(gotDoubleJ) {
			numDJ++;
		}
		canUse = true;
		return heDead;
	}
	
	
	public boolean  duck(Character guy, Bullets[] allBullets) {
		
		boolean dead = false;
		avoidBullet = false;
		 
		guy.drawCrouch(bodyX, bodyY-(halfWidth/2), halfWidth,halfHeight);

		if(isHit(allBullets,halfWidth, halfHeight,bodyY-(halfWidth/2))){
			System.out.println("sint");
			dead = true;
			return dead;
		}

	return dead;
		
		
	}
	
	public void addScore(Character guy,Bullets[] allBullets) {
		
			if(duck(guy,allBullets)) {
				score++;
				speed+=0.000015;
			}
		addedAlready = true;
		
	}
}
