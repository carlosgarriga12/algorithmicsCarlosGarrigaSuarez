package algstudent.s6;

public class Song implements Comparable<Song> {
	private String ID;
	private int seconds;
	private int score;
	
	public Song(String theID, int theSeconds, int theScore) {
		this.ID = theID;
		this.seconds = theSeconds;
		this.score = theScore;		
	}

	public String getID() {
		return ID;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getScore() {
		return score;
	}
	
	public String toString() {
		String aux = "";
		
		aux+= "ID: " + getID() +  ",\t";
		aux+= "Seconds: " + (int)(getSeconds() / 60) + ":";
		if(getSeconds() % 60 >= 10) {
			aux+= getSeconds() % 60+  ",\t";
		} else {
			aux+= "0" + getSeconds() % 60+  ",\t";
		}
		aux+= "Score: " + getScore();
		
		return aux;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public int compareTo(Song o) {
		if(this.score > o.getScore()) {
			return -1;
		}
		if(this.score < o.getScore()) {
			return 1;
		}
		else {
			if(this.seconds < o.getSeconds()) {
				return -1;
			}
			if(this.seconds > o.getSeconds()) {
				return 1;
			}
			return 0;
		}
	}
}
