
package coffeecity;

import javax.swing.JFrame;

/**
 *
 * @author 
 */
public class CoffeeCyty {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana=new JFrame("City:Coffee");
        ventana.setContentPane(new PanelJuego());
        ventana.setVisible(true);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}