public abstract class Question{
	private String description;
	private String answer;
	protected static int num = 1;
	private int numOfOptions;
	public void setDescription(String a){
		this.description = a;
	}
	public void setAnswer(String b){
		this.answer = b;
	}
	public String getAnswer(){
		return answer;
	}
	public String getDescription(){
		return description;
	}
}