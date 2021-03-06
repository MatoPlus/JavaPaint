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
    public Line(int x1, int y1, int x2, int y2, Color color, int lineWidth, boolean isGradient, Color gradientColor, boolean isDashed, int dashLength) {
        super(x1, y1, x2, y2, color, lineWidth, isGradient, gradientColor, isDashed, dashLength);
    }

    // Alternative constructor without dash line properties.
    public Line(int x1, int y1, int x2, int y2, Color color, int lineWidth, boolean isGradient, Color gradientColor) {
        super(x1, y1, x2, y2, color, lineWidth, isGradient, gradientColor);
    }

    // Alternative constructor without gradient properties.
    public Line(int x1, int y1, int x2, int y2, Color color, int lineWidth) {
        super(x1, y1, x2, y2, color, lineWidth);
    }

    // Alternative constructor without line width property.
    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }
    
    // Determine how to draw graphic when called.
    @Override
    public void draw(Graphics g) {

        // Creates a copy of the Graphics instance with Graphics2D.
        Graphics2D g2d = (Graphics2D) g.create();

        // Set stroke/line width based on if lines are dashed.
        if (getIsDashed()) {
            g2d.setStroke(new BasicStroke(getLineWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{getDashLength()}, 0));
        }
        else {
            g2d.setStroke(new BasicStroke(getLineWidth()));
        }

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
