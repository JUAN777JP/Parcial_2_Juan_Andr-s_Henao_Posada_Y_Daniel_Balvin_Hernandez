package modelo;

/**
 * Representa un envío por medio terrestre.
 * Tarifa: $1500 por Km + $2000 por Kg.
 */
public class Terrestre extends Envio {

    public Terrestre(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (getDistancia() * 1500) + (getPeso() * 2000);
    }
}