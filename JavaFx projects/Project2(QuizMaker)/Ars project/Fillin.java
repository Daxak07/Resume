import java.util.*;
public class Fillin extends Question{ 
	private int numOfOptions;
	Fillin() {}
	public void setnumOfOptions(int d){
		this.numOfOptions = d;
	}
	public int getnumOfOptions(){
		return numOfOptions;
	}

	@Override
	public String toString(){
		String d = super.getDescription();
		String[] split = d.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (split[i].equals("{blank}")) {
				split[i] = "_______";
			}
		}
		d = "";
		for (int i = 0; i < split.length; i++) {
			d += split[i] + " ";
		}
	return d + "\n";	
	}
}