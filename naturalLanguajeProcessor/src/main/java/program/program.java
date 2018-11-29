package program;

import java.awt.*;
import java.io.*;
import java.util.Hashtable;
import static program.usaClase.*;

public class program {


    public static void main(String[] args) throws FileNotFoundException, java.io.IOException {
        String contenido = "",total="";
        String[] saltos,array;
        int totalCharacterCount = 0,totalSilabas = 0;

        Hashtable<String,String> sinRepetir = new Hashtable<String, String>();
        Hashtable<String,String> repetidas = new Hashtable<String, String>();

        FileWriter write = new FileWriter("/home/cesar/0mvca.txt",false);
        PrintWriter print_line = new PrintWriter(write);

        for(int i = 0; i<3;i++) {

            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getDirectory();
            String name = dialog.getFile();
            int characterCount = 0,silabas = 0;

            BufferedReader in = new BufferedReader(new FileReader(file + name));
            String line = "", text="";
            while ((line = in.readLine()) != null) {
                text += line + " ";
                total += line + " ";
                silabas +=  contarSilabas(line);
                totalSilabas += contarSilabas(line);
                if(!(line.equals(""))){
                    characterCount += line.length();
                    totalCharacterCount += line.length();
                }
            }
            in.close();

            saltos = text.split("\t");
            for(int j = 0; j<saltos.length;j++) {
                contenido = saltos[j] + " ";
            }

            array = contenido.split(" ");

            addContenidoToHash(sinRepetir, repetidas,array);


            WriteTextInfile(print_line,name,repetidas,sinRepetir,false,characterCount,silabas);


        }

        String[] prueba = total.split(" ");
        addContenidoToHash(sinRepetir, repetidas,prueba);

        WriteTextInfile(print_line,"en todos los archivos",repetidas,sinRepetir,true,totalCharacterCount,totalSilabas);

    }

}
