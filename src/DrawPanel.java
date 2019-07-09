import dataStructure.DynamicStack;
import dataStructure.LinkedList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import shape2D.Line;
import shape2D.Oval;
import shape2D.Rectangle;
import shape2D.Shape;

/*
 * Author: Ri Xin Yang
 * Date: July 9, 2019
 * This class is a JPanel that handles the drawing and handles the events made by the mouse.
 */
public class DrawPanel extends JPanel {

    // Initialize objects used in drawing.
    private static final int LINE_MODE = 0;
    private static final int RECT_MODE = 1;
    private static final int OVAL_MODE = 2;
    private boolean isFilled;
    private boolean isGradient;
    private Color shapeColor;
    private Color gradientColor;
    private int shapeMode;
    private Shape currentShape;
    private JLabel statusBar;
    private LinkedList<Shape> shapes;
    private DynamicStack<Shape> redoStack;
    
    // Constructor instantiates the main interactions within the panel.
    public DrawPanel(JLabel statusLabel) {

        // Initialize all the required objects for the panel.
        statusBar = statusLabel;
        isFilled = false;
        isGradient = false;
        shapeColor = Color.BLACK;
        gradientColor = Color.BLACK;
        shapeMode = LINE_MODE;
        shapes = new LinkedList<>();
        redoStack = new DynamicStack<>();
        MouseEventListener drawPanelListener = new MouseEventListener(); 

        // Mouse events.
        addMouseListener(drawPanelListener); 
        addMouseMotionListener(drawPanelListener);    

        // Initiate color of background.
        setBackground(Color.WHITE); 

    }

    // Remove the latest action from drawn shapes, add it to the redo stack.
    public void undo() {
        Shape shape = shapes.removeLast();
        if(shape != null) {
            redoStack.push(shape);
        }

        // Repaint panel after any change.
        repaint();
    }

    // Pop the redo stack, add it to the latest change of shapes if possible.
    public void redo() {
        Shape shape = redoStack.pop();
        if (shape != null) {
            shapes.addLast(shape);
        }

        // Repaint panel after any change.
        repaint();
    }

    // Clear all shapes and redo stack as required.
    public void clear() {
        shapes.clear();
        redoStack.clear();

        // Repaint panel after any change.
        repaint();
    }

    // A setter for isFilled.
    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    // A setter for isGradient.
    public void setIsGradient(boolean isGradient) {
        this.isGradient = isGradient;
    }

    // A setter for shapeColor.
    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }

    // A setter for shapeMode.
    public void setShapeMode(int shapeMode) {
        this.shapeMode = shapeMode;
    }

    // A setter for gradientColor.
    public void setGradientColor(Color gradientColor) {
        this.gradientColor = gradientColor;
    }

    class MouseEventListener extends MouseAdapter {
        // Mouse press indicates a new shape drawing has been started
        @Override
        public void mousePressed(MouseEvent event) {
            
            // Decide which shape to draw depending on current shape type.
            if (shapeMode == LINE_MODE) {
                currentShape = new Line(event.getX(), event.getY(), event.getX(), event.getY(), shapeColor, isGradient, gradientColor);
            }
            else if (shapeMode == RECT_MODE) {
                currentShape = new Rectangle(event.getX(), event.getY(), event.getX(), event.getY(), shapeColor, isGradient, gradientColor, isFilled);
            }
            else if (shapeMode == OVAL_MODE) {
                currentShape = new Oval(event.getX(), event.getY(), event.getX(), event.getY(), shapeColor, isGradient, gradientColor, isFilled);
            }
            // Tell JVM to call paintComponent( g )
            repaint();
        } 
        
        // Mouse release indicates the new shape is finished
        @Override
        public void mouseReleased(MouseEvent event) {

            // Make sure command is from left clicks only to avoid call exceptions.
            if (! (event.isMetaDown() || event.isAltDown())) {
                
                // Update ending coordinates and switch color to current selected color
                currentShape.setX2(event.getX());
                currentShape.setY2(event.getY());
                currentShape.setColor(shapeColor);

                // Add the new shape to linked list containing all shapes.
                shapes.addLast(currentShape);

                // Clear redoStack after new shape is created, redo is now in another branch, discarded.
                redoStack.clear();

                // Get ready for the next shape to be drawn
                currentShape = null;

                // Update panel.
                repaint();
            }            
        } 
        
        // As mouse is dragged, update ending coordinates of currentShape and statusBar
        @Override
        public void mouseDragged(MouseEvent event) {
                
            // Make sure command is for left click only to avoid call exceptions.
            if (! (event.isMetaDown() || event.isAltDown())) {
                currentShape.setX2(event.getX());
                currentShape.setY2(event.getY());
                statusBar.setText(String.format("(%d, %d)", event.getX(), event.getY()));
                repaint();
            }
        } 
        
        // As mouse is moved, just update the statusBar
        @Override
        public void mouseMoved(MouseEvent event) {
            statusBar.setText(String.format("(%d, %d)", event.getX(), event.getY()));
        } 
    } 
    
    // This method is called automatically by the JVM when the window needs to be (re)drawn.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Call the draw() method for each shape object in the array
        Shape shape = null;
        int size = shapes.size();
        for (int counter = 0; counter < size; counter++) {
            shape = shapes.removeFirst();
            shapes.addLast(shape);
            shape.draw(g);
        }
        
        // If a shape is in progress, draw it on top of all others
        if (currentShape != null) {
            currentShape.draw(g);
        }
    } 
} 
        