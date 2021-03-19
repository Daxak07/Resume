import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Quiz {
	private String name;
	private static ArrayList<Question> questions = new ArrayList<>();

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public  void addQuestion(Question question) {
		questions.add(question);
	}

	public static Quiz loadFromFile(String filename) throws FileNotFoundException {
        Quiz q = new Quiz();
		Scanner sc = new Scanner(new File(filename));
		while (sc.hasNextLine()) {
            String description = sc.nextLine();
			String answer = sc.nextLine();
			
			if (sc.hasNextLine()) {
				String temp = sc.nextLine();
				
				if (temp.equals("")) {
					Question question = new FillIn();
					question.setDescription(description.replace(" {blank} ", "________"));
					question.setAnswer(answer);
					q.addQuestion(question);
				} 
				else {
					Question question = new Test();
					question.setDescription(description);
					question.setAnswer(answer);
					String[] listOptions = new String[4];
					listOptions[0] = answer;
					int n = 1;
					
					while(!temp.equals("")) {
						listOptions[n] = temp;
						temp = sc.nextLine();
						n++;
					}

					((Test) question).setOptions(listOptions);
					q.addQuestion(question);
				}
			}
		}
		return q;
	}

	public void start() {
		System.out.println("WELCOME TO \"JavaQuiz\" QUIZ");
		System.out.println("__________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
		int correctAnswer = 0;

        for (int i = 0; i < questions.size(); i++) {
            int randomNumber = random.nextInt(questions.size());
            while (randomNumbers.contains(randomNumber)) {
                randomNumber = random.nextInt(questions.size());
            }
            randomNumbers.add(randomNumber);
        }

        for (int i = 0; i < randomNumbers.size(); i++) {

            Question question = questions.get(randomNumbers.get(i));
            System.out.println((i + 1) + ". " + question.getDescription());

            if (question.getClass().getName().equals("Test")) {
                Test test = (Test) question;
                ArrayList<Integer> exict = new ArrayList<>();
                String[] options = new String[10];
                for (int j = 0; j < 4; j++) {
                    int rand = random.nextInt(4);
                    while (exict.contains(rand)) {
                        rand = random.nextInt(4);
                    }
                    exict.add(rand);
                    System.out.println(test.labels.get(j) + ") " + test.getOptionsAt(rand));
                    options[j] = test.getOptionsAt(rand);
                }
                test.setOptions(options);


                System.out.print("Enter the correct choice: ");
                String choice = sc.next();
                boolean boo = true;
                while (boo) {
                    int c = choice.charAt(0) - 65;
                    if (test.getOptionsAt(c).equals(test.getAnswer())) {
                        System.out.println("Correct!");
                        System.out.println("");
                        correctAnswer++;
                    } else {
                        System.out.println("Incorrect!");
                        System.out.println("");
                    }
                    boo = false;
                    c++;
                }
            }
            else { 
                FillIn fillIn = (FillIn) question;
                System.out.print(fillIn);
                String choice = sc.next();
                if (choice.toLowerCase().equals(fillIn.getAnswer().toLowerCase())) {
                    System.out.println("Correct!\n");
                    correctAnswer++;
                } else {
                    System.out.println("Incorrect!\n");
                }
            }
            System.out.println("---------------------------------------------------\n");
        }
        System.out.println("Correct answers: " + correctAnswer + "/" + questions.size() + " (" + ((double)correctAnswer / questions.size() * 100.0) + "%)");
    }
}