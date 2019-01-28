package Inicio.Vista;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * Clase encargada de ejecutar el juego. Se trata de un JFrame y simplemente contiene el Jpanel que realiza
 * toda la animación del juego. Contiene el metodo main() para ejecutar el juego.
 * 
 * @author Yeray Rodríguez Martín 
 * @version 1.0
 */
public class Pacman extends JFrame
{
    /**
     * Constructor
     */
    public Pacman()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize ((d.width),(d.height));
        setLocationRelativeTo(null);
        setResizable(false);
        add(new TableroGrafico());
        setVisible(true);
    }
    /**
     * Método main para ejecutar el juego
     */
    public static void main (String [] args){
        new Pacman();
    }
}