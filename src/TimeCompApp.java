/**Main GUI: The user will select a range that will determine the number of random elements the array will be filled up
with. Each button will sort the array and calculate the time the specified sorting algorithm took.
 TODO: Add Open option that allows user to open file and output sorting functions and saved files
 TODO: Add Data Base to output table of different algorithm and their time complexity
 TODO: Add multithreading so multiple users can play the app
 TODO: Add other Algorithms such as Binary Search
@author: Wilyendri Duran
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class TimeCompApp extends Application{
    Button btnMerge = new Button("MergeSort");
    Button btnBubble = new Button("BubbleSort");
    Button btnInsertion = new Button("InsertionSort");
    Button btnQuick = new Button("QuickSort");
    Button btnClear = new Button("Clear");
    Button btnInsert = new Button("Insert/Shuffle");
    Label lblSize = new Label("Size ");
    Label lblRange = new Label("From 0 to ");
    TextField textSize = new TextField();
    TextField textEnd = new TextField();
    TextArea textOut = new TextArea();
    int[] arrayToSort;
    boolean isPressedInsert = false;
    TimeCalculator timeCalculator;
    ErrorWindow errorWindow;
    SaveResult saveResult;
    MenuBar menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    Menu menuHelp = new Menu("Help");

    @Override
    public void start(Stage stage){
        // Set up menu
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        MenuItem menuInst = new MenuItem("Instructions", new ImageView("Resources/icons8-instructions-25.png"));
        MenuItem menuAbout = new MenuItem("About", new ImageView("Resources/icons8-about-25.png"));
        MenuItem menuOpen = new MenuItem("Open", new ImageView("Resources/icons8-opened-folder-25.png"));
        MenuItem menuSave = new MenuItem("Save", new ImageView("Resources/icons8-save-25.png"));
        menuOpen.setAccelerator(KeyCombination.keyCombination("Ctrl + o"));
        menuSave.setAccelerator(KeyCombination.keyCombination("Ctrl + s"));
        textOut.setEditable(false);
        menuFile.getItems().addAll(menuOpen, menuSave);
        menuHelp.getItems().addAll(menuInst, menuAbout);

        // Setting UI panes, buttons, and labels
        HBox hBox = new HBox();
        HBox hBoxBtns = new HBox();
        hBox.getChildren().addAll(lblSize, textSize, lblRange, textEnd, btnInsert);
        hBoxBtns.getChildren().addAll(btnBubble,btnInsertion,btnMerge,btnQuick, btnClear);
        GridPane gridPane = new GridPane();
        menuBar.getMenus().addAll(menuFile, menuHelp);
        gridPane.add(menuBar,0,0);
        gridPane.add(hBox, 0,1);
        Pane pane = new Pane(textOut);
        gridPane.add(pane, 0,2);
        gridPane.add(hBoxBtns, 0, 3);
        // Stage to output window error
        Stage errorStage = new Stage();

        // Buttons actions
        btnInsert.setOnAction(e->{
            if(!textSize.getText().isEmpty() && !textEnd.getText().isEmpty()
                    || !textSize.getText().isBlank() && !textEnd.getText().isBlank()){
                isPressedInsert = true;
                fillArray();
                textOut.appendText("Data has been updated on " + new Date() + "\n");
            }else{
                errorWindow = new ErrorWindow("Both data must be inserted.");
                try {
                    errorWindow.start(errorStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        menuSave.setOnAction(e->{
            Stage stageSave = new Stage();
            if(isPressedInsert){
                try {
                    saveResult = new SaveResult(new File(fileChooser.showSaveDialog(stageSave).getPath()), textOut.getText());
                    saveResult.writeToFile();
                    textOut.appendText("Data has been saved on " + new Date() + "\n");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (NullPointerException ex){
                    System.out.println("Operation cancelled: Path is empty");
                }
            }
        });

        btnClear.setOnAction(e->{
            textOut.clear();
            textSize.clear();
            textEnd.clear();
            isPressedInsert = false;
        });


        btnBubble.setOnAction(e-> {
            if(isPressedInsert){
                try {
                    long time = timeCalculator.calculateBubbleTime();
                    processSortBtn("Bubble-Sort", time, timeCalculator);
                } catch (TimeoutException ex) {
                    textOut.setText(
                            "Operation cancelled due to time out exception. Try again with a lower size. \n"
                    );
                }

            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
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
                    processSortBtn("Insertion-Sort", time, timeCalculator);
                } catch (TimeoutException ex) {
                    textOut.setText(
                            "Operation cancelled due to time out exception. Try again with a lower size. \n"
                    );
                }
            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
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
                    processSortBtn("Quick-Sort", time, timeCalculator);

                }catch (StackOverflowError | java.lang.OutOfMemoryError ex){
                    textOut.setText(
                            "Operation cancelled due to memory overflow error. Try again with a lower size. \n"
                    );
                }
            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
                    errorWindow.start(errorStage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }

        });

        btnMerge.setOnAction(e->{
            if(isPressedInsert){
                long time = timeCalculator.calculateMergeTime();
                processSortBtn("Merge Sort", time, timeCalculator);
            }else{
                errorWindow = new ErrorWindow("Must insert elements into array first.");
                try {
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

    public void processSortBtn(String sortType, long time, TimeCalculator timeCalculator){
        textOut.appendText(sortType +" took " + time + "ms  to sort an array of "
                + arrayToSort.length + " elements. \n");
        textOut.appendText("  ^Elements ordered by " + sortType+": " + timeCalculator + ". \n");
    }


}
