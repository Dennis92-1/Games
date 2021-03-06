package game;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import gui.Gui;

public class Snake {
	
	public static int score = 0, bestscore = loadBestScore(), bestScoreCheck = bestscore;
	
	public static boolean waitToMove = false;
	
	public static Head head = new Head(7, 7);
	
	public static ArrayList<Tail> tails = new ArrayList<>();
	
	public static PickUp pickUp = new PickUp();
	
	public static void addTail() {
		if(tails.size() < 1 ) {
			tails.add(new Tail(head.getX(), head.getY()));
		}else {
			tails.add(new Tail(tails.get(tails.size()-1).x, tails.get(tails.size()-1).y));
		}
	}
	
	public static void move() {
		
		//Move Tails
		if(tails.size() >= 2) {
			for(int i = tails.size() -1; i >= 1; i--) {
				//you can't move
				if(tails.get(i).isWait()) {
					tails.get(i).setWait(false);
				//you can move
				}else {
					tails.get(i).setX(tails.get(i -1).getX());
					tails.get(i).setY(tails.get(i -1).getY());
				}
			}
		}
		
		//Move first Tail to Head
		if(tails.size() >= 1) {
			
			if(tails.get(0).isWait()) {
				tails.get(0).setWait(false);
			}else {
				tails.get(0).setX(head.getX());
				tails.get(0).setY(head.getY());
			}
		}
		
		//Move Head
		switch (head.getDir()) {
			case RIGHT: 
				head.setX(head.getX() +1);
				break;
			case UP: 
				head.setY(head.getY() -1);
				break;
			case LEFT: 
				head.setX(head.getX() -1);
				break;
			case DOWN: 
				head.setY(head.getY() +1);
				break;
		}
		
	}
	
	public static Point positionToCordinate(int x, int y) {
		
		Point p = new Point(0,0);
		p.x = x * 32 + Gui.xOff;
		p.y = y * 32 + Gui.yOff;
		
		return p;
	}
	
	private static int loadBestScore() {
		
		File file = new File("BestScore.txt");
		int bestScore = 0;

        if (!file.canRead() || !file.isFile())
            System.exit(0);

            BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("BestScore.txt"));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                bestScore = Integer.valueOf(zeile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
        
        return bestScore;	
	}
}
