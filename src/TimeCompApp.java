/**Main GUI: The user will select a range that will determine the number of random elements the array will be filled up
with. Each button will sort the array and calculate the time the specified sorting algorithm took.
 TODO: Add Save option which will write to a text file and save the file
 TODO: Add Open option that allows user to open file and output sorting functions and saved files
 TODO: Add Data Base to output table of different algorithm and their time complexity
 TODO: Add multithreading so multiple users can play the app
 TODO: Add other Algorithms such as Binary Search
@author: Wilyendri Duran
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class TimeCompApp extends Application{
    Button btnMerge = new Button("MergeSort");
    Button btnBubble = new Button("BubbleSort");
    Button btnInsertion = new Button("InsertionSort");
    Button btnQuick = new Button("QuickSort");
    Button btnClear = new Button("Clear");
    Button btnInsert = new Button("Insert");
    Button btnSave = new Button("Save");
    Label lblSize = new Label("Array Size");
    Label lblRange = new Label("Insert Range");
    //Label lblSep = new Label("-");
    TextField textSize = new TextField();
    //TextField textInit = new TextField();
    TextField textEnd = new TextField();
    TextArea textOut = new TextArea();
    int[] arrayToSort;
    boolean isPressedInsert = false;
    TimeCalculator timeCalculator;
    ErrorWindow errorWindow;
    SaveResult saveResult;
    File file = new File("result.txt");

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        HBox hBoxBtns = new HBox();
        hBox.getChildren().addAll(lblSize, textSize, lblRange, textEnd, btnInsert);
        hBoxBtns.getChildren().addAll(btnBubble,btnInsertion,btnMerge,btnQuick, btnClear, btnSave);
        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0,0);
        Pane pane = new Pane(textOut);
        gridPane.add(pane, 0,1);
        gridPane.add(hBoxBtns, 0, 2);

        btnSave.setOnAction(e->{
            if(isPressedInsert){
                saveResult = new SaveResult(file, textOut.getText());
                try {
                    saveResult.writeToFile();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnClear.setOnAction(e->{
            textOut.clear();
            textSize.clear();
            textEnd.clear();
            isPressedInsert = false;
        });

        btnInsert.setOnAction(e->{
            if(!textSize.getText().isEmpty() && !textEnd.getText().isEmpty()
                    || !textSize.getText().isBlank() && !textEnd.getText().isBlank()){
                isPressedInsert = true;
                fillArray();
            }else{
                errorWindow = new ErrorWindow("Both data must be inserted.");
                Stage errorStage = new Stage();
                try {
                    errorWindow.start(errorStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnBubble.setOnAction(e-> {
            if(isPressedInsert){
                try {
                    long time = timeCalculator.calculateBubbleTime();
                    textOut.appendText("Bubble Sort took " + time + "ms  to sort an array of "
                            + arrayToSort.length + " elements. \n");
                    textOut.appendText("  ^Elements ordered by BubbleSort: " + timeCalculator + ". \n");
                } catch (TimeoutException ex) {
                    textOut.setText(
                            "Operation cancelled due to time out exception. Try again with a lower size. \n"
                    );
                }

            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
                    Stage errorStage = new Stage();
                    errorWindow.start(errorStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        btnInsertion.setOnAction(e->{
            if(isPressedInsert){
                try {
                    long time = timeCalculator.calculateInsertionTime();
                    textOut.appendText("Insertion Sort took " + time + "ms  to sort an array of "
                            + arrayToSort.length + " elements. \n");
                    textOut.appendText("  ^Elements ordered by InsertionSort: " + timeCalculator + ". \n");
                } catch (TimeoutException ex) {
                    textOut.setText(
                            "Operation cancelled due to time out exception. Try again with a lower size. \n"
                    );
                }
            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
                    Stage errorStage = new Stage();
                    errorWindow.start(errorStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }

        });

        btnQuick.setOnAction(e->{
            if(isPressedInsert){
                try{
                    long time = timeCalculator.calculateQuickTime();
                    textOut.appendText("Quick Sort took " + time + "ms  to sort an array of "
                            + arrayToSort.length + " elements. \n");
                    textOut.appendText("  ^Elements ordered by QuickSort: " + timeCalculator + ". \n");

                }catch (StackOverflowError ex){
                    textOut.setText(
                            "Operation cancelled due to memory overflow error. Try again with a lower size. \n"
                    );
                }
            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
                    Stage errorStage = new Stage();
                    errorWindow.start(errorStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }

        });

        btnMerge.setOnAction(e->{
            if(isPressedInsert){
                long time = timeCalculator.calculateMergeTime();
                textOut.appendText("Insertion Sort took " + time + "ms  to sort an array of "
                        + arrayToSort.length + " elements. \n");
                textOut.appendText("  ^Elements ordered by MergeSort: " + timeCalculator + ". \n");
            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
                    Stage errorStage = new Stage();
                    errorWindow.start(errorStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        Scene scene = new Scene(gridPane, 500, 270);
        stage.setTitle("Time Complexity Application");
        stage.setScene(scene);
        stage.show();
    }

    public void fillArray(){
        Random rand = new Random();
        arrayToSort = new int[Integer.parseInt(textSize.getText().trim())];
        for(int i = 0; i < arrayToSort.length; i++){
            arrayToSort[i] = rand.nextInt(Integer.parseInt(textEnd.getText().trim()));
        }
        timeCalculator = new TimeCalculator(arrayToSort);
    }


}
