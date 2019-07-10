package shape2D;

import java.awt.Color;


/* Author: Ri Xin Yang
 * Date: July 9, 2018
 * Desc: Parent class of fillable shapes. Contain shape coordinates and basic shape properties. This class inherits 
 * from the Shape class.
 */
public abstract class FillableShape extends Shape {
    
    // Instance variables
    private boolean isFilled;
    
    // Paratermized constructor. Receives x1, y1, x2, y2 as coordinates of diagonal end points. Further receives color,
    // gradient properties, and isFilled to initialize the line.
    public FillableShape(int x1, int y1, int x2, int y2, Color color, boolean isGradient, Color gradientColor, int lineWidth, boolean isFilled) {
        super(x1, y1, x2, y2, color, isGradient, gradientColor, lineWidth);
        setIsFilled(isFilled);
    }

    // accessor method for isFilled. Returns a boolean.
    public boolean getIsFilled() {
        return isFilled;
    }
    
    // mutator method for isFilled. Receives a boolean for isFilled and set the isFilled as the argument passed in.
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    
    // method for determining upper left X coordinate of rect on screen, return lowest value in int
    public int getUpperLeftX() {
        if (getX1() < getX2()) {
            return getX1();
        } else {
            return getX2();
        }
    }
    
    // method for determining upper left Y coordinate of rect on screen, return lowest value in int
    public int getUpperLeftY() {
        if (getY1() < getY2()) {
            return getY1();
        } else {
            return getY2();
        }
    }
    
    // method to determine the width of the rectangle, returns width in int
    public int getWidth() {
        return Math.abs(getX1() - getX2());
    }
    
    // method to determine the height of the rectangle, returns height in int
    public int getHeight() {
        return Math.abs(getY1() - getY2());
    }
    
    // toString method, returns string of data that represents shape.
    @Override
    public String toString() {
        return (super.toString()+", filled:"+isFilled);
    }    
}
