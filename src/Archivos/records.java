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
     public static void modifica_archivo(String path) throws IOException{
            FileWriter fw = null;
            BufferedWriter bw = null;
            PrintWriter out = null;
            try
            {
                fw = new FileWriter(path,true);
                bw = new BufferedWriter(fw);
                out = new PrintWriter(bw);
                //LLENA EL ARCHIVO
                out.println(StateLevel_1.nombre_jugador);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               try {
               if (null != fw)
                  fw.close();
               } catch (Exception e2) {
                  e2.printStackTrace();
               }
            }
     }
        
}
