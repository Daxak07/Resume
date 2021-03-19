import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import java.util.Map;
import java.util.HashMap;

public class Run1 extends BorderPane{
      private int index = 0;
      private int numques = 1;
      private String status = String.format("Status: %d/%d questions", this.numques, 7);
      private Label l = new Label(status);
      private HBox v1 = new HBox();
      private TextField t1 = new TextField();
      private RadioButton rb1 = new RadioButton();
      private RadioButton rb2 = new RadioButton();
      private RadioButton rb3 = new RadioButton();
      private RadioButton rb4 = new RadioButton();
      private Map<Integer, String> map = new HashMap<>();

      
      public Run1() throws Exception{

        Quiz q = new Quiz();
        Quiz d = q.loadfromFile("JavaQuiz.txt");
        ArrayList<Question> list = d.getQuestion();
        Collections.shuffle(list);
        BorderPane buttons = new BorderPane();
        GridPane textGrid = new GridPane();
        String[] text = new String[list.size()];
        Button b1 = new Button("<<");
        Button b2 = new Button(">>");
        Button b3 = new Button("Check answer");
        buttons.setLeft(b1);
        buttons.setRight(b2);     
        this.t1.setMaxWidth(200);

        TextArea text1 = new TextArea();
        text1.setPrefSize(600, 150);
        text1.setText(list.get(this.index).getDescription());
        buttons.setTop(text1);
        setTop(buttons);
        

        if (list.get(this.index).toString().equals("test")){

            ToggleGroup group = new ToggleGroup();
            this.rb1 = new RadioButton(((Test)list.get(this.index)).getOptionAt(0));
            this.rb2 = new RadioButton(((Test)list.get(this.index)).getOptionAt(1));
            this.rb3 = new RadioButton(((Test)list.get(this.index)).getOptionAt(2));
            this.rb4 = new RadioButton(((Test)list.get(this.index)).getOptionAt(3));
            this.rb1.setToggleGroup(group);
            this.rb2.setToggleGroup(group);
            this.rb3.setToggleGroup(group);
            this.rb4.setToggleGroup(group);
            VBox vforradio = new VBox();
            vforradio.getChildren().addAll(this.rb1, this.rb2, this.rb3, this.rb4);
            setCenter(vforradio);

        }else{
            setCenter(t1);
        }
    


       

        this.v1.setSpacing(200);
        this.v1.setAlignment(Pos.BOTTOM_CENTER);
        this.v1.getChildren().addAll(l, b3);
        setBottom(v1);
      b1.setOnAction(e ->{
        if (this.index!=0){
          this.index --;
          this.numques--;
          text1.setText(d.getQuestion().get(this.index).getDescription());
          if (list.get(this.index).toString().equals("test")){
            ToggleGroup group = new ToggleGroup();
              this.rb1 = new RadioButton(((Test)list.get(this.index)).getOptionAt(0));
              this.rb2 = new RadioButton(((Test)list.get(this.index)).getOptionAt(1));
              this.rb3 = new RadioButton(((Test)list.get(this.index)).getOptionAt(2));
              this.rb4 = new RadioButton(((Test)list.get(this.index)).getOptionAt(3));
              this.rb1.setToggleGroup(group);
              this.rb2.setToggleGroup(group);
              this.rb3.setToggleGroup(group);
              this.rb4.setToggleGroup(group);
              this.rb4.setSelected(true);
              VBox vforradio = new VBox();
              vforradio.getChildren().addAll(this.rb1, this.rb2, this.rb3, this.rb4);
              setCenter(vforradio);
            

              

          }else{
              if (this.map.containsKey(this.index)){
                this.t1.setText(map.get(this.index));
                setCenter(this.t1);
            }

          
          }
          this.status =  String.format("Status: %d/%d questions", this.numques, list.size());
              this.v1 = new HBox();
              this.l = new Label(status);
              this.v1.setSpacing(200);
              this.v1.setAlignment(Pos.BOTTOM_CENTER);
    
              this.v1.getChildren().addAll(l, b3);
              setBottom(this.v1);
    
        }else{
          this.status =  String.format("Status: %d/%d questions\n Start of Quiz!", 1, list.size());
              this.v1 = new HBox();
              this.l = new Label(status);
              this.v1.setSpacing(200);
              this.v1.setAlignment(Pos.BOTTOM_CENTER);
    
              this.v1.getChildren().addAll(l, b3);
              setBottom(this.v1);
        }




      });
      b2.setOnAction(e ->{
        if(this.index==0){
          this.map.put(this.index, t1.getText());

        }
        if (this.index>list.size()){
          this.index ++;
          this.numques++;
          text1.setText(d.getQuestion().get(this.index).getDescription());
          if (list.get(this.index).toString().equals("test")){
              ToggleGroup group = new ToggleGroup();
              this.rb1 = new RadioButton(((Test)list.get(this.index)).getOptionAt(0));
              this.rb2 = new RadioButton(((Test)list.get(this.index)).getOptionAt(1));
              this.rb3 = new RadioButton(((Test)list.get(this.index)).getOptionAt(2));
              this.rb4 = new RadioButton(((Test)list.get(this.index)).getOptionAt(3));
              this.rb1.setToggleGroup(group);
              this.rb2.setToggleGroup(group);
              this.rb3.setToggleGroup(group);
              this.rb4.setToggleGroup(group);

              VBox vforradio = new VBox();
              vforradio.getChildren().addAll(this.rb1, this.rb2, this.rb3, this.rb4);
              setCenter(vforradio);
          


          }else{
            if(map.containsKey(this.index)){
              this.t1.setText(map.get(this.index));

            }else{

              map.put(this.index, this.t1.getText());
              this.t1.clear();
          }
            setCenter(this.t1);
            
          }
    
          this.status =  String.format("Status: %d/%d questions", this.numques, list.size());
              this.v1 = new HBox();
              this.l = new Label(status);
              this.v1.setSpacing(200);
              this.v1.setAlignment(Pos.BOTTOM_CENTER);
    
              this.v1.getChildren().addAll(l, b3);
              setBottom(this.v1);
        }else{
          this.status =  String.format("Status: %d/%d questions\n End of Quiz!", list.size(), list.size());
              this.v1 = new HBox();
              this.l = new Label(status);
              this.v1.setSpacing(200);
              this.v1.setAlignment(Pos.BOTTOM_CENTER);
    
              this.v1.getChildren().addAll(l, b3);
              setBottom(this.v1);
        }
        

        System.out.println(this.map);
    

      });
          
      

      }


}