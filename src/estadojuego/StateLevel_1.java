/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadojuego;

import TileMap.BackGround;
import TileMap.TileMap;
import coffeecity.PanelJuego;
import com.sun.istack.internal.logging.Logger;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Rapter
 */
public class StateLevel_1 extends EstadoJuego{
    private TileMap tilemap;
    static String nombre_jugador;
    BackGround bg;
    
    public StateLevel_1(ManagerEstados ms){
        this.ms=ms;        
        this.iniciar();
    }

    @Override
    public void iniciar() {
        try {            
            tilemap=new TileMap(32);/*
            tilemap.cargarTiles("..\\Assets\\Tileset\\ciudad_ai.png");
            tilemap.cargarMapa("..\\Assets\\maps\\level_5.txt");   */   
            tilemap.cargarTiles("..\\Assets\\Tileset\\prueba.png");
            tilemap.cargarMapa("..\\Assets\\maps\\level_4.txt");  
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(StateLevel_1.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, PanelJuego.WIDTH, PanelJuego.HEIGHT);
        tilemap.draw(g2d);
    }

    @Override
    public void update() {
    
    }
    
    public static void pedir_nombre()
    {
        nombre_jugador= JOptionPane.showInputDialog("Escribe tu nombre jugador");
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
      
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    
}
