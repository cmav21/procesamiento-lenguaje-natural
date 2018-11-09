import java.awt.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class program {


    public static void addContenidoToHash(Hashtable hash,Hashtable repetidas,String[] array) {

        for(int i = 0; i < array.length; i++) {
            if(!hash.contains(array[i])) {
                hash.put(array[i],array[i]);
            }
            else {
                hash.remove(array[i]);
                repetidas.put(array[i],array[i]);
            }
        }
    }


    public static void WriteTextInfile(PrintWriter print_line, String fileName, Hashtable repetidas, Hashtable sinRepetir, boolean cerrar) throws IOException{

        print_line.println("palabras Repetidas en " + fileName + ":");

        Enumeration<String> enumeration = repetidas.elements();
        while (enumeration.hasMoreElements()) {
            print_line.println(enumeration.nextElement());
        }

        print_line.println("palabras sin repetir en " + fileName + ":");
        Enumeration<String> words = sinRepetir.elements();
        while (words.hasMoreElements()) {
            print_line.println(words.nextElement());
        }

        print_line.println("\n");

        repetidas.clear();
        sinRepetir.clear();

        if(cerrar) {
            print_line.close();

        }

    }

    public static void main(String[] args) throws FileNotFoundException, java.io.IOException {
        String contenido = "",total="";
        String[] saltos,array;

        Hashtable<String,String> contenedor = new Hashtable<String, String>();
        Hashtable<String,String> repetidas = new Hashtable<String, String>();

        FileWriter write = new FileWriter("/home/cesar/new.txt",false);
        PrintWriter print_line = new PrintWriter(write);

        for(int i = 0; i<3;i++) {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();

            BufferedReader in = new BufferedReader(new FileReader("/home/cesar/" + file));
            String line = "", text="";
            while ((line = in.readLine()) != null) {
                text += line + " ";
                total += line + " ";
            }
            in.close();

            saltos = text.split("\t");
            for(int j = 0; j<saltos.length;j++) {
                contenido = saltos[j] + " ";
            }

            array = contenido.split(" ");

            addContenidoToHash(contenedor, repetidas,array);

            WriteTextInfile(print_line,file,repetidas,contenedor,false);


        }

        String[] prueba = total.split(" ");
        addContenidoToHash(contenedor, repetidas,prueba);

        WriteTextInfile(print_line,"en todos los archivos",repetidas,contenedor,true);

    }

}
