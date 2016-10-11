package dataaccess;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import business.Book;
import business.FineRecord;
import business.LibraryMember;

public class FineRecordDataAccess implements DataAccess {
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "/src/dataaccess/storage/finerecord/";
	public static final String DATE_PATTERN = "MM/dd/yyyy";


	@Override
	public void write(String name, Object obj) {
		ObjectOutputStream out = null;
		try {
			FineRecord fines = (FineRecord) obj;

			FileOutputStream fileOut = new FileOutputStream(OUTPUT_DIR + name + ".ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(fines);
			out.close();
			fileOut.close();
			System.out.printf("\nSerialized Fine data is saved in " + OUTPUT_DIR + name + ".ser");

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

	@Override
	public Object read(String name) {
		ObjectInputStream in = null;
		FineRecord fines = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, name + ".ser");
			in = new ObjectInputStream(Files.newInputStream(path));
			fines = (FineRecord)in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return fines;
	}

}
