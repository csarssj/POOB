    package Rainfall;

    import Shapes.*;
    import java.util.*;
    import java.awt.Color;
    import java.awt.*;
    import java.awt.Graphics;
    import javax.swing.JOptionPane;

    /**
     * Clase valle en donde se encuentra viñedos juntos sus lonas
     * 
     * @author César Eduardo González y Brayan Santiango Buitrago 
     * @version 1/10/2019
     */
    public class Valley
    {
    private int maxY; 
    private int maxX;
    private int x;
    private ArrayList<Trap> lonas= new ArrayList<Trap>();
    private Hashtable<String,VineYard> vinedos=new Hashtable<String,VineYard>();
    private ArrayList<String> nombres = new ArrayList<String>();
    private ArrayList<String> colores =new ArrayList<String>(Arrays.asList("red","gold","fire","golden","chocolate","lightgreen","gray","cyan","yellowdark","peru","blue","yellow","green","magenta","black","tomamo","orange"));
    private ArrayList<Rain> lluvias = new ArrayList<Rain>();
    private boolean ok;
    private boolean isVisible;
    private String ultimaAccion = "";
    private ArrayList ultObj = new ArrayList();
    /**
     * Constructor para objetos de la clase Valle
     */
    public Valley()
    {
        this.maxX = 100;
        this.maxY = 100;
        Shapes.Canvas canvas = new Shapes.Canvas("New", this.maxX,this.maxY,Color.white);
        canvas.getCanvas(maxX,maxY);
        ok = true;
        isVisible = false;
    }
    /**
     * Constructor para objetos de la clase Valle
     * @param  int maxX para el largo del tablero
     * @param  int maxY para el alto del tablero
     */
    public Valley(int maxX,int maxY)
    {
        this.maxX = maxX;
        this.maxY = maxY;
        Shapes.Canvas canvas = new Shapes.Canvas("New", this.maxX,this.maxY,Color.white);
        canvas.getCanvas(maxX,maxY);
        ok = true;
        isVisible = false;
    }

    /**
     * Crea un viñedo 
     * @param name nombre del viñedo
     * @param xi posición x en donde empieza el viñedo
     * @param xf posición x en donde termina el viñedo 
     */
    public void openVineYard(String name,int xi,int xf)
    {
        boolean condicion = true; 
        for(String  n: nombres){
            int pos []=vinedos.get(n).getPos();
            if((xi >= pos[0] && xf <= pos[1])||(pos[0] >= xi && pos[1] <= xf)
            ||((pos[0] >= xi || pos[1] >= xi) && pos[1] <= xf)||(pos[0] >= xi && (pos[0] <= xf && pos[1] >= xf))){
                JOptionPane.showMessageDialog(null,"El viñedo no se puede crear.");
                condicion = false;
                ok=false;
                break;
            }
        }
        if(condicion){
            vinedos.put(name,new VineYard(name,xi,xf,colores.get(0),maxY));
            nombres.add(name);
            ultimaAccion = "openVineyard";
            ultObj = new ArrayList();
            ultObj.add(name);
            ultObj.add(xi);
            ultObj.add(xf);
            ultObj.add(colores.get(0));
            ultObj.add(maxY);
            colores.remove(colores.remove(0));
            actualizar();
            if(isVisible){
                vinedos.get(name).makeVisible();
            }
            ok=true;
        }
    }
    /**
     * Elimina un viñedo
     * @param nombre el nombre del viñedo a eliminar
     */
    public void closeVineYard(String name)
    {
        if (vinedos.get(name)==null){
                JOptionPane.showMessageDialog(null,"El viñedo que esta intentando eliminar no existe.");
                ok=false;
            }
        else{
                ultimaAccion = "closeVineyard";
                ultObj = new ArrayList();
                ultObj.add(name);
                ultObj.add(vinedos.get(name).getPos()[0]);
                ultObj.add(vinedos.get(name).getPos()[1]);
                ultObj.add(vinedos.get(name).getColor());
                ultObj.add(this.maxY);
                vinedos.get(name).makeInvisible();
                vinedos.remove(name);
                nombres.remove(name);
                actualizar();
                ok=true;
        }
        
    }
    /**
     * Crea una lona
     * @param name nombre del viñedo
     * @param ini punto en donde empieza la lona
     * @param fin punto en donde termina la lona 
     */
    public void addTrap(int[] ini,int[] fin)
    {
        lonas.add(new Trap(ini,fin));
        ultimaAccion = "addTrap";
        ultObj = new ArrayList();
        ultObj.add(ini);
        ultObj.add(fin);
        if(isVisible){
            lonas.get(lonas.size()-1).makeVisible();
        }
        actualizar();
        ok=true;
    }
    /**
     * Elimina un viñedo
     * @param nombre el nombre del viñedo a eliminar
     */
    public void removeTrap(int position)
    {
        if (lonas.get(position)==null){
                JOptionPane.showMessageDialog(null,"La lona que esta intentando eliminar no existe.");
                ok=false;
            }
        else{
                ultimaAccion = "removeTrap";
                ultObj = new ArrayList();
                ultObj.add(lonas.get(position).getPos()[0]);
                ultObj.add(lonas.get(position).getPos()[1]);    
                lonas.get(position).makeInvisible();
                lonas.remove(position);
                actualizar();
                ok=true;
        }
        
    }
    /**
     * Hace visible el valle junto con todos sus objetos (viñedos,lonas ,lluvia, etc). Si esta visible no hace nada.
     */
    public void makeVisible(){
        isVisible=true;
        for(int i=0; i<vinedos.size();i++){
            vinedos.get(nombres.get(i)).makeVisible();
        }
        for(int i=0; i< lonas.size();i++){
            lonas.get(i).makeVisible();
            for(int j=0; i<lonas.get(i).huecos.size();j++){
                lonas.get(i).huecos.get(j).makeVisible();
            }
        }
        
        for(int i=0;i<lluvias.size();i++){
            lluvias.get(i).makeVisible();
        }
        ultimaAccion="makeVisible";
        ok = true;
    }
    /**
     * Hace los huecos de las lonas en un punto idicado
     * @param trap la lona a la cual se le va a hacer el hueco
     * @param x que es el punto en x donde se hace el hueco en la lona especificada
     */
    public void makePuncture(int trap, int x){
        lonas.get(trap-1).makePuncture(x);
        ultimaAccion = "makePuncture";
        ultObj = new ArrayList();
        ultObj.add(trap);
        ultObj.add(x);
        ArrayList<Puncture> huecos = lonas.get(trap-1).getHuecos();
        if(isVisible){
            huecos.get(huecos.size()-1).makeVisible();
        }
        ok=true;
    }
    /**
     * Tapa el hueco especificado de una lona
     * @param trap la lona a la cual se le va a tapar el hueco
     * @param x es el punto en x donde se tapa el hueco de la lona especificada
     */
    public void patchPuncture(int trap, int x){
        ultimaAccion = "patchPuncture";
        ultObj = new ArrayList();
        ultObj.add(trap);
        ultObj.add(x);
        lonas.get(trap-1).patchPuncture(x);
        ok=true;
    }
    /**
     * Hace invisible el valle junto con todos sus objetos (viñedos,lonas ,lluvia, etc). Si esta invisible no hace nada.
     */
    public void makeInvisible(){
        isVisible=false;
        for(int i=0; i<vinedos.size();i++){
            vinedos.get(nombres.get(i)).makeInvisible();
        }
        for(int i=0; i< lonas.size();i++){
            lonas.get(i).makeInvisible();
            for(int j=0; i<lonas.get(i).huecos.size();j++){
                lonas.get(i).huecos.get(j).makeInvisible();
            }
        }
        
        for(int i=0;i<lluvias.size();i++){
            lluvias.get(i).makeInvisible();
        }
        ultimaAccion="makeInvisible";
        ok = true;
    }
    /**
     * Empieza la lluvia en un punto dado
     * @param x el punto donde empieza la lluvia
     */
    public void startRain(int x){
        Rain lluvia = new Rain(x, maxY, lonas);
        lluvias.add(lluvia);
        ultimaAccion = "startRain";
        ultObj = new ArrayList();
        ultObj.add(x);
        if(isVisible){
            lluvia.makeVisible();
        }
        ok = true;
    }
    /**
     * Para la lluvia en un punto dado
     * @param x el punto donde queremos parar la lluvia
     */
    public void stopRain(int x){
        int a=0;
        for(int i = 0; i<lluvias.size();i++){
            if(lluvias.get(i).getX() == x){
                lluvias.get(i).makeInvisible();
                a=i;
            }
        }
        ultimaAccion = "stopRain";
        ultObj = new ArrayList();
        ultObj.add(x);
        lluvias.remove(a);
        ok=true;
    }
    /**
     * Hacer zoom para aumentar o disminuir el tamaño del valle
     * @param c char que puede ser "+" para aumentar o "-" para disminuir
     */
    public void zoom(char c){
        if(c =='+'){
            for(int i=0; i<vinedos.size();i++){
                vinedos.get(nombres.get(i)).changeSize1();
            }
            for(int i=0; i< lonas.size();i++){
                lonas.get(i).changeSize1();
                if(lonas.get(i).huecos.size()!=0){
                    for(int j=0; i<lonas.get(i).huecos.size();j++){
                        lonas.get(i).huecos.get(j).changeSize1();
                    }
                }
            }
            
            for(int i=0;i<lluvias.size();i++){
                lluvias.get(i).changeSize1();
            }
            ultimaAccion = "zoom";
            ultObj = new ArrayList();
            ultObj.add(c);
        }
        else{
            for(int i=0; i<vinedos.size();i++){
                vinedos.get(nombres.get(i)).changeSize2();
            }
            for(int i=0; i< lonas.size();i++){
                lonas.get(i).changeSize2();
                if(lonas.get(i).huecos.size()!=0){
                    for(int j=0; i<lonas.get(i).huecos.size();j++){
                        lonas.get(i).huecos.get(j).changeSize2();
                    }
                }
            }
            
            for(int i=0;i<lluvias.size();i++){
                lluvias.get(i).changeSize2();
            }
            ultimaAccion = "zoom";
            ultObj = new ArrayList();
            ultObj.add(c);
        }
        ok=true;
    }
    
    /**
     * Revisa si la acción de un metodo se realizo correctamente o no. 
     * @return boolean ok true si se realizo correctamente la acción false dlc. 
     */
    public boolean ok(){
        return ok;
    }
    /**
     * Termina el programa eliminando el valle y todo lo que contenga dentro. 
     */
    public void finish(){
        System.exit(0);
    }
    /**
     * Se encarga de actualizar el color de las lonas deacuerdo al viñedo que cubren
     */
    private void actualizar(){
        String newColor = "black";
        for(Trap t: lonas){
            int [][] posLon = t.getPos();
            boolean condicion = false;;
            for(String v: nombres){
                int [] posVin = vinedos.get(v).getPos();
                if(posLon[0][0]<posVin[0] && posLon[0][0]<posVin[1] && posLon[1][0]>posVin[0] && posLon[1][0]>posVin[1]){
                    newColor=vinedos.get(v).getColor();
                    condicion = true;
                }
            
            }
            if(condicion){
                if (isVisible){
                    t.changeColor(newColor);
                    t.makeVisible();
                }
            }else if(!condicion){
                if (isVisible && (t.getColor()!="black")){
                    
                    t.changeColor("black");
                    t.makeVisible();
                }
            }
            }
    }
    /**
     * Rehace o Deshace la ultima accion realizada
     * @param d si es "U" deshace la última acción y si es "R" rehace la última acción,
     */
    public void _do(char d){
        if(d=='U' || d== 'R'){
            if (ultimaAccion.equals("openVineyard")){
                closeVineYard((String)ultObj.get(0));
            }else if(ultimaAccion.equals("closeVineyard")){
                openVineYard((String)ultObj.get(0),(int)ultObj.get(1),(int)ultObj.get(2));
            }else if(ultimaAccion.equals("addTrap")){
                removeTrap(lonas.size()-1);
            }else if(ultimaAccion.equals("removeTrap")){
                addTrap((int[])ultObj.get(0),(int[])ultObj.get(1));
            }else if(ultimaAccion.equals("startRain")){
                stopRain((int)ultObj.get(0));
            }
            else if(ultimaAccion.equals("stopRain")){
                startRain((int)ultObj.get(0));
            }
            else if(ultimaAccion.equals("makePuncture")){
                patchPuncture((int)ultObj.get(0),(int)ultObj.get(1));
            }
            else if(ultimaAccion.equals("patchPuncture")){
                makePuncture((int)ultObj.get(0),(int)ultObj.get(1));
            }
            else if(ultimaAccion.equals("zoom")){
                if((char)ultObj.get(0)=='+'){
                    zoom('-');
                }else{
                    zoom('+');
                }
            }
            else if(ultimaAccion.equals("makeVisible")){
                makeInvisible();
            }
            else if(ultimaAccion.equals("makeInvisible")){
                makeVisible();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Ingreso el caracter incorrecto.");
        }
    }
    /**
     * Consulta los viñedos que pueden ser mojados por la lluvia
     * @returns mojados ArrayList de los viñedos mojados
     */
    public void rainsfall(){
        ArrayList<String> mojados = new ArrayList<String>();
        for(Rain r: lluvias){
            for(Gota g: r.getGotas()){
                if (g.getPos()[1]>=(maxY-30)){
                    for(String v: nombres){
                        if(vinedos.get(v).getPos()[0]<=g.getPos()[0] && vinedos.get(v).getPos()[1]>=g.getPos()[0]){
                            mojados.add(v);
                        }
                    }
                }
            }
        }
        for(String s: mojados){
            System.out.println(s);
        }
        
    }
}   
