package persistencia;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import aplicacion.*;
import java.awt.Color;

public class TeatroColonDAO {
	public TeatroColonDAO(){
		
	}
	public void salve(Teatro ac, File file) throws TeatroColonException {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(ac);
			out.close();
		}catch (IOException e) {
			throw new TeatroColonException("Ocurrio un error al salvar " + file.getName());
		}
	}
	public Teatro abra(File file) throws TeatroColonException{
		Teatro auto = null;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			auto = (Teatro)in.readObject();
			in.close();
		}catch(Exception e) {
			throw new TeatroColonException("Ocurrio un error al abrir " + file.getName());
		}
		return auto;
	}
	public void exporte(Teatro teatro,File file) throws TeatroColonException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new TeatroColonException(TeatroColonException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new TeatroColonException("No se encuentra el archivo " + file.getName());
		}
		int size = teatro.numeroEnEscena();
		for(int i=0;i<size;i++){
			String temp = teatro.getElementos().get(i).toString();
			out.println(teatro.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
		
	}
	public Teatro importe(File file) throws TeatroColonException{
		int i = 1;
		Teatro teatro = null;
		if (!file.getName().endsWith(".txt")) throw new TeatroColonException(TeatroColonException.TYPE_TXT_ERROR);
		try {
			Teatro.nuevoTeatro();
			teatro = Teatro.demeTeatro();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if(line==null) {
				throw new TeatroColonException(TeatroColonException.NO_TEXT_FOUND);
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
						Object o = c.getDeclaredConstructor(Teatro.class , String.class , int.class , int.class).newInstance(teatro,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {	
						throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					}
				}
				else if(linea[0].equals("Cortinas")){
					checkLength(3,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						//System.out.println(linea[1]+" "+linea[2]);
						Object o = c.getDeclaredConstructor(Teatro.class ,String.class,int.class).newInstance(teatro,linea[1],Integer.parseInt(linea[2]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} 
				}
				else if(linea[0].equals("Luz")){
					checkLength(8,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						//System.out.println(linea[1]+" "+linea[2]+" "+linea[3]+" "+linea[4]+" "+linea[5]+" "+linea[6]+" "+linea[7]);
						Object o = c.getDeclaredConstructor(Teatro.class ,String.class , int.class , int.class , int.class, int.class , int.class , int.class ).newInstance(teatro ,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),Integer.parseInt(linea[5]),Integer.parseInt(linea[6]),Integer.parseInt(linea[7]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} 
				}
				else {
					throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
				}
				line = in.readLine();
				i+=1;
			}
			in.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
			throw new TeatroColonException("Ocurrio un error al importar " + file.getName());
		}
		return teatro;
	}
	
	public Teatro abra01(File file) throws TeatroColonException{
		Teatro Teatro = null;
		if (!file.getName().endsWith(".dat")) throw new TeatroColonException(TeatroColonException.TYPE_DAT_ERROR);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			Teatro = (Teatro) in.readObject();
			in.close();
		}catch(ClassNotFoundException e) {
			throw new TeatroColonException(TeatroColonException.FILE_NOT_FOUND_ERROR);
		}catch (IOException e) {
			throw new TeatroColonException("Ocurrio un error al abrir el archivo" + file.getName());
		}
		return Teatro;
	}
	
	public void salve01(Teatro Teatro, File file) throws TeatroColonException{
		if (!file.getName().endsWith(".dat")) throw new TeatroColonException(TeatroColonException.TYPE_DAT_ERROR);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(Teatro);
			out.close();
		}catch (IOException e) {
			throw new TeatroColonException("Ocurrio un error al salvar " + file.getName());
		}
	}
	private void checkLength(int size , String[] line) throws TeatroColonException{
		if(line.length!=size) {
			throw new TeatroColonException(TeatroColonException.SIZE_ERROR);
		}
	}
	public void exporte01(Teatro teatro,File file) throws TeatroColonException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new TeatroColonException(TeatroColonException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new TeatroColonException("No se encuentra el archivo " + file.getName());
		}
		int size = teatro.numeroEnEscena();
		for(int i=0;i<size;i++){
			String temp = teatro.getElementos().get(i).toString();
			out.println(teatro.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
	}
	public void exporte02(Teatro teatro,File file) throws TeatroColonException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new TeatroColonException(TeatroColonException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new TeatroColonException("No se encuentra el archivo " + file.getName());
		}
		int size = teatro.numeroEnEscena();
		for(int i=0;i<size;i++){
			String temp = teatro.getElementos().get(i).toString();
			out.println(teatro.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
	}
	public void exporte03(Teatro teatro,File file) throws TeatroColonException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new TeatroColonException(TeatroColonException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new TeatroColonException("No se encuentra el archivo " + file.getName());
		}
		int size = teatro.numeroEnEscena();
		for(int i=0;i<size;i++){
			String temp = teatro.getElementos().get(i).toString();
			out.println(teatro.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
		
	}
	public Teatro importe01(Teatro teatro2,File file) throws TeatroColonException{
		Teatro teatro = null;
		try{
			Teatro.nuevoTeatro();
			teatro = Teatro.demeTeatro();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String l = in.readLine();
			while(l!=null){
				String[] li = l.split(" ");
				if(li[0].equals("Actor")) new Actor (teatro,"a",Integer.parseInt(li[1]),Integer.parseInt(li[4]));
				else if(li[0].equals("ActorBailarin")) new ActorBailarin (teatro,"b",Integer.parseInt(li[1]),Integer.parseInt(li[4]));
				else if(li[0].equals("ActorNecio")) new ActorNecio (teatro,"c",Integer.parseInt(li[1]),Integer.parseInt(li[4]));
				else if(li[0].equals("ActorPerezoso")) new ActorPerezoso (teatro,"d",Integer.parseInt(li[1]),Integer.parseInt(li[4]));
				else if(li[0].equals("Luz")) new Luz (teatro,"Soy una bola",Integer.parseInt(li[1]),Integer.parseInt(li[2]),Integer.parseInt(li[3]),Integer.parseInt(li[4]),Integer.parseInt(li[5]),Integer.parseInt(li[6]));
				else if(li[0].equals("Cortinas")) new Cortinas (teatro,"Cortina",Integer.parseInt(li[4]));
				l = in.readLine();
			}
			in.close();

		}catch(IOException e ) {
			throw new TeatroColonException("Ocurrio un error al importar el archivo " + file.getName());
		}
		return teatro;
	}
	public Teatro importe02(File file) throws TeatroColonException{
		int i = 1;
		Teatro Teatro = null;
		if (!file.getName().endsWith(".txt")) throw new TeatroColonException(TeatroColonException.TYPE_TXT_ERROR);
		try {
			Teatro.nuevoTeatro();
			Teatro = Teatro.demeTeatro();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if(line==null) {
				throw new TeatroColonException(TeatroColonException.NO_TEXT_FOUND);
			}
			while (line!=null){
				line = line.trim();
				if(line.equals("")){continue;}
				String[] linea = line.split(" ");
				if(linea[0].equals("Actor")){
					checkLength(4,linea);
					try {
						new Actor(Teatro,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					}
				}
				else if(linea[0].equals("ActorBailarin")){
					checkLength(4,linea);
					try {
						new ActorBailarin(Teatro,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					}
				}
				else if(linea[0].equals("ActorPerezoso")){
					checkLength(4,linea);
					try {
						new ActorPerezoso(Teatro,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					}
				}
				else if(linea[0].equals("ActorNecio")){
					checkLength(6,linea);
					try {
						new ActorNecio(Teatro,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					} 
					
				}
				else if(linea[0].equals("Cortinas")){
					checkLength(3,linea);
					try {
						new Cortinas(Teatro ,linea[1],Integer.parseInt(linea[2]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					}
					
				}
				else if(linea[0].equals("Luz")){
					checkLength(8,linea);
					try {
						new Luz(Teatro,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),Integer.parseInt(linea[5]),Integer.parseInt(linea[6]),Integer.parseInt(linea[7]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					}
				}
				else {
					throw new TeatroColonException("Error : linea "+i+" La linea "+linea[0]+" no es una clase definida");
				}
				line = in.readLine();
				i+=1;
			}
			in.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
			throw new TeatroColonException("Ocurrio un error al importar " + file.getName());
		}
		return Teatro;
	}
	public Teatro importe03(File file) throws TeatroColonException{
		int i = 1;
		Teatro teatro = null;
		if (!file.getName().endsWith(".txt")) throw new TeatroColonException(TeatroColonException.TYPE_TXT_ERROR);
		try {
			Teatro.nuevoTeatro();
			teatro = Teatro.demeTeatro();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if(line==null) {
				throw new TeatroColonException(TeatroColonException.NO_TEXT_FOUND);
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
						Object o = c.getDeclaredConstructor(Teatro.class , String.class , int.class , int.class).newInstance(teatro,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {	
						throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					}
				}
				else if(linea[0].equals("Cortinas")){
					checkLength(3,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						//System.out.println(linea[1]+" "+linea[2]);
						Object o = c.getDeclaredConstructor(Teatro.class ,String.class,int.class).newInstance(teatro,linea[1],Integer.parseInt(linea[2]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} 
				}
				else if(linea[0].equals("Luz")){
					checkLength(8,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						//System.out.println(linea[1]+" "+linea[2]+" "+linea[3]+" "+linea[4]+" "+linea[5]+" "+linea[6]+" "+linea[7]);
						Object o = c.getDeclaredConstructor(Teatro.class ,String.class , int.class , int.class , int.class, int.class , int.class , int.class ).newInstance(teatro ,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),Integer.parseInt(linea[5]),Integer.parseInt(linea[6]),Integer.parseInt(linea[7]));
					} catch(NumberFormatException e) {
						throw new TeatroColonException("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} 
				}
				else {
					throw new TeatroColonException("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
				}
				line = in.readLine();
				i+=1;
			}
			in.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
			throw new TeatroColonException("Ocurrio un error al importar " + file.getName());
		}
		return teatro;
	}
}