package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private String answer;
	private int lives;
	private char[] isAnswer;
	private List<String> playerGuess;
	private LinkedList<String> dictionary;
	
	public Game() throws FileNotFoundException {
		this.lives = 8;
		this.playerGuess = new ArrayList<>(); 
		this.dictionary = new LinkedList<>();
		
		//generates the dictionary where the word is found
		File file = new File("words_alpha.txt");
		Scanner in = new Scanner(file).useDelimiter(" ");
		
		while(in.hasNext()) {
			this.dictionary.add(in.nextLine());
		}
		
		//generates a word that is stored in answer for the game
		Random r = new Random();
		int random = r.nextInt(dictionary.size());
		this.answer = this.dictionary.get(random);
		
		this.isAnswer = new char[answer.length()];
	}
	
	/**
	 * Finds all the occurrences where the char is found in the answer and adds it to the 
	 * array.
	 * @param guess is char
	 */
	public void exists(char guess) {
		int index = answer.indexOf(guess);
		while(index >= 0) {
			this.isAnswer[index] = guess;
			index = answer.indexOf(guess, index+1);
		}
	}
	
	/**
	 * takes a char and checks weather it exists in answer, if it does it adds it to the
	 * char array isAnswer using the exists method otherwise it decrements life by one.
	 * @param letter
	 */
	public void checkChar(char letter) {
		if(!this.isAnswerFull()) {
			if(answer.indexOf(letter) >= 0) {
				this.exists(letter);
				this.playerGuess.add(letter + "");
			}else {
				lives--;
				System.out.println(letter + " is not in the word\n"
						+ "You have " + lives + " lives left");
				this.playerGuess.add(letter + "");
			}
		}
	}
	
	/**
	 * check's if the char array isAnswer full
	 * @return true if it is otherwise false
	 */
	public boolean isAnswerFull() {
		boolean isFull = true;
		for(int i = 0; i < isAnswer.length; i++) {
			if(isAnswer[i] == 0) {
				isFull = false;
			}
		}
		return isFull;
	}
	
	/**
	 * checks if the guess was the same as the answer
	 * if its true you automatically win
	 * @param str
	 */
	public void checkString(String str) {
		if (answer.equals(str)) {
			isAnswer = str.toCharArray();
		}else {
			lives--;
			System.out.println(str + " is wrong\n"
					+ "You have " + lives + " lives left");
			playerGuess.add(str);
		}
	}
	
	/**
	 * prints out a congratulation message if you get the answer.
	 */
	public void winOrLose() {
		if(this.getIsAnswer().equals(answer)) {
			System.out.println(this.getIsAnswer() + " is the right answer");
			System.out.println("\n\nCONGRATULATIONS YOU WIN!");
		}
	}

	/**
	 * displays either the correct guessed letter or _ if the specific letter has
	 * not been guessed
	 */
	public void display() {
		for(int i=0; i<answer.length(); i++) {
			if(isAnswer[i]==0) {
				System.out.print("_ ");
			}else {
				System.out.print(isAnswer[i] + " ");
			}
		}
		System.out.println();
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public List<String> getPlayerGuess() {
		return playerGuess;
	}

	public void setPlayerGuess(List<String> playerGuess) {
		this.playerGuess = playerGuess;
	}

	public LinkedList<String> getDictionary() {
		return dictionary;
	}

	public void setDictionary(LinkedList<String> dictionary) {
		this.dictionary = dictionary;
	}
	
	public String getIsAnswer() {
		String isAnswer = "";
		for(int i =0; i < this.isAnswer.length; i++) {
			isAnswer+= this.isAnswer[i];
		}
		return isAnswer;
	}

}
