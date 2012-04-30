package wallbreaker;

public interface Observable {

	public void addObs(Observers o);
	public void delObs(Observers o);
	public void updateObs();
}
