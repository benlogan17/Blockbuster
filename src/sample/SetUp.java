package sample;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class SetUp {
    
    private int numCol;
    private Polygon selectedPolygon;
    private Boolean person;
    private Text questionHolder;
    private Hexagon hexagon;
    private Cheaker cheaker;
    
    public SetUp() {
    	numCol = 5;
    	cheaker = new Cheaker(numCol);
    	hexagon = new Hexagon(this);
    	person = true;
    	
    }
    
    public void setSelectedPolygon(Polygon polygon) {
    	this.selectedPolygon = polygon;
    }
    
    public int getNumCol(){
        return this.numCol;
    }

    @SuppressWarnings("exports")
	public Polygon getSelectedPolygon(){
        return this.selectedPolygon;
    }

    public Boolean getPerson(){
        return this.person;
    }

    public void switchPerson(){
    	this.selectedPolygon = null;
        this.person = !this.person;
    }

    @SuppressWarnings("exports")
	public Text getQuestionHolder(){
        return this.questionHolder;
    }

    public void setQuestionHolder( @SuppressWarnings("exports") Text holder ){
    	holder.setFill( Color.WHITE );
        this.questionHolder = holder;
    }

    public String[] subArray( String[] letters, int start, int end ){
        String[] newArray = new String[end - start + 1];
        int counter = 0;
        for( int i = start; i<= end; i++){
            newArray[counter] = letters[i];
            counter += 1;
        }
        return newArray;
    }
    
    public void load( @SuppressWarnings("exports") Stage stage, double hexagonSize, double windowSize ) {
    	SetUp setUp = new SetUp();
    	hexagon.setHexagonSize( hexagonSize );
        String[] letters = randomLetters();
        GridPane root = new GridPane( );
        HBox columns = new HBox();
        GridPane bPane = new GridPane();
        for( int i = 1; i<= setUp.getNumCol(); i++ ){
            int start = ( i -1 ) * 4 ;
            int end = ( i ) * 4;
            if( i % 2 == 1 )columns.getChildren().add( setUp.generateColumn( 4, setUp.subArray( letters, start, end ) ) );
            else{
                VBox edit = setUp.generateColumn( 4, setUp.subArray( letters, start , end ) );
                columns.getChildren().add( edit );
                HBox.setMargin( edit, new Insets(0.5 * hexagon.getHexagonSize(), hexagon.getHexagonSize() * -0.2, 0, hexagon.getHexagonSize() *- 0.2) );
            }

        }
        root.getChildren().add( columns );
        bPane.add(root, 1, 1);
       
        Button correct = new Button( );
        correct.setText( "Correct" );
        correct.setStyle( "-fx-font-size: 20; -fx-background-color: green;" );
        correct.setTextFill( Color.WHITE );
        correct.setOnMouseClicked( mouseEvent -> {
        	try {
        		if( setUp.getPerson() ) {
        			setUp.getSelectedPolygon().setFill( Color.BLUE );
        			if( cheaker.checkWinnerHorizontal( columns, Color.BLUE ) ) {
        				root.setStyle( "-fx-background-color: blue" );
        			}
        			else if( cheaker.checkWinnerVertical( columns, 0, Color.BLUE ) ) {
        				root.setStyle( "-fx-background-color: blue" );
        			}
        		}
                else {
                	setUp.getSelectedPolygon().setFill( Color.RED );
                	if( cheaker.checkWinnerHorizontal( columns, Color.RED ) ) {
                		root.setStyle( "-fx-background-color: red" );
                	}
                	else if (cheaker.checkWinnerVertical( columns, 0, Color.RED ) ) {
                		root.setStyle( "-fx-background-color: red" );
                	}
                	
                }
                setUp.switchPerson();
                setUp.getQuestionHolder().setText("");
        	}
        	catch( Exception ex ) {
        		
        	}
            
        } );

        Button wrong = new Button();
        wrong.setText( "Wrong" );
        wrong.setTextFill( Color.WHITE );
        wrong.setStyle( "-fx-font-size: 20; -fx-background-color: red;" );
        wrong.setOnMouseClicked( mouseEvent ->{
        	try {
	            setUp.getSelectedPolygon().setFill( Color.GRAY );
	            setUp.switchPerson();
	            setUp.getQuestionHolder().setText("");
        	}
        	catch(Exception ex) {
        		
        	}
        });

        Text text = new Text();
        text.setStyle( "-fx-font-size: 40" );
        setUp.setQuestionHolder( text );
        setUp.getQuestionHolder().setText( "" );

        VBox choices = new VBox();
        HBox quesHolder = new HBox();
        HBox choicesHolder = new HBox();

        quesHolder.getChildren().add( text );
        choicesHolder.getChildren().addAll( correct, wrong );
        
        choices.getChildren().addAll( quesHolder, choicesHolder );
        
        bPane.add(choices,1,2);
        VBox.setMargin( choicesHolder , new Insets( 0,0,0, windowSize/2 - hexagonSize*0.5 ));
        VBox.setMargin( quesHolder, new Insets( 0, 0, 0, windowSize/2 - hexagonSize * 1.75 ) );
        //Creating a scene object
        bPane.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(bPane, windowSize, 600, Color.BLACK);
        //Setting title to the Stage
        stage.setTitle("Drawing a Polygon");
        
        
        //Adding scene to the stage
        stage.setScene(scene);
        GridPane.setMargin( root , new Insets( 10,0,0, windowSize/2 - hexagonSize*3 ));
        
        //Displaying the contents of the stage
        stage.show();
    }

   
    

    @SuppressWarnings("exports")
	public VBox generateColumn(int num, String[] letters ){
        VBox column = new VBox();
        column.setSpacing( 5 );

        for( int i = 0; i < num; i++ ){
        	column.getChildren().add( hexagon.createHexagon( letters[i] ) );
        }
        return column;
    }
    
    public String[] randomLetters(){
        String[] rndLetters = new String[21];
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "Z"};
        Random rnd = new Random();
        int index = 0;
        for( int i = 0; i < 21; i++ ){
            do{
                index = rnd.nextInt( 25 );

            }while( Arrays.asList(rndLetters).contains( letters[index] ));
            rndLetters[ i ] = letters[ index ];
        }
        return rndLetters;
    }
}
