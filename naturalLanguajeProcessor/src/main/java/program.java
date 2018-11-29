import java.awt.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class program {

    public static int contarSilabas(String word){
        int count = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '\"' || word.charAt(i) == '\'' || word.charAt(i) == '-' || word.charAt(i) == ',' || word.charAt(i) == ')' || word.charAt(i) == '(') {
                word = word.substring(0,i)+word.substring(i+1, word.length());
            }
        }
        boolean isPrevVowel = false;
        for (int j = 0; j < word.length(); j++) {
            if (word.contains("a") || word.contains("e") || word.contains("i") || word.contains("o") || word.contains("u")) {
                if (esVocal(word.charAt(j)) && !((word.charAt(j) == 'e') && (j == word.length()-1))) {
                    if (isPrevVowel == false) {
                        count++;
                        isPrevVowel = true;
                    }
                } else {
                    isPrevVowel = false;
                }
            } else {
                count++;
                break;
            }
        }
        return count;
    }

    public static boolean esVocal(char c){

        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        } else {
            return false;
        }
    }


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


    public static void WriteTextInfile(PrintWriter print_line, String fileName, Hashtable repetidas, Hashtable sinRepetir, boolean cerrar,int caracteres,int silabas) throws IOException{

        print_line.println("cantidad de palabras repetidas en " + fileName + ":");

        Enumeration<String> enumeration = repetidas.elements();
        print_line.println(repetidas.size());


        print_line.println("cantidad de palabras sin repetir en " + fileName + ":");
        print_line.println(sinRepetir.size());

        print_line.println("cantidad de caracteres en " + fileName + ":");
        print_line.println(caracteres);

        print_line.println("cantidad de silabas en " + fileName + ":");
        print_line.println(silabas);



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
        int totalCharacterCount = 0,totalSilabas = 0;

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
            int characterCount = 0,silabas = 0;

            BufferedReader in = new BufferedReader(new FileReader(file + name));
            String line = "", text="";
            while ((line = in.readLine()) != null) {
                text += line + " ";
                total += line + " ";
                silabas += contarSilabas(line);
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
