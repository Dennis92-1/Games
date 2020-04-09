package vokabeltrainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VokabeltrainerConsoleVersion {
	

	Map<String, ArrayList<String>> english2German = new HashMap<>();
	String wordToGuess;
	
	public void addToDictionary(String englishWord, ArrayList<String> germanWord) { 
		english2German.put(englishWord, germanWord);
	}
	
	
	
	public void createRandomWordToGuess(){
		
		// returns all keys of the map as set
		Set<String> keySet = english2German.keySet(); 
		int randomIndex = (int)(Math.random()*keySet.size());
		Iterator<String> iterator = keySet.iterator();
		int i= 0;
		
		while(iterator.hasNext()) {
			String word = iterator.next();
			
			if (i== randomIndex){
						wordToGuess= word;
						return;
			}
			i++;
		}
	}
	
	public String getWordToGuess() 	{
		return wordToGuess;
	}
	
	// passes the word I have entered.
	public boolean guess(String guess){	 	
					
		//checks if there is a mapping to the string guess in the variable wordToGuess
		ArrayList<String> solution = english2German.get(wordToGuess);
		Iterator<String> iterator = solution.iterator();
		
		while(iterator.hasNext()){
			String s= iterator.next();
			
			if (guess.equals(s)){	
				return true;
			}	
		}
		return false;
}
	
	public void getSolution() {
		
		//checks if there is a mapping to the string guess in the variable wordToGuess
		ArrayList<String> solution = english2German.get(wordToGuess);
		Iterator<String> iterator = solution.iterator();
		
		String text1 = "Die richtige Antwort wäre";
		
		while(iterator.hasNext()){
			String s= iterator.next();
			text1 = text1 + " " +  s;	
		}
		System.out.println(text1 + " gewesen.");		
	}
	
	public static void main(String[] args){
		
	VokabeltrainerConsoleVersion guessingGame = new VokabeltrainerConsoleVersion();
	
	// an entry is made in the vocabulary book
	ArrayList<String> clean = new ArrayList<>();
	clean.add("reinigen");
	clean.add("säubern");
	guessingGame.addToDictionary("to clean", clean);							
	
	ArrayList<String> expand = new ArrayList<>();
	expand.add("erweitern");
	expand.add("ausbauen");
	guessingGame.addToDictionary("to expan", expand);
	
	ArrayList<String> face = new ArrayList<>();
	face.add("Gesicht");
	guessingGame.addToDictionary("face", face);
	
	ArrayList<String> mouth = new ArrayList<>();
	mouth.add("Mund");
	guessingGame.addToDictionary("mouth", mouth);
	
	Scanner scanner= new Scanner(System.in);
	int wrongAnswer = 0;
	int rightAnswer = 0;
	
	
	do {
		
		// a random word is chosen that one should estimate
		if(wrongAnswer == 0 ) {
			guessingGame.createRandomWordToGuess();
		}
												
		System.out.println("Was heißt \"" + guessingGame.getWordToGuess() + "\" auf Deutsch?");
	
		String guess = scanner.nextLine();
		//check if the word is correct
		boolean correct = guessingGame.guess(guess);
	
		if (correct) {
			System.out.println("Korrekt!");
			wrongAnswer = 0;
			rightAnswer++;
		}
		else {
			System.out.println("Leider falsch!");

			if(wrongAnswer == 5) {
				guessingGame.getSolution();
				wrongAnswer = 0;
			}else {
				wrongAnswer++;
			}
		}
		
	}while(rightAnswer != 20);

	System.out.println("Super, du hast heute genug geübt!");
	scanner.close();
	}
	
}