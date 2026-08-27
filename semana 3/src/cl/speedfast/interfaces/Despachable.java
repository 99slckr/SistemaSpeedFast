package cl.speedfast.interfaces;

/**
 * Contrato para todo objeto que pueda ser despachado hacia su destino.
 */
public interface Despachable {

    /**
     * Ejecuta el despacho del pedido hacia la direccion de entrega.
     */
    void despachar();
}