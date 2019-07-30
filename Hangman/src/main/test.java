package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		LinkedList<String> checked = new LinkedList<>();
		
		File file = new File("words_alpha.txt");
		Scanner in = new Scanner(file); 
		
		while(in.hasNextLine()) {
			checked.add(in.nextLine());
		}
		
		for(String words: checked) {
			System.out.println(words);
		}
		
//		checked.stream().filter(e -> (e.indexOf("z") > -1)? true: false).forEach(e-> System.out.println(e));
		
//		Random r = new Random();
//		int lives = 8;
//		String str = "hello";
//		
//		while(lives != 0) {
//			int number = r.nextInt(26) + 97;
//			char guess = (char)number;
//			
//			if(!checked.contains(number)) {
//				if(str.indexOf(guess) > -1) {
//					checked.add(number);
//					System.out.println(guess + " is in string hello");
//				}else {
//					lives--;
//					System.out.println(guess + " is not in the string hello and has not been guessed before");
//					checked.add(number);
//				}
//			}else {
//				System.out.println(guess + " has been guessed before.");
//			}
//		}
		
	}
}
