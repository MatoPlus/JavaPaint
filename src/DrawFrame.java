import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Author: Ri Xin Yang
 * Date: April 19, 2019
 * Desc: This class is a JFrame sets up what is on the frame to be displayed in the application. 
 */
public class DrawFrame extends JFrame {

    // Declaration of required variables for the draw frame.
    private JLabel statusBar; 
    private DrawPanel drawPanel;
    private String[] colourNames = {"Black", "White", "Red", "Green","Blue", "Cyan", "Pink", "Yellow", "Magenta", "Orange", "Gray"};
    private Color[] colourValues = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, 
                                    Color.CYAN, Color.PINK, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.GRAY};
    private String[] shapeNames = {"Line", "Rectangle", "Oval"};
    private JComboBox<String> colourChooser;
    private JComboBox<String> shapeChooser;
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JPanel interactionPanel;
    private JCheckBox fillBox;
    private Color selectedColour;
    private int selectedShapeMode;

    // A constructor that creates the frame for display.
    public DrawFrame() {

        // Call parent constructor to initiate window title.
        super("JavaPaint");

        // Initialize objects used to create panel.
        statusBar = new JLabel();
        drawPanel = new DrawPanel(statusBar);

        // Initialize all the required objects for the frame.
        interactionPanel = new JPanel();
        clearButton = new JButton("Clear");
        fillBox = new JCheckBox("Filled");
        redoButton = new JButton("Redo");
        undoButton = new JButton("Undo");
        colourChooser = new JComboBox<String>(colourNames);
        shapeChooser = new JComboBox<String>(shapeNames);
        ActionListener eventListener = new ButtonEventListener();
        ItemListener comboBoxListener = new ComboBoxEventListener();
        ItemListener fillBoxListener = new CheckBoxEventListener();

        // associate buttons with eventListener
        undoButton.addActionListener(eventListener);
        redoButton.addActionListener(eventListener);
        clearButton.addActionListener(eventListener);

        // Set JComboBox events and properties 
        colourChooser.setMaximumRowCount(12);  
        shapeChooser.setMaximumRowCount(3);    
        shapeChooser.setPreferredSize(new Dimension(5,5));
        shapeChooser.setPreferredSize(new Dimension(5,5));
        colourChooser.addItemListener(comboBoxListener);
        shapeChooser.addItemListener(comboBoxListener);

        // Check box events.
        fillBox.addItemListener(fillBoxListener);

        // Set layout and overall set up of interactionPanel.
        interactionPanel.setLayout(new GridLayout(1, 6, 10, 10));
        interactionPanel.add(undoButton);
        interactionPanel.add(redoButton);
        interactionPanel.add(clearButton);
        interactionPanel.add(colourChooser);
        interactionPanel.add(shapeChooser);
        interactionPanel.add(fillBox); 

        // Append interactions panel to main panel.
        add(interactionPanel, BorderLayout.NORTH);

        // Initialize GUI elements to java window to specific regions.
        add(statusBar, BorderLayout.SOUTH);
        add(drawPanel, BorderLayout.CENTER);

    } 

    // Inner class for event handling
    class ButtonEventListener implements ActionListener {
        // The ActionListener interface requires that we override the actionPerformed() method.
        // This method will be called automatically whenever a button event occurs.
        @Override
        public void actionPerformed(ActionEvent e) {
            // The ActionEvent getSource() method returns a reference to the button widget that was clicked.
            // This allows us to use one event listener for more than one JButton, if desired.

            // Undo button, remove the latest action from shapes, add it to the redo stack.
            if (e.getSource() == undoButton) {
                drawPanel.undo();
            }
            // Redo button, pop the redo stack, add it to the latest change of shapes if possible.
            else if (e.getSource() == redoButton) {
                drawPanel.redo();
            }
            // Clear button, clear all shapes and redo stack as required.
            else if (e.getSource() == clearButton) {
                drawPanel.clear();
            }

        }
    }
    
    // Inner class for handling events on lockBox (JCheckBox)
    class CheckBoxEventListener implements ItemListener {
        // Override the itemStateChanged() method as required by the ActionListener Interface
        @Override 
        public void itemStateChanged(ItemEvent e) {
            // Determine the isFilled flag with the check box. Change the flag accordingly.
            if (fillBox.isSelected()) {
                drawPanel.setIsFilled(true);
            } 
            else {
                drawPanel.setIsFilled(false);
            }
        }         
    }

    // Inner class for handling events on comboBox (JComboBox)
    class ComboBoxEventListener implements ItemListener {
        // We override the itemStateChanged() method as required by the ActionListener Interface
        @Override 
        public void itemStateChanged(ItemEvent e) {
            // Change selectedColour depending on user selection.
            if (e.getSource() == colourChooser) {
                selectedColour = colourValues[colourChooser.getSelectedIndex()];
                drawPanel.setSelectedColour(selectedColour);
            }
            // Change selectedShape depending on user selection. Note that selectedShape is an integer, representing
            // the type of shape.
            else if (e.getSource() == shapeChooser) {
                selectedShapeMode = shapeChooser.getSelectedIndex();
                drawPanel.setSelectedShapeMode(selectedShapeMode);
            }
        }
    }      
}