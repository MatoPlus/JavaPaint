import javax.swing.JFrame;

/*
 * Author: Ri Xin Yang
 * Date: April 19, 2019 
 * Desc: This is the main source file which executes the application at the highest level. 
 */
public class JavaPaint{

    // Main method of program. Where program starts.
    public static void main(String[] args) {

        // Initiate the graphics frame for display.
        DrawFrame appWindow = new DrawFrame();
        
        // Initiate appWindow properties                          
        appWindow.setSize(480, 320);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.setVisible(true);
    }    
}