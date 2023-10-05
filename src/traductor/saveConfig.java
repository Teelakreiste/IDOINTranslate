/*
    Clase la cual nos permite guardar la configuración de la interfaz gráfica utilizando fichero xml.
 */
package traductor;

/**
 *
 * @author David
 */
public class saveConfig {
    //Atributos de la clase.
    private boolean modo;
    private int de, a;
    
    //constructor
    public saveConfig() {
    }
    
    /*getters y setters de los atributos*/
    public int getDe() {
        return de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    
    public boolean isModo() {
        return modo;
    }

    public void setModo(boolean modo) {
        this.modo = modo;
    }
    
    
    //método toString() para impirmir los valores de los atributos
    @Override
    public String toString() {
        return "saveConfig{" + "modo=" + modo + ", de=" + de + ", a=" + a + '}';
    }
}
