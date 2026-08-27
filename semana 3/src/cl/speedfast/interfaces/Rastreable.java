package cl.speedfast.interfaces;

import java.util.List;

/**
 * Contrato para obtener el historial de eventos asociados al objeto.
 */
public interface Rastreable {

    /**
     * Devuelve los eventos registrados durante el ciclo de vida del objeto.
     *
     * @return Lista de textos que representan el historial.
     */
    List<String> verHistorial();
}