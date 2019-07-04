package shape2D;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Author: Ri Xin Yang
 * Date: April 19, 2018 
 * Desc: Create a class containing propeties of a visual 2-D line.This class inherits from the Shape class
 */
public class Line extends Shape {
    
    // Paratermized constructor. Receives ints for x1, y1, x2, y2 as coordinates of diagonal end points.
    // Further receives color as Color to initialize line.
    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }
    
    // Determine how to draw graphic when called.
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(getX1(), getY1(), getX2(), getY2());    
    }
    
}
