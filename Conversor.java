package Conversor;

public class Conversor {
    
    // Armazena o valor bruto que o usuário digitou
    private double valorInserido; 
    
    // Construtor: inicializa o objeto com o valor fornecido
    public Conversor (double valorInserido) { 
        this.valorInserido = valorInserido;
    }
    
    // Getter: retorna o valor armazenado
    public double getValorInserido() { 
        return valorInserido;
    }
    
    // Setter: altera o valor armazenado
    public void setValorInserido(double valorInserido) { 
        this.valorInserido = valorInserido;
    }
    
    // Método de cálculo:
    // Converte o valor inserido para a unidade base (1.0) e depois para a escala de destino
    public double converter(Conversor destino) { 
        double valorBase = this.getValorInserido() * this.exibir(); 
        return valorBase / destino.exibir();
    }
    
    // Método que será sobrescrito pelas subclasses (Polimorfismo)
    public double exibir() { 
        return 1.0;
    }
}