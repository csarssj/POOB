package persistencia;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import aplicacion.*;
import java.awt.Color;

public class donkeyDAO {
	public donkeyDAO(){
		
	}
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
}