import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    protected List<Animal> predateurs;
    protected List<Animal> proies;

    public Animal() {
        predateurs = new ArrayList<>();
        proies = new ArrayList<>();
    }

    public abstract void seDeplacer();
    public abstract void manger();
    public abstract void boire();
    public abstract void mourir();
    public abstract void seReproduire();

    public List<Animal> getPredateurs() {
        return predateurs;
    }

    public void setPredateurs(List<Animal> predateurs) {
        this.predateurs = predateurs;
    }

    public List<Animal> getProies() {
        return proies;
    }

    public void setProies(List<Animal> proies) {
        this.proies = proies;
    }
}
