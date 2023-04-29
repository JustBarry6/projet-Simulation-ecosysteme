package ecosystem;

public abstract class Vegetal {
	private int seuilEauCritique;
	private int seuilTempCritique;

	public Vegetal(int seuilEauCritique, int seuilTempCritique) {
		this.seuilEauCritique = seuilEauCritique;
		this.seuilTempCritique = seuilTempCritique;
	}

	public abstract void consommerEau();

	// Getters and setters
}
