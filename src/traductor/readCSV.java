/*
    Clase la cual nos sirve para obtener la lista de idiomas asi como su codigo de un fichero csv, 
    utilizando el manejo de archivos para leer dicho fichero
 */
package traductor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class readCSV {
    
    //Metodo para leer el fichero de los idiomas
    public ArrayList readCsv() {
        //Se crea un arraylist para almacenar los idioms y sus codigos.
        ArrayList<String> temporal = new ArrayList<>();
        //Se crea una varibale tipo File que obtiene ruta de ubicación del fichero.
        //File archivo = new File(System.getProperty("user.dir")+"\\src\\idiomas\\CodigoIdiomas.csv");
        //Bloque try catch para algun prosible error de ejecución.
        try {
            //Variable InputStreamReader la cual permite leer el firecho en formato utf-8 con el bufferedreader
            //InputStreamReader isr = new InputStreamReader(new FileInputStream(System.getProperty("user.dir")+"\\idiomas\\CodigoIdiomas.csv"), "UTF-8");
            //Variable FileReader la cual nos permite leer el fichero con el BufferedReader.
            FileReader fr = new FileReader(new File(System.getProperty("user.dir")+"\\src\\idiomas\\CodigoIdiomas.csv"));
            BufferedReader br = new BufferedReader(fr);
            String linea;
            //Se alcamenan los datos contenidos en el fichero un una variable tipo string
            while ((linea = br.readLine()) != null) {
                // Se almancena el dato obtenido dato obtenido en la variable linea en un vector. el split nos permite
                // dividir la cadena sin encuentra una coma.
                String [] datos = linea.split(",");
                
                //Bucle que sirve para almacenar los datos del vector al arraylist
                for (String dato : datos) {
                    temporal.add(dato);
                }
            }
            //Se cierran los ficheros.
            br.close();
            fr.close();
        } catch (Exception e) {
        }
        
        //Se retorna el arraylist
        return temporal;
    }
}
