package cl.speedfast.interfaces;

/**
 * Contrato para todo objeto cuyo proceso pueda ser cancelado.
 */
public interface Cancelable {

    /**
     * Intenta cancelar el pedido.
     *
     * @param motivo Explicacion de por que se cancela.
     * @return true si la cancelacion fue exitosa; false en caso contrario.
     */
    boolean cancelar(String motivo);
}