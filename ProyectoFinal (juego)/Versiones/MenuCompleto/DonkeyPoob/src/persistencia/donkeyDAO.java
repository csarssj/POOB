package persistencia;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import aplicacion.*;
import java.awt.Color;

public class donkeyDAO {

	/**
	 * Clase que ejecuta controla la relacion entre archivos y el Donkeypoob.
	 * @author: Cesar Gonzalez y Brayan Buitrago
	 * @version: 9/12/2019
	*/
	public donkeyDAO(){
		
	}
	public static String nombre="DonkeyPoob";
	/**
     * Abre un nuevo DonkeyPoob desde un archivo con extension (.dat)
	 * @param file El archivo donde se va a abrir el DonkeyPoob.
     * @return El nuevo DonkeyPoob del archivo.
	 * @throws DonkeyException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .dat.
	 * @throws DonkeyException Si ocurrio un error al deserializar el archivo.
    */
	public static DonkeyPoob open(File file) throws DonkeyException{	
		DonkeyPoob DonkeyPoob = null;
		if (!file.getName().endsWith(".dat")) throw new DonkeyException(DonkeyException.TYPE_DAT_ERROR);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			DonkeyPoob = (DonkeyPoob) in.readObject();
			in.close();
		}catch(ClassNotFoundException e) {
			throw new DonkeyException(DonkeyException.FILE_NOT_FOUND_ERROR);
		}catch (IOException e) {
			throw new DonkeyException("Ocurrio un error al abrir el archivo" + file.getName());
		}
		return DonkeyPoob;
	}
	/**
     * Guarda el DonkeyPoob desde un archivo con extension (.dat)
	 * @param game El juego DonkeyPoob que va a ser guardado.
	 * @param file El archivo donde se va a guardar el DonkeyPoob.
	 * @throws DonkeyException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .dat.
	 * @throws DonkeyException - FILE_NOT_FOUND_ERROR Si no se encontro el archivo para guardar el DonkeyPoob.
	 * @throws DonkeyException Si ocurrio un error al serializar el archivo.
    */
	public static void save(DonkeyPoob DonkeyPoob, File file) throws DonkeyException{
		if (!file.getName().endsWith(".dat")) throw new DonkeyException(DonkeyException.TYPE_DAT_ERROR);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(DonkeyPoob);
			out.close();
		}catch (IOException e) {
			throw new DonkeyException("Ocurrio un error al salvar " + file.getName());
		}
	}
	/**
     * Importa el DonkeyPoob desde un archivo con extension (.txt)
	 * @param file El archivo donde se va a importar el DonkeyPoob.
	 * @return El juego obtenido del archivo txt.
	 * @throws DonkeyException - TYPE_TXT_ERROR Si el archivo no tiene la extension .txt.
	 * @throws DonkeyException - NO_TEXT_FOUND Si el archivo esta vacio.
	 * @throws DonkeyException - Si el archivo no cumple con los requerimientos.
	 * @throws DonkeyException - Si el archivo tiene un error de escritura.
	 * @throws DonkeyException - Si el archivo tiene una clase desconocida.
	 * @throws DonkeyException - Si ocurrio un error al importar.
    */
	public static DonkeyPoob importar(File file) throws DonkeyException{
		int i = 1;
		DonkeyPoob game = null;
		Jugador jugador = null;
		if (!file.getName().endsWith(".txt")) throw new DonkeyException(DonkeyException.TYPE_TXT_ERROR);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if(line==null) {
				throw new DonkeyException(DonkeyException.NO_TEXT_FOUND);
			}
			while (line!=null){
				line = line.trim();
				if(line.equals("")){continue;}
				String[] linea = line.split(",");
				if(i==1){
					try{
						DonkeyPoob.newDonkey(linea[0]);
						game = game.deme(linea[0]);
					}catch(Exception e) {
						throw new DonkeyException("No cumple los requerimientos de un archivo ");
					}
				}
				else{
					if(linea[0].startsWith("Cereza")||linea[0].startsWith("Manzana")||linea[0].startsWith("Martillo")||linea[0].startsWith("Plataforma")||linea[0].startsWith("Escalera")||linea[0].startsWith("Hongo")||linea[0].startsWith("Soga")||linea[0].startsWith("Corazon")||linea[0].startsWith("Peach")||linea[0].startsWith("DonkeyKong")){
						checkLength(5,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class,int.class , int.class).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));
							game.addStaticObject((ObjetoEstatico)o);
						} catch(NumberFormatException e) {
							throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else {
						throw new DonkeyException("Error : En la linea "+i+" la clase "+linea[0]+" no es una clase definida");
					}
				}
				line = in.readLine();
				i+=1;
			}
			in.close();
		}catch (IOException e) {
			throw new DonkeyException("Ocurrio un error al importar " + file.getName());
		}
		return game;
	}
	/**
     * Verifica si la longitud de una linea es la correcta.
	 * @param size El tamaño correcto de la linea de texto.
	 * @param line La linea de texto.
	 * @throws DonkeyException - SIZE_ERROR Si la longitud de una linea es incorrecta.
    */
	private static void checkLength(int size , String[] line) throws DonkeyException{
		if(line.length!=size) {
			throw new DonkeyException(DonkeyException.SIZE_ERROR);
		}
	}
}