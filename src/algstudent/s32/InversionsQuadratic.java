package algstudent.s32;

import java.util.List;

public class InversionsQuadratic {

	List<Integer> ranking;
	
	public InversionsQuadratic(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		long nInv = 0;
        for (long i = 0; i < ranking.size() - 1; i++)
            for (long j = i + 1; j < ranking.size(); j++)
                if (ranking.get((int) i) > ranking.get((int) j))
                    nInv++;
 
        return String.valueOf(nInv);
        
	}

}
