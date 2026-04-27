package Conversor;

// Subclasse para a escala Micro (multiplicador 0.000001)
public class Micro extends Conversor {
    public Micro(double valor) { 
        super(valor);
    }
    
    public double exibir() { 
        return 0.000001; 
    }
}