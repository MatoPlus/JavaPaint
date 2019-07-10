import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/*
 * Author: Ri Xin Yang
 * Date: July 9, 2019
 * Desc: This class is a JFrame sets up what is on the frame to be displayed in the application. 
 */
public class DrawFrame extends JFrame {

    // Declaration of required variables for the draw frame.
    private static final int LINE_MODE = 0;
    private static final int MAX_ROW_COUNT = 13;
    private JLabel statusBar; 
    private DrawPanel drawPanel;
    private String[] colorNames = {"Black", "White", "Red", "Green","Blue", "Cyan", "Pink", "Yellow", "Magenta", "Orange", "Gray"};
    private Color[] colorValues = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, 
                                    Color.CYAN, Color.PINK, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.GRAY};
    private String[] shapeNames = {"Line", "Rectangle", "Oval"};
    private JComboBox<String> colorChooser;
    private JComboBox<String> gradientChooser;
    private JComboBox<String> shapeChooser;
    private JFormattedTextField lineWidthTextField;
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JPanel interactionPanel;
    private JCheckBox fillBox;
    private JCheckBox gradientBox;
    private JLabel lineWidthPrompt;
    private Color shapeColor;
    private int shapeMode;
    private int lineWidth;

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
        gradientBox = new JCheckBox("Gradient");
        redoButton = new JButton("Redo");
        undoButton = new JButton("Undo");
        colorChooser = new JComboBox<String>(colorNames);
        gradientChooser = new JComboBox<String>(colorNames);
        shapeChooser = new JComboBox<String>(shapeNames);
        ActionListener buttonListener = new ButtonEventListener();
        PropertyChangeListener textListener = new TextChangeListener();
        ItemListener comboBoxListener = new ComboBoxEventListener();
        ItemListener checkBoxListener = new CheckBoxEventListener();
        lineWidthPrompt = new JLabel("Line Width:");

        // Set up formatter for text fields
        NumberFormat inputFormat = NumberFormat.getInstance();
        NumberFormatter inputFormatter = new NumberFormatter(inputFormat);
        inputFormatter.setValueClass(Integer.class);
        inputFormatter.setMinimum(1);
        inputFormatter.setMaximum(300);
        inputFormatter.setCommitsOnValidEdit(true);

        // Initiate text field.
        lineWidthTextField = new JFormattedTextField(inputFormatter);
        lineWidthTextField.setValue(1);

        // associate buttons with eventListener
        undoButton.addActionListener(buttonListener);
        redoButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);
        lineWidthTextField.addPropertyChangeListener(textListener);

        // Set JComboBox events and properties 
        colorChooser.setMaximumRowCount(MAX_ROW_COUNT);  
        gradientChooser.setMaximumRowCount(MAX_ROW_COUNT);  
        shapeChooser.setMaximumRowCount(MAX_ROW_COUNT);  
        colorChooser.addItemListener(comboBoxListener);
        gradientChooser.addItemListener(comboBoxListener);
        shapeChooser.addItemListener(comboBoxListener);

        // Check box events.
        fillBox.addItemListener(checkBoxListener);
        gradientBox.addItemListener(checkBoxListener);

        // Setting additional default properties.
        gradientChooser.setEnabled(false);
        fillBox.setEnabled(false);

        // Set layout and overall set up of interactionPanel.
        interactionPanel.setLayout(new GridLayout(1, 7, 10, 10));
        interactionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        interactionPanel.add(undoButton);
        interactionPanel.add(redoButton);
        interactionPanel.add(clearButton);
        interactionPanel.add(colorChooser);
        interactionPanel.add(shapeChooser);
        interactionPanel.add(fillBox); 
        interactionPanel.add(gradientBox);
        interactionPanel.add(gradientChooser);
        interactionPanel.add(lineWidthPrompt);
        interactionPanel.add(lineWidthTextField);

        // Append interactions panel to main panel.
        add(interactionPanel, BorderLayout.NORTH);

        // Initialize GUI elements to java window to specific regions.
        add(statusBar, BorderLayout.SOUTH);
        add(drawPanel, BorderLayout.CENTER);

    } 

    // Inner class for button event handling
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

     // Inner class for text input change handling
     class TextChangeListener implements PropertyChangeListener {
        // The ActionListener interface requires that we override the actionPerformed() method.
        // This method will be called automatically whenever a button event occurs.
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            // The ActionEvent getSource() method returns a reference to the button widget that was clicked.
            // This allows us to use one event listener for more than one JButton, if desired.

            if (e.getSource() == lineWidthTextField) {
                lineWidth = (Integer)(lineWidthTextField.getValue());
                drawPanel.setLineWidth(lineWidth);
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
                lineWidthTextField.setEnabled(false);
            } 
            else {
                drawPanel.setIsFilled(false);
                lineWidthTextField.setEnabled(true);
            }

            if (gradientBox.isSelected()) {
                drawPanel.setIsGradient(true);
                gradientChooser.setEnabled(true);
            }
            else {
                drawPanel.setIsGradient(false);
                gradientChooser.setEnabled(false);
            }
        }         
    }

    // Inner class for handling events on comboBox (JComboBox)
    class ComboBoxEventListener implements ItemListener {
        // We override the itemStateChanged() method as required by the ActionListener Interface
        @Override 
        public void itemStateChanged(ItemEvent e) {
            // Change shapeColor depending on user selection.
            if (e.getSource() == colorChooser) {
                shapeColor = colorValues[colorChooser.getSelectedIndex()];
                drawPanel.setShapeColor(shapeColor);
            }
            // Change shapeMode depending on user selection. Note that shapeMode is an integer, representing
            // the type of shape.
            else if (e.getSource() == shapeChooser) {
                shapeMode = shapeChooser.getSelectedIndex();

                if (shapeMode == LINE_MODE) {
                    fillBox.setEnabled(false);
                }
                else {
                    fillBox.setEnabled(true);
                }

                drawPanel.setShapeMode(shapeMode);
            }
            // Change selectedColor depending on user selection.
            else if (e.getSource() == gradientChooser) {
                shapeColor = colorValues[gradientChooser.getSelectedIndex()];
                drawPanel.setGradientColor(shapeColor);
            }
        }
    }      
}