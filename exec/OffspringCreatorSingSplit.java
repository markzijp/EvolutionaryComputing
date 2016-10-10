import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.vu.contest.ContestEvaluation;

public class OffspringCreatorSingSplit implements OffspringCreator{

	private Random rng;
	private int popSize;
	private int geneSize;
	private ContestEvaluation eval;
	
	public OffspringCreatorSingSplit(Random random, int popSize, int geneSize, ContestEvaluation eval){
		rng = random;
		this.popSize = popSize;
		this.geneSize = geneSize;
		this.eval = eval;
	}
	
	@Override
	public List<Individual> createOffspring(List<Individual> parents) {
		List<Individual> children = new ArrayList<Individual>();
		while(children.size() < popSize){
			int r = rng.nextInt(parents.size());
			Individual p1 = parents.get(r);
			int r2 = rng.nextInt(parents.size());
			while(r == r2){
				r2 = rng.nextInt(parents.size());
			}
			Individual p2 = parents.get(r2);
			
			int split = rng.nextInt(geneSize);
			double[] gene = new double[geneSize];
			double[] gene2 = new double[geneSize];
			
			for (int i = 0; i < geneSize; i++) {
				if(i<=split){
					gene[i] = p1.getGene()[i];
					gene2[i] = p2.getGene()[i];
				}else{
					gene[i] = p2.getGene()[i];
					gene2[i] = p1.getGene()[i];
				}
			}
			children.add(new Individual(gene, rng, geneSize, eval));
			if(children.size() < popSize){
				children.add(new Individual(gene2, rng, geneSize, eval));
			}
		}
		

		return children;
	}

}
