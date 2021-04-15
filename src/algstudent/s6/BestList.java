package algstudent.s6;

import java.util.ArrayList;
import java.util.List;

public class BestList {
	private List<Song> allSongs;
	private List<Song> blockA = new ArrayList<>();
	private List<Song> blockB = new ArrayList<>();
	private int maxSeconds;
	private List<Song> songsToManipulate = new ArrayList<>();
	
	public BestList(List<Song> songs, int maxSeconds) {
		this.allSongs = songs;
		this.maxSeconds = maxSeconds;
		
		for (Song song : allSongs) {
			songsToManipulate.add(song);
		}
	}
	
	public void printSongs(List<Song> songs) {
		for (Song song : songs) {
			System.out.println(song.toString());
		}
	}
	
	public int getDurationPlayList(List<Song> songs) {
		int seconds = 0;
		for (Song song : songs) {
			 seconds += song.getSeconds();
		}
		return seconds;
	}
	
	public int getScorePlayList(List<Song> songs) {
		int score = 0;
		for (Song song : songs) {
			score += song.getScore();
		}
		return score;
	}
	
	public Song getBestSong(List<Song> songs) {
		int ratio = 0;
		Song bestSong = null;
		
		for (Song song : songs) {
			if(song.getScore()/song.getSeconds() > ratio) {
				ratio = song.getScore()/song.getSeconds();
				bestSong = song;
			}
		}
		
		return bestSong;
	}
	
	public boolean isInPlayList(List<Song> songs, Song theSong) {	
		for (Song song : songs) {
			if(song.equals(theSong)) {
				return true;
			}
		}
		return false;
	}
	
	public void recursiveApproach(int level) {
		if(level <= 0) {
			printSolution();
		} else {

			Song bestSong = getBestSong(songsToManipulate);

			boolean discardSong = bestSong.getSeconds() + getDurationPlayList(blockA) > maxSeconds &&
					bestSong.getSeconds() + getDurationPlayList(blockB) > maxSeconds;
			boolean mustBeAddedToA = bestSong.getSeconds() + getDurationPlayList(blockA) <= maxSeconds;
			boolean mustBeAddedToB = !mustBeAddedToA && 
					bestSong.getSeconds() + getDurationPlayList(blockB) <= maxSeconds;
			//Discard the song
			if( discardSong ) {
				recursiveApproach(level - 1);
			}
			//Add to block A
			if( mustBeAddedToA ) {
				songsToManipulate.remove(bestSong);
				blockA.add(bestSong);				
				recursiveApproach(level - 1);
			}
			//Add to block B
			if( mustBeAddedToB ) {
				songsToManipulate.remove(bestSong);
				blockB.add(bestSong);
				recursiveApproach(level - 1);
			}
		}
	}
	
	public void backtracking(int level) {
		if(level == allSongs.size()) {
			printSolution();
		} else {
			for (int i = level; level < allSongs.size(); i++) {
				boolean discardSong = allSongs.get(i).getSeconds() + getDurationPlayList(blockA) > maxSeconds &&
						allSongs.get(i).getSeconds() + getDurationPlayList(blockB) > maxSeconds;
				boolean mustBeAddedToA = allSongs.get(i).getSeconds() + getDurationPlayList(blockA) <= maxSeconds;
				boolean mustBeAddedToB = !mustBeAddedToA && 
						allSongs.get(i).getSeconds() + getDurationPlayList(blockB) <= maxSeconds;
				if(!isInPlayList(blockA, allSongs.get(i)) && !isInPlayList(blockB, allSongs.get(i))) {
					//Discard the allSongs.get(i)
					if( discardSong ) {
						backtracking(level + 1);
					}
					//Add to block A
					if( mustBeAddedToA ) {
						blockA.add(allSongs.get(i));				
						backtracking(level + 1);
						blockA.remove(allSongs.get(i));
					}
					//Add to block B
					if( mustBeAddedToB ) {
						blockB.add(allSongs.get(i));
						backtracking(level + 1);
						blockB.remove(allSongs.get(i));
					}
				}
				
			}
			
		}
	}
	
	public List<Song> getAllSongs() {
		return allSongs;
	}

	public List<Song> getBlockA() {
		return blockA;
	}

	public List<Song> getBlockB() {
		return blockB;
	}

	public void printSolution() {
		System.out.println("Number of songs: " + allSongs.size());
		System.out.println();
		System.out.println();
		System.out.println("List of songs:");
		printSongs(allSongs);
		System.out.println();
		System.out.println();
		System.out.print("Lenght of the blocks: ");
		System.out.print((int)(maxSeconds / 60));
		
		if(maxSeconds % 60 >= 10) {
			System.out.println(":" + maxSeconds % 60);
		} else {
			System.out.println(":0" + maxSeconds % 60);
		}
		
		System.out.println("Total score: " + (getScorePlayList(blockA) + getScorePlayList(blockB)));
		System.out.println("Total counter: ");
		
		
		System.out.println("Best block A: ");
		System.out.println();
		printSongs(blockA);
		
		System.out.println("Best block B: ");
		System.out.println();
		printSongs(blockB);
		
	}
}
