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
    		
    		System.out.println("____________________________");
    	}
    	System.out.println("");
    	return false;
    }
    
    
    public boolean checkWinner(HBox columns, int[] indexs, int colNum, Color checkCol) {
    	System.out.println("Start Index " + indexs[0]);
    	System.out.println("Col " + colNum);
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
    
    
    private boolean checkWinnerHorizontalRepeat( HBox columns, int startIndex, int colNum, Color checkCol ) {
    	System.out.println("Start Index  " + startIndex);
    	System.out.println("Column number " + colNum);
    	if ( colNum == numCol ) {
    		return true;
    	}
    	else if ( startIndex > numCol ) {
    		return false;
    	}
    	else {
    		VBox column = (VBox)(columns.getChildren().get( colNum ));
    		
    		Polygon poly1 = null;
    		Polygon poly2 = null;
    		if ( ( startIndex == 3 && colNum % 2 == 1 ) || ( startIndex == 0 && colNum % 2 == 1 ) ) {
    			poly1 = (Polygon)((StackPane)column.getChildren().get( startIndex )).getChildren().get( 0 );
    			if ( poly1.getFill().toString().equals( checkCol.toString() ) ) {
    				if( startIndex == 3 ) {
    					return checkWinnerHorizontalRepeat( columns, startIndex - 1 , colNum + 1, checkCol );
    				}
//    				else if(startIndex == 0 && colNum % 2 == 1) {
//    					
//    				}
    				else {
    					return checkWinnerHorizontalRepeat( columns, startIndex , colNum + 1, checkCol );
    				}
    				
    			}
    		}
    		else {
    			poly1 = (Polygon)((StackPane)column.getChildren().get( startIndex )).getChildren().get( 0 );
    			poly2 = (Polygon)((StackPane)column.getChildren().get( startIndex + 1 )).getChildren().get( 0 );
    			if ( poly1.getFill().toString().equals( checkCol.toString() ) ){
    				System.out.println("First");
    				return checkWinnerHorizontalRepeat( columns, startIndex ,colNum + 1, checkCol ); 
    			}
    			else if( poly2.getFill().toString().equals( checkCol.toString() ) ) {
    				if( ( startIndex == 0 && colNum % 2 == 0  ) || colNum % 2 ==1) {
    					System.out.println("none");
    					return checkWinnerHorizontalRepeat( columns, startIndex ,colNum + 1, checkCol );
    				}
//    				else if( colNum % 2 == 0 ) {
//    					System.out.println("minus  1");
//    					return checkWinnerHorizontalRepeat( columns, startIndex - 1 ,colNum + 1, checkCol );
//    				}
    				else {
    					System.out.println("add 1");
    					return checkWinnerHorizontalRepeat( columns, startIndex + 1 ,colNum + 1, checkCol ); 				
    				}
    			}
    			else {
    				return false;
    			}
    		}
    		}
    	//}
    	return false;
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
