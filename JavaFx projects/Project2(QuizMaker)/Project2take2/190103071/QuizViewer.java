import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.*;

public class QuizViewer extends Application {
    private RadioButton[] radioButtons = new RadioButton[4];
    private BorderPane borderPane = new BorderPane();
    private TextField tfAnswer = new TextField();
    private TextArea textArea = new TextArea();
    private Label status = new Label();
    private List<Question> questions;
    private VBox vBox = new VBox();
    private int countAnswers = 0;
    private int count = 0;


    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        Button loadFile = new Button("Load file");
        borderPane.setCenter(loadFile);

        Scene scene = new Scene(borderPane, 400, 320);
        primaryStage.setTitle("Quiz Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        loadFile.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file.getName().contains("_") || file.length() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Quiz Viewer: Error");
                alert.setHeaderText("InvalidQuizFormatException");
                alert.setContentText("The file selected does not fit the requirements for a standard Quiz text file format....");
                alert.showAndWait();
            } else {
                openFile(file, 0);
            }
        });
    }

    private void openFile(File file, int item) {
        try {
            Quiz q = Quiz.loadFromFile(file.getPath());
            questions = q.getQuestions();
            Collections.shuffle(questions);

            showQuestionOptions(vBox, count);

            HBox hBox = new HBox(45);
            HBox hBox1 = new HBox(45);
            Button btNext = new Button(">>");
            Button btPrev = new Button("<<");

            status.setText("Status: " + (count + 1) + "/" + questions.size() + " questions");


            btPrev.setOnAction(e -> {
                if (count > 0) {
                    if (questions.get(count) instanceof Test) {
                        if (radioButtons[0].isSelected()) {
                            if (questions.get(count).getAnswer().equals(radioButtons[0].getText())) {
                                countAnswers++;
                            }
                        } else if (radioButtons[1].isSelected()) {
                            if (questions.get(count).getAnswer().equals(radioButtons[1].getText())) {
                                countAnswers++;
                            }
                        } else if (radioButtons[2].isSelected()) {
                            if (questions.get(count).getAnswer().equals(radioButtons[2].getText())) {
                                countAnswers++;
                            }
                        } else if (radioButtons[3].isSelected()) {
                            if (questions.get(count).getAnswer().equals(radioButtons[3].getText())) {
                                countAnswers++;
                            }
                        }
                    } else if (questions.get(count) instanceof FillIn) {
                        if (questions.get(count).getAnswer().toLowerCase().equals(tfAnswer.getText().toLowerCase())) {
                            countAnswers++;
                        }
                        tfAnswer.setText("");

                    }
                    --count;
                    status.setText("Status: " + (count + 1) + "/" + questions.size() + " questions");
                    showQuestionOptions(vBox, count);
                } else {
                    status.setText("Status: " + (1) + "/" + questions.size() + " questions" + "\nStart of Quiz");
                }
            });

            btNext.setOnAction(e -> {
                if (questions.get(count) instanceof Test) {
                    if (radioButtons[0].isSelected()) {
                        if (questions.get(count).getAnswer().equals(radioButtons[0].getText())) {
                            countAnswers++;
                        }
                    } else if (radioButtons[1].isSelected()) {
                        if (questions.get(count).getAnswer().equals(radioButtons[1].getText())) {
                            countAnswers++;
                        }
                    } else if (radioButtons[2].isSelected()) {
                        if (questions.get(count).getAnswer().equals(radioButtons[2].getText())) {
                            countAnswers++;
                        }
                    } else if (radioButtons[3].isSelected()) {
                        if (questions.get(count).getAnswer().equals(radioButtons[3].getText())) {
                            countAnswers++;
                        }
                    }
                } else if (questions.get(count) instanceof FillIn) {
                    if (questions.get(count).getAnswer().toLowerCase().equals(tfAnswer.getText().toLowerCase())) {
                        countAnswers++;
                    }
                    tfAnswer.setText("");
                }

                if (count < questions.size() - 1) {
                    ++count;
                    status.setText("Status: " + (count + 1) + "/" + questions.size() + " questions");
                    showQuestionOptions(vBox, count);
                } else if (count == questions.size() - 1 || count > questions.size() - 1){
                    status.setText("Status: " + (count + 1) + "/" + questions.size() + " questions" + "\nEnd Of Quiz");
                }
            });

            Button check = new Button("Check answers");
            check.setOnAction(e -> {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Quiz Viewer: Results");
                alert1.setHeaderText("Number of correct chosers: " + countAnswers + "/" + questions.size());
                alert1.setContentText("You may try again.");
                alert1.showAndWait();
            });

            hBox.getChildren().addAll(status, check);
            hBox.setAlignment(Pos.BOTTOM_CENTER);

            hBox1.getChildren().add(textArea);
            hBox1.setAlignment(Pos.TOP_CENTER);
            hBox1.setPadding(new Insets(10));

            borderPane.setTop(hBox1);
            borderPane.setLeft(btPrev);
            borderPane.setRight(btNext);
            borderPane.setBottom(hBox);


        } 
        catch (InvalidQuizFormatException ex) {
            System.err.print("Invalid Quiz Format");
        }
    }

    private void showQuestionOptions(VBox vBox, int item) {
        Question question = questions.get(item);
        if (question instanceof Test){
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add(((Test) questions.get(item)).getOptionAt(i));
            }

            list.add(((Test) questions.get(item)).getAnswer());
            Collections.shuffle(list);
            textArea.setText(questions.get(item).getDescription());
            ToggleGroup tg = new ToggleGroup();

            for (int j = 0; j < 4; j++) {
                radioButtons[j] = new RadioButton(list.get(j));
                radioButtons[j].setToggleGroup(tg);
            }

            vBox.getChildren().clear();
            vBox.getChildren().addAll(radioButtons);
            borderPane.setAlignment(vBox, Pos.CENTER);
            borderPane.setCenter(vBox);


        } else {
            textArea.setText(questions.get(item).getDescription().replace("{blank}","______"));
            borderPane.setCenter(tfAnswer);
        }
    }
}
