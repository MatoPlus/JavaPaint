package shape2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;

/*
 * Author: Ri Xin Yang
 * Date: July 9, 2018
 * Desc: Create a class containing propeties of a visual 2-D oval. This class inherits from the fillable shape class.
 */
public class Oval extends FillableShape{
    
    // Paratermized constructor. Receives ints for x1, y1, x2, y2 as coordinates of diagonal end points. 
    // Further receives color, gradient properties, and filled as boolean to initialize the oval. 
    public Oval(int x1, int y1, int x2, int y2, Color color, boolean isGradient, Color gradientColor, boolean isFilled) {
        super(x1, y1, x2, y2, color, isGradient, gradientColor, isFilled);
    }
    
    // Determines how to draw graphic when called.
    @Override
    public void draw(Graphics g) {

        // Gradient drawing.
        if (getIsGradient() == true) {

            // Create gradient.
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(getX1(), getY1(), getColor(),
            getX2(), getY2(), getGradientColor());
            g2.setPaint(gradient);

            // Determine whether to draw a filled shape or the outline of the shape.
            if (getIsFilled() == true) {
               g2.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
            } 
            else {  
                g2.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
            } 
        }
        // Non-gradient drawing.
        else {

            // Set color
            g.setColor(getColor());

            // Determine whether to draw a filled shape or the outline of the shape.
            if (getIsFilled() == true) {
                g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
            } 
            else {  
                g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
            } 
        }
    }
}
