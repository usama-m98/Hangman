package main;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Game;
import model.GameMechanics;

public class PlayGame {

	private Game game;
	
	public PlayGame() throws FileNotFoundException {
		
		game = new Game();
		System.out.println("A game of hangman is starting");
		System.out.println("Word has been generated");
		game.display();
		Scanner in = new Scanner(System.in);
		
		/*a loop that repeats until lives and answer are either both true or false
		 * which means game has ended. (XNor)
		 */
		while(!(game.getLives() != 0) == game.getIsAnswer().equals(game.getAnswer())) {
			System.out.println("Make a guess:");
			String input = in.nextLine();
			
			if(input.length() == 1) {
				char letter = input.charAt(0);
				game.checkChar(letter);
			}else {
				game.checkString(input);
			}

			game.display();
			
			if(game.getLives() == 0) {
				System.out.println("You've lost\n"
						+ "The correct answer was: " + game.getAnswer());
			}
			
		}
		game.winOrLose();
		
		game.getPlayerGuess().stream().forEach(e -> System.out.print(e + " "));
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		new PlayGame();
	}
}
