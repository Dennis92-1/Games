package Hangman;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HangmanConsole {
	
	// ArrayList with names to guess
	private ArrayList<String> names;
	private Scanner scanner = new Scanner(System.in);

	
	public void run() {
		
		//enter all names from the file "dictionary.txt in the ArrayList
		names = new ArrayList<String>();
		loadData("input/dictionary.txt");
			
		do {
			//get name to Guess
			String nameToGuess = createNameToGuess();
			char[] charNameToGuess = nameToGuess.toCharArray();
			char[] emptyName = new char[charNameToGuess.length];
				
			for(int i = 0; i < emptyName.length; i++) { 
				emptyName[i] = '_';
			}
			
			//if you want to cheat ;)
			System.out.println(nameToGuess);
				
			while(true) {
				
				if(Arrays.equals(charNameToGuess, emptyName)) {
					System.out.println("You won!");
					break;
				}
				
				for (int i = 0; i < emptyName.length; i++) {
					System.out.print(emptyName[i] + " ");
				}
				
				System.out.println();
				System.out.println("Guess");
				
				String input = scanner.next();
				char inputLowerCase = input.toLowerCase().charAt(0);
				char inputUpperCase = input.toUpperCase().charAt(0);
							
				//check if the letter fits
				for (int i = 0; i < charNameToGuess.length; i++) {
					
					if(charNameToGuess[i] == inputLowerCase) {
						emptyName[i] = inputLowerCase;
					}
					
					if (charNameToGuess[i] == inputUpperCase){
						emptyName[i] = inputUpperCase;
					}
				}						
			}
					
		//check if you want play again	
		}while(playAnotherGame());		
	}
	
	//load names to guess from .txt data
	private void loadData(String datName) {
		
		File file = new File(datName);
		
		if(!file.canRead() || !file.isFile()) {
			System.exit(0);
		}
				
		BufferedReader in = null;	
		try {
			in = new BufferedReader(new FileReader(datName));
			String line = null;
			while((line = in.readLine()) != null) {
				names.add(line);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				}catch (IOException e) {
					
				}	
			}		
		}
	}
	
	//take a random word from the .txt file
	private String createNameToGuess(){
		
		int randomNumber = (int) (Math.random() * names.size());
		return names.get(randomNumber);	
	}
	
	//check if you want play again
	private boolean playAnotherGame() {
		
		System.out.println("You wanna play another round?");
		String input = scanner.next().toLowerCase();
		if(input.equals("yes") || input.equals("y")) {
			return true;
		}else {
			System.out.println("Game was closed.");
			scanner.close();
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		HangmanConsole game = new HangmanConsole();
		game.run();
	}
	
	
}

