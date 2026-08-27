package cl.speedfast.modelo;

import cl.speedfast.interfaces.Rastreable;
import java.util.ArrayList;
import java.util.List;

/**
 * Pedido express con prioridad maxima.
 * Implementa Rastreable para mantener su propio historial de eventos.
 */
public class PedidoExpress extends Pedido implements Rastreable {

    private List<String> historial = new ArrayList<>();

    public PedidoExpress(int numeroPedido, String direccion, double distanciaKm) {
        super(numeroPedido, direccion, distanciaKm);
        historial.add("Pedido express #" + numeroPedido + " creado.");
    }

    @Override
    public List<String> verHistorial() {
        return new ArrayList<>(historial);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (distanciaKm * 2);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Ignacio Morales";
        System.out.println("Repartidor automatico asignado: " + repartidor);
        historial.add("Repartidor asignado: " + repartidor);
    }

    @Override
    public boolean cancelar(String motivo) {
        if (cancelado) {
            System.out.println("El pedido #" + numeroPedido + " ya estaba cancelado.");
            historial.add("Intento de cancelar un pedido ya cancelado.");
            return false;
        }
        cancelado = true;
        System.out.println("Pedido Express #" + numeroPedido + " cancelado. Motivo: " + motivo);
        historial.add("Pedido cancelado. Motivo: " + motivo);
        return true;
    }

    @Override
    public void despachar() {
        System.out.println("Pedido Express #" + numeroPedido + " Entregar de manera inmediata.");
        historial.add("Pedido despachado con prioridad maxima.");
    }
}