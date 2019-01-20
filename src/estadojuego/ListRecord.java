/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadojuego;

import TileMap.BackGround;
import coffeecity.PanelJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 *
 * @author Rapter
 */
public class ListRecord extends EstadoJuego{
    
    BackGround bg;
    
    String[] lopciones={"Menu","Salir"};
    int opcionactual=0;
    
    public ListRecord(ManagerEstados ms) throws IOException {
       this.ms=ms;
       bg=new BackGround("..\\Assets\\Background\\Fondo.gif",1.0);
       bg.setPosition(0, 0);
       bg.setVector(0, 0.08);
       this.iniciar();
    }

    @Override
    public void iniciar() {
    
    }

    @Override
    public void draw(Graphics2D g2d) {
        bg.draw(g2d);//pinta el fondo
        
        ImageIcon Img = new ImageIcon(getClass().getResource("..\\images\\Logo2.png"));
        g2d.drawImage(Img.getImage(), 25, 5, 115, 65, null);   
        ImageIcon Img2 = new ImageIcon(getClass().getResource("..\\images\\edificios.png"));
        g2d.drawImage(Img2.getImage(), 0, 0, 320, 240, null);
        
        
        Font font=new Font("Arial",Font.PLAIN,10);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        
        g2d.drawString("Jugador 1", 150, 40);
        g2d.drawString("Jugador 2", 150, 60);
        g2d.drawString("Jugador 3", 150, 80);
        g2d.drawString("Jugador 4", 150, 100);
        g2d.drawString("Jugador 5", 150, 120);
        
        g2d.drawString("9999999", 250, 40);
        g2d.drawString("9999999", 250, 60);
        g2d.drawString("9999999", 250, 80);
        g2d.drawString("9999999", 250, 100);
        g2d.drawString("9999999", 250, 120);
        
        
        for(int i=0;i<this.lopciones.length;i++){
            if(i==this.opcionactual){
                g2d.setColor(Color.white);
            }else{
                g2d.setColor(Color.GREEN);
            }
             g2d.drawString(this.lopciones[i], 200, 155+(i*20));
        }
        
        
    }
    
    public void seleccionar(){
        if(this.opcionactual==0){
            this.ms.setEstadoactual(ms.MENU);
            System.out.println("Menu");
        }        
        if(this.opcionactual==1){
            System.exit(0);
        }
    }
    
    @Override
    public void update() {
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int tecla=ke.getKeyCode();
        switch(tecla){
           case KeyEvent.VK_UP: {this.opcionactual=this.opcionactual-1;
                                  if(opcionactual<0){
                                      opcionactual=this.lopciones.length-1;
                                  }} break;
           case KeyEvent.VK_DOWN: {this.opcionactual=this.opcionactual+1;
                                  if(opcionactual>this.lopciones.length-1){
                                      opcionactual=0;
                                  }} break;
           case KeyEvent.VK_ENTER: {this.seleccionar();} break;
       }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
