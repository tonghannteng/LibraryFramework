package dataaccess;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import business.Book;
import business.LendableCopy;
import business.LibraryMember;

public class LendableCopyDataAccess implements DataAccess {
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "/src/dataaccess/storage/";
	public static final String DATE_PATTERN = "MM/dd/yyyy";


	@Override
	public void write(String copyId, Object obj) {
		ObjectOutputStream out = null;
		try {

			LendableCopy book = (LendableCopy) obj;

			FileOutputStream fileOut = new FileOutputStream(OUTPUT_DIR + copyId + ".ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(book);
			out.close();
			fileOut.close();
			System.out.printf("\nSerialized data is saved in " + OUTPUT_DIR + copyId + ".ser");

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
	public Object read(String copyId) {
		ObjectInputStream in = null;
		LendableCopy lendableCopy = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, copyId+".ser");
			in = new ObjectInputStream(Files.newInputStream(path));
			lendableCopy = (LendableCopy)in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return lendableCopy;
	}

}
