package shape2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.BasicStroke;

/*
 * Author: Ri Xin Yang
 * Date: July 9, 2018 
 * Desc: Create a class containing propeties of a visual 2-D line.This class inherits from the Shape class
 */
public class Line extends Shape {
    
    // Paratermized constructor. Receives ints for x1, y1, x2, y2 as coordinates of diagonal end points.
    // Further receives color and gradient properties to initialize line.
    public Line(int x1, int y1, int x2, int y2, Color color, boolean isGradient, Color gradientColor, int lineWidth) {
        super(x1, y1, x2, y2, color, isGradient, gradientColor, lineWidth);
    }
    
    // Determine how to draw graphic when called.
    @Override
    public void draw(Graphics g) {

        // Uses Graphics2D instead of Graphics for more relevant properties.
        Graphics2D g2 = (Graphics2D) g;

        // Set stroke/line width.
        g2.setStroke(new BasicStroke(getLineWidth()));

        // Gradient drawing.
        if (getIsGradient() == true) {

            // Create gradient.
            GradientPaint gradient = new GradientPaint(getX1(), getY1(), getColor(),
                getX2(), getY2(), getGradientColor());
            g2.setPaint(gradient);

            // Draw shape with gradient.
            g2.drawLine(getX1(), getY1(), getX2(), getY2());
        }
        // Non-gradient drawing.
        else {
            // Set color and draw line.
            g.setColor(getColor());
            g.drawLine(getX1(), getY1(), getX2(), getY2());  
        }  
    }
    
}
