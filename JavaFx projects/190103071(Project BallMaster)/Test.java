import java.util.ArrayList;

class Test extends Question{
    String[] options;
    int numOfOptions;
    ArrayList<Character> labels;

    Test(){
        labels = new ArrayList<Character>();
        labels.add('A');
        labels.add('B');
        labels.add('C');
        labels.add('D');
        numOfOptions = labels.size();
    }
    public void setOptions(String[] options){
        this.options = options;
    }
    public String getOptionsAt(int numOfOptions){
        return options[numOfOptions];
    }
    
    public String toString(){
        return "";
    }
}