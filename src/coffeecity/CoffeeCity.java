package coffeecity;

import estadojuego.StateLevel_1;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.System.in;
import javax.swing.JFrame;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class CoffeeCity {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){             
        JFrame ventana=new JFrame("City:Coffee");
        StateLevel_1.pedir_nombre();
        CoffeeCity game = new CoffeeCity();        
        ventana.setContentPane(new PanelJuego());
        ventana.setVisible(true);        
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
}
