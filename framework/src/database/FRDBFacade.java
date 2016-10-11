package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import common.*;

public class FRDBFacade {
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "/src/dataaccess/storage/";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	public static void saveObject(FRIModel o){
		String dir = OUTPUT_DIR  + o.getClass().getName() + "/";
		new File(dir).mkdir();
		String path =  dir + o.getKey();
		ObjectOutputStream out = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
//			System.out.printf("\nSerialized CR data is saved in " + path );
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	public static void deleteObject(FRIModel o){
		String path = OUTPUT_DIR + o.getClass().getName() + "/" + o.getKey();
		File file = new File(path);
		file.delete();
	}
	public static Object readObject(String p){
		Object ret = null;
		ObjectInputStream in = null;
		try {
			Path path = FileSystems.getDefault().getPath(p);
			in = new ObjectInputStream(Files.newInputStream(path));
			ret = in.readObject();
		} catch(Exception e) {

		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return ret;
	}
	public static Object readObject(String classname, String filename){
		String p = OUTPUT_DIR + classname + "/"  + filename;
		return readObject(p);
	}
	public static Object readObject(String classname, long id){
		String p = OUTPUT_DIR + classname + "/"  + id;
		return readObject(p);
	}
	
	public static List readAllFiles(String classname){
		List lm = new ArrayList<>();
		ObjectInputStream in = null;
		Object member = null;
		String dir = OUTPUT_DIR+classname+"/";
		try {

			File f = new File(dir);

			for(File ff: f.listFiles())
			{
				Path path = FileSystems.getDefault().getPath(dir,ff.getName());
				in = new ObjectInputStream(Files.newInputStream(path));
				member = (Object)in.readObject();
				lm.add(member);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return lm;
	}
}
