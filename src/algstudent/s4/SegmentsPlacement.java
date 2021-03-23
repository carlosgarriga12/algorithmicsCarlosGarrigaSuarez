package algstudent.s4;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentsPlacement {
	private List <Integer> segments = new ArrayList<>();
	private List <Double> costs = new ArrayList<>();
	
	public static void main(String[] args) {
		String fileName =Paths.get("").toAbsolutePath().toString() + "/src/algstudent/s4/" + args[0];
		
		SegmentsPlacement sp = new SegmentsPlacement(fileName);
		
		System.out.println(sp.greedy1());
		
		System.out.println(sp.greedy2());
		
		System.out.println(sp.greedy3());
	}
	
	public SegmentsPlacement(String fileName) {
		this.segments = readFile(fileName);
	}
	
	public SegmentsPlacement(List <Integer> segments) {
		this.segments = segments;
	}
	
	public double greedy1() {
		double cost = 0.0;
		double result = 0.0;
		costs = new ArrayList<>();
		for (int i = 0; i < segments.size(); i++) {
			if(i == 0) {
				cost += (double)(0 + segments.get(i))/2;
				costs.add(cost);
			} else {
				cost += (double)(segments.get(i - 1) + segments.get(i))/2;
				costs.add(cost);
			}
		}
		
		for (Double tc : costs) {
			result += tc;
		}
		
		return result;
	}
		
	public double greedy2() {
		double cost = 0.0;
		double result = 0.0;
		costs = new ArrayList<>();
		Collections.sort(segments);
		Collections.reverse(segments);
		for (int i = 0; i < segments.size(); i++) {
			if(i == 0) {
				cost += (double)(0 + segments.get(i))/2;
				costs.add(cost);
			} else {
				cost += (double)(segments.get(i - 1) + segments.get(i))/2;
				costs.add(cost);
			}
		}
		
		for (Double tc : costs) {
			result += tc;
		}
		
		return result;
	}
	
	public double greedy3() {
		double cost = 0.0;
		double result = 0.0;
		costs = new ArrayList<>();
		Collections.sort(segments);
		for (int i = 0; i < segments.size(); i++) {
			if(i == 0) {
				cost += (double)(0 + segments.get(i))/2;
				costs.add(cost);
			} else {
				cost += (double)(segments.get(i - 1) + segments.get(i))/2;
				costs.add(cost);
			}
		}
		
		for (Double tc : costs) {
			result += tc;
		}
		
		return result;
	}
	
	public static List<Integer> readFile(String fileName) {
		BufferedReader fich = null;
		String line;
		List<Integer> elements = new ArrayList<>();
		int nLine;

		try {
			fich = new BufferedReader(new FileReader(fileName));
			line = fich.readLine(); //first element of the file
			nLine = 0;
			while (line != null) {
				if(nLine == 0) {
					elements = new ArrayList<>(Integer.parseInt(line));
					line = fich.readLine();
					nLine++;
				} else {
					elements.add(Integer.parseInt(line));
					line = fich.readLine();
					nLine++;
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
		
		return elements;
	}
}
