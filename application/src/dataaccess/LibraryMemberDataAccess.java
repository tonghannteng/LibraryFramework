package dataaccess;

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

import business.LibraryMember;

public class LibraryMemberDataAccess implements DataAccess {
	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "/src/dataaccess/storage/librarymember/";
	public static final String DATE_PATTERN = "MM/dd/yyyy";


	@Override
	public void write(String memberID, Object obj) {
		ObjectOutputStream out = null;
		try {

			LibraryMember member = (LibraryMember) obj;

			FileOutputStream fileOut = new FileOutputStream(OUTPUT_DIR + memberID);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(member);
			out.close();
			fileOut.close();
			System.out.printf("\nSerialized library Member data is saved in " + OUTPUT_DIR + memberID);

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
	public Object read(String memberID) {
		ObjectInputStream in = null;
		LibraryMember member = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, memberID);
			in = new ObjectInputStream(Files.newInputStream(path));
			member = (LibraryMember)in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return member;
	}



	public List<LibraryMember> readAllMembers() {
		ObjectInputStream in = null;
		List<LibraryMember> lm = new ArrayList<>();

		LibraryMember member = null;
		try {

			File f = new File(OUTPUT_DIR);

			for(File ff: f.listFiles())
			{
				Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,ff.getName());
				in = new ObjectInputStream(Files.newInputStream(path));
				member = (LibraryMember)in.readObject();
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
