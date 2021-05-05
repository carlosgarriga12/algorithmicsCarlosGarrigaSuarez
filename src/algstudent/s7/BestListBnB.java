package algstudent.s7;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import algstudent.s6.Song;
import algstudent.s7.util.BranchAndBound;
import algstudent.s7.util.Node;

public class BestListBnB extends BranchAndBound {
	
    public BestListBnB(BestList board) {
    	rootNode = board; //we create the puzzle to start playing
    }
}

@SuppressWarnings("unused")
class BestList extends Node {
	private int MAX_SECONDS = 1200;
	private List <Song> allSongs;
	private List <Song> blockA;
	private List <Song> blockB;
	

	public BestList(List<Song> allSongs, List<Song> blockA, List <Song> blockB, int depth, UUID id) { 
		this.allSongs = allSongs;
		this.blockA = blockA;
		this.blockB = blockB;
		super.depth = depth;
		this.parentID = id;
		calculateHeuristicValue();
	}
	
	public BestList(List<Song> allSongs, List<Song> blockA, List <Song> blockB, int depth) { 
		this.allSongs = allSongs;
		this.blockA = blockA;
		this.blockB = blockB;
		super.depth = depth;
		calculateHeuristicValue();
	}
	
	public BestList(List<Song> allSongs, List<Song> blockA, List<Song> blockB) {
		this.allSongs = allSongs;
		this.blockA = blockA;
		this.blockB = blockB;
		super.depth = 0;
		this.MAX_SECONDS =  (int) (getDurationPlayList(allSongs) * 0.4);
	}
	
	public int getDurationPlayList(List<Song> songs) {
		int seconds = 0;
		for (Song song : songs) {
			seconds += song.getSeconds();
		}
		return seconds;
	}
	
    @Override
    public String toString() {
		String aux = "";
//		
//		aux+= "Number of songs: " + allSongs.size() + "\n\n";
//		aux+= "List of songs: \n";
//		
//		for (Song song : allSongs) {
//			aux+= song.toString() + "\n";
//		}
//		
//		aux+= "\n\n";
//		aux+= "Length of the blocks: ";
//		aux+= (int) (MAX_SECONDS / 60);
//
//		if (MAX_SECONDS % 60 >= 10) {
//			aux += ":" + MAX_SECONDS % 60 + "\n";
//		} else {
//			aux += ":0" + MAX_SECONDS % 60 + "\n";
//		}
		
		aux+= "Total score: " + (getScorePlayList(blockA) + getScorePlayList(blockB)) + "\n";

		aux+= "Best block A: \n\n";
		for (Song song : blockA) {
			aux+= song.toString() + "\n";
		}
		aux+= "Best block B: \n\n";;
		for (Song song : blockB) {
			aux+= song.toString() + "\n";
		}
		
		aux+= String.format("\nDepth: %d", depth);
		
		return aux;
    }

    @Override
    public void calculateHeuristicValue() {
    	if(getDurationPlayList(blockA) > MAX_SECONDS || getDurationPlayList(blockB) > MAX_SECONDS) {
    		heuristicValue = Integer.MAX_VALUE;
    	}
    	else {
    		int score = getScorePlayList(blockA) + getScorePlayList(blockB);
            int time1 = getDurationPlayList(blockA);
            int time2 = getDurationPlayList(blockB);

            for(int i = depth; i < allSongs.size(); i++) {
                Song song = allSongs.get(i);

                if(time1 + song.getSeconds() <= MAX_SECONDS) { 
                    score += song.getScore();
                    time1 += song.getSeconds();
                }else if (time2 + song.getSeconds() <= MAX_SECONDS) { 
                    score += song.getScore();
                    time2 += song.getSeconds();
                }
            }

            heuristicValue = -score;
    	}
    }
    
    public int getScorePlayList(List<Song> songs) {
		int score = 0;
		for (Song song : songs) {
			score += song.getScore();
		}
		return score;
	}
    
	@Override
	public boolean isSolution() {
		if(depth == allSongs.size() - 1 && 
				getDurationPlayList(blockA) <= MAX_SECONDS &&
				getDurationPlayList(blockB) <= MAX_SECONDS) {
			return true;
		}
		return false;
	}
	
	private boolean containsSong(List<Song> songs, Song s) {
		for(Song song: songs) {
			song.equals(s);
			return true;
		}
		return false;
	}
	
	/**
	 * To get the children of the current node. They 
     * point to their parent through the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
		
		ArrayList<Node> result = new ArrayList<Node>();
		ArrayList<Song> auxSongs;
		BestList temp;
		
		auxSongs = new ArrayList<Song>(blockA);
		auxSongs.add(allSongs.get(depth));
		temp = new BestList(allSongs, auxSongs, blockB, depth + 1, ID);
		result.add(temp);
		
		auxSongs = new ArrayList<Song>(blockB);
		auxSongs.add(allSongs.get(depth));
		temp = new BestList(allSongs, blockA, auxSongs, depth + 1, ID);
		result.add(temp);
		
		temp = new BestList(allSongs, blockA, blockB, depth + 1, ID);
		result.add(temp);
		
		return result;
	}
}
