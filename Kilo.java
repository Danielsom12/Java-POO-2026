package Conversor;

//Polimorfismo do método exibir presente em conversor
public class Kilo extends Conversor { 
    public Kilo(double valor) { super(valor); }
    public double exibir() { return 1000.0; } 
}