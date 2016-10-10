import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.vu.contest.ContestEvaluation;

public class Population {
	private Individual[] pop;
	private List<Individual> parents;
	private List<Individual> children;
	private int popSize;
	private Random rnd;
	private int geneSize;
	private ContestEvaluation eval;
	
	public Population(boolean r, int s, Random rand, int c, ContestEvaluation evaluation_){
		eval = evaluation_;
		geneSize = c;
		rnd = rand;
		popSize = s;
		parents = new ArrayList<Individual>();
		children = new ArrayList<Individual>();
		pop = new Individual[popSize];
		if(r){
			for (int i = 0; i < pop.length; i++) {
				pop[i] = new Individual(true, rnd, geneSize, eval);
			}
		}
	}
	
	public double getFitness(){
		double highest = 0;
		for (Individual i : pop) {
			if(i.getFitness() >= highest){
				highest = i.getFitness();
			}
		}
		return highest;
	}
	
	public String toString(){
		String str = "";
		for(Individual i: pop){
			str += i.toString() + "\n";
		}
		str += "Highest Fitness = " + getFitness();
		return str;
	}
	
	public String parentsToString(){
		String str = "";
		for(Individual i: parents){
			str += i.toString() + "\n";
		}
		return str;
	}
	
	public String childrenToString(){
		String str = "";
		for(Individual i: children){
			str += i.toString() + "\n";
		}
		return str;
	}
	
	public void selectParents(ParentSelector ps){
		parents.clear();
		parents = ps.selectParents(pop);
		
	}
	
	public void createChildren(OffspringCreator oc){
		children.clear();
		children = oc.createOffspring(parents);
	}
	
	public void mutate(int c){
		int chance = rnd.nextInt(c);
		for (Individual i : children) {
			for (int j = 0; j < geneSize ; j++) {
				chance = rnd.nextInt(c);
				if(chance == 0){
					i.flipGeneBit(j);
				}
			}
		}
	}

	public void selectSurvivors() {
		Individual[] newPop = new Individual[popSize];
		for (int i = 0; i < newPop.length; i++) {
			newPop[i] = children.get(i);
		}
		pop = newPop;
	}

}
