package algstudent.s7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import algstudent.s6.Song;

public class Main {
	
	public static final String LIST1 = "C:\\Users\\carlo\\git\\repository\\Lab1\\src\\algstudent\\s7\\Lista01.txt";
	public static final String LIST2 = "C:\\Users\\carlo\\git\\repository\\Lab1\\src\\algstudent\\s7\\Lista02.txt";
	public static final String LIST3 = "C:\\Users\\carlo\\git\\repository\\Lab1\\src\\algstudent\\s7\\Lista03.txt";
	
	public static void main(String[] args) {
		List<Song> allSongs = readFile(LIST1);
		List<Song> blockA = new ArrayList<Song>();
		List<Song> blockB = new ArrayList<Song>();
		
		
		BestList bl = new BestList(allSongs, blockA, blockB, 0);
		BestListBnB bnb = new BestListBnB(bl);
		bnb.branchAndBound(bl);
		bnb.printSolutionTrace();
		
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
