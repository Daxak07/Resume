import java.io.*;

public class QuizMaker {
	public static void main(String[] args) {
		try {
			String name = args[0];
        	Quiz quiz = Quiz.loadFromFile(name);
        	quiz.start();
		} 
		catch (FileNotFoundException e) {
			System.err.println("Such a file does not exict!");
		}
	}
}