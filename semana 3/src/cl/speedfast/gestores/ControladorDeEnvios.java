package cl.speedfast.gestores;

import cl.speedfast.interfaces.Rastreable;
import cl.speedfast.modelo.Pedido;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el ciclo de vida de los pedidos sin conocer sus tipos concretos.
 */
public class ControladorDeEnvios implements Rastreable {

    private List<Pedido> pedidos = new ArrayList<>();
    private List<String> historialEntregas = new ArrayList<>();

    /** Registra un pedido en el sistema. */
    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /**
     * Procesa un pedido de principio a fin: asigna repartidor,
     * muestra el resumen y lo despacha.
     * No necesita saber que tipo de pedido es: cada uno responde a su manera.
     */
    public void procesarPedido(Pedido pedido) {

        if (pedido.getRepartidor().equals("sin asignar")) {
            pedido.asignarRepartidor();
        }

        pedido.mostrarResumen();
        pedido.despachar();

        historialEntregas.add(
                pedido.getClass().getSimpleName()
                        + " #" + pedido.getNumeroPedido()
                        + " - entregado por " + pedido.getRepartidor()
        );
    }

    /**
     * Cancela un pedido y deja registro solo si la cancelacion tuvo exito.
     */
    public void cancelarPedido(Pedido pedido, String motivo) {
        boolean exito = pedido.cancelar(motivo);

        if (exito) {
            historialEntregas.add(
                    pedido.getClass().getSimpleName()
                            + " #" + pedido.getNumeroPedido()
                            + " - cancelado (" + motivo + ")"
            );
        } else {
            System.out.println("No se pudo cancelar el pedido #" + pedido.getNumeroPedido());
        }
    }

    @Override
    public List<String> verHistorial() {
        return new ArrayList<>(historialEntregas);
    }
}