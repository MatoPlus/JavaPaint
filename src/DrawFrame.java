import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Font;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

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
    private String[] colorNames = { "Black", "White", "Red", "Green", "Blue", "Cyan", "Pink", "Yellow", "Magenta",
            "Orange", "Gray" };
    private Color[] colorValues = { Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN,
            Color.PINK, Color.YELLOW, Color.MAGENTA, Color.ORANGE, Color.GRAY };
    private String[] shapeNames = { "Line", "Rectangle", "Oval" };
    private JComboBox<String> colorChooser;
    private JComboBox<String> gradientChooser;
    private JComboBox<String> shapeChooser;
    private JFormattedTextField lineWidthTextField;
    private JFormattedTextField dashLengthTextField;
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JPanel interactionPanel;
    private JCheckBox fillBox;
    private JCheckBox gradientBox;
    private JCheckBox dashBox;
    private JLabel lineWidthPrompt;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem aboutItem;
    private JMenuItem prefItem;
    private JMenuItem exitItem;
    private Color shapeColor;
    private int shapeMode;
    private int lineWidth;
    private int dashLength;
    private NumberFormatter inputFormatter;
    JCheckBox defaultDashBox;
    JCheckBox defaultFilledBox;
    JCheckBox defaultGradientBox;
    JComboBox<String> defaultColorChooser;
    JComboBox<String> defaultGradientChooser;
    JComboBox<String> defaultShapeChooser;
    JFormattedTextField defaultLineWidthTextField;
    JFormattedTextField defaultDashLengthTextField;

    // A constructor that creates the frame for display.
    public DrawFrame() {

        // Call parent constructor to initiate window title.
        super("JavaPaint");

        // Initialize objects used to create panel.
        statusBar = new JLabel();

        drawPanel = new DrawPanel(statusBar);

        // Initialize all the required JObjects for the frame.
        interactionPanel = new JPanel();
        clearButton = new JButton("Clear");
        fillBox = new JCheckBox("Filled");
        gradientBox = new JCheckBox("Gradient");
        dashBox = new JCheckBox("Dashed");
        redoButton = new JButton("Redo");
        undoButton = new JButton("Undo");
        colorChooser = new JComboBox<String>(colorNames);
        gradientChooser = new JComboBox<String>(colorNames);
        shapeChooser = new JComboBox<String>(shapeNames);
        lineWidthPrompt = new JLabel("Line Width:");
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Files");
        aboutItem = new JMenuItem("About");
        prefItem = new JMenuItem("Preferences");
        exitItem = new JMenuItem("Exit");

        // Create listeners.
        ActionListener buttonListener = new ButtonEventListener();
        ActionListener menuListener = new MenuEventListener();
        PropertyChangeListener textListener = new TextChangeListener();
        ItemListener comboBoxListener = new ComboBoxEventListener();
        ItemListener checkBoxListener = new CheckBoxEventListener();

        // Set up menu.
        menuBar.add(fileMenu);
        fileMenu.add(aboutItem);
        fileMenu.add(prefItem);
        fileMenu.add(exitItem);

        // Set up formatter for text fields
        NumberFormat inputFormat = NumberFormat.getInstance();
        inputFormatter = new NumberFormatter(inputFormat);
        inputFormatter.setValueClass(Integer.class);
        inputFormatter.setMinimum(1);
        inputFormatter.setMaximum(300);
        inputFormatter.setAllowsInvalid(false);
        inputFormatter.setCommitsOnValidEdit(true);

        // Initiate text field.
        lineWidthTextField = new JFormattedTextField(inputFormatter);
        lineWidthTextField.setValue(1);

        dashLengthTextField = new JFormattedTextField(inputFormatter);
        dashLengthTextField.setValue(1);

        // Associate buttons with event listeners.
        undoButton.addActionListener(buttonListener);
        redoButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);

        // Associate menu items with event listeners.
        aboutItem.addActionListener(menuListener);
        prefItem.addActionListener(menuListener);
        exitItem.addActionListener(menuListener);

        // Associate text-fields with property change listeners.
        lineWidthTextField.addPropertyChangeListener(textListener);
        dashLengthTextField.addPropertyChangeListener(textListener);

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
        dashBox.addItemListener(checkBoxListener);

        // Setting default enabled properties.
        gradientChooser.setEnabled(false);
        fillBox.setEnabled(false);
        dashLengthTextField.setEnabled(false);

        // Set layout and overall set up of interactionPanel.
        interactionPanel.setLayout(new GridLayout(12, 1, 10, 15));
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
        interactionPanel.add(dashBox);
        interactionPanel.add(dashLengthTextField);

        // Append interactions panel to main panel.
        add(interactionPanel, BorderLayout.EAST);
        add(menuBar, BorderLayout.NORTH);

        // Initialize GUI elements to java window to specific regions.
        add(statusBar, BorderLayout.SOUTH);
        add(drawPanel, BorderLayout.CENTER);

    }

    // Inner class for button event handling
    class ButtonEventListener implements ActionListener {
        // The ActionListener interface requires that we override the actionPerformed()
        // method.
        // This method will be called automatically whenever a button event occurs.
        @Override
        public void actionPerformed(ActionEvent e) {
            // The ActionEvent getSource() method returns a reference to the button widget
            // that was clicked.
            // This allows us to use one event listener for more than one JButton, if
            // desired.

            // Undo button, remove the latest action from shapes, add it to the redo stack.
            if (e.getSource() == undoButton) {
                drawPanel.undo();
            }
            // Redo button, pop the redo stack, add it to the latest change of shapes if
            // possible.
            else if (e.getSource() == redoButton) {
                drawPanel.redo();
            }
            // Clear button, clear all shapes and redo stack as required.
            else if (e.getSource() == clearButton) {
                drawPanel.clear();
            }

        }
    }

    // Inner class for menu event handling
    class MenuEventListener implements ActionListener {
        // The ActionListener interface requires that we override the actionPerformed()
        // method.
        // This method will be called automatically whenever a button event occurs.
        @Override
        public void actionPerformed(ActionEvent e) {
            // The ActionEvent getSource() method returns a reference to the button widget
            // that was clicked.
            // This allows us to use one event listener for more than one JButton, if
            // desired.

            // Show information about the program on called.
            if (e.getSource() == prefItem) {
          
                int result = JOptionPane.showConfirmDialog(null, getPrefPanel(), 
                         "Preferences: Default Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

                if (result == JOptionPane.OK_OPTION) {
                    writeDefaultPreferences();
                }
                
            }
            else if (e.getSource() == aboutItem) {

                // Show about dialog via JOptionPane.
                JOptionPane.showMessageDialog(null, getAboutPanel(), "About", JOptionPane.PLAIN_MESSAGE, null);
            }
            // Exit program upon called.
            else if (e.getSource() == exitItem) {
                System.exit(0);
            }

        }
    }

    // Inner class for text input change handling
    class TextChangeListener implements PropertyChangeListener {
        // The ActionListener interface requires that we override the actionPerformed()
        // method.
        // This method will be called automatically whenever a button event occurs.
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            // The ActionEvent getSource() method returns a reference to the button widget
            // that was clicked.
            // This allows us to use one event listener for more than one JButton, if
            // desired.

            // Change lineWidth on detection.
            if (e.getSource() == lineWidthTextField) {
                lineWidth = (Integer) (lineWidthTextField.getValue());
                drawPanel.setLineWidth(lineWidth);
            }
            // Change dashLength on detection.
            else if (e.getSource() == dashLengthTextField) {
                dashLength = (Integer) (dashLengthTextField.getValue());
                drawPanel.setDashLength(dashLength);
            }

        }
    }

    // Inner class for handling events on lockBox (JCheckBox)
    class CheckBoxEventListener implements ItemListener {
        // Override the itemStateChanged() method as required by the ActionListener
        // Interface
        @Override
        public void itemStateChanged(ItemEvent e) {
            // Determine the isFilled flag with the check box. Change the flag accordingly.
            if (fillBox.isSelected()) {
                drawPanel.setIsFilled(true);
                lineWidthTextField.setEnabled(false);
                dashLengthTextField.setEnabled(false);
                dashBox.setEnabled(false);
            } else {
                drawPanel.setIsFilled(false);
                lineWidthTextField.setEnabled(true);
                dashLengthTextField.setEnabled(true);
                dashBox.setEnabled(true);
            }

            // Determine the isGradient flag with the check box. Change flag accordingly.
            if (gradientBox.isSelected()) {
                drawPanel.setIsGradient(true);
                gradientChooser.setEnabled(true);
            } else {
                drawPanel.setIsGradient(false);
                gradientChooser.setEnabled(false);
            }

            // Determine the isDashed flag with the check box. Change flag accordingly.
            if (dashBox.isSelected()) {
                drawPanel.setIsDashed(true);
                dashLengthTextField.setEnabled(true);
            } else {
                drawPanel.setIsDashed(false);
                dashLengthTextField.setEnabled(false);
            }

        }
    }

    // Inner class for handling events on comboBox (JComboBox)
    class ComboBoxEventListener implements ItemListener {
        // We override the itemStateChanged() method as required by the ActionListener
        // Interface
        @Override
        public void itemStateChanged(ItemEvent e) {
            // Change shapeColor depending on user selection.
            if (e.getSource() == colorChooser) {
                shapeColor = colorValues[colorChooser.getSelectedIndex()];
                drawPanel.setShapeColor(shapeColor);
            }
            // Change shapeMode depending on user selection. Note that shapeMode is an
            // integer, representing
            // the type of shape.
            else if (e.getSource() == shapeChooser) {
                shapeMode = shapeChooser.getSelectedIndex();

                // Disable isFilled checkbox depending on context.
                if (shapeMode == LINE_MODE) {
                    fillBox.setEnabled(false);
                } else {
                    fillBox.setEnabled(true);
                }

                // Inform drawPanel of shapeMode.
                drawPanel.setShapeMode(shapeMode);
            }

            // Change selectedColor depending on user selection.
            else if (e.getSource() == gradientChooser) {
                shapeColor = colorValues[gradientChooser.getSelectedIndex()];
                drawPanel.setGradientColor(shapeColor);
            }
        }
    }

    // This method creates the about panel to be displayed in a separate dialog in the files menu.
    private JPanel getAboutPanel() {

        // Create Jlabels
        JLabel titleLabel = new JLabel("JavaPaint");
        JLabel authorLabel = new JLabel("Made by Ri Xin Yang");
        JLabel descLabel = new JLabel("A paint/draw program made in Java");

        // Change title label style.
        titleLabel.setFont(new Font("Courier", Font.BOLD, 20));
        
        // Set alignments.
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        authorLabel.setHorizontalAlignment(JLabel.CENTER);
        descLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create panel and add elements to it.
        JPanel aboutPanel = new JPanel(new GridLayout(3, 1, 5, 3));
        aboutPanel.add(titleLabel);
        aboutPanel.add(authorLabel);
        aboutPanel.add(descLabel);

        // Return the result.
        return aboutPanel;
    }

    // This method creates the preference panel to be displayed in a separate dialog in the files menu.
    private JPanel getPrefPanel() {

        // Set up elements for the panel.
        defaultDashBox = new JCheckBox("Dashed");
        defaultFilledBox = new JCheckBox("Filled");
        defaultGradientBox = new JCheckBox("Gradient");
        defaultColorChooser = new JComboBox<String>(colorNames);
        defaultGradientChooser = new JComboBox<String>(colorNames);
        defaultShapeChooser = new JComboBox<String>(shapeNames);
        defaultLineWidthTextField = new JFormattedTextField(inputFormatter);
        defaultDashLengthTextField = new JFormattedTextField(inputFormatter);

        // Set default values.
        defaultLineWidthTextField.setValue(1);
        defaultDashLengthTextField.setValue(1);
        
        // Set up prefPanel.
        JPanel prefPanel = new JPanel(new GridLayout(13, 1, 5, 10));
        prefPanel.add(defaultDashBox);
        prefPanel.add(defaultFilledBox);
        prefPanel.add(defaultGradientBox);
        prefPanel.add(new JLabel("Shape Type"));
        prefPanel.add(defaultShapeChooser);
        prefPanel.add(new JLabel("Primary Color"));
        prefPanel.add(defaultColorChooser);
        prefPanel.add(new JLabel("Gradient Color"));
        prefPanel.add(defaultGradientChooser);
        prefPanel.add(new JLabel("Line Width"));
        prefPanel.add(defaultLineWidthTextField);
        prefPanel.add(new JLabel("Dash Length"));
        prefPanel.add(defaultDashLengthTextField);

        // Return the final result.
        return prefPanel;
    }

    // This method takes the current values of the default preferences and write them into a config file.
    private void writeDefaultPreferences() {

        try {
            PrintWriter configOutput = new PrintWriter( "../settings/config" ); 
            
            // Write to config and close file after finishing.
            configOutput.println("dashed: "+defaultDashBox.isSelected());
            configOutput.println("filled: "+defaultFilledBox.isSelected());
            configOutput.println("gradient: "+defaultGradientBox.isSelected());
            configOutput.println("shape_type: "+defaultShapeChooser.getSelectedItem());
            configOutput.println("primary_color: "+defaultColorChooser.getSelectedItem());
            configOutput.println("gradient_color: "+defaultGradientChooser.getSelectedItem());
            configOutput.println("line_width: "+defaultLineWidthTextField.getValue());
            configOutput.println("dash_length: "+defaultDashLengthTextField.getValue());
            configOutput.close();
        }
        catch ( IOException ioException ) {
            System.err.println( "Java Exception: " + ioException );
            System.out.println( "Sorry, error with outputting to config file." );
        }
    }

}