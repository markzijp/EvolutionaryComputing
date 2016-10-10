import java.util.Arrays;
import java.util.Random;

import org.vu.contest.ContestEvaluation;

public class Individual implements Comparable<Individual>{
	private double fitness;
	private double[] gene;
	private Random rnd;
	private int geneSize;
	private ContestEvaluation eval;
	
	public Individual(boolean r, Random rand, int c, ContestEvaluation evaluation_){
		eval = evaluation_;
		geneSize = c;
		rnd = rand;
		fitness = 0;
		gene = new double[geneSize];
		if(r){
			for(int i = 0; i<geneSize; i++){
				//create random double between 1 and 0
				gene[i] = Math.round(rand.nextDouble());
			}
		}
		calcFitness();
	}
	
	public Individual(double[] g, Random rand, int c, ContestEvaluation evaluation_){
		eval = evaluation_;
		geneSize = c;
		rnd = rand;
		fitness = 0;
		gene = g;
		calcFitness();
	}
	
	private void calcFitness(){
		//try{
			//System.out.println(Arrays.toString(gene));
			fitness = (double) eval.evaluate(gene);
		//}catch(Exception e){
			//fitness = 0;
		//}
	}
	
	public double getFitness() {return fitness;}
	public double[] getGene() {return gene;}
	
	public void flipGeneBit(int i){
		if(gene[i] == 0.0){
			gene[i] = 1.0;
		}else{
			gene[i] = 0.0;
		}
	}

	public String toString(){
		return (Arrays.toString(gene) + " = " + fitness);
	}

	@Override
	public int compareTo(Individual o) {
		if(o.getFitness() < fitness){
			return 1;
		}else if(o.getFitness() == fitness){
			return 0;
		}else{
			return -1;
		}
	}

}
