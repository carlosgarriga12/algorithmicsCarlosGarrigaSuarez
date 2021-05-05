package algstudent.s7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algstudent.s6.Song;

public class MeasureTimes {

	public static void main(String[] args) {
		List<Song> songs = null;
		long start, stop;
		for(int i = 25; i < Integer.MAX_VALUE;i *= 2) {
			songs = getSongsRandomly(i);
			List<Song> blockA = new ArrayList<>();
			List<Song> blockB = new ArrayList<>();
			BestList bl = new BestList(songs, blockA, blockB);
			BestListBnB bnb = new BestListBnB(bl);
			
			start = System.currentTimeMillis();
			bnb.branchAndBound(bl);
			
			stop = System.currentTimeMillis();
			System.out.println("Amount of work: " + i + " Time(ms): " + (stop - start));
		}
	}
	
	/*
     * Generates n random songs
     * Song time generated according a normal distribution mean 2 mins and standard distribution 1 min (> 30 secs)
     * Scores are generated according a normal distribution mean 2000 and standard distribution 1000 (> 300)
     */
	private static List<Song> getSongsRandomly(int n) {
		List<Song> songs = new ArrayList<Song>();
        int t_secs, score;
        Random rand = new Random();
        for (int i=0; i<n; i++) {
            do {
                t_secs = (int) (rand.nextGaussian() * 120 + 60);
            } while (t_secs < 30);

            do {
                score = (int) (rand.nextGaussian() * 2000 + 1000);
            }while (score < 300);

            songs.add(new Song(String.valueOf(i), t_secs, score));
        }
        return songs;
    }
}
