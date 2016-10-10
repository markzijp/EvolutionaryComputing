import java.util.List;

public interface ParentSelector {
	
	public List<Individual> selectParents(Individual[] pop);
	
}
