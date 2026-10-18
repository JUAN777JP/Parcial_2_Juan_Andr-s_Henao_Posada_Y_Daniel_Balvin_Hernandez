package factory;

import modelo.Aereo;
import modelo.Envio;

/**
 * Fábrica concreta para crear envíos aéreos.
 */
public class AereoFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(String codigo, String cliente, double peso, double distancia) {
        return new Aereo(codigo, cliente, peso, distancia);
    }
}