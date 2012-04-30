package wallbreaker;

public interface Observable {

	public void addObs(Observer o);
	public void delObs(Observer o);
	public void updateObs();
}
