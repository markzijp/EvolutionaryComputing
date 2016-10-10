import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;


public class player42 implements ContestSubmission
{
	final int POP_SIZE = 20;
	final int NUM_PARENTS = 10;
    final int CHILD_SIZE = 10;
	Random rnd_;
    ContestEvaluation evaluation_;
    private int evaluations_limit_;
    

    public player42()
    {
    	rnd_ = new Random();        
    }

    public void setSeed(long seed)
    {
        // Set seed of algortihms random process
        rnd_.setSeed(seed);
    }

    public void setEvaluation(ContestEvaluation evaluation)
    {
        // Set evaluation problem used in the run
        evaluation_ = evaluation;

        // Get evaluation properties
        Properties props = evaluation.getProperties();
        
        //System.out.println(props);
        // Get evaluation limit
        evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
        // Property keys depend on specific evaluation
        // E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

        // Do sth with property values, e.g. specify relevant settings of your algorithm
        if(isMultimodal){
            // Do sth
        }else{
            // Do sth else
        }
    }

    public void run(){
        // Run your algorithm here

        int evals = 0;
        // init population
        Population p = new Population(true, POP_SIZE, rnd_, CHILD_SIZE, evaluation_);
        System.out.println("Highest Fitness: " + p.getFitness());
        
        while(evals<evaluations_limit_){
            // Select parents
        	p.selectParents(new ParentsSelectorHighestFitness(2));
        	
            // Apply crossover / mutation operators
            p.createChildren(new OffspringCreatorSingSplit(rnd_, POP_SIZE, CHILD_SIZE, evaluation_));
            p.mutate(10);
            
            // Select survivors
            p.selectSurvivors();
            
            System.out.println("Highest Fitness: " + p.getFitness());
            evals++;
        }

    }
}