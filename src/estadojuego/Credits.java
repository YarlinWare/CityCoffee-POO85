/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadojuego;

import tilemap.BackGround;
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
public class Credits extends EstadoJuego {
    
    BackGround bg;
    
    String[] lopciones={"Menu","Salir"};
    int opcionactual=0;

    public Credits(ManagerEstados ms) throws IOException  {
       this.ms=ms;
       bg=new BackGround("/Assets/Background/Fondo.gif",1.0);
       bg.setPosition(0, 0);
       bg.setVector(0, 0.08);
       this.iniciar();
    }        

    @Override
    public void iniciar() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g2d) {
        bg.draw(g2d);//pinta el fondo
        
        /*ImageIcon Img = new ImageIcon(getClass().getResource("..\\images\\Logo2.png"));
        g2d.drawImage(Img.getImage(), 25, 5, 115, 65, null);  */ 
        ImageIcon Img2 = new ImageIcon(getClass().getResource("/images/edificios.png"));
        g2d.drawImage(Img2.getImage(), -100, 0, 320, 240, null);        
        
        Font font=new Font("Arial",Font.PLAIN,10);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        
        String universidad="Universidad Distrital Francisco"
                + " Josè de Caldas";
        String Facultad="Facultad de Ingeniería";
        String Asignatura="POO-G85";        
        g2d.drawString(universidad, 25, 20);
        g2d.drawString(Facultad, 75, 40);
        g2d.drawString(Asignatura, 95, 60);
                
        String nombre_1="Juan Felipe Herrera Poveda";
        String codigo_1="20181020077";
        String nombre_2="Cristhian Mauricio Yara Pardo";
        String codigo_2="20181020081";        
                
        g2d.drawString("Miembros: ", 95, 80);        
        g2d.drawString(nombre_1, 55, 100);
        g2d.drawString(codigo_1, 210, 100);
        g2d.drawString(nombre_2, 55, 120);
        g2d.drawString(codigo_2, 210, 120);
        
        Font font2=new Font("Arial",Font.PLAIN,9);
        g2d.setFont(font2);  
        g2d.setColor(Color.BLACK);
        
        String message="Colabora a hacer este proyecto más grande";
        String link="https://github.com/YarlinWare/CityCoffee-POO85"; 
        
        g2d.drawString(message, 95, 210);
        g2d.drawString(link, 95, 220);
        
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
            System.out.println("Salir");
        }
    }
    
    @Override
    public void update() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int tecla=ke.getKeyCode();
        switch(tecla){
           case KeyEvent.VK_UP: {
                this.opcionactual=this.opcionactual-1;
                if(opcionactual<0){
                    opcionactual=this.lopciones.length-1;
                }
            } 
            break;
            case KeyEvent.VK_DOWN: {
                this.opcionactual=this.opcionactual+1;                                  
                if(opcionactual>this.lopciones.length-1){
                    opcionactual=0;
                }
            } 
            break;
            case KeyEvent.VK_ENTER: {
                this.seleccionar();
            } 
            break;
       }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
