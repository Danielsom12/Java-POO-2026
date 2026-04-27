package Conversor;

// Subclasse para a escala Mega (multiplicador 1.000.000)
public class Mega extends Conversor {
    public Mega(double valor) { 
        super(valor); 
    }
    
    public double exibir() {
        return 1000000.0;
    }
}