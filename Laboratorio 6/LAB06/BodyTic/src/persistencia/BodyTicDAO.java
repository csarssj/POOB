package persistencia;

import java.io.*;
import java.lang.reflect.*;
import aplicacion.*;

public class BodyTicDAO{
	public BodyTicDAO(){

	}
	public void salve(Salon ac, File file) throws bodyTIcExcepcion {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ac);
			out.close();
		}catch (IOException e) {
			throw new bodyTIcExcepcion("Ocurrio un error al salvar " + file.getName());
		}
	}
	
	public void salve01(Salon ac, File file) throws bodyTIcExcepcion {
		if (!file.getName().endsWith(".dat")) throw new bodyTIcExcepcion("La extension no es \".dat\"");
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ac);
			out.close();
		}catch (IOException e) {
			throw new bodyTIcExcepcion("Ocurrio un error al salvar " + file.getName());
		}
	}
	
	public Salon abra01(File file) throws bodyTIcExcepcion{
		Salon auto = null;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			auto = (Salon)in.readObject();
			in.close();
		}catch(ClassNotFoundException e) {
			throw new bodyTIcExcepcion("Ocurrio un error al encontrar la clase");
		}catch(Exception e) {
			throw new bodyTIcExcepcion("Ocurrio un error al abrir " + file.getName());
		}
		return auto;
	}
	
	public Salon abra(File file) throws bodyTIcExcepcion{
		Salon auto = null;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			auto = (Salon)in.readObject();
			in.close();
		}catch(Exception e) {
			throw new bodyTIcExcepcion("Ocurrio un error al abrir " + file.getName());
		}
		return auto;
	}
	
	public void exporte01(Salon salon,File file) throws bodyTIcExcepcion{
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(file));
		}catch (FileNotFoundException e) {	
			throw new bodyTIcExcepcion("No se encontro el archivo " + file.getName());
		}
		System.setOut(out);
		for(int i=1;i<=salon.numeroEnSalon();i++){
			System.out.println(salon.deme(i).getClass().getSimpleName()+" " +salon.deme(i).getPosicionX()+" "+salon.deme(i).getPosicionY());
		}
		out.close();
	}
	
	public void exporte02(Salon salon,File file) throws bodyTIcExcepcion{
		if (!file.getName().endsWith(".txt")) throw new bodyTIcExcepcion("La extension no es \".txt\""); 
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(file));
		}catch (FileNotFoundException e) {	
			throw new bodyTIcExcepcion("No se encontro el archivo " + file.getName());
		}
		System.setOut(out);
		for(int i=1;i<=salon.numeroEnSalon();i++){
			System.out.println(salon.deme(i).getClass().getSimpleName()+" " +salon.deme(i).getPosicionX()+" "+salon.deme(i).getPosicionY());
		}
		out.close();
	}
	
	public void exporte03(Salon salon,File file) throws bodyTIcExcepcion{
		if (!file.getName().endsWith(".txt")) throw new bodyTIcExcepcion("La extension no es \".txt\""); 
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream(file));
		}catch (FileNotFoundException e) {	
			throw new bodyTIcExcepcion("No se encontro el archivo " + file.getName());
		}
		System.setOut(out);
		for(int i=1;i<=salon.numeroEnSalon();i++){
			System.out.println(salon.deme(i).getClass().getSimpleName()+" " +salon.deme(i).getPosicionX()+" "+salon.deme(i).getPosicionY());
		}
		out.close();
	}
	
	
	
	public void importe01(Salon ac,File file) throws bodyTIcExcepcion{
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String l = in.readLine();
			while(l!=null){
				String[] li = l.split(" ");
				if(li[0].equals("Deportista")) new Deportista (ac,"a",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("DeportistaAvanzado")) new DeportistaAvanzado (ac,"b",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("DeportistaHablador")) new DeportistaHablador (ac,"c",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("DeportistaPrincipiante")) new DeportistaPrincipiante (ac,"d",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("Bola")) new Bola (ac,"Soy una bola",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("Dado")) new Dado (ac,"Soy un dado",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				l = in.readLine();
			}
			in.close();

		}catch(IOException e ) {
			throw new bodyTIcExcepcion("Ocurrio un error al importar el archivo " + file.getName());
		}
	}
	
	public void importe02(Salon ac,File file) throws bodyTIcExcepcion{
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String l = in.readLine();
			int linea = 1;
			while(l!=null){
				String[] li = l.split(" ");
				try{
					Integer.parseInt(li[1]);
				}
				catch(NumberFormatException e){
					in.close();
					throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[1]+" no corresponde a un entero.");
				}
				try{
					Integer.parseInt(li[2]);
				}
				catch(NumberFormatException e){
					in.close();
					throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[2]+" no corresponde a un entero.");
				}
				if(Integer.parseInt(li[1]) < 0 || Integer.parseInt(li[1]) > 600) throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[1]+" no es una posicion valida");
				if(Integer.parseInt(li[2]) < 0 || Integer.parseInt(li[2]) > 635) throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[2]+" no es una posicion valida");
				if(li[0].equals("Deportista")) new Deportista (ac,"a",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("DeportistaAvanzado")) new DeportistaAvanzado (ac,"b",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("DeportistaHablador")) new DeportistaHablador (ac,"c",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("DeportistaPrincipiante")) new DeportistaPrincipiante (ac,"d",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("Bola")) new Bola (ac,"Soy una bola",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else if(li[0].equals("Dado")) new Dado (ac,"Soy un dado",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				else{
					in.close();
					throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[0]+" no es una clase definida.");
				}
				linea++;
				l = in.readLine();
			}
			in.close();

		}catch(IOException e ) {
			throw new bodyTIcExcepcion("Ocurrio un error al importar el archivo " + file.getName());
		}
	}
	
	public void importe03(Salon ac,File file) throws bodyTIcExcepcion{
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String l = in.readLine();
			int linea = 1;
			while(l!=null){
				String[] li = l.split(" ");
				try{
					Integer.parseInt(li[1]);
				}
				catch(NumberFormatException e){
					in.close();
					throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[1]+" no corresponde a un entero.");
				}
				try{
					Integer.parseInt(li[2]);
				}
				catch(NumberFormatException e){
					in.close();
					throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[2]+" no corresponde a un entero.");
				}
				if(Integer.parseInt(li[1]) < 0 || Integer.parseInt(li[1]) > 600) throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[1]+" no es una posicion valida");
				if(Integer.parseInt(li[2]) < 0 || Integer.parseInt(li[2]) > 635) throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[2]+" no es una posicion valida");
				
				try {
					Class <?> c = Class.forName("aplicacion."+li[0]);
					Object o = c.getDeclaredConstructor(Salon.class,String.class,int.class,int.class).newInstance(ac,"s",Integer.parseInt(li[1]),Integer.parseInt(li[2]));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					in.close();
					throw new bodyTIcExcepcion("Error: linea "+linea+" dato "+li[0]+" no es un elemento ");
				}
				linea++;
				l = in.readLine();
			}
			in.close();

		}catch(IOException e ) {
			throw new bodyTIcExcepcion("Ocurrio un error al importar el archivo " + file.getName());
		}
	}
}