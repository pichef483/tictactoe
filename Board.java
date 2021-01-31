//Scott Smith
//SSM155
import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;
public class Board extends JComponent {
	private int xCent,yCent;
	private int[][] boardArr; 
	public Board(int[][] arr) {
		boardArr = arr;
		}
	public void set(int[][] arr) {
		boardArr = arr;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//main board creation
		Graphics2D graphicsObj = (Graphics2D) g;
	    Rectangle but2 = new Rectangle(190, 20, 80, 60); 
	    graphicsObj.draw(but2);
	    Rectangle but3 = new Rectangle(360, 20, 80, 60); 
	    graphicsObj.draw(but3);
	    Line2D.Double line = new Line2D.Double(190,190,190,490);
	    graphicsObj.draw(line);
	    Line2D.Double line2 = new Line2D.Double(290,190,290,490);
	    graphicsObj.draw(line2);
	    Line2D.Double line3 = new Line2D.Double(90,290,390,290);
	    graphicsObj.draw(line3);
	    Line2D.Double line4 = new Line2D.Double(90,390,390,390);
	    graphicsObj.draw(line4);
	    Line2D.Double line5 = new Line2D.Double(0,100,480,100);
	    graphicsObj.draw(line5);
	    //x and o creation
	    for(int i=0;i<3;i++) {
	    	for(int j=0;j<3;j++) {
	    		if (boardArr[i][j]==1) {
	    			X x = new X(i*100+140,j*100+240);
	    			x.DrawSelf(g);
	    		}
	    		if (boardArr[i][j]==2) {
	    			O o = new O(i*100+140,j*100+240);
	    			o.DrawSelf(g);
	    		}
	    	}
	    }
	}
	
}
