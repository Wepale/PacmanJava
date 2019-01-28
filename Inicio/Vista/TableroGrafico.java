package Inicio.Vista;
import Inicio.Modelo.*;
import Inicio.Controlador.ControladorFantasmas;
import Inicio.Controlador.ControladorPacman;
import Inicio.Controlador.Marcador;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
/**
 * Esta clase será un JPanel. Será la encargada de realizar toda la animación del juego.
 * 
 * @author Yeray Rodríguez Martín 
 * @version 1.0
 */
public class TableroGrafico extends JPanel
{
    private final int VELOCIDAD=5;                              //Velocidad del juego
    private final Dimension d =
    Toolkit.getDefaultToolkit().getScreenSize();                //Para hayar el tamaño de la pantalla independientemente del S.O.
    private final int ANCHO=d.width;                            //El ancho de la pantalla
    private final int ALTO=d.height;                            //El alto de la pantalla
    private final int DURACION_PROTECCION=5000;                 //El tiempo que durará la protección (5 segundos con la velocidad del juego a 5ms)
    private final int DURACION_INICIO=2000;                     //El tiempo que durará la entradilla al juego(2 segundos con la velocidad del juego a 5ms)
    
    private AyudaDibujo dibujar;                                //Nuestra clase de apoyo para dibujar
    private Tablero tablero;                                    //Nuestro tablero matemático
    private Blinky blinky;                                      //El fantasma Blinky
    private Pinky pinky;                                        //El fantasma Pinky
    private Clyde clyde;                                        //El fantasma Clyde
    private PacMan pacman;                                      //Pacman
    private Marcador marcador;                                  //El marcador el juego
    
    private Timer timerJuego;                                   //El timer que controlara el juego
    private Timer timerPausa;                                   //El timer que controlara la pausa
    private Timer timerInicio;                                  //El timer para controlar la imagen de entrada al juego
    private Timer timerGameOver;                                //El timer que se activará si el usuario pierde la partida
    private Timer timerGanador;                                 //El timer que se activará si el usuario gana la partida
    
    private boolean proteccion;                                 //La bandera que indicara si la proteccion esta activada
    private boolean colisionBlinky;                             //La bandera que indica si Blinky sufre una colisión con otro fantasma
    private boolean colisionPinky;                              //La bandera que indica si Pinky sufre una colisión con otro fantasma
    private boolean colisionClyde;                              //La bandera que indica si Clyde sufre una colisión con otro fantasma
   
 
    private Image imagenIntro;                                  //La imagen de inicio
    private Image imagenGameOver;                               //La imagen del fin del juego
    private Image imagenBlinky;                                 //La imagen de Blinky
    private Image imagenPinky;                                  //La imagen de Pinky
    private Image imagenClyde;                                  //La imagen de Clyde
    private Image imagenPacman;                                 //La imagen de Pacman
    
    
    private ControladorFantasmas controladorBlinky;             //El controlador del fantasma Blinky
    private ControladorFantasmas controladorPinky;              //El controlador del fantasma Pinky
    private ControladorFantasmas controladorClyde;              //El controlador del fantasma Clyde
    private ControladorPacman controladorPacman;                //El controlador de Pacman
    
    private int pixelBlinkyX;                                   //El pixel en el eje X que ocupará Blinky
    private int pixelBlinkyY;                                   //El pixel en el eje X que ocupará Blinky
    
    private int pixelPinkyX;                                    //El pixel en el eje X que ocupará Pinky
    private int pixelPinkyY;                                    //El pixel en el eje Y que ocupará Pinky
    
    private int pixelClydeX;                                    //El pixel en el eje X que ocupará Blinky
    private int pixelClydeY;                                    //El pixel en el eje X que ocupará Blinky
    
    private int pixelPacmanX;                                   //El pixel en el eje X que ocupará Blinky
    private int pixelPacmanY;                                   //El pixel en el eje X que ocupará Blinky
    
    private int contadorGameOver;
    private int duracionProteccion;                             //Para controlar el tiempo que la protección está activa
    private int duracionInicio;                                 //Para controlar el tiempo que durará el inicio  
    
    /**
     * Contructor.
     * Propiamente dicho, todo esta labor la realiza el metodo constructor()
     */
    public TableroGrafico(){
        addKeyListener(new TAdapter());
        constructor();
   }
   
   /**
    * Inicilazacion y creacion de todos los compentes del juego, los cuales estaran contenidos en el jPanel
    */
  public void constructor()
  {
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        dibujar = new AyudaDibujo();
        tablero = new Tablero();
        blinky = new Blinky();
        pinky = new Pinky();
        clyde = new Clyde();
        pacman = new PacMan();
        controladorBlinky = new ControladorFantasmas();
        controladorPinky = new ControladorFantasmas();
        controladorClyde= new ControladorFantasmas();
        controladorPacman= new ControladorPacman();
        marcador= new Marcador();
           
        proteccion=false;
        duracionProteccion=0;
        duracionInicio=0;
        
        imagenIntro= new ImageIcon(getClass().getResource("pacmanIntro.gif")).getImage();
        imagenGameOver= new ImageIcon(getClass().getResource("gameover.png")).getImage();
        
        timerJuego= new Timer (VELOCIDAD, new MoverJuego());
        timerInicio= new Timer(VELOCIDAD, new Inicio());
        timerPausa= new Timer (VELOCIDAD, new Neutral());
        timerGameOver= new Timer(VELOCIDAD, new Neutral());
        timerGanador= new Timer(VELOCIDAD, new Neutral());
        timerInicio.start();
        
  }
  
    /**
     * Este método sera ejecutado cada vez que queramos actualizar nuestra vista del juego.
     */
  public void paint(Graphics g){
        super.paint(g);
        Graphics2D contextoGrafico= (Graphics2D) g;
           
        RenderingHints rh=new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                                RenderingHints.VALUE_ANTIALIAS_ON);
          
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        contextoGrafico.setRenderingHints(rh);
        //Pintamos el juego 
        if(timerJuego.isRunning()){
            //Pintamos el marcador y la información de pausa
            contextoGrafico.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 50));
            contextoGrafico.setColor(Color.YELLOW);
            contextoGrafico.drawString("Score", ANCHO/9, ALTO/7);
            contextoGrafico.drawString(""+marcador.getPuntos(), ANCHO/9, ALTO/4);
            contextoGrafico.setFont(new Font("SERIF", Font.BOLD, 18));
            contextoGrafico.drawString("Pausa: \"P\"", ANCHO/2+ANCHO/3, ALTO/8);
            //Pintamos el laberinto
            dibujar.pintarLaberinto(contextoGrafico, tablero);
            //Pintamos a Blinky
            if(!proteccion){
                imagenBlinky= blinky.getImagen();
            }else{
                imagenBlinky= blinky.getImagenAzul();
            }
            contextoGrafico.drawImage(imagenBlinky, pixelBlinkyX, pixelBlinkyY, this);
            //Pintamos a Pinky
            if(!proteccion){
                imagenPinky= pinky.getImagen();
            }else{
                imagenPinky= pinky.getImagenAzul();
            }
            contextoGrafico.drawImage(imagenPinky, pixelPinkyX, pixelPinkyY, this);
            //Pintamos a Clyde
            if(!proteccion){
                imagenClyde= clyde.getImagen();
            }else{
                imagenClyde= clyde.getImagenAzul();
            }
            contextoGrafico.drawImage(imagenClyde, pixelClydeX, pixelClydeY, this);
            //Pintamos a Pacman
            if(controladorPacman.getControladorImagen()==1){
                imagenPacman= pacman.getImagen();
            }else if(controladorPacman.getControladorImagen()==2){
                        imagenPacman= pacman.getImagenDerecha();
                  }else if(controladorPacman.getControladorImagen()==3){
                            imagenPacman= pacman.getImagenArriba();
                        }else{
                            imagenPacman= pacman.getImagenAbajo();
                        }
            contextoGrafico.drawImage(imagenPacman, pixelPacmanX, pixelPacmanY, this);
        }
        //Pintamos la imagen de la entradilla
        if(timerInicio.isRunning()){
            contextoGrafico.drawImage(imagenIntro, ANCHO/4+ ANCHO/15, ALTO/3, this);
        }
        //Pintamos la imagen de GameOver si el usuario a perdido
        if(timerGameOver.isRunning()){
            contextoGrafico.setColor(Color.YELLOW);
            contextoGrafico.drawImage(imagenGameOver, ANCHO/4, ALTO/11, this);
            contextoGrafico.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 18));
            contextoGrafico.drawString("Si deseas jugar otra partida, pulsa \"Y\"", ANCHO/3+20,ALTO/2);
        }
        //Pintamos las intruciones para voler al juego cuando lo pausamos
        if(timerPausa.isRunning()){
            contextoGrafico.setColor(Color.YELLOW);
            contextoGrafico.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 80));
            contextoGrafico.drawString("JUEGO PAUSADO", ANCHO/5,ALTO/4);
            contextoGrafico.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 20));
            contextoGrafico.drawString("Pulsa \"P\" para volver al juego", ANCHO/3, ALTO/2);
        }
        //Pintamos las felicitaciones si el usuario a ganado
        if(timerGanador.isRunning()){
            contextoGrafico.setColor(Color.YELLOW);
            contextoGrafico.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 40));
            contextoGrafico.drawString("¡Felicidades! ¡Ha superado el nivel!", ANCHO/4,ALTO/3);
            contextoGrafico.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 20));
            contextoGrafico.drawString("Si deseas jugar otra partida, pulsa \"Y\"", ANCHO/3,ALTO/2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
  }   
   
   /**
     * Clase interna que contendrá nuestro ActionListener principal, el cual se ocupará de realizar la animación del juego
     */
   private class MoverJuego implements ActionListener
   {
          public void actionPerformed(ActionEvent e)
          {   
                  requestFocus();
                  //Animación de Blinky
                  if(!controladorBlinky.getASalido()){
                        controladorBlinky.salirCasaDeFantasmas(blinky);
                  }else{
                     if(colisionBlinky){
                         controladorBlinky.cambiarDireccion(blinky);
                         if(controladorBlinky.getBuscarCasillaAnterior()){
                             colisionBlinky=false;
                         }
                     }else{
                         controladorBlinky.dibujarNextPosicion(blinky, pacman, tablero);
                     }
                  }
                  pixelBlinkyX=controladorBlinky.getPixelX();
                  pixelBlinkyY=controladorBlinky.getPixelY();
                  //Animación de Pinky
                  if(!controladorPinky.getASalido()){
                        controladorPinky.salirCasaDeFantasmas(pinky);
                  }else{
                      if(colisionPinky){
                          controladorPinky.cambiarDireccion(pinky);
                          colisionPinky= !controladorPinky.getBuscarCasillaAnterior();
                      }else{
                          controladorPinky.dibujarNextPosicion(pinky, pacman, tablero);
                      }
                  }
                  pixelPinkyX=controladorPinky.getPixelX();
                  pixelPinkyY=controladorPinky.getPixelY();
                  //Animación de Clyde
                  if(!controladorClyde.getASalido()){
                      controladorClyde.salirCasaDeFantasmas(clyde);
                  }else{
                      if(colisionClyde){
                          controladorClyde.cambiarDireccion(clyde);
                          colisionClyde=!controladorClyde.getBuscarCasillaAnterior();
                      }else{
                          controladorClyde.dibujarNextPosicionAleatoria(clyde, pacman, tablero);
                      }
                  }
                  pixelClydeX=controladorClyde.getPixelX();
                  pixelClydeY=controladorClyde.getPixelY();
                  //Animación de Pacman
                  controladorPacman.moverPacman(pacman);
                  pixelPacmanX=controladorPacman.getPixelX();
                  pixelPacmanY=controladorPacman.getPixelY();
                  if(marcador.bolaComida(controladorPacman, tablero)){
                       proteccion=true;
                       duracionProteccion=0;
                  }
                  //Colisiones de Pacman con los fantasmas
                  if(proteccion){
                    if(Colisiones.colisionPacmanFantasma(pacman, blinky)){
                        blinky.setPosicion(14,14);
                        marcador.sumarFantasma();
                        controladorBlinky.setASalido(false);
                        controladorBlinky.setControlCaja(true);
                        controladorBlinky.setControlMovimiento(true);
                        controladorBlinky.resetearDesplazamientoX();
                        controladorBlinky.resetearDesplazamientoY();
                    }
                    if(Colisiones.colisionPacmanFantasma(pacman, pinky)){
                        pinky.setPosicion(14,12);
                        marcador.sumarFantasma();
                        controladorPinky.setASalido(false);
                        controladorPinky.setControlCaja(true);
                        controladorPinky.setControlMovimiento(true);
                        controladorPinky.resetearDesplazamientoX();
                        controladorPinky.resetearDesplazamientoY();
                    }
                    if(Colisiones.colisionPacmanFantasma(pacman, clyde)){
                        clyde.setPosicion(14,15);
                        marcador.sumarFantasma();
                        controladorClyde.setASalido(false);
                        controladorClyde.setControlCaja(true);
                        controladorClyde.setPrimerMovimiento(true);
                        controladorClyde.setControlMovimiento(true);
                        controladorClyde.resetearDesplazamientoX();
                        controladorClyde.resetearDesplazamientoY();
                    }
                    duracionProteccion +=10;
                    if(duracionProteccion==DURACION_PROTECCION){           //Han pasado 5 segundos(con la velocidad del juego a 10)
                        proteccion=false;
                    }
                  }else{
                      if(Colisiones.colisionPacmamFantasma(pacman, blinky, pinky, clyde)){
                          timerJuego.stop();
                          timerGameOver.start();
                        }
                    } 
                  //Colisiones entre los fastasmas
                  if(Colisiones.colisionEntreFantasmas(blinky, pinky)){
                      colisionBlinky=true;
                      colisionPinky=true;
                      controladorBlinky.setBuscarCasillaAnterior(true);
                      controladorPinky.setBuscarCasillaAnterior(true);
                  }
                  if(Colisiones.colisionEntreFantasmas(blinky, clyde)){
                      colisionBlinky=true;
                      colisionClyde=true;
                      controladorBlinky.setBuscarCasillaAnterior(true);
                      controladorClyde.setBuscarCasillaAnterior(true);
                  }
                  if(Colisiones.colisionEntreFantasmas(pinky, clyde)){
                      colisionPinky=true;
                      colisionClyde=true;
                      controladorPinky.setBuscarCasillaAnterior(true);
                      controladorClyde.setBuscarCasillaAnterior(true);
                  }
                  
                  //Se comprueba si se ha ganado
                  if(marcador.getNumeroBolas()==00){
                      timerJuego.stop();
                      timerGanador.start();
                  }
                  repaint();
          }
   }
   
   /**
    * Clase interna que se encargara de capturar nuestras pulsaciones en el teclado
    */
   private class TAdapter extends KeyAdapter
   {
        public void keyPressed(KeyEvent e)
        {
            controladorPacman.keyPressed(e, pacman);
            int tecla= e.getKeyCode();
            
            if(tecla ==  KeyEvent.VK_P){
                if(timerJuego.isRunning()){
                    timerJuego.stop();
                    timerPausa.start();
                }else{
                    timerPausa.stop();
                    timerJuego.start();
                }
            }
            
            if(tecla == KeyEvent.VK_Y){
                if(timerGameOver.isRunning() || timerGanador.isRunning()){
                    timerGameOver.stop();
                    timerGanador.stop();
                    constructor();
                }
            }
        }
        
        public void keyReleased(KeyEvent e)
        {
            controladorPacman.keyReleased(e);
        }
   }
    
   /**
    * Clase interna que se encargara de controlar el inicio
    */
   private class Inicio implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        {
            duracionInicio +=10;
            if(duracionInicio==DURACION_INICIO){  //Cuando pasen 2 segundos (Siempre y cuando la velocidad del juego sea 10)
                timerInicio.stop();
                timerJuego.start();
            }
            repaint();
        }
   }
   /**
    * Clase interna que se encargara de controlar el fin del juego y la pausa 
    */
   private class Neutral implements ActionListener
   {
        public void actionPerformed(ActionEvent e)
        {
            repaint();
        }
   }
}
               


