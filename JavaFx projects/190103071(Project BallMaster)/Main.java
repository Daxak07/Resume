public class Main {
	public static Quiz loadFromFile() throws FileNotFoundException {
		Quiz q = new Quiz();
		Scanner sc = new Scanner(new File(filename));
		while (sc.hasNextLine()) {
			String description = sc.nextLine();
			String answer = sc.nextLine();
			
			if (sc.hasNextLine()) {
				String temp = sc.nextLine();
				
				if (temp.equals("")) {
					Question question = new FillIn();
					question.setDescription(description.replace("{blank}", "________"));
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
}