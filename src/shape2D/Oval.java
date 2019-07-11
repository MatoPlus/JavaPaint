package shape2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.BasicStroke;

/*
 * Author: Ri Xin Yang
 * Date: July 9, 2018
 * Desc: Create a class containing propeties of a visual 2-D oval. This class inherits from the fillable shape class.
 */
public class Oval extends FillableShape{
    
    // Paratermized constructor. Receives ints for x1, y1, x2, y2 as coordinates of diagonal end points. 
    // Further receives color, gradient properties, and filled as boolean to initialize the oval. 
    public Oval(int x1, int y1, int x2, int y2, Color color, boolean isGradient, Color gradientColor, int lineWidth, boolean isDashed, int dashLength, boolean isFilled) {
        super(x1, y1, x2, y2, color, isGradient, gradientColor, lineWidth, isDashed, dashLength, isFilled);
    }
    
    // Determines how to draw graphic when called.
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
            // Set color
            g2d.setColor(getColor());
        }

        // Determine whether to draw a filled shape or the outline of the shape.
        if (getIsFilled() == true) {
            g2d.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        } 
        else {  
            g2d.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        } 

        // Gets rid of the copy.
        g2d.dispose();
    }
}
