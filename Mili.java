package Conversor;

// Subclasse para a escala Mili (multiplicador 0.001)
public class Mili extends Conversor {
    public Mili (double valor) {
        super(valor);
    }
    
    public double exibir() {
        return 0.001;
    }
}