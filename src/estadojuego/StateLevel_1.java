/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadojuego;

import TileMap.BackGround;
import TileMap.TileMap;
import coffeecity.PanelJuego;
import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Rapter
 */
public class StateLevel_1 extends EstadoJuego{
    private TileMap tilemap;
    private Player player;
    
    public static String nombre_jugador;
    BackGround bg;
    
    public StateLevel_1(ManagerEstados ms){
        this.ms=ms;        
        this.iniciar();
    }

    @Override
    public void iniciar() {
        try {            
            tilemap=new TileMap(32); 
            tilemap.cargarTiles("/Assets/Tileset/prueba.png");
            tilemap.cargarMapa("/Assets/maps/level_4.txt");
            player = new Player(tilemap);
            player.setPosition(0, 0);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(StateLevel_1.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    private URL url = getClass().getResource("/Assets/Tileset/Level_1.png");
    
    @Override
    public void draw(Graphics2D g2d) {        
        //g2d.setColor(Color.decode("#707cc2"));
        g2d.setColor(Color.decode("#0087b6"));
        g2d.fillRect(0, 0, PanelJuego.WIDTH, PanelJuego.HEIGHT);
        tilemap.draw(g2d);
        player.draw(g2d);
    }

    @Override
    public void update() {
       //tilemap.setPosicion(PanelJuego.WIDTH / 2 - player.getX(),PanelJuego.HEIGHT / 2 - player.getY());
       player.update();
    }
    
    public static void pedir_nombre()
    {
        nombre_jugador= JOptionPane.showInputDialog(
            "Escribe tu nombre jugador",
            "New Player"
        );
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keycode=ke.getKeyCode();
        switch(keycode){
           case KeyEvent.VK_RIGHT:{player.setRight(true);}break;
           case KeyEvent.VK_LEFT:{player.setLeft(true);}break;
           case KeyEvent.VK_SPACE:{player.setSalto(true);}break;
           //case KeyEvent.VK_F:{player.setAtacando(true);}break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keycode=ke.getKeyCode();
        switch(keycode){
           case KeyEvent.VK_RIGHT:{player.setRight(false);}break;
           case KeyEvent.VK_LEFT:{player.setLeft(false);}break;
           case KeyEvent.VK_SPACE:{player.setSalto(false);}break;
           //case KeyEvent.VK_F:{player.setAtacando(false);}break;
        }
    }
    
    
    
    
}
