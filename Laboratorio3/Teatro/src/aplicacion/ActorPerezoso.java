package aplicacion;


import java.awt.Color;

/**
 * Clase actor perezoso que es extendiende a actor
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class ActorPerezoso extends Actor
{
    // instance variables - replace the example below with your own
    private int x;
    private Color color;
    /**
     * Constructor del Actor perezoso
     * @param teatro en donde se trabaja
     * @param nombre del actor
     * @param  posicionx del actor perezoso
     * @param  posicionxy del actor perezoso
     */
    public ActorPerezoso(Teatro teatro,String name,int posicionx, int posiciony)
    {
        super( teatro, name, posicionx,  posiciony);
        super.setColor(Color.GREEN);
        x = 0;
    }
    @Override
    public void corte(){
        muevaBrazo('I','B'); 
        muevaBrazo('I','B');
        muevaPierna('I','S');
        muevaPierna('I','S');
        muevaBrazo('D','B');
        muevaBrazo('D','B');
        muevaPierna('D','S');    
        muevaPierna('D','S');       
        palabras="";
    }
    @Override
    public void actue(){
      
        if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } else if  (getPosicionBrazo('I')==FRENTE){
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } else if (getPosicionBrazo('I')==ARRIBA){
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
        }else if (getPosicionBrazo('D')==FRENTE){
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
        }else if (getPosicionBrazo('D')==ARRIBA){
            muevaBrazo('D','B'); 
            muevaPierna('D','B');
            muevaBrazo('D','B'); 
            muevaPierna('D','B');
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } 
        palabras="Soy " + this;
    }
    @Override
    public void decida(){
        corte();
    }
}
