package Archivos;

import java.io.*;
import estadojuego.*;

public class records {
    
    public static boolean crea_archivo(String filePath, String fileName){
        boolean result;
        File newFile = new File(filePath, fileName);
        if ((result = !newFile.exists())){
            try {
                result = newFile.createNewFile();
            } catch (IOException ex) {
                result = false;
                System.out.println(".");
            }
        }
        return result;
        }
     public static void modifica_archivo(String path){
            FileWriter fichero = null;
            PrintWriter pw = null;
            try
            {
                fichero = new FileWriter(path);
                pw = new PrintWriter(fichero);
            //LLENA EL ARCHIVO
            pw.append(StateLevel_1.nombre_jugador);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               try {
               if (null != fichero)
                  fichero.close();
               } catch (Exception e2) {
                  e2.printStackTrace();
               }
            }
     }
        
}
