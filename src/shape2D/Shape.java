package shape2D;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Author: Ri Xin Yang
 * Date: April 19, 2018 
 * Desc: General, abstract shape class that includes basic properties of any shape in general - 1D and 2D properties.
 */
public abstract class Shape {

    // Instance variables
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;

    // Parameterized constructor. Takes ints for x1, y1, x2, y2 as coordinates of diagonal endpoints.
    // Also, a color argument as Color. These properties are used to initialize properties of the shape.
    public Shape(int x1, int y1, int x2, int y2, Color color){
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor(color);
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

    // Mutator method for x1. Receive arugument for newX as int, do validation and set value if valid.
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

    // Mutator method for y1. Receive arugument for newY as int, do validation and set value if valid.
    public void setY1(int newY) {

        //sanity checking for non-negative values
        if (newY >= 0) {
            y1 = newY;
        }
        else {
            System.err.println("Attempt to set y1 to a value less than 0, set to 0 by default.");
            y1 = 0;
        }
    }

    // Mutator method for x2. Receive arugument for newX as int, do validation and set value if valid.
    public void setX2(int newX) {

        //sanity checking for non-negative values
        if (newX >= 0) {
            x2 = newX;
        }
        else {
            System.err.println("Attempt to set x2 to a value less than 0, set to 0 by default.");
            x2 = 0;
        }
    }

    // Mutator method for y2. Receive arugument for newY as int, do validation and set value if valid.
    public void setY2(int newY) {

        //sanity checking for non-negative values
        if (newY >= 0) {
            y2 = newY;
        }
        else {
            System.err.println("Attempt to set y2 to a value less than 0, set to 0 by default.");
            y2 = 0;
        }
    }

    //mutator method for color. Receive argument for color as Color, set argument as new color.
    public void setColor(Color color) {
        this.color = color;
    }
    
    //accessor method for color. Returns a Color type.
    public Color getColor() {
        return color;
    }

    // abstract method for drawing specific shapes.
    public abstract void draw(Graphics g);
    
    // toString method, returns string representing shape with properties.
    public String toString() {
        return ("x1:"+x1+", y1:"+y1+", x2:"+x2+", y2:"+y2+", color:"+color);
    }    

}   
