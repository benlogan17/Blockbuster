package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Hexagon {
	private double hexagonSize = 90.0;
	private SetUp setUp;
	
	public Hexagon(SetUp setUp) {
		this.setUp = setUp;
	}
	
	public double getHexagonSize(){
        return this.hexagonSize;
    }
    
    public void setHexagonSize( double size ) {
    	this.hexagonSize = size;
    }
    @SuppressWarnings("exports")
	public StackPane createHexagon(String letter){
        Polygon polygon = new Polygon();
        double startPoint = hexagonSize;
        polygon.getPoints().addAll(new Double[]{
                startPoint, 0.25*startPoint,
                2*startPoint, 0.25*startPoint,
                2.25*startPoint, 0.75*startPoint,
                2*startPoint, 1.25*startPoint,
                startPoint, 1.25*startPoint,
                0.75*startPoint, 0.75*startPoint, });

        polygon.setOnMouseClicked( mouseEvent ->  onMouseClickPoly( letter, polygon ) );
        polygon.setFill( Color.GRAY );
        StackPane sp = new StackPane();
        Text text = new Text( letter );
        text.setFill(Color.WHITE);
        sp.getChildren().addAll( polygon, text );
        return sp;
    }
    private void onMouseClickPoly(String letter, @SuppressWarnings("exports") Polygon polygon){
        setUp.setSelectedPolygon(polygon);
        String out = "";
        if( setUp.getPerson() ) out = "Blue Player";
        else out = "Red Player";
        out += " has choosen: " + letter;
        setUp.getQuestionHolder().setText( out );
    }
    
    
}
