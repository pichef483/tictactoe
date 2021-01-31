//Scott Smith
//SSM155
import java.awt.*;
import java.awt.geom.*;
public class O {
	private int x;
	private int y;
	private int size;
	public  O(int x, int y){
		this.x=x;
		this.y=y;
		size=20;
	}
	//method for drawing the o
	public void DrawSelf(Graphics g) {
		Graphics2D graphicsObj = (Graphics2D) g;
		Ellipse2D.Double circ = new Ellipse2D.Double(x-size/2,y-size/2,size,size);
		graphicsObj.draw(circ);
	}
	
}