package persistencia;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import aplicacion.*;
import java.awt.Color;

public class donkeyDAO {
	public donkeyDAO(){
		
	}
	/*
	public void exporte(DonkeyPoob DonkeyPoob,File file) throws DonkeyException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new DonkeyException(DonkeyException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new DonkeyException("No se encuentra el archivo " + file.getName());
		}
		int size = DonkeyPoob.numeroEnEscena();
		for(int i=0;i<size;i++){
			String temp = DonkeyPoob.getElementos().get(i).toString();
			out.println(DonkeyPoob.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
		
	}*/
	/**
     * Exporta el donkeypoob desde un archivo con extension (.txt)
	 * @param game El juego donkeypoob que va a ser guardado.
	 * @param file El archivo donde se va a exportar el donkeypoob.
	 * @throws DonkeyException - TYPE_TXT_ERROR Si el archivo no tiene la extension .txt.
	 * @throws DonkeyException - Si no se encontro el archivo para exportar el donkeypoob.
    */
	/*public static void exportar(DonkeyPoob game,File file) throws DonkeyException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new DonkeyException(DonkeyException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new DonkeyException("No se encuentra el archivo " + file.getName());
		}
		out.println(game.getNivel()+","+game.getRandom()+","+game.getCpu());
		out.println(game.getBola().getClass().getSimpleName()+","+game.getBola().toString());
		for(int i=0;i<game.getSorprisesAmount();i++){
			Sorpresa sorpresa = game.getSorprise(i);
			out.println(sorpresa.getClass().getSimpleName()+","+sorpresa.toString());
		}
		for(int i=0;i<game.getBlocksAmount();i++){
			Bloque bloque = game.getBloque(i);
			out.println(bloque.getClass().getSimpleName()+","+bloque.toString());
		}
		for(int i=0;i<game.getPlayersAmount();i++){
			Jugador jugador = game.getJugador(i);
			Plataforma plataforma = jugador.getPlataforma();
			out.println(jugador.getClass().getSimpleName()+","+jugador.toString());
			out.println(plataforma.getClass().getSimpleName()+","+plataforma.toString());
		}
		out.close();
	}
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
	private void checkLength(int size , String[] line) throws DonkeyException{
		if(line.length!=size) {
			throw new DonkeyException(DonkeyException.SIZE_ERROR);
		}
	}
	/**
     * Importa el donkeypoob desde un archivo con extension (.txt)
	 * @param file El archivo donde se va a importar el donkeypoob.
	 * @return El juego obtenido del archivo txt.
	 * @throws DonkeyException - TYPE_TXT_ERROR Si el archivo no tiene la extension .txt.
	 * @throws DonkeyException - NO_TEXT_FOUND Si el archivo esta vacio.
	 * @throws DonkeyException - Si el archivo no cumple con los requerimientos.
	 * @throws DonkeyException - Si el archivo tiene un error de escritura.
	 * @throws DonkeyException - Si el archivo tiene una clase desconocida.
	 * @throws DonkeyException - Si ocurrio un error al importar.
    */
	/*public static DonkeyPoob importar(File file) throws DonkeyException{
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
						DonkeyPoob.nuevoDonkey(Boolean.parseBoolean(linea[1]),Boolean.parseBoolean(linea[2]));
						game = game.deme(Boolean.parseBoolean(linea[1]),Boolean.parseBoolean(linea[2]));
						game.setNivel(Integer.parseInt(linea[0]));
					}catch(Exception e) {
						throw new DonkeyException("No cumple los requerimientos de un archivo donkeypoob");
					}
				}
				else{
					if(linea[0].startsWith("Bloque")){
						checkLength(6,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class , int.class , int.class , int.class ).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),Integer.parseInt(linea[5]));
							game.addBloque((Bloque)o);
						} catch(NumberFormatException e) {
							throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].equals("Jugador")){
						checkLength(4,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(String.class , int.class , int.class).newInstance(linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
							jugador = (Jugador)o;
							game.addJugador(jugador);
						} catch(NumberFormatException e) {
							throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].equals("Mimo")||linea[0].equals("Destructor")||linea[0].equals("Curioso")){
						checkLength(4,linea);
						try {
							game.addCPU(linea[1],game.getCpuType(),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
						} catch(NumberFormatException e) {
							throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
						}
					}
					else if(linea[0].startsWith("Plataforma")){
						checkLength(6,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class , int.class , int.class,String.class).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),linea[5]);
							jugador.setPlataforma((Plataforma)o);
						} catch(NumberFormatException e) {
							throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].startsWith("Sorpresa")){
						checkLength(3,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]));
							game.addSorpresa((Sorpresa)o);
						} catch(NumberFormatException e) {
							throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].equals("Bola")){
						checkLength(5,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class , int.class , int.class).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));
							game.setBola((Bola)o);
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
	public DonkeyPoob importe03(File file) throws DonkeyException{
		int i = 1;
		DonkeyPoob DonkeyPoob = null;
		if (!file.getName().endsWith(".txt")) throw new DonkeyException(DonkeyException.TYPE_TXT_ERROR);
		try {
			DonkeyPoob.nuevoDonkeyPoob();
			DonkeyPoob = DonkeyPoob.demeDonkeyPoob();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if(line==null) {
				throw new DonkeyException(DonkeyException.NO_TEXT_FOUND);
			}
			while (line!=null){
				line = line.trim();
				if(line.equals("")){continue;}
				String[] linea = line.split(" ");
				if(linea[0].equals("Actor") || linea[0].equals("ActorBailarin") || linea[0].equals("ActorPerezoso") || linea[0].equals("ActorNecio")){
					checkLength(4,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						//System.out.println(linea[1]+" "+linea[2]+" "+linea[3]);
						Object o = c.getDeclaredConstructor(DonkeyPoob.class , String.class , int.class , int.class).newInstance(DonkeyPoob,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
					}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {	
						throw new DonkeyException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					}
				}
				else if(linea[0].equals("Cortinas")){
					checkLength(3,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						//System.out.println(linea[1]+" "+linea[2]);
						Object o = c.getDeclaredConstructor(DonkeyPoob.class ,String.class,int.class).newInstance(DonkeyPoob,linea[1],Integer.parseInt(linea[2]));
					} catch(NumberFormatException e) {
						throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new DonkeyException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} 
				}
				else if(linea[0].equals("Luz")){
					checkLength(8,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						//System.out.println(linea[1]+" "+linea[2]+" "+linea[3]+" "+linea[4]+" "+linea[5]+" "+linea[6]+" "+linea[7]);
						Object o = c.getDeclaredConstructor(DonkeyPoob.class ,String.class , int.class , int.class , int.class, int.class , int.class , int.class ).newInstance(DonkeyPoob ,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),Integer.parseInt(linea[5]),Integer.parseInt(linea[6]),Integer.parseInt(linea[7]));
					} catch(NumberFormatException e) {
						throw new DonkeyException("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new DonkeyException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} 
				}
				else {
					throw new DonkeyException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
				}
				line = in.readLine();
				i+=1;
			}
			in.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
			throw new DonkeyException("Ocurrio un error al importar " + file.getName());
		}
		return DonkeyPoob;
	}*/
}