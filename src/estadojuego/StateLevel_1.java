/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadojuego;

import tilemap.BackGround;
import tilemap.TileMap;
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
import java.applet.AudioClip;
import javax.swing.*;
/**
 *
 * @author Rapter
 */
public class StateLevel_1 extends EstadoJuego{
    private TileMap tilemap;
    private Player player;
    private int vidas = 3;
    
    public static String nombre_jugador;
    BackGround bg;
    
    public StateLevel_1(ManagerEstados ms){
        this.ms=ms;        
        this.iniciar();
    }
    
    
    @Override
    public void iniciar() {    
        try { 
            //AudioClip sonido;
            //sonido = java.applet.Applet.newAudioClip(getClass().getResource("/assets/sounds/1.wav"));      
            tilemap=new TileMap(32); 
            tilemap.cargarTiles("/assets/tileset/prueba.png");
            tilemap.cargarMapa("/assets/maps/level_4_1.txt");
            player = new Player(tilemap);
            player.setPosition(0, 0);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(StateLevel_1.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    private URL url = getClass().getResource("/assets/tileset/Level_4.png");
    
    @Override
    public void draw(Graphics2D g2d) {        
        g2d.setColor(Color.decode("#000000"));
        g2d.fillRect(0, 0, PanelJuego.WIDTH, PanelJuego.HEIGHT);
        player.draw(g2d);
        tilemap.draw(g2d);         
    }
    
    @Override
    public void update() {
        tilemap.setPosicion(
            (PanelJuego.WIDTH / 2) - player.getX(),
            (PanelJuego.HEIGHT / 2) - player.getY()
        );
        player.update();
        this.game_over();
        this.level_finished();
        System.out.println("tamaño ventana" +PanelJuego.WIDTH / 2 +" Posicion heroe x" +player.getX());
        System.out.println("tamaño ventana" +PanelJuego.HEIGHT / 2 +" Posicion heroe y" +player.getY());

    }
    
    public static void pedir_nombre()
    {
        nombre_jugador= JOptionPane.showInputDialog(
            "Escribe tu nombre jugador",
            "New Player"
        );
    }
    public void game_over()
    {
        if(player.getY()>270)
        {
            System.out.println("GAME OVER");
            vidas=vidas-1;
            
            if(vidas<1)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Se acabaron todas las vidas.", "¡Oh no!", JOptionPane.INFORMATION_MESSAGE);
                this.ms.setEstadoactual(0);
                vidas=3;
                player.setX(5);
                player.setY(260);
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(), "Acabas de caer al vacio, te quedan "+vidas+" vidas.", "¡Oh No!", JOptionPane.INFORMATION_MESSAGE);
                player.setX(5);
                player.setY(260);
            }
            
        }
            
    }
    public void level_finished()
    {
        if(player.getX()>1890)
        {
            System.out.println("NIVEL COMPLETADO");
            player.setX(5);
            this.ms.setEstadoactual(0);            
            JOptionPane.showMessageDialog(new JFrame(), "Has terminado exitosamente el nivel 1, el nivel 2 está en construcción.", "¡Felicitaciones!", JOptionPane.INFORMATION_MESSAGE);       
        }
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
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keycode=ke.getKeyCode();
        switch(keycode){
           case KeyEvent.VK_RIGHT:{player.setRight(false);}break;
           case KeyEvent.VK_LEFT:{player.setLeft(false);}break;
           case KeyEvent.VK_SPACE:{player.setSalto(false);}break;
        }
    }
    
    
    
    
}
