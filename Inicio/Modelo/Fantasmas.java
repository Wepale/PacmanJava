package Inicio.Modelo;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Esta clase es la base del modelo todos los fantasmas
 * 
 * 
 * @author Yeray Rodriguez Martin
 * @version 1.0
 */
public abstract class Fantasmas extends Componente
{  
    private Image imagenAzul;
    /**
     * Inicializa la imagen azul de todos los fantasmas
     */
    public Fantasmas()
    {
        super();
        imagenAzul=new ImageIcon(getClass().getResource("fantasmaAzul.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT);
    }
    
    /**
     * Devuelve la imagen azul de los fantasmas
     * 
     * @return imagenAzul El campo imagenAzul
     */
    public Image getImagenAzul()
    {
        return imagenAzul;
    }
    
    /**
     * Este método se usa para saber la distancia en columnas que existe entre el fantasma y Pacman.
     * 
     * Si la distancia es negativa Pacman se encuentra a la derecha del fantasma
     * Si la distancia es positiva Pacman se encuentra a la izquierda del fantasma
     * 
     * NOTA: Este método he decidio utilizarlo por que necesito calcular en varios lugares
     * la distancia entre los fantasmas y pacman, por lo que así evito la reescritura de codigo
     */
    
    public int calcularDistanciaColumnas(PacMan pacman)
    {
        int columnaPacman= pacman.getPosicion().getColumna();           //Columna en la que se encuentra PacMan//getCoordenadas().
        int columnaFantasma= getPosicion().getColumna();                //Columna en la que se encuentra el fantasma
        return columnaFantasma-columnaPacman;
    }
    
    /**
     * Este método se usa para saber la distancia en filas que existe entre el fantasma y Pacman.
     * 
     * Si la distancia es negativa PacMan se encuentra debajo del fantasma
     * Si la distancia es positiva Pacman se encuentra encima del fantasma
     * 
     * NOTA: Este método he decidio utilizarlo por que necesito calcular en varios lugares
     * la distancia entre los fantasmas y pacman, por lo que así evito la reescritura de codigo
     */
    
    public int calcularDistanciaFilas(PacMan pacman)
    {
        int filaPacman= pacman.getPosicion().getFila(); //Fila en la que se encuentra PacMan
        int filaFantasma= getPosicion().getFila();              //Fila en la que se encuentra el fantasma
        return filaFantasma-filaPacman;
    }
    
    /**
     * Este método se usa para saber hacia que dirección se tiene que mover el fantama para acercarce
     * horizontalmente a Pacman
     * 
     * @param pacman Un objeto tipo pacman para saber donde se encuentra
     * @return 1 si se mueve a la izquierda, 2 si se mueve a la derecha, 0 si se encuntra en la misma columna
     */
    public int dondeMoverseHorizontal(PacMan pacman)
    {
        int distanciaColumnas= calcularDistanciaColumnas(pacman);
        
        if(distanciaColumnas==0){               //Pacman y el fantasma se encuentran en la misma columna
            return 0;
        }else if(distanciaColumnas>0){          //Pacman se encuentra a la izquierda del fantasma
            return 1;
              }else{                            //Pacman se encuentra a la derecha del fantasma
                  return 2;
               }    
    }

    /**
     * Este método se usa para saber hacia que dirección se tiene que mover el fantasma para acercarse
     * verticalmente a Pacman.
     * @param pacman Un objeto tipo pacman para saber donde se encuentra
     * @return 1 si se mueve hacia arriba, 2 si se mueve  hacia abajo, 0 si se encuntra en la misma fila
     */
    public int dondeMoverseVertical(PacMan pacman)
    {
        int distanciaFilas= calcularDistanciaFilas(pacman);
        if(distanciaFilas==0){                      //Pacman y el fantasma se encuentran en la misma fila
            return 0;
        }else if(distanciaFilas>0){                 //Pacman se encuentra encima del fantasma
            return 1;
              }else{                               //Pacman se encuentra debajo el fantasma
                  return 2;
               }     
    }
    
    /**
     * Este metodo sirve para encontrar la primera calle vertical por la que pueda circular el fantasma. La dirección de la busqueda se selecciona mediante
     * sus parametros. Este método solo funcionara correctamente con los siguientes valores para los parametros: 1 , -1.
     * 
     * @ param sentidoHorizontal Dependiendo del valor del parametro buscaremos hacia una dirección la primera calle vertical .Si el valor
     * es -1, buscará la calle hacia la izquierda, y si el valor es 1 buscara la calle hacia la derecha
     * 
     * @param sentidoVertical Dependiendo del valor del parametro indicaremos hacia que sentido es la calle vertical que estamos buscando.
     * Si el valor es -1, queremos encontrar la primera calle vertical hacia arriba, y si el valor es 1 queremos encontrar la primera calle
     * vertical hacia abajo
     * 
     * @return El número de casillas que hay hasta nuestro primer camino valido. Devuelve 0 si no hay ningun camino.
     */
    public int primeraCalleVertical(int sentidoHorizontal, int sentidoVertical,Tablero tablero){
        boolean murosDeCamino=false;
        int contador=0;
        int fila= getPosicion().getFila();       
        int columna= getPosicion().getColumna();
        int filaAux=fila;
        int columnaAux=columna;
        
        fila=fila+sentidoVertical; 
        columna= columna+sentidoHorizontal;
        while(columna>0 && columna<tablero.getColumnas()){  
            if(tablero.tablero[fila][columna]==-1){
               contador++;
            }else{
                contador++;
                break;
            }
            columna= columna+sentidoHorizontal;
        }
        /*
         * Comprobamos que de camino a nuestra calle vertical no haya muros.
         */
        columnaAux=columnaAux+sentidoHorizontal;
        for(int i=0; i<contador; i++){
            if(tablero.tablero[filaAux][columnaAux]==-1){
               murosDeCamino=true;
            }
            columnaAux=columnaAux+sentidoHorizontal;
        }
        if(murosDeCamino){
            return 0;
        }else{
            return contador;
        }
    }
    
    /**
     * Este método es el encargado de elegir que calle vertical se tomará y devolvera la primera casilla hacia esa calle.
     * 
     * 
     * @param contadorDer La distancia a la que se encuentra la primera calle vertical hacia la derecha
     * @param contadorIz La distancia a la que se encuentra la primera calle vertical hacia la izquierda
     * 
     * @return casillaDer,casillaIz La siguiente casilla hacia la calle elegida.
     */
    
    public Coordenadas elegirCalleVertical(int contadorDer, int contadorIz){
        Random aleatorio= new Random();
        int fila= getPosicion().getFila();
        int columna= getPosicion().getColumna();
        Coordenadas casillaDer= new Coordenadas(fila, columna+1);    //Una casilla a la Derecha
        Coordenadas casillaIz= new Coordenadas(fila, columna-1);     //Una casilla a la Izquierda
        Coordenadas casillaActual= new Coordenadas(fila, columna);
        
        if(contadorDer==contadorIz){                                                //Si hay la misma distancia, se devuelve una aleatoriamente
             if(aleatorio.nextDouble()<=0.5){
                    if(movimientoValido(casillaDer)){
                        return casillaDer;
                    }else{
                        return casillaIz;
                    }
             }else{
                    if(movimientoValido(casillaIz)){
                        return casillaIz;
                    }else{
                        return casillaDer;
                    }
             }
        }else if(contadorDer<contadorIz && contadorDer==0){                         
                    return casillaIz;
              }else if(contadorIz<contadorDer && contadorIz==0){
                    return casillaDer;
                    }else if(contadorDer<contadorIz && contadorDer!=0){
                                return casillaDer;
                          }else {
                                return casillaIz;
                            }
    }
    
    /**
     * Este método sirve para encontrar la primera calle horizontal por la que pueda circular el fantasma. La dirección de la busqueda se selecciona mediante
     * sus parametros. Este método solo funcionara correctamente con los siguientes valores para los parametros: -1 , 1.
     * 
     * @ param sentidoVertical Dependiendo del valor del parametro buscaremos hacia una dirección o en otra la primera calle horizontal .Si el valor
     * es -1, buscara la calle hacia la arriba, y si el valor es 1 buscara la calle hacia abajo
     * 
     * @param sentidoHorizontal Dependiendo del valor del parametro indicaremos hacia que sentido es la calle horizontal que estamos buscando.
     * Si el valor es -1, queremos encontrar la primera calle horizontal hacia la izquierda, y si el valor es 1 queremos encontrar la primera calle
     * horizontal hacia la derecha
     * 
     * @return El número de casillas que hay hasta nuestro primer camino válido. Devuelve 0 si no hay ningun camino.
     */
    public int primeraCalleHorizontal(int sentidoVertical, int sentidoHorizontal, Tablero tablero){
        boolean murosDeCamino=false;
        int contador=0;
        int fila= getPosicion().getFila();       
        int columna= getPosicion().getColumna();
        int filaAux=fila;
        int columnaAux=columna;
        
        columna=columna+sentidoHorizontal;
        fila= fila+sentidoVertical;
        while(fila>0 && fila<tablero.getFilas()){  
            if(tablero.tablero[fila][columna]==-1){
               contador++;
            }else{
                contador++;
                break;
            }
            fila= fila+sentidoVertical;
        }
        /*
         * Comprobamos que no haya muros de camino a nuestra calle horizontal
         */
        filaAux= filaAux+sentidoVertical;
        for(int i=0; i<contador; i++){
            if(tablero.tablero[filaAux][columnaAux]==-1){
                murosDeCamino=true;
            }
            filaAux= filaAux+sentidoVertical;
        }
        if(murosDeCamino){
            return 0;
        }else{
            return contador;
        }
    }
    
    /**
     * Este metodo es el encargado de elegir que calle horizontal se tomará y devolvera la primera casilla hacia esa calle.
     * 
     * La idea de escribir este metodo es para usarlo como apoyo con el metodo "buscarMovimientoVertical()" ya que así evito tener que escribir
     * lo mismo varias veces.
     * 
     * @param contadorArriba La distancia a la que se encuentra la primera calle horizontal si buscamos hacia arriba
     * @param contadorAbajo La distancia a la que se encuentra la primera calle horizontal si buscamos hacia abajo
     * @return casillaArriba,casillaAbajo La prrimera casilla hacia la calle elegida
     */
    
    public Coordenadas elegirCalleHorizontal(int contadorArriba, int contadorAbajo){
        Random aleatorio= new Random();
        int fila= getPosicion().getFila();
        int columna= getPosicion().getColumna();
        Coordenadas casillaArriba= new Coordenadas(fila-1, columna);
        Coordenadas casillaAbajo= new Coordenadas(fila+1, columna);
        Coordenadas casillaActual= new Coordenadas(fila, columna);
        
        if(contadorArriba==contadorAbajo){                                                //Si hay la misma distancia, se devuelve una aleatoriamente
             if(aleatorio.nextDouble()<=0.5){
                    if(movimientoValido(casillaArriba)){
                        return casillaArriba;
                    }else{
                        return casillaActual;
                    }
             }else{
                    if(movimientoValido(casillaAbajo)){
                        return casillaAbajo;
                    }else{
                        return casillaActual;
                    }
             }
        }else if(contadorArriba<contadorAbajo && contadorArriba==0){                         
                    return casillaAbajo;
              }else if(contadorAbajo<contadorArriba && contadorAbajo==0){
                    return casillaArriba;
                    }else if(contadorArriba<contadorAbajo && contadorArriba!=0){
                                return casillaArriba;
                          }else{
                                return casillaAbajo;
                            }
    }
    
    /**
     * Este metodo se utiliza cuando el fantasma tiene que moverse de forma horizontal para acercarse a PacMan
     * pero hay un muro. Por lo tanto este metodo encuentra el camino mas corto hacía una calle que permita
     * acercarse a PacMan. 
     * 
     * Si el fantasma necesita moverse hacia la izquierda o hacia la derecha pero hay un muro:
     * 
     * 1º Caso: si Pacman se encuentra justo en la misma fila, este metodo buscara la calle más
     * próxima por la que pueda desplazarse hacia la izquierda o la derecha segun la posicion de Pacman.
     * 
     * 2º Caso :En caso de que Pacman se encuentre encima/abajo del fantasma buscara la primera calle
     * hacia arriba/abajo por la que pueda desplazarse hacia la izquierda o la derecha(Depende de si pacamn esta a la derecha o a la izquierda del fantasma).
     * En el caso de que no haya ninguna calle en esa dirección buscará una en la direccion contraria.
     * 
     * @param pacman Nuestro Pacman, para saber donde se encuentra
     * @param tablero El tablero, necesario para encontrar el camino
     * @return Coordenadas La primera casilla hacia la calle que hemos encontrado
     * 
     */
    public Coordenadas buscarCalleHorizontal(PacMan pacman, Tablero tablero)
    {
        //El fantasma debe moverse Verticalmente hasta encontrar una calle horizontal por la que pueda circular
        int contadorArriba, contadorAbajo;
        int cantidadColumnas=calcularDistanciaColumnas(pacman);  //Para saber en que sentido es la calle horizontal que estoy buscando, hacia la derecha o izquierda
        int cantidadFilas=calcularDistanciaFilas(pacman);        //Para saber en que direccion buscar la calle horizontal, hacia arriba o hacia abajo
                
        if(cantidadFilas==0 && cantidadColumnas<0){                                         //Busco hacia los dos lados la primera calle posible hacia la derecha
            contadorArriba= primeraCalleHorizontal(-1,1,tablero);
            contadorAbajo= primeraCalleHorizontal(1,1,tablero);
            return elegirCalleHorizontal(contadorArriba, contadorAbajo);
        }else if(cantidadFilas==0 && cantidadColumnas>0){                                   //Busco hacia los dos lados la primera calle disponible hacia la izquierda
                  contadorArriba=primeraCalleHorizontal(-1,-1,tablero);
                  contadorAbajo=primeraCalleHorizontal(1,-1,tablero);
                  return elegirCalleHorizontal(contadorArriba, contadorAbajo);
              }else if(cantidadFilas<0 && cantidadColumnas<0){                              //Busco hacia abajo la primera calle a la derecha                                                  
                        contadorAbajo=primeraCalleHorizontal(1,1,tablero);
                        if(contadorAbajo!=0){                                               //Hemos encontrado la calle
                            return elegirCalleHorizontal(0,contadorAbajo);
                        }else{                                                               //No la hemos encontrado, buscamos hacia arriba
                            contadorArriba=primeraCalleHorizontal(-1,1,tablero);
                            return elegirCalleHorizontal(contadorArriba,contadorAbajo);
                        }
                    }else if(cantidadFilas<0 && cantidadColumnas>0){                            //Busco hacia abajo la primera calle a la izquierda
                                contadorAbajo=primeraCalleHorizontal(1,-1,tablero);
                                if(contadorAbajo!=0){                                           //Hemos encontrado la calle    
                                    return elegirCalleHorizontal(0,contadorAbajo);
                                }else{                                                          //No la hemos encontrado, buscamos calle hacia arriba
                                    contadorArriba=primeraCalleHorizontal(-1,-1,tablero);
                                    return elegirCalleHorizontal(contadorArriba,contadorAbajo);
                                }
                           }else if(cantidadFilas>0 && cantidadColumnas<0){                         //Busco hacia hacia arriba la primera calle a la derecha
                                      contadorArriba=primeraCalleHorizontal(-1,1,tablero);
                                      if(contadorArriba!=0){                                        //Hemos encontrado la calle
                                          return elegirCalleHorizontal(contadorArriba,0);
                                      }else{                                                        //No la hemos encontrado, buscamos hacia abajo
                                          contadorAbajo=primeraCalleHorizontal(1,1,tablero);
                                          return elegirCalleHorizontal(contadorArriba,contadorAbajo);
                                      }
                                 }else{                                                                  //Busco hacia arriba la primera calle a la izquierda
                                       contadorArriba=primeraCalleHorizontal(-1,-1,tablero);
                                       if(contadorArriba!=0){                                               //Hemos encontrado la calle
                                          return elegirCalleHorizontal(contadorArriba,0);
                                       }else{                                                            //No la hemos encontrado, buscamos hacia abajo
                                          contadorAbajo=primeraCalleHorizontal(1,-1,tablero);
                                          return elegirCalleHorizontal(contadorArriba,contadorAbajo);
                                       }
                                 } 
    }
    
    /**
     * Este método se utiliza cuando el fantasma tiene que moverse de forma vertical para acercarse a PacMan
     * pero hay un muro. Por lo tanto este método encuentra el camino mas corto hacia una calle que permita
     * acercarse a PacMan. 
     * 
     * Si el fantasma necesita moverse hacia abajo o hacia arriba pero hay un muro:
     * 
     * 1º Caso: si Pacman se encuentra justo en la misma columna, este metodo buscara la calle más
     * próxima por la que pueda desplazarse hacia abajo o hacia arriba
     * 
     * 2º Caso :En caso de que Pacman se encuentre a la izquierda/derecha del fantasma buscara la primera calle
     * hacia la izquierda/izquierda por la que pueda desplazarse hacia abajo o hacia arriba.En la caso de que no haya ninguna calle
     * en esa dirección buscara una en la direccion contraria.
     * 
     * @param pacman Nuestro Pacman, para saber donde se encuentra
     * @param tablero El tablero, necesario para encontrar el camino
     * @return Coordenadas La primera casilla hacia la calle que hemos encontrado
     * 
     */
    public Coordenadas buscarCalleVertical(PacMan pacman, Tablero tablero)
    {
        //El fantasma debe moverse horizontalmente hasta encontrar un camino vertical por el que pueda circula
        int contadorDer, contadorIz;
        int distanciaColumnas=calcularDistanciaColumnas(pacman); //Para saber en que direccion buscar la calle vertical que estoy buscando, izquierda o derecha
        int cantidadFilas=calcularDistanciaFilas(pacman);        //Para saber en que sentido es la calle vertical que estoy buscando, hacia arriba o hacia bajo
                
        if(distanciaColumnas==0 && cantidadFilas<0){                                         //Buscamos hacia los dos lados la primera calle posible hacia abajo
            contadorDer= primeraCalleVertical(1,1,tablero);
            contadorIz= primeraCalleVertical(-1,1,tablero);
            return elegirCalleVertical(contadorDer, contadorIz);
        }else if(distanciaColumnas==0 && cantidadFilas>0){                                   //Buscamos hacia los dos lados la primera calle disponible hacia arriba
                  contadorDer=primeraCalleVertical(1,-1,tablero);
                  contadorIz=primeraCalleVertical(-1,-1,tablero);
                  return elegirCalleVertical(contadorDer, contadorIz);
              }else if(distanciaColumnas<0 && cantidadFilas<0){                              //Primera calle hacia abajo por la derecha                                                    
                        contadorDer=primeraCalleVertical(1,1,tablero);
                        if(contadorDer!=0){
                            return elegirCalleVertical(contadorDer,0);
                        }else{
                            contadorIz=primeraCalleVertical(-1,1,tablero);                  //Primera calle hacia abajo por la izquierda
                            return elegirCalleVertical(contadorDer,contadorIz);
                        }
                    }else if(distanciaColumnas<0 && cantidadFilas>0){                        //Primera calle hacia arriba por la derecha 
                                contadorDer=primeraCalleVertical(1,-1,tablero);
                                if(contadorDer!=0){
                                    return elegirCalleVertical(contadorDer,0);
                                }else{
                                    contadorIz=primeraCalleVertical(-1,-1,tablero);         //Primera calle hacia arriba por la izquierda
                                    return elegirCalleVertical(contadorDer,contadorIz);
                                }
                           }else if(distanciaColumnas>0 && cantidadFilas<0){                  //Primera calle hacia abajo por la izquierda
                                      contadorIz=primeraCalleVertical(-1,1,tablero);
                                      if(contadorIz!=0){
                                          return elegirCalleVertical(0,contadorIz);
                                      }else{
                                          contadorDer=primeraCalleVertical(1,1,tablero);       //Primera calle hacia abajo por la derecha
                                          return elegirCalleVertical(contadorDer,contadorIz);
                                      }
                                 }else{                                                       //Primera calle hacia arriba por la izquierda
                                       contadorIz=primeraCalleVertical(-1,-1,tablero);
                                       if(contadorIz!=0){
                                          return elegirCalleVertical(0,contadorIz);
                                       }else{
                                          contadorDer=primeraCalleVertical(+1,-1,tablero);       //Primera calle hacia arriba por la derecha
                                          return elegirCalleVertical(contadorDer,contadorIz);
                                       }
                                 } 
    }
    
    /**
     * Calcula del movimiento del fantasma. Cada fantasma reescribira este metodo con su
     * correspondiente movimiento 
     * @param pacman Nuestro pacman, necesitamos saber donde está para calcular
     * el movimiento de los fantasmas
     * @param tablero Nuestro tablero 
     */
    
    
    abstract public Coordenadas calcularMovimiento(PacMan pacman, Tablero tablero);
}
