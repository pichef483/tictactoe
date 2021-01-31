//Scott Smith
//SSM155
import java.awt.*;
import java.awt.geom.*;
public class X {
	private int x;
	private int y;
	private int size;
	public  X(int x, int y){
		this.x=x;
		this.y=y;
		size=20;
	}
	//method for drawing the x
	public void DrawSelf(Graphics g) {
		Graphics2D graphicsObj = (Graphics2D) g;
		Line2D.Double line1 = new Line2D.Double(x-size/2,y+size/2,x+size/2,y-size/2);
		Line2D.Double line2 = new Line2D.Double(x-size/2,y-size/2,x+size/2,y+size/2);
	    graphicsObj.draw(line1);
	    graphicsObj.draw(line2);
	}
	
}
