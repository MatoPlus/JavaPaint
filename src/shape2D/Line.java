package shape2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.BasicStroke;

/*
 * Author: Ri Xin Yang
 * Date: July 9, 2018 
 * Desc: Create a class containing properties of a visual 2-D line.This class inherits from the Shape class
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

        // Creates a copy of the Graphics instance with Graphics2D.
        Graphics2D g2d = (Graphics2D) g.create();

        // Set stroke/line width.
        g2d.setStroke(new BasicStroke(getLineWidth()));

        // Gradient drawing.
        if (getIsGradient() == true) {

            // Create gradient.
            GradientPaint gradient = new GradientPaint(getX1(), getY1(), getColor(),
                getX2(), getY2(), getGradientColor());
            g2d.setPaint(gradient);

        }
        // Non-gradient drawing.
        else {
            // Set color and draw line.
            g2d.setColor(getColor());
        }  

        // Draw shape with gradient.
        g2d.drawLine(getX1(), getY1(), getX2(), getY2());
        
        // Gets rid of the copy.
        g2d.dispose();
    }
    
}
