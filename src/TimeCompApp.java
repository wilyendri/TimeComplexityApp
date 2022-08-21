/**Main GUI: The user will select a range that will determine the number of random elements the array will be filled up
with. Each button will sort the array and calculate the time the specified sorting algorithm took.
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

import java.util.Random;
import java.util.concurrent.TimeoutException;

public class TimeCompApp extends Application{
    Button btnMerge = new Button("MergeSort");
    Button btnBubble = new Button("BubbleSort");
    Button btnInsertion = new Button("InsertionSort");
    Button btnQuick = new Button("QuickSort");
    Button btnClear = new Button("Clear");
    Label lblSize = new Label("Array Size");
    Label lblRange = new Label("Insert Range");
    //Label lblSep = new Label("-");
    TextField textSize = new TextField();
    //TextField textInit = new TextField();
    TextField textEnd = new TextField();
    TextArea textOut = new TextArea();
    int[] arrayToSort;
    TimeCalculator timeCalculator;

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        HBox hBoxBtns = new HBox();
        hBox.getChildren().addAll(lblSize, textSize, lblRange, textEnd);
        hBoxBtns.getChildren().addAll(btnBubble,btnInsertion,btnMerge,btnQuick, btnClear);
        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0,0);
        Pane pane = new Pane(textOut);
        gridPane.add(pane, 0,1);
        gridPane.add(hBoxBtns, 0, 2);

        btnClear.setOnAction(e->textOut.clear());


        btnBubble.setOnAction(e-> {
            try {
                fillArray();
                long time = timeCalculator.calculateBubbleTime();
                textOut.appendText("Bubble Sort took " + time + "ms  to sort an array of "
                        + arrayToSort.length + " elements. \n");
                textOut.appendText("  ^Elements ordered by BubbleSort: " + timeCalculator + ". \n");
            } catch (TimeoutException ex) {
                textOut.setText(
                        "Operation cancelled due to time out exception. Try again with a lower size. \n"
                );
            }
        });

        btnInsertion.setOnAction(e->{
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
        });

        btnQuick.setOnAction(e->{
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

        });

        btnMerge.setOnAction(e->{
            long time = timeCalculator.calculateMergeTime();
            textOut.appendText("Insertion Sort took " + time + "ms  to sort an array of "
                    + arrayToSort.length + " elements. \n");
            textOut.appendText("  ^Elements ordered by MergeSort: " + timeCalculator + ". \n");
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
