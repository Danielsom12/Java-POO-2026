package Conversor;

public class Kilo extends Conversor { //polimorfismo do método exibir presente em conversor
    public Kilo(double valor) { super(valor); }
    public double exibir() { return 1000.0; } 
}