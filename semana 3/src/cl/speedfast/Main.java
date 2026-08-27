package cl.speedfast;

import cl.speedfast.gestores.ControladorDeEnvios;
import cl.speedfast.modelo.Pedido;
import cl.speedfast.modelo.PedidoComida;
import cl.speedfast.modelo.PedidoEncomienda;
import cl.speedfast.modelo.PedidoExpress;

/**
 * Punto de entrada del sistema SpeedFast.
 * Simula el ciclo completo de gestion de pedidos.
 */
public class Main {

    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        // Se crean los tres tipos de pedido
        Pedido comida = new PedidoComida(101, "Av. Los Leones 1234", 3.5);
        Pedido encomienda = new PedidoEncomienda(102, "Av. Santa Rosa 567", 7);
        PedidoExpress express = new PedidoExpress(103, "Calle Prat 89", 2);

        controlador.registrarPedido(comida);
        controlador.registrarPedido(encomienda);
        controlador.registrarPedido(express);

        System.out.println("===== CASO 1: PEDIDO DE COMIDA (asignacion automatica) =====");
        controlador.procesarPedido(comida);

        System.out.println();
        System.out.println("===== CASO 2: ENCOMIENDA (asignacion manual) =====");
        encomienda.asignarRepartidor("Carla Rojas");
        controlador.procesarPedido(encomienda);

        System.out.println();
        System.out.println("===== CASO 3: PEDIDO EXPRESS =====");
        controlador.procesarPedido(express);

        System.out.println();
        System.out.println("===== CASO 4: CANCELACION DE UN PEDIDO =====");
        controlador.cancelarPedido(express, "El cliente ya no se encuentra en el domicilio");

        System.out.println();
        System.out.println("--- Se intenta cancelar el mismo pedido otra vez ---");
        controlador.cancelarPedido(express, "Solicitud duplicada");

        System.out.println();
        System.out.println("===== CASO 5: HISTORIAL GENERAL DE ENTREGAS =====");
        for (String evento : controlador.verHistorial()) {
            System.out.println("- " + evento);
        }

        System.out.println();
        System.out.println("===== HISTORIAL INTERNO DEL PEDIDO EXPRESS =====");
        for (String evento : express.verHistorial()) {
            System.out.println("- " + evento);
        }
    }
}