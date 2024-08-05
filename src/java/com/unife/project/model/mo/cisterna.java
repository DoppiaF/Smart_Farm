package SMART_FARM.src.java.com.unife.project.mo;

public class cisterna {
    private int id;
    private int capacita;
    private int quantita;

    public Cisterna(int id, int capacita, int quantita) {
        this.id = id;
        this.capacita = capacita;
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacita() {
        return capacita;
    }

    public void setCapacita(int capacita) {
        this.capacita = capacita;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

}
