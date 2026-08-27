package cl.speedfast.modelo;

/**
 * Pedido de comida preparada. Suma tiempo de preparacion en cocina.
 */
public class PedidoComida extends Pedido {

    public PedidoComida(int numeroPedido, String direccion, double distanciaKm) {
        super(numeroPedido, direccion, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (distanciaKm * 4) + 10;
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Luis Diaz";
        System.out.println("Repartidor automatico asignado: " + repartidor);
    }

    @Override
    public boolean cancelar(String motivo) {
        if (cancelado) {
            System.out.println("El pedido #" + numeroPedido + " ya estaba cancelado.");
            return false;
        }
        cancelado = true;
        System.out.println("Pedido de comida #" + numeroPedido + " cancelado. Motivo: " + motivo);
        return true;
    }

    @Override
    public void despachar() {
        System.out.println("Pedido de comida #" + numeroPedido + " despachado. Mantener caliente.");
    }
}