package Inicio.Modelo;
/**
 * Esta clase sera la encargada de controlar las colisiones entre Pacman y los fantasmas, así como las de los fanastasmas
 * entre ellos.
 * 
 * @author Yeray Rodríguez Martín
 * @version 1.0
 */
public abstract class Colisiones
{
    /**
     * Este método servirá para saber si Pacman a colisionado con alguno de los fantasmas
     * 
     * @param pacman Nuestro pacman
     * @param blinky Cualquier fantasma
     * @param pinky Cualquier fantasma
     * @param clyde Cualquier fantasma
     * @return true si pacman a colisionado con algun fantasma, false en caso contrario
     */
    public static boolean colisionPacmamFantasma(PacMan pacman, Fantasmas blinky, Fantasmas pinky, Fantasmas clyde)
    {
        int filaPacman=pacman.getPosicion().getFila();
        int columnaPacman=pacman.getPosicion().getColumna();
        if( blinky.getPosicion().getFila()==filaPacman && blinky.getPosicion().getColumna()==columnaPacman){
                return true;
        }
        
        if( pinky.getPosicion().getFila()==filaPacman && pinky.getPosicion().getColumna()==columnaPacman){
                return true;
        }
        
        if( clyde.getPosicion().getFila()==filaPacman && clyde.getPosicion().getColumna()==columnaPacman){
                return true;
        }
        return false;
    }
    
    /**
     * Este método servirá para saber si Pacman a colisionado con un fantasma en particular
     * 
     * @param pacman Nuestro pacman
     * @param fantasma Cualquier fantasma
     * @return true si pacman a colisionado con el fantasma, false en caso contrario
     */
    public static boolean colisionPacmanFantasma(PacMan pacman, Fantasmas fantasma){
        int filaPacman=pacman.getPosicion().getFila();
        int columnaPacman=pacman.getPosicion().getColumna();
        int filaFantasma=fantasma.getPosicion().getFila();
        int columnaFantasma=fantasma.getPosicion().getColumna();
        if(filaFantasma==filaPacman && columnaFantasma==columnaPacman){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Este método tendrá la finalidad de saber si dos fantasmas han colisionado entre ellos
     * 
     * @param fantasma1 el primer fantasma
     * @param fantasma2 el segundo fantasma
     * @return true si han colisionado entre ellos, false en caso contrario
     */
    public static boolean colisionEntreFantasmas(Fantasmas fantasma1, Fantasmas fantasma2)
    {
        int filaPrimerFantasma=fantasma1.getPosicion().getFila();
        int columnaPrimerFantasma=fantasma1.getPosicion().getColumna();
        int filaSegundoFantasma=fantasma2.getPosicion().getFila();
        int columnaSegundoFantasma=fantasma2.getPosicion().getColumna();
        if(filaPrimerFantasma==filaSegundoFantasma && columnaPrimerFantasma==columnaSegundoFantasma){
            return true;
        }else{
            return false;
        }
    }
}
