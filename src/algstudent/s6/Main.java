package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
	
	public static final String FILENAME = "C:\\Users\\carlo\\git\\repository\\Lab1\\src\\algstudent\\s6\\List01.txt";

	public static void main(String[] args) {
		
		List<Song> songs = readFile(args[0]);
		
		BestList bl = new BestList(songs, Integer.valueOf(args[1]) * 60);
		
	
		bl.backtracking(0);
		bl.printSolution();
		
	}
	
	public static List<Song> readFile(String fileName) {		
		BufferedReader fich = null;
		String line;
		List<Song> songs = new ArrayList<Song>();

		try {
			fich = new BufferedReader(new FileReader(fileName));
			line = fich.readLine(); //first element of the file
			boolean isLineOne = true;
			while (line != null) {
				if(!isLineOne) {
					String [] parts = line.split("\t");
					String [] time = parts[1].split(":");
					int minutes = Integer.valueOf(time[0]);
					int seconds = Integer.valueOf(time[1]);
					int songSecs = minutes * 60 + seconds;
					songs.add(new Song(parts[0], songSecs, Integer.valueOf(parts[2])));
					line = fich.readLine();
				} else {
					line = fich.readLine();
					isLineOne = false;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("There is no file: "+ fileName);
		} catch (IOException e) {
			System.out.println("Error reading the file: "+ fileName);
		} finally {
			if (fich!=null)
				try {
					fich.close();
				} catch (IOException e) {
					System.out.println("Error closing the file: "+ fileName);
					e.printStackTrace();
				}
		}

		return songs;
	}
	
}
