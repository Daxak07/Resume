import java.util.*;
public class Test extends Question{
	private String[] options;
	private int numOfOptions;
	private ArrayList<String> labels = new ArrayList<String>(Arrays.asList("A", "B", "C", "D"));
	public Test(){

	}
	public void setOptions(String[] a){
		this.options = a;
	}
	
	public String getOptionAt(int k){
		return options[k];
	}
	public void setnumOfOptions(int d){
		this.numOfOptions = d;
	}
	public int getnumOfOptions(){
		return numOfOptions;
	}
	public ArrayList<String> getLabels(){
		return labels;
	}
	@Override
	public String toString(){
		return "test";
		
	}
}