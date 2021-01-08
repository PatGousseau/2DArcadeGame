import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; 

public class Gameplay {
	
	private static double bulletDiff = 0.03; // randomize 
	private static int totalBullets= 900;
	private static int score = 0;
	public static  String FILE_NAME = "Leaderboard.txt";
	static ArrayList<String[]> allScores = new ArrayList<String[]>();
	static boolean lost = false;
	static boolean dead = false;
	public static char difficulty; 
	private static int totalDoubleJ = 90;
	public static double characterSpeed = 0.0005;
	private static int maxBulletGap;
	private static int minBulletGap;
	private static int maxNumBullets;
	
	
	public static String getName() {
		 Scanner keyboard = new Scanner(System.in);
	      System.out.println("Enter your name:");
	     String name = keyboard.nextLine();
	     return name;
	}
	
	public static void displayLeaderboard(int score, String name) throws IOException{
		
		 String[] lineArray = null;
		 
		try{
			BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
			String line = br.readLine(); // read the first line
			  
			if(line == null) {
				allScores.add(0,new String[] {name, String.valueOf(score)});
			}
				
			else {
				
				//add all scores on .txt to arraylist
				while(line != null){
					lineArray = line.split(" ");
					allScores.add(lineArray);
					line = br.readLine();
				}
				
				int arraySize = allScores.size();
				int spot = 0;
				//add new score to correct spot in arraylist
				for(int i = 0; i < arraySize; i++) {
					if(Integer.parseInt(allScores.get(i)[1]) <= score) {
						spot = i;
						break;
					}
				}
				allScores.add(spot,new String[] {name, String.valueOf(score)});
			}
			
			PrintWriter writer = null;
			FileWriter fw = new FileWriter(FILE_NAME, false);
			writer = new PrintWriter(fw);
			int newSize = allScores.size();
						
			for(int i = 0; i < newSize; i ++) {
				writer.println(allScores.get(i)[0] + " " + allScores.get(i)[1]);
			}
			writer.close();
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		while(true) {
			StdDraw.clear(StdDraw.PRINCETON_ORANGE);
			Font font = new Font("Engravers MT", Font.BOLD, 32);
			Font font1 = new Font("Times new roman", Font.BOLD, 20);
			StdDraw.setFont(font);
			switch(difficulty) {
				case 'b':
					StdDraw.text(0.5, 0.8, "Leaderboard (easy)");
					break;
				case 'i':
					StdDraw.text(0.5, 0.8, "Leaderboard (int.)");
					break;
				case 'h':
					StdDraw.text(0.5, 0.8, "Leaderboard (hard)");
					break;
			}
			
			double yPos = 0.6;
			StdDraw.setPenColor();
			StdDraw.setFont(font1);
			int size = allScores.size();
			for(int i =0; i < size; i++) {
				if(i < 5) {
					StdDraw.setPenColor();
					StdDraw.setFont(font1);
					StdDraw.text(0.3, yPos, String.valueOf(i+1) + ".");
					StdDraw.text(0.5, yPos,  " " + allScores.get(i)[0] + ": " + allScores.get(i)[1]);
					yPos -= 0.1;
					}
			}
			StdDraw.show(1);
		}
	}
	
	public static String chooseDifficulty() {
		
		String background = "";
		
		while(true) {
			StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
			StdDraw.text(0.5, 0.8, "Choose the difficulty:");
			StdDraw.text(0.5, 0.6, "Beginner ('b')");
			StdDraw.text(0.5, 0.5, "Intermediate ('i')");
			StdDraw.text(0.5, 0.4, "HardAsHell ('h')");
			if(StdDraw.isKeyPressed(KeyEvent.VK_B)) {
				maxBulletGap = 7;
				minBulletGap = 4;
				maxNumBullets = 2;
				FILE_NAME = "Leaderboard(beginner).txt";
				difficulty = 'b';
				background = "background_beg.png";
				break;
			}
			else if(StdDraw.isKeyPressed(KeyEvent.VK_I)) {
				maxBulletGap = 6;
				minBulletGap = 3;
				maxNumBullets = 3;
				FILE_NAME = "Leaderboard(intermediate).txt";
				difficulty = 'i';
				background = "background_interm.png";
				break;
			}
			
			else if(StdDraw.isKeyPressed(KeyEvent.VK_H)) {
				maxBulletGap = 5;
				minBulletGap = 2;
				maxNumBullets = 3;
				FILE_NAME = "Leaderboard(hard).txt";
				difficulty = 'h';
				background = "background_hard_1.png";
				break;
			}
			StdDraw.show(1);
		}
		return background;
	}
	
	public static int  constantBullets(String name, String background) {
		
		Bullets[] allBullets = new Bullets[totalBullets];
		ArrayList<DoubleJump> allDoubleJumps = new ArrayList<DoubleJump>();
		Random generator = new Random();
		double gap = 0.3;
		double dJY = 0.3;
		double doubleJGap = 4;
		
		//create all bullets
		for(int i =0; i< allBullets.length; i++) {
			int gapDifference = generator.nextInt(maxBulletGap + 1 -minBulletGap) + minBulletGap;
			double gapDiff = (double)gapDifference/10;
			int randomHeight = generator.nextInt(30 + 1 -20) + 20;
			double height = ((double) randomHeight/100);
			//height of guy is 0.303;
			allBullets[i] = new Bullets(1 + gap, height ,bulletDiff, ((int)(Math.random() *maxNumBullets)) +1);
			gap+= gapDiff;
		}
		
		//create all double jumps
		for(int i =0; i < totalDoubleJ; i++) {
			int doubleJGapDiff = (int)(Math.random() * 10);
			allDoubleJumps.add(new DoubleJump(1+ doubleJGap, dJY));
			doubleJGap += doubleJGapDiff;	
		}


		Character guy = new Character(0.24,0.06*2);
		
		while(true) {
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, background, 1, 1);

			if(!lost) {
				
				//move all bullets
				for(int i = 0; i < 500; i ++) {
					allBullets[i].moveBullets(guy.getSpeed());
				}
				
				//move all double jumps
				for(int i =0; i < allDoubleJumps.size(); i++) {
						allDoubleJumps.get(i).moveDoubleJ(guy.getSpeed());	
				}
				

				
				//if jump
				if(StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
					lost = guy.jump(guy, allBullets, allDoubleJumps,0.4,0.24, background);
				
				}
				
				//if duck
				if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
					 lost = guy.duck(guy,allBullets);
					 if(!lost) {
						 guy.gotPoint(allBullets, guy,guy.getHalfWidth(), guy.getHalfHeight());
					 }
				
					
				
					}

				
				else {
					
					guy.drawCharacter(guy.getBodyX(), guy.getBodyY(), guy.getHalfHeight(), guy.getHalfWidth());//0.1,0.215,0.03,0.03/2);
					if(guy.isHit(allBullets,0.06*2,0.06, guy.getBodyY())){
						lost = true;	
					}
				}
			}
			
			 if(lost){
				StdDraw.clear(StdDraw.RED);
				StdDraw.text(0.5, 0.8, "You lose!");
				StdDraw.text(0.5, 0.6, "Press 's' to save your score and check out the leaderboard");
				
				if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
					break;
				}
			}

			StdDraw.setPenColor();
			StdDraw.text(0.5, 0.7, "score: "+ guy.howManyPoints(allBullets));
			StdDraw.text(0.5, 0.9, "Number of double jumps left: "+ guy.getNumDJ());
			StdDraw.show(1);
	
		}
		return (int) guy.howManyPoints(allBullets);
	}
	
	
	public static void main(String[] args) {
		String name = getName();
		String background = chooseDifficulty();
		int newScore = constantBullets(name, background);

		try {
		displayLeaderboard(newScore, name);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	
	}
}

