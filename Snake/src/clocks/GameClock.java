package clocks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import actions.Collision;
import game.Snake;

public class GameClock extends Thread{
	
	public static boolean running = true;
	
	public void run() {
		while(running) {
			try {
				sleep(200);
				Snake.move();
				Snake.waitToMove = false;
				Collision.collidePickUp();
				if(Collision.collideSelf()) {
					Snake.tails.clear();
					//Score
					Snake.score = 0;
					
					if(Snake.bestscore > Snake.bestScoreCheck) {
						writeBestScore();
					}
				}
				if(Collision.colliedWall()) {
					Snake.tails.clear();
					Snake.head.setX(7);
					Snake.head.setY(7);
					//Score
					Snake.score = 0;
					
					if(Snake.bestscore > Snake.bestScoreCheck) {
						writeBestScore();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void writeBestScore() {
		
        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("BestScore.txt")));
            pWriter.println(Snake.bestscore);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        }
	}
}
