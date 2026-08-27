package cl.speedfast.modelo;

/**
 * Pedido de encomienda. Tiempo de entrega mayor por tratarse de carga.
 */
public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int numeroPedido, String direccion, double distanciaKm) {
        super(numeroPedido, direccion, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (distanciaKm * 6);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Daniela Tapia";
        System.out.println("Repartidor automatico asignado: " + repartidor);
    }

    @Override
    public boolean cancelar(String motivo) {
        if (cancelado) {
            System.out.println("El pedido #" + numeroPedido + " ya estaba cancelado.");
            return false;
        }
        cancelado = true;
        System.out.println("Pedido de Encomienda #" + numeroPedido + " cancelado. Motivo: " + motivo);
        return true;
    }

    @Override
    public void despachar() {
        System.out.println("Pedido de Encomienda #" + numeroPedido + " despachado. Que no se golpee en el trayecto.");
    }
}