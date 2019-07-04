package shape2D;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Author: Ri Xin Yang
 * Date: April 19, 2018 
 * Desc: Create a class containing propeties of a visual 2-D rectangle.This class extends from the FillableShape class
 */
public class Rectangle extends FillableShape {
    
    // Paratermized constructor. Receives ints for x1, y1, x2, y2 as coordinates of diagonal end points. 
    // Further receives color as Color, and filled as boolean to initialize the rectangle. 
    public Rectangle(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super(x1, y1, x2, y2, color, filled);
    }
    
    @Override
    public void draw(Graphics g) {

        // Set shape color.
        g.setColor(getColor());

        // Determine whether to draw a filled shape or the outline of the shape.
        if (getFilled() == true) {
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        } 
        else {  
            g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        } 
    }
    
}
