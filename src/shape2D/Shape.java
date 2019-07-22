package shape2D;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Author: Ri Xin Yang
 * Date: July 9, 2018 
 * Desc: General, abstract shape class that includes basic properties of any shape in general - 1D and 2D properties.
 */
public abstract class Shape {

    // Instance variables
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;
    private boolean isGradient;
    private Color gradientColor;
    private int lineWidth;
    private boolean isDashed;
    private int dashLength;

    // Parameterized constructor. Takes ints for x1, y1, x2, y2 as coordinates of diagonal endpoints.
    // Also, a color argument and all the remaining arguments is used to define the properties of the shape.
    // These properties are used to initialize properties of the shape.
    public Shape(int x1, int y1, int x2, int y2, Color color, int lineWidth, boolean isGradient, Color gradientColor, boolean isDashed, int dashLength) {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
        setIsGradient(isGradient);
        setGradientColor(gradientColor);
        setLineWidth(lineWidth);
        setIsDashed(isDashed);
        setDashLength(dashLength);
    }

    // Alternative constructor without dashed lines properties.
    public Shape(int x1, int y1, int x2, int y2, Color color, int lineWidth, boolean isGradient, Color gradientColor) {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
        setLineWidth(lineWidth);
        setIsGradient(isGradient);
        setGradientColor(gradientColor);
        setIsDashed(false);
        setDashLength(1);
    }

    // Alternative constructor without gradient properties.
    public Shape(int x1, int y1, int x2, int y2, Color color, int lineWidth) {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
        setLineWidth(lineWidth);
        setIsGradient(false);
        setGradientColor(color);
        setIsDashed(false);
        setDashLength(1);
    }

    // Alternative constructor without lineWidth property.
    public Shape(int x1, int y1, int x2, int y2, Color color) {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
        setLineWidth(1);
        setIsGradient(false);
        setGradientColor(color);
        setIsDashed(false);
        setDashLength(1);
    }

    // Accessor method for x1. Returns x1 as int.
    public int getX1() {
        return x1;
    }

    // Accessor method for y1. Returns y1 as int.
    public int getY1() {
        return y1;
    }

    // Accessor method for x2. Returns x2 as int.
    public int getX2() {
        return x2;
    }

    // Accessor method for y2. Returns y2 as int.
    public int getY2() {
        return y2;
    }

    // Mutator method for x1. Receive argument for newX as int, do validation and set value if valid.
    public void setX1(int newX) {

        // Sanity checking for non-negative values
        if (newX >= 0) {
            x1 = newX;
        }
        else {
            System.err.println("Attempt to set x1 to a value less than 0, set to 0 by default.");
            x1 = 0;
        }
    }

    // Mutator method for y1. Receive argument for newY as int, do validation and set value if valid.
    public void setY1(int newY) {

        // Sanity checking for non-negative values
        if (newY >= 0) {
            y1 = newY;
        }
        else {
            System.err.println("Attempt to set y1 to a value less than 0, set to 0 by default.");
            y1 = 0;
        }
    }

    // Mutator method for x2. Receive argument for newX as int, do validation and set value if valid.
    public void setX2(int newX) {

        //Sanity checking for non-negative values
        if (newX >= 0) {
            x2 = newX;
        }
        else {
            System.err.println("Attempt to set x2 to a value less than 0, set to 0 by default.");
            x2 = 0;
        }
    }

    // Mutator method for y2. Receive argument for newY as int, do validation and set value if valid.
    public void setY2(int newY) {

        // Sanity checking for non-negative values
        if (newY >= 0) {
            y2 = newY;
        }
        else {
            System.err.println("Attempt to set y2 to a value less than 0, set to 0 by default.");
            y2 = 0;
        }
    }

    // Mutator method for color. Receive argument for color as Color, set argument as new color.
    public void setColor(Color color) {
        this.color = color;
    }
    
    // Accessor method for color. Returns a Color type.
    public Color getColor() {
        return color;
    }

    // Mutator method for isGradient. Receive boolean argument for isGradient, set argument as new isGradient.
    public void setIsGradient(boolean isGradient) {
        this.isGradient = isGradient;
    }
    
    // Accessor method for isGradient. Returns a boolean type.
    public boolean getIsGradient() {
        return isGradient;
    }

    // Mutator method for gradient color. It sets the new color argument as new gradientColor.
     public void setGradientColor(Color gradientColor) {
        this.gradientColor = gradientColor;
    }

    // Accessor method for gradient color. Returns a Color type.
    public Color getGradientColor() {
        return gradientColor;
    }

    // Mutator method for line width. It sets the new int argument as new lineWidth;
    public void setLineWidth(int lineWidth) {

        // Sanity check to invalidate non-positive values.
        if (lineWidth > 0) {
            this.lineWidth = lineWidth;
        }
        else {
            System.err.println("Attempt to set lineWidth to a value less than 1");
        }
    }

    // Accessor method for line width. Returns a int type.
    public int getLineWidth() {
        return lineWidth;
    }

    // Mutator method for isDashed. It sets the new boolean argument as new dashLength;
    public void setIsDashed(boolean isDashed) {
        this.isDashed = isDashed;
    }

    // Accessor method for isDashed. Returns a boolean type.
    public boolean getIsDashed() {
        return isDashed;
    }

    // Mutator method for dash length. It sets the new int argument as new dashLength;
    public void setDashLength(int dashLength) {
        // Sanity check to invalidate non-positive values.
        if (dashLength > 0) {
            this.dashLength = dashLength;
        }
        else {
            System.err.println("Attempt to set dashLength to a value less than 1");
        }
    }

    // Accessor method for dash length. Returns a int type.
    public int getDashLength() {
        return dashLength;
    }

    // Abstract method for drawing specific shapes.
    public abstract void draw(Graphics g);
    
    // toString method, returns string representing shape with properties.
    public String toString() {
        return ("x1:"+x1+", y1:"+y1+", x2:"+x2+", y2:"+y2+", color:"+color+" lineWidth:"+lineWidth+" isGradient:"+isGradient+
        " GradientColor:"+gradientColor+" isDashed:"+isDashed+" dashLength:"+dashLength);
    }    

}   
