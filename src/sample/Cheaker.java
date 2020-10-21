package sample;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cheaker {
	private int numCol;
	public Cheaker(int numCol) {
		this.numCol = numCol;
	}
    public boolean checkWinnerHorizontal( HBox columns, Color checkColor ) {
    	for( int i = 0; i< 4; i++ ) {
    		if(i == 0) {
    			if(checkWinner(columns, new int[] {0, 0}, 0, checkColor)) {
        			return true;
        		}
    		}
    		else {
    			if(checkWinner(columns, new int[] {i-1, i}, 0, checkColor)) {
        			return true;
        		}
    		}
    	}
    	return false;
    }
    
    
    public boolean checkWinner(HBox columns, int[] indexs, int colNum, Color checkCol) {
    	if(colNum == numCol) {
    		return true;
    	}
    	else {
    		VBox column = (VBox)(columns.getChildren().get( colNum ));
    		
    		Polygon poly1 = (Polygon)((StackPane)column.getChildren().get( indexs[0] )).getChildren().get( 0 );
    		Polygon poly2 = (Polygon)((StackPane)column.getChildren().get( indexs[1] )).getChildren().get( 0 );
    		if(colNum % 2 ==0 ) {
    			
    			if(poly2.getFill().toString().equals( checkCol.toString() )) {
    				indexs[0]+=1;
        			if(indexs[1]+1 <= 3 )indexs[1]+=1;
        			return checkWinner(columns, indexs, colNum+1, checkCol);
    			}
    			if(poly1.getFill().toString().equals( checkCol.toString() ) && indexs[0] == indexs[1]){
    				indexs[0]+=1;
    				if(indexs[1]+1 <= 3 )indexs[1]+=1;
        			return checkWinner(columns, indexs, colNum+1, checkCol);
    			}
    			
    		}
    		else {
    			if(poly1.getFill().toString().equals( checkCol.toString() ) && indexs[0] == indexs[1]) {
    				indexs[0]-=1;
        			return checkWinner(columns, indexs, colNum+1, checkCol);
    			}
    			if(poly2.getFill().toString().equals( checkCol.toString() )) {
    				indexs[0]+=1;
        			if(indexs[1]+1 <= 3 )indexs[1]+=1;
        			return checkWinner(columns, indexs, colNum+1, checkCol);
    			}
    		}
    		
    		if(poly1.getFill().toString().equals( checkCol.toString() ) ) {
				return checkWinner(columns, indexs, colNum+1, checkCol);
			}
    		
    		return false;
    	}
    	
    }
    
    public boolean checkWinnerVertical( HBox columns, int colNum, Color checkCol ) {
    	if( colNum == numCol ) {
    		return false;
    	}
    	else {
    		
    		for( Node stack : ( (VBox)columns.getChildren().get( colNum ) ).getChildren() ) {
    			Polygon poly = ( Polygon )((StackPane)stack).getChildren().get( 0 );
    			if( !poly.getFill().toString().equals( checkCol.toString() ) ) {
    				return checkWinnerVertical( columns, colNum + 1, checkCol );
    			}
    		}
    		return true;
    	}
    }
}
