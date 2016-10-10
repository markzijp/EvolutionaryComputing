import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParentsSelectorHighestFitness implements ParentSelector{
	
	private int numParents;

	public ParentsSelectorHighestFitness(int n){
		numParents = n;
	}
	
	@Override
	public List<Individual> selectParents(Individual[] pop) {
		List<Individual> parents = new ArrayList<Individual>();
		for(Individual i: pop){
			if(parents.size() < numParents){
				parents.add(i);
			}else{
				Collections.sort(parents);
				Collections.reverse(parents);
				
				for (int j = 0; j < parents.size(); j++) {
					if(i.getFitness()>=parents.get(j).getFitness()){
						if(parents.size()>=numParents){
							parents.remove(parents.size()-1);
						}
						parents.add(i);
					}
				}
			}
		}
		return parents;
	}
	

}
