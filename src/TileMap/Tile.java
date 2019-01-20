/*
 * Encargada de cada cuadro de la imagen que permitira definir
 * que zona es traspasable, que zona esta bloqueada, etc.
 * 
 */
package TileMap;

import java.awt.image.BufferedImage;

/**
 *
 * @author 
 */
public class Tile {
    private BufferedImage image;
    private int type;//si es bloqueo o no
    private int id;//identificador de cada tile
    
    public static final int normal=0;    
    public static final int colision=1;
    
    public int[] VectorBlockInt ={
        55,56,57,58,59,60,87,88,89,90,91,408,119,120,121,122,123,440,151,
        152,153,154,155,156,183,184,185,186,187,188,215,216,217,218,219,220,
        247,248,249,250,251,252,279,280,281,282,283,284,343,348,375,376,377,
        378,379,380    
    };
    
    public Tile() {
    }

    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    public Tile(BufferedImage image, int type, int id) {
        this.image = image;
        this.type = type;
        this.id = id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int asignarTipo(int contador){
        int result=normal;
        for(int c:this.VectorBlockInt){
            if(c==contador){
                result=Tile.colision;
            }
        }
        return result;
    }
    
    
    
}
