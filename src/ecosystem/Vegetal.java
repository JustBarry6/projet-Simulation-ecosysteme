package ecosystem;

public abstract class Vegetal {
	protected String nom ; 
	protected int seuilEauCritique;
	protected int seuilTempCritique;

	public Vegetal(int seuilEauCritique, int seuilTempCritique) {
		this.seuilEauCritique = seuilEauCritique;
		this.seuilTempCritique = seuilTempCritique;
	}

	public abstract void consommerEau();
	
	public String toString()
	{
		return nom ; 
	}
	
	// Getters and setters
}
