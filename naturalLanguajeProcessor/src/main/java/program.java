import java.awt.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class program {


    public static void addContenidoToHash(Hashtable repetidas, Hashtable hash, String[] array)  {

        for(int i = 0; i < array.length; i++) {
            if(!hash.contains(array[i])) {
                hash.put(array[i],array[i]);
            }
            else {
                repetidas.put(array[i],array[i]);
            }
        }

        Enumeration<String> enumeration = repetidas.elements();
        while (enumeration.hasMoreElements()) {
            String value = enumeration.nextElement();
            if(hash.contains(value)) {
                hash.remove(value);
            }
        }
    }


    public static void WriteTextInfile(PrintWriter print_line, String fileName, Hashtable repetidas, Hashtable sinRepetir, boolean cerrar) throws IOException{

        print_line.println("palabras sin repetir en " + fileName + ":");

        Enumeration<String> enumeration = repetidas.elements();
        while (enumeration.hasMoreElements()) {
            print_line.println(enumeration.nextElement());
        }

        print_line.println("palabras repetidas en " + fileName + ":");
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

        Hashtable<String,String> sinRepetir = new Hashtable<String, String>();
        Hashtable<String,String> repetidas = new Hashtable<String, String>();

        FileWriter write = new FileWriter("/home/cesar/new.txt",false);
        PrintWriter print_line = new PrintWriter(write);

        for(int i = 0; i<3;i++) {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getDirectory();
            String name = dialog.getFile();

            BufferedReader in = new BufferedReader(new FileReader(file + name));
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

            addContenidoToHash(sinRepetir, repetidas,array);

            WriteTextInfile(print_line,name,repetidas,sinRepetir,false);


        }

        String[] prueba = total.split(" ");
        addContenidoToHash(sinRepetir, repetidas,prueba);

        WriteTextInfile(print_line,"en todos los archivos",repetidas,sinRepetir,true);

    }

}
