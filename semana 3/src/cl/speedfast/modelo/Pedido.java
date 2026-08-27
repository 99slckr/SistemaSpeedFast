package cl.speedfast.modelo;

import cl.speedfast.interfaces.Cancelable;
import cl.speedfast.interfaces.Despachable;

/**
 * Clase base abstracta para todos los tipos de pedido de SpeedFast.
 * Define los atributos comunes y el comportamiento compartido,
 * dejando a cada subclase la logica propia de su tipo de entrega.
 */
public abstract class Pedido implements Despachable, Cancelable {

    protected int numeroPedido;
    protected String direccion;
    protected double distanciaKm;
    protected String repartidor;
    protected boolean cancelado = false;

    public Pedido(int numeroPedido, String direccion, double distanciaKm) {
        this.numeroPedido = numeroPedido;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;
        this.repartidor = "sin asignar";
    }

    /** Metodo ABSTRACTO: cada tipo de pedido calcula su tiempo distinto. */
    public abstract int calcularTiempoEntrega();

    /** Metodo ABSTRACTO: cada tipo asigna repartidor con su propia regla. */
    public abstract void asignarRepartidor();

    /**
     * SOBRECARGA de asignarRepartidor(): mismo nombre, distinto parametro.
     * Permite asignar un repartidor de forma manual.
     */
    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
        System.out.println("Repartidor asignado manualmente: " + nombre);
    }

    /**
     * Metodo IMPLEMENTADO que heredan todas las subclases sin reescribirlo.
     */
    public void mostrarResumen() {
        System.out.println("Pedido #" + numeroPedido);
        System.out.println("Direccion: " + direccion);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor asignado: " + repartidor);
        System.out.println("Tiempo estimado: " + calcularTiempoEntrega() + " minutos");
    }

    /** Getter usado por el controlador para registrar el historial. */
    public int getNumeroPedido() {
        return numeroPedido;
    }

    /** Getter usado por el controlador para registrar el historial. */
    public String getRepartidor() {
        return repartidor;
    }

    /** Indica si el pedido ya fue cancelado. */
    public boolean estaCancelado() {
        return cancelado;
    }
}