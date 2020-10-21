package sample;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws Exception{
    	SetUp setUp = new SetUp();
        setUp.load( stage, 100.0, 800.0 );
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
