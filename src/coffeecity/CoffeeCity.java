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

    public void sonido() throws JavaLayerException{
        try {
            FileInputStream fis;
            Player player;
            fis = new FileInputStream(
                    "/home/rapterpakfa/NetBeansProjects/CityCoffee-POO85/src/assets/sounds/MegaDriveConverter.mp3");
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new Player(bis); // Llamada a constructor de la clase Player
            player.play();          // Llamada al método play
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JavaLayerException {             
        JFrame ventana=new JFrame("City:Coffee");
        StateLevel_1.pedir_nombre();
        CoffeeCity game = new CoffeeCity();        
        ventana.setContentPane(new PanelJuego());
        ventana.setVisible(true);        
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.sonido();
        
    }
    
}