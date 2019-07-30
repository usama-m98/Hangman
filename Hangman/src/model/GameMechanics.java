package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class GameMechanics {

	private String answer;
	private LinkedList<String> dictionary;
	private LinkedList<String> tempStore;
	private LinkedList<Character> checked;
	private char[] checkedChar;
	private int lives;
	
	public GameMechanics(String answer) {
		this.answer = answer;
		this.dictionary = new LinkedList<>();
		this.tempStore = new LinkedList<>();
		this.checked = new LinkedList<>();
		this.checkedChar = new char[answer.length()];
		this.lives = 8;
	}
	

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void createDictionary() throws FileNotFoundException {
		File file = new File("words_alpha.txt");
		Scanner in = new Scanner(file).useDelimiter(" ");
		
		while(in.hasNext()) {
			this.dictionary.add(in.nextLine());
		}
	}

	public LinkedList<String> getDictionary() {
		return dictionary;
	}

	public void setDictionary(LinkedList<String> dictionary) {
		this.dictionary = dictionary;
	}

	public LinkedList<Character> getChecked() {
		return checked;
	}

	public void setChecked(LinkedList<Character> checked) {
		this.checked = checked;
	}

	public void addToCheck(char num) {
		this.checked.add(num);
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void decrementLives() {
		this.lives--;
	}
	
	public char randomChar() {
		Random r = new Random();
		char letter = (char) (r.nextInt(26) + 'a');
		return letter;
	}
	
	public char[] getCheckedChar() {
		return this.checkedChar;
	}
		
	/*
	 * filter the dictionary and keep all the words that guess at the position that is expected
	 */
	public void filteredDictionary(char guess, int pos) {
		this.dictionary.stream().filter(e -> e.indexOf(guess) == pos).forEach(e -> tempStore.add(e));
		this.dictionary.clear();
		this.tempStore.stream().forEach(e -> dictionary.add(e));
	}
	
	/*
	 * if char exists it adds it to the checkedChar array at the same position 
	 * as the field which contains the answer
	 */
	public void gamePlay(String yesOrNo, char guess) {
		if (yesOrNo.equals("y")) {
			int index = this.answer.indexOf(guess);
			while(index >=0) {
				checkedChar[index] = guess;
				filteredDictionary(guess, index);
				index = this.answer.indexOf(guess, index + 1);
			}
			this.printWords();
			
		}else {
			lives--;
			if(lives == 0) {
				System.out.println("You have lost");
			}else {
				System.out.printf("You have %d lives left\n", lives);	
			}
		}
	}
	
	public void printWords() {
		for(int i = 0; i< checkedChar.length; i++) {
			if(checkedChar[i] == 0) {
				System.out.print(".");
			}else {
				System.out.print(checkedChar[i]);
			}
		}
		System.out.println();
	}
	
	
	public char nonDuplicateChar() {
		char letter = this.randomChar();
		
		if(!checked.contains(letter)) {
			checked.add(letter);
			return letter;
		}
		return nonDuplicateChar();
	}
}
