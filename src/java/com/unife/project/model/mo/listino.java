public class listino {
    
    private String tipo_prodotto;
    private Float prezzo;

    public listino(String tipo_prodotto, Float prezzo) {
        this.tipo_prodotto = tipo_prodotto;
        this.prezzo = prezzo;
    }

    public String getTipo_prodotto() {
        return tipo_prodotto;
    }

    public void setTipo_prodotto(String tipo_prodotto) {
        this.tipo_prodotto = tipo_prodotto;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    
}
