package Inicio.Vista;
import java.awt.*;
import java.awt.geom.*;
import Inicio.Modelo.*;
/**
 * Esta clase será la encargada de contenener todo lo necesario para transformar el tablero matemático del juego en el tablero gráfico. En definitiva, contendrá
 * metodos para encontrar los píxeles correspondientes a cada casilla del juego
 * 
 * El laberentio gráfico esta basado en la matriz de la clase Tablero. Cada posicion de la matriz será una casilla en nuestro tablero grafico.
 * 
 * Las casillas tendran un tamaño de 18x18 pixeles, estando separadas 20 pixeles cada una. Es decir, habra 1 pixel entre cada una de ellas
 * 
 * Para dibujar el laberinto se usa como referencia el pixel (AnchoDeLaPantalla/4,AltoDeLaPantalla/13) el cual es el pixel superior derecho de nuestra
 * primera casilla en el tablero (tablero[0][0])
 * 
 * La formula para hallar el pixel superior derecho de cualquier casilla de nuestro tablero es: (PIXEL_CLAVE_X+ 20*columna ,PIXEL_CLAVE_Y + 20*fila)
 * 
 * La formula para centrar un punto pequeño de tamaño 5x5 en el centro de cualquier casilla es: (PIXEL_CLAVE_X + 20*columna + 7, PIXEL_CLAVE_Y + 20*fila + 7)
 * 
 * La formula para centrar un punto grande de tamaño 8x8 en el centro de cualquier casilla es: (PIXEL_CLAVE_X + 20*columna + 5, PIXEL_CLAVE_Y + 20*fila + 5)
 * 
 * La formula para hallar la longuitud de todas nuestras casillas es: (nºpixelesCadaCasilla*nºcasillas) + (espacioEntreCasillas*(nºcasillas - 1))
 * 
 * @author Yeray Rodriguez Martin 
 * @version 1.0
 */
public class AyudaDibujo
{
    private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private final int PIXEL_CLAVE_X= d.width/4;                     //La componente X del pixel correspondiente a la esquina superior de nuestra primera casilla
    private final int PIXEL_CLAVE_Y= d.height/13;                   //La componente Y del pixel correspondiente a la esquina superior de nuestra primera casilla
    private final int NEXT_CASILLA= 20;                             //El número de pixeles de separacion de nuestras casillas
    private final int TAMANIO_PUNTO_PEQUENIO=5;                     //El tamaño del punto pequeño
    private final int CENTRAR_PUNTO_PEQUENIO=7;                     //La cantidad de pixeles que hay que mover el punto pequeño para centrarlo en la casilla
    private final int TAMANIO_PUNTO_GRANDE=8;                       //El tamaño del punto grande
    private final int CENTRAR_PUNTO_GRANDE=5;                       //La cantidad de pixeles que hay que mover el punto grande para centrarlo en la casilla
    private final int TAMANIO_CASILLA=18;                           //El tamaño de cada casilla
    /**
    * Este método devuelve el píxel en el eje X que se le asocia a la esquina superior izquierda de una determinada casilla de nuestra clase Tablero
    * 
    * @param columna El índice correspondiente a la columna de la casilla a la que queremos acceder
    * @return Su pixel asociado en el eje X
    */
   public int getCasillaX(int columna){
       return PIXEL_CLAVE_X + (NEXT_CASILLA*columna);
   }
    /**
     * Este método devuelve el pixel en el eje Y que se le asocia a la esquina superior izquierda de una determinada casilla de nuestra clase Tablero
     * 
     * @param fila El índice correspondiente a la fila de la casilla a la que queremos acceder
     * @return Su pixel asociado en el eje Y
     */
    public int getCasillaY(int fila){
         return PIXEL_CLAVE_Y + (NEXT_CASILLA*fila);
   }
   
   /**
    * Este método es el encargado de dibujar los muros.
    * 
    * @param contextoGrafico El contexto gráfico en el que estamos trabajando
    * @param grosor El grosor del muro
    * @param color El color del muro
    * @param filaInicio La fila donde empezaremos a dibujar nuestro muro. Se corresponde con las filas de la matriz de la clase Tablero
    * @param columnaInicio La columna donde empezaremos a dibujar nuestro muro. Se corresponde con las columnas de la matriz de la clase Tablero
    * @param filaFinal  La fila donde terminaremos de dibujar nuestro muro. Se corresponde con las filas de la matriz de la clase Tablero
    * @param columnaFinal   La columna donde terminaremos a dibujar nuestro muro. Se corresponde con las columnas de la matriz de la clase Tablero
    * @param desplazamientoX La cantidad de píxeles que queremos mover nuestro muro en el eje de las X
    * @param desplazamientoY La cantidad de píxeles que queremos mover nuestro muro en el eje de las Y
    * 
    * @param addPixelX Añadir pixeles al comienzo de nuestro muro en el eje X. Debido a que tenemos que desplazar algunos muros para que no coincidad
    *                                                                          con el borde las casilas adyacentes, puede suceder que los muro no se alinen
    *                                                                          perfectamente los unos con los otros. Añadiendo varios pixeles esto se solucionaria
    *                                                                          
    * @param addPixelY Añadir pixeles al comienzo de nuestro muro en el eje Y.Debido a que tenemos que desplazar algunos muros para que no coincidad
    *                                                                          con el borde las casilas adyacentes, puede suceder que los muro no se alinen
    *                                                                          perfectamente los unos con los otros. Añadiendo varios pixeles esto se solucionaria
    */
   public void pintarMuro(Graphics2D contextoGrafico, int grosor, Color color, int filaInicio, int columnaInicio, 
                          int filaFinal, int columnaFinal, int desplazamientoX, int desplazamientoY, int addPixelX, int addPixelY)
   {
       Line2D linea= new Line2D.Double(getCasillaX(columnaInicio)+desplazamientoX-addPixelX, getCasillaY(filaInicio)+desplazamientoY-addPixelY,
                                       getCasillaX(columnaFinal)+desplazamientoX, getCasillaY(filaFinal)+desplazamientoY);
       contextoGrafico.setStroke(new BasicStroke(grosor));
       contextoGrafico.setColor(color);
       contextoGrafico.draw(linea);
   }
   
   
   /**
    * Este método tiene como finalidad dibujar un punto pequeño y centrarlo dentro de la casilla correspondiente
    * 
    * @param fila El índice de la fila de nuestra casilla
    * @param columna El índice de la columna de nuestra casilla
    */
   
   public void centrarPequenio(Graphics2D contextoGrafico, int fila, int columna){
       Ellipse2D puntoPequeño= new Ellipse2D.Double(getCasillaX(columna)+CENTRAR_PUNTO_PEQUENIO,getCasillaY(fila)+CENTRAR_PUNTO_PEQUENIO, TAMANIO_PUNTO_PEQUENIO,TAMANIO_PUNTO_PEQUENIO);
           contextoGrafico.setStroke(new BasicStroke(1));
           contextoGrafico.setColor(Color.yellow);
           contextoGrafico.draw(puntoPequeño);
           contextoGrafico.fill(puntoPequeño); 
    }
    
    /**
    * Este método tiene como finalidad dibujar un punto grande y centrarlo dentro de la casilla correspondiente
    * 
    * @param fila El indice de la fila de nuestra casilla
    * @param columna El indice de la columna de nuestra columna
    */
   
   public void centrarGrande(Graphics2D contextoGrafico, int fila, int columna){
       Ellipse2D puntoGrande= new Ellipse2D.Double(getCasillaX(columna)+CENTRAR_PUNTO_GRANDE,getCasillaY(fila)+CENTRAR_PUNTO_GRANDE, TAMANIO_PUNTO_GRANDE,TAMANIO_PUNTO_GRANDE);
           contextoGrafico.setStroke(new BasicStroke(1));
           contextoGrafico.setColor(Color.yellow);
           contextoGrafico.draw(puntoGrande);
           contextoGrafico.fill(puntoGrande);
    }
    
   /**
    * Dibuja todo el laberinto
    * @param contextoGrafico Nuestro contexto gráfico
    * @param tablero Nuestro tablero para saber donde dibujar
    */
   public void pintarLaberinto(Graphics2D contextoGrafico, Tablero tablero)
   {
 
        /*
         * Primero pintaremos lor muros exteriores de nuestro laberinto. Nuestro muro superior se pintara justo encima de nuestra segunda fila, o lo que es
         * lo mismo, justo debajo de nuestra primera fila. Tendra un grosor de tamaño 3, por lo que para que no se pinte encima en el borde de nuestra
         * segunda fila de casillas, tendremos que moverlo justo 2 pixeles por arriba. Lo mismo pasara con el resto de muros exteriores pero desplazandolos
         * en su correcta dirección
         * 
         */
        
         
        
                                                      //LINEA SUPERIOR
                                                      
           pintarMuro(contextoGrafico, 3, Color.cyan, 1,1, 1,27, 0, -2, 2, 0); 
        
                                                     //LINEA INFERIOR
                                                     
           pintarMuro(contextoGrafico, 3, Color.cyan, 30,1, 30,27, 0, 0, 0, 0);
                                                     
                                                    //1º LINEA LATERAL IZQUIERDA
                         
           pintarMuro(contextoGrafico, 3, Color.cyan, 1,1, 9,1, -2, -2, 0, -2); 
                           
                                                   //2º LINEA LATERAL IZQUIERDA
                                                   
           pintarMuro(contextoGrafico, 3, Color.cyan, 20,1, 30,1, -2, 0, 0, 0);
                                                 
                                                  //1º LINEA LATERAL DERECHA
                                                  
           pintarMuro(contextoGrafico, 3, Color.cyan, 1,27, 9,27, 0, -2, 0, -2);
                                              
                                                  //2º LINEA LATERAL DERECHA
                                                  
           pintarMuro(contextoGrafico, 3, Color.cyan, 20,27, 30,27, 0, 0, 0, 0);
            
           
           /*
            *  Ahora dibujaremos los muros interiores. Los muros interiores tendrán un grosor de 1 PÍXEL. Cada casilla del tablero esta separada 1 píxel,
            *  por lo que ese es el lugar por el que tendrá que ir nuestro muro.
            *  
            *  Muro Vertical: Tenemos que desplazarlo 1 pixel a la izquierda en el eje de las X. Tendremos que añadir o/y quitar píxeles o/y moverlo
            *  verticalmente para que alinee perfectamente con los demas muros
            *  
            *  Muro horizontal: Tenemos que desplazarlo 1 píxel hacia arriba en el eje de las Y. Tendremos que añadir o/y quitar pixeles o/y moverlo
            *  horizontalmente para que alinee perfectamente con los demas muros
            */
           
                  //1º FILA DE MUROS
           
                                                           //1º FIGURA DE MUROS
                                                           
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,2, 2,6, -1, -1, 0, 0);             //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,2, 5,2, -1, -1, 0, -1);            //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 5,2, 5,6, -1, -1, -1, 0);            //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,6, 5,6, -1, -1, 0, -1);            //vertical derecha
            
                                                           //2º FIGURA DE MUROS
                                                           
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,7, 2,12, -1, -1, 0, 0);            //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,7, 5,7, -1, -1, 0, -1);            //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 5,7, 5,12, -1, -1, -1, 0);           //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,12, 5,12, -1, -1, 0, -1);          //vertical derecha
            
                                                          //3º FIGURA DE MUROS
                                                          
            pintarMuro(contextoGrafico, 1, Color.cyan, 1,13, 5,13, -1, -1, 0, -1);          //Vertical izquierda       
            pintarMuro(contextoGrafico, 1, Color.cyan, 5,13, 5,15, -1, -1, -1, 0);          //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 1,15, 5,15, -1, -1, 0, -1);          //vertical derecha
             
                                                         //4º FIGURA DE MUROS
                                                         
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,16, 2,21, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,16, 5,16, -1, -1, 0, -1);          //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 5,16, 5,21, -1, -1, -1, 0);          //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,21, 5,21, -1, -1, 0, -1);          //vertical derecha
            
                                                         //5º FIGURA DE MUROS
            
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,22, 2,26, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,22, 5,22, -1, -1, 0, -1);          //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 5,22, 5,26, -1, -1, -1, 0);          //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 2,26, 5,26, -1, -1, 0, -1);          //vertical derecha
            
                                                        
                //2º FILA DE MUROS
                                         
                                                        //1º FIGURA DE MUROS
                                                        
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,2, 6,6, -1, -1, 0, 0);             //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,2, 8,2, -1, -1, 0, -1);            //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 8,2, 8,6, -1, -1, -1, 0);            //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,6, 8,6, -1, -1, 0, -1);            //vertical derecha
                                                        
                                                       //2º FIGURA DE MUROS
                           //BASE                            
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,7, 6,9, -1, -1, 0, 0);             //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,7, 20,7, -1, -1, 0, -1);           //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,7, 20,9, -1, -1, -1, 0);          //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,9, 9,9, -1, -1, 0, -1);            //vertical derecha superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 11,9, 20,9, -1, -1, 0, -1);          //verical derecha inferior
                           //SALIENTE
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,9, 9,12, -1, -1, 0, 0);            //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,12, 11,12, -1, -1, 0, -1);         //vertical derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 11,9, 11,12, -1, -1, -1, 0);         //Horizontal inferior
            
                                                       //3º FIGURA DE MUROS
                          //BASE
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,10, 6,18, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,10, 8,10, -1, -1, 0, -1);          //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 8,10, 8,13, -1, -1, -1, 0);          //Horizontal inferior izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 8,15, 8,18, -1, -1, -1, 0);          //Horizontal inferior derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,18, 8,18, -1, -1, 0, -1);          //Vertical derecha
                          //SALIENTE
            pintarMuro(contextoGrafico, 1, Color.cyan, 8,13, 11,13, -1, -1, 0, -1);         //Vertical izquierda  
            pintarMuro(contextoGrafico, 1, Color.cyan, 11,13, 11,15, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 8,15, 11,15, -1, -1, 0, -1);         //Vertical derecha
            
                                                      //3º FIGURA DE MUROS
                                                      
                         //BASE
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,19, 6,21, -1, -1, 0, 0);           //Horizontal superior 
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,19, 9,19, -1, -1, 0, -1);          //Vertical izquierda superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 11,19, 20,19, -1, -1, 0, -1);        //Vertical izquierda inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,19, 20,21, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,21, 20,21, -1, -1, 0, -1);         //Vertical izquierda
                        //SALIENTE
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,16, 9,19, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,16, 11,16, -1, -1, 0, -1);         //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 11,16, 11,19, -1, -1, -1, 0);        //Horizontal inferior
            
                                                       //4º FIGURA DE MUROS
                                                       
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,22, 6,26, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,22, 8,22, -1, -1, 0, -1);          //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 8,22, 8,26, -1, -1, -1, 0);          //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 6,26, 8,26, -1, -1, 0, -1);          //vertical derecha
            
                                            //CASA DE FANTASMAS
                                    
                         //CAJA EXTERIOR
            pintarMuro(contextoGrafico, 1, Color.cyan, 12,10, 12,12, -1, -1, 0, 0);         //Horizontal superior izquierda
            pintarMuro(contextoGrafico, 1, Color.pink.darker(), 12,12, 12,16, -1, -1, 0, 0);         //Horizontal salida
            pintarMuro(contextoGrafico, 1, Color.cyan, 12,16, 12,18, -1, -1, 0, 0);         //Horizontal superior derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 12,10, 17,10, -1, -1, 0, -1);        //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 17,10, 17,18, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 12,18, 17,18, -1, -1, 0, -1);        //vertical derecha
                        
                        //CAJA INTERIOR
            pintarMuro(contextoGrafico, 1, Color.cyan, 13,11, 13,12, -1, -1, 0, 0);         //Horizontal superior izquierda
            pintarMuro(contextoGrafico, 1, Color.pink.darker(), 13,12, 13,16, -1, -1, 0, 0);         //Horizontal salida
            pintarMuro(contextoGrafico, 1, Color.cyan, 13,16, 13,17, -1, -1, 0, 0);         //Horizontal superior derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 13,11, 16,11, -1, -1, 0, -1);        //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 16,11, 16,17, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 13,17, 16,17, -1, -1, 0, -1);        //vertical derecha
            
                                        //ENTRADA A TELETRANSPORTE IZQUIERDO
                          //SUPERIOR                    
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,1, 9,6, -1, -1, 0, 0);             //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,6, 20,6, -1, -1, 0, -1);           //Vertical
            //dibujar.pintarMuro(contextoGrafico, 1, Color.cyan, 14,1, 14,6, -1, -1, -1, 0);          //Horizontal inferior
            
                         //INFERIOR
            //dibujar.pintarMuro(contextoGrafico, 1, Color.cyan, 15,1, 15,6, -1, -1, 0, 0);           //Horizontal superior
            //dibujar.pintarMuro(contextoGrafico, 1, Color.cyan, 15,6, 20,6, -1, -1, 0, -1);          //Vertical
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,1, 20,6, -1, -1, -1, 0);          //Horizontal inferior
            
                                        //ENTRADA A TELETRANSPORTE DERECHO
                                        
                         //SUPERIOR                    
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,22, 9,27, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 9,22, 20,22, -1, -1, 0, -1);         //Vertical
            //dibujar.pintarMuro(contextoGrafico, 1, Color.cyan, 14,22, 14,27, -1, -1, -1, 0);        //Horizontal inferior
            
                        //INFERIOR
            //dibujar.pintarMuro(contextoGrafico, 1, Color.cyan, 15,22, 15,27, -1, -1, 0, 0);         //Horizontal superior
            //dibujar.pintarMuro(contextoGrafico, 1, Color.cyan, 15,22, 20,22, -1, -1, 0, -1);        //Vertical
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,22, 20,27, -1, -1, -1, 0);        //Horizontal inferior
                                                           
                                      //3º FILA DE MUROS
                                        
                                                    //UNICA FIGURA DE MUROS
                                                    
                          //BASE
            pintarMuro(contextoGrafico, 1, Color.cyan, 18,10, 18,18, -1, -1, 0, 0);         //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 18,10, 20,10, -1, -1, 0, -1);        //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,10, 20,13, -1, -1, -1, 0);        //Horizontal inferior izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,15, 20,18, -1, -1, -1, 0);        //Horizontal inferior derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 18,18, 20,18, -1, -1, 0, -1);        //Vertical derecha
                          //SALIENTE
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,13, 23,13, -1, -1, 0, -1);        //Vertical izquierda  
            pintarMuro(contextoGrafico, 1, Color.cyan, 23,13, 23,15, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 20,15, 23,15, -1, -1, 0, -1);        //Vertical derecha
            
                                    //4º FILA DE MUROS
                                    
                                                        //1º FIGURA DE MUROS
                                                        
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,2, 21,6, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,2, 23,2, -1, -1, 0, -1);          //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 23,2, 23,4, -1, -1, -1, 0);          //Horizontal media
            pintarMuro(contextoGrafico, 1, Color.cyan, 23,4, 26,4, -1, -1, 0, -1);          //vertical media
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,4, 26,6, -1, -1, -1, 0);          //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,6, 26,6, -1, -1, 0, -1);          //Vertical derecha
            
                                                       //2º FIGURA DE MUROS
                                                        
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,7, 21,12, -1, -1, 0, 0);          //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,7, 23,7, -1, -1, 0, -1);          //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 23,7, 23,12, -1, -1, -1, 0);         //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,12, 23,12, -1, -1, 0, -1);        //vertical derecha
                    
            
                                                        //3º FIGURA DE MUEROS
                                                    
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,16, 21,21, -1, -1, 0, 0);         //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,16, 23,16, -1, -1, 0, -1);        //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 23,16, 23,21, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,21, 23,21, -1, -1, 0, -1);        //vertical derecha                                        
                                                    
                                                    
                                                       //4º FIGURA DE MUROS
                                                        
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,22, 21,26, -1, -1, 0, 0);         //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,22, 26,22, -1, -1, 0, -1);        //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,22, 26,24, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 23,24, 26,24, -1, -1, 0, -1);        //vertical media
            pintarMuro(contextoGrafico, 1, Color.cyan, 23,24, 23,26, -1, -1, -1, 0);        //Horizontal media
            pintarMuro(contextoGrafico, 1, Color.cyan, 21,26, 23,26, -1, -1, 0, -1);        //Vertical derecha
                                                     
            
                                    //5º FILA DE MUROS 
                                        
                                                       //1º FIGURA DE MUROS
                                                       
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,1, 24,3, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,3, 26,3, -1, -1, 0, -1);          //vertical derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,1, 26,3, -1, -1, -1, 0);          //Horizontal inferior
            
                                                       //2º FIGURA DE MUROS                                                    
                          //BASE
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,2, 27,7, -1, -1, 0, 0);           //Horizontal superior izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,9, 27,12, -1, -1, -1, 0);         //Horizontal superior derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,2, 29,2, -1, -1, 0, -1);          //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 29,2, 29,12, -1, -1, -1, 0);         //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,12, 29,12, -1, -1, 0, -1);        //Vertical derecha
                          //SALIENTE
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,7, 24,9, -1, -1, -1, 0);          //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,7, 27,7, -1, -1, 0, -1);          //Vertical izquierda  
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,9, 27,9, -1, -1, 0, -1);          //Vertical derecha
                                                       
                                                       //3º FIGURA DE MUROS
                           //BASE
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,10, 24,18, -1, -1, 0, 0);         //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,10, 26,10, -1, -1, 0, -1);        //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,10, 26,13, -1, -1, -1, 0);        //Horizontal inferior izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,15, 26,18, -1, -1, -1, 0);        //Horizontal inferior derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,18, 26,18, -1, -1, 0, -1);        //Vertical derecha
                          //SALIENTE
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,13, 29,13, -1, -1, 0, -1);        //Vertical izquierda  
            pintarMuro(contextoGrafico, 1, Color.cyan, 29,13, 29,15, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,15, 29,15, -1, -1, 0, -1);        //Vertical derecha     
                                                       
                                                       //4º FIGURA DE MUROS
                           //BASE
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,16, 27,19, -1, -1, 0, 0);         //Horizontal superior izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,21, 27,26, -1, -1, -1, 0);        //Horizontal superior derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,16, 29,16, -1, -1, 0, -1);        //Vertical izquierda
            pintarMuro(contextoGrafico, 1, Color.cyan, 29,16, 29,26, -1, -1, -1, 0);        //Horizontal inferior
            pintarMuro(contextoGrafico, 1, Color.cyan, 27,26, 29,26, -1, -1, 0, -1);        //Vertical derecha
                          //SALIENTE
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,19, 24,21, -1, -1, -1, 0);        //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,19, 27,19, -1, -1, 0, -1);        //Vertical izquierda  
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,21, 27,21, -1, -1, 0, -1);        //Vertical derecha                                         
                                                       
                                                       //5º FIGURA DE MUROS
                                                    
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,25, 24,27, -1, -1, 0, 0);           //Horizontal superior
            pintarMuro(contextoGrafico, 1, Color.cyan, 24,25, 26,25, -1, -1, 0, -1);          //vertical derecha
            pintarMuro(contextoGrafico, 1, Color.cyan, 26,25, 26,27, -1, -1, -1, 0);          //Horizontal inferior                                       
                                                       
                                                       
            /*
             * Ahora rellenaremos nuestro tablero gráfico con los puntos que comera Pacman. Revisaremos nuestra matriz de la clase Tablero y en las posiciones
             * que haya un 1, pondremos un punto pequeño en la casilla correspondiente, y si hay un 2, un punto grande.
             */                             
            
            for(int i=0; i<tablero.getFilas(); i++){
                for(int j=0; j<tablero.getColumnas(); j++){
                    
                    if(tablero.getTablero(i,j)==1){
                        centrarPequenio(contextoGrafico, i, j);
                    }
                    
                    if(tablero.getTablero(i,j)==2){
                        centrarGrande(contextoGrafico, i, j);
                    }
                } 
            }
   }
}