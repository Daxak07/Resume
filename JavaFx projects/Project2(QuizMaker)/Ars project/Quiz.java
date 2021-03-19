import java.util.*;
import java.io.*;
class Quiz{
	private String name;
	private ArrayList<Question> questions;
	Quiz(){
		questions = new ArrayList<>(); 
	}
	public void setName(String a){
		this.name = a;
	}
	public String getName(){
		return this.name;
	} 
	public void addQuestion(Question q){
		this.questions.add(q);
		}
	public ArrayList<Question> getQuestion(){
		return questions;
	}
	public static Quiz loadfromFile(String v)throws Exception{
		try{
			if (!v.contains(".txt"))
				throw new InvalidQuizFormatException(); 
			}catch(Exception ex){
				System.out.println("such file doesn't exist");
				//ex.printStackTrace();
				System.exit(1);
			}
			File file = new File(v);
			Scanner in = new Scanner(file);
			ArrayList<String> res = new ArrayList<>();
			ArrayList<ArrayList<String>> arr = new ArrayList<>();
			Quiz quiz = new Quiz();
			try{
				do{
				String s = in.nextLine();
				if (s.length()!=0)
					res.add(s);
					if(s.isEmpty() || !in.hasNext()){
						arr.add(res);
						res = new ArrayList<>();
	
				}
			}while (in.hasNext());
		}catch(Exception ex1){
			//System.out.println(ex1);
			ex1.printStackTrace();
			System.exit(1);
		}
			for (int i=0; i<arr.size(); i++){
				if (arr.get(i).size()==2){
					Fillin d = new Fillin();
					String f =arr.get(i).get(0).toString(); 
					((Fillin)d).setnumOfOptions(arr.get(i).size());
					//String d = f.getDescription();
					String ans = "";
					String[] split = f.split(" ");
						for (int j = 0; j < split.length; j++) {
							if (split[j].equals("{blank}")) {
								split[j] = "_______";
							}
						}
						for (int k = 0; k < split.length; k++) {
							ans += split[k] + " ";
						}
					d.setDescription(ans);
					d.setAnswer(arr.get(i).get(1));
					quiz.addQuestion(d);
			}
			else{
				Test f = new Test();
				f.setDescription(arr.get(i).get(0));
				f.setAnswer(arr.get(i).get(1));
				f.setnumOfOptions(arr.get(i).size());
				String[] op = {arr.get(i).get(1), arr.get(i).get(2), arr.get(i).get(3),arr.get(i).get(4)};
				ArrayList<String> list = new ArrayList<>(Arrays.asList(op));
				Collections.shuffle(list);
				String[] array = new String[list.size()];
				array = list.toArray(array);
				((Test)f).setOptions(array);
				quiz.addQuestion(f);
		}

	}
	return quiz;

	}
	@Override
	public String toString(){
		String res = "";
		for (int i = 0; i<this.questions.size(); i++) {
			res += this.questions.get(i);
		}
		return  res;

	}
	public void start()throws Exception{
		//this.loadfromFile(this.name);

		System.out.println("========================================");
		System.out.println("WELCOME TO \"JavaQuiz\" QUIZ!!!");
		System.out.println();

		Collections.shuffle(questions);

		int point = 0;
		int all = 0;
		String choices ="ABCD";
		Scanner input = new Scanner(System.in);
		for (Question  q : questions) {
			String a="";
			System.out.println("___________________________________________________________________________");
			System.out.println();
			System.out.print(Question.num + "." + " ");
			//System.out.print(q);
			//System.out.println("----------------------------------");
			Question.num++;
			if (q.toString().equals("test")){
				String res = q.getDescription() + "\n";
				String re = q.getAnswer();
				for (int i=0; i<4; i++){
					res += ((Test)q).getLabels().get(i) + ") " + ((Test)q).getOptionAt(i) + "\n";
				}
				System.out.print(res);
				System.out.println("----------------------------------");
				System.out.print("Enter the correct choice:  ");
				a = input.nextLine();
				all++;
				boolean istrue = true;
				do{
					try{	
						if (choices.contains(a))
							istrue = false;
						else 
							throw new Exception();
					}catch(Exception e){
						System.out.print("Invalid choice! Try again (Ex: A, B, ...): ");
					a = input.nextLine();
				}
			}while(istrue);
			int k;
			if (a.equals(Character.toString('A')))
				k = 0;
			else if (a.equals(Character.toString('B')))
				k = 1;
			else if(a.equals(Character.toString('C')))
				k = 2;
			else
				k = 3;

			String an = ((Test)q).getOptionAt(k);
			if (q.getAnswer().equals(an)){
				System.out.println("Correct!");
				point++;
			}

			else
				System.out.println("Incorrect!");
			

			}
			else{
				System.out.println(((Fillin)q).getDescription().toString());
				System.out.println("----------------------------------");
				System.out.print("Try your answer: ");
				a = input.nextLine();
				all++;
				if (a.equals(q.getAnswer())){
					System.out.println("Correct!");
					point++;
					
				}
				else 
					System.out.println("Incorrect!");

			}
	}

	System.out.println("\n__________________________________________________________________________\n");
	System.out.println("Correct answer is: " + point+ "/" + all + " " + (double)point/all*100 + "%");
}


}
