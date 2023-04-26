import java.util.ArrayList;
import java.util.List;

public class Zone {
    private double niveauEau;
    private double temperature;
    private TypeZone type;
    private List<Animal> animaux;
    private List<Vegetal> vegetaux;

    public Zone(double niveauEau, double temperature, TypeZone type) {
        this.niveauEau = niveauEau;
        this.temperature = temperature;
        this.type = type;
        this.animaux = new ArrayList<>();
        this.vegetaux = new ArrayList<>();
    }

	public double getNiveauEau() {
		return niveauEau;
	}

	public void setNiveauEau(double niveauEau) {
		this.niveauEau = niveauEau;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public TypeZone getType() {
		return type;
	}

	public void setType(TypeZone type) {
		this.type = type;
	}
}
