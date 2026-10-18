package servicio;

import modelo.Envio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lista genérica de envíos.
 */
public class EnvioService {
    private final List<Envio> listaEnvios;

    public EnvioService() {
        this.listaEnvios = new ArrayList<>();
    }

    public boolean agregarEnvio(Envio envio) {
        if (buscarPorCodigo(envio.getCodigo()).isPresent()) {
            return false; // El código ya existe
        }
        return listaEnvios.add(envio);
    }

    public boolean eliminarEnvio(String codigo) {
        return listaEnvios.removeIf(e -> e.getCodigo().equalsIgnoreCase(codigo));
    }

    public Optional<Envio> buscarPorCodigo(String codigo) {
        return listaEnvios.stream()
                .filter(e -> e.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public List<Envio> obtenerTodos() {
        return new ArrayList<>(listaEnvios);
    }
}