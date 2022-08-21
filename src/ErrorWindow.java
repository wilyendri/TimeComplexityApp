import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ErrorWindow extends Application {
    String message;
    Label errorLbl;
    Button btnOk = new Button("Ok");

    public ErrorWindow(String message){
        this.message = message;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        HBox hBox = new HBox(btnOk);
        BorderPane borderPane = new BorderPane();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        errorLbl = new Label(message);
        pane.getChildren().addAll(errorLbl);
        borderPane.setCenter(errorLbl);
        borderPane.setBottom(hBox);


        btnOk.setOnAction(e->{
            stage.close();
        });


        Scene scene = new Scene(borderPane, 250, 150);
        stage.setTitle("Error Window");
        stage.setScene(scene);
        stage.show();
    }
}
