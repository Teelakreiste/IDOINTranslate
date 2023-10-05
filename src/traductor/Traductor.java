/* Programa que permite la traducir texto a otros idiomas.



    Esté proyecto implementa la API del traductor de google para hacer las traducciones. Dicha API se obtuvo al crear una script en Google Script
    y dicha Script se implemento en la función translate.
 */
package traductor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import traductor.gui.main;

/**
 *
 * @author David
 */
public class Traductor {

    public static void main(String[] args) throws IOException {
        //Se crear un variable de tipo File la cual sirve para comprobar si la caparte "saves" existe, de no ser así sera creada.
        File directorio = new File(System.getProperty("user.dir")+"\\saves");
        if (!directorio.exists()) directorio.mkdir();
        // Se crea un objeto de tipo main, el cuál es la interfaz gráfica del traductor. dicho objeto se hace visible para su posterior visualización.
        main windows = new main();
        windows.setVisible(true);
        //Translated text: Hallo Welt!
        //System.out.println("Translated text: " + translate("es", "en", "Hola, cómo estás?"));
    }
    
    // Función que permite obtener la traducción del texto ejemplo "hello" -> "hola"
    public static String getTranslate(String de, String a, String texto) throws IOException {
        return translate(de, a, texto);
    }
    
    /* 
        Función que permite traducir un texto, y recive como parametros el idioma de origen, el idioma a traducir y el texto a traducir.
        dicha función utuiliza una conección HTTPS al Script dicho script fue creado por nosotros para permitir la traducción.
        Luego de hacer la conexión con el script el cual recive como parametros el texto y los idiomas, sigue el manejo de archivos leer dichas lineas
        y retornar el texto traducido.
    */
    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // Variable tipo String la cual sirve para concantenar la ruta del script con el texto y los idiomas de la traducción
        String urlStr = "https://script.google.com/macros/s/AKfycbwe_Yc-beP_EctVuR6sPQVoHa1bvJTT0XV7abUd5zuAY4LmMa9w-uCbA5Es7k6_2akjIA/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        // variable de tipo URL que nos ayuda para la manipulación de URL, y la conexión web del script 
        URL url = new URL(urlStr);
        // Variable de tipo StringBuilder la cual permite crear obejetos que almacenen cadenas de caracteres.
        StringBuilder response = new StringBuilder();
        // Esto nos permite leer el espacio de memoria de entrada de la conexión URL de una forma mas eficiente.
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //Protocolo de red
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        //Se utiliza el manejor de archivos para leer el arraylist del texto traducido para su posterior retorno.
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
