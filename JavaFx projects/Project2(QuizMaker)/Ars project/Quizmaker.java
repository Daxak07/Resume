public class Quizmaker{
	public static void main(String args[])throws Exception{
			Quiz a = new Quiz();
			Quiz c = a.loadfromFile(args[0]);
			c.start();
	}
}