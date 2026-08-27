# SpeedFast - Sistema de Gestión de Entregas

Prototipo de sistema modular en Java para la empresa de repartos **SpeedFast**, que gestiona pedidos de distintos tipos (comida, encomiendas y entregas express), su asignación de repartidores, despacho, cancelación y seguimiento, aplicando principios avanzados de la Programación Orientada a Objetos.

## Descripción general

SpeedFast gestiona pedidos con reglas de negocio distintas según su tipo: un pedido de comida requiere tiempo de preparación, una encomienda avanza más lento y un envío express tiene prioridad máxima. Resolver esto con condicionales dentro de una sola clase produce código rígido, difícil de mantener y que hay que modificar cada vez que se agrega un tipo nuevo.

Este prototipo resuelve el problema mediante una jerarquía de clases encabezada por una clase abstracta, donde cada tipo de pedido define su propia lógica de asignación y cálculo de tiempo. Las capacidades funcionales del sistema —despachar, cancelar y rastrear— se declaran en interfaces independientes, de modo que cada clase implemente únicamente lo que necesita.

Un controlador central coordina todas las operaciones sin conocer los tipos concretos de pedido, lo que permite incorporar nuevas categorías sin modificar el código existente.

## Estructura de paquetes y clases

```
semana 3/
├── src/
│   └── cl/speedfast/
│       ├── Main.java                     # Punto de entrada y simulación
│       ├── modelo/                       # Clases del dominio
│       │   ├── Pedido.java               # Superclase abstracta
│       │   ├── PedidoComida.java         # Hereda de Pedido
│       │   ├── PedidoEncomienda.java     # Hereda de Pedido
│       │   └── PedidoExpress.java        # Hereda de Pedido e implementa Rastreable
│       ├── interfaces/                   # Contratos del sistema
│       │   ├── Despachable.java          # Interfaz: despachar()
│       │   ├── Cancelable.java           # Interfaz: cancelar(String)
│       │   └── Rastreable.java           # Interfaz: verHistorial()
│       └── gestores/
│           └── ControladorDeEnvios.java  # Orquesta las operaciones
└── README.md
```

## Resumen de las clases principales

- **`interfaces/Despachable`**: declara `despachar()`. Define el contrato para todo objeto que pueda enviarse a destino.
- **`interfaces/Cancelable`**: declara `cancelar(String motivo)`, que retorna `boolean`. El motivo permite trazabilidad y el retorno le informa al sistema si la operación fue posible.
- **`interfaces/Rastreable`**: declara `verHistorial()`, que retorna `List<String>`. Cada implementación decide qué eventos registrar.
- **`modelo/Pedido`**: superclase abstracta. Implementa `Despachable` y `Cancelable`, define los atributos comunes (número, dirección, distancia, repartidor, estado) y el método `mostrarResumen()` ya implementado. Declara abstractos `calcularTiempoEntrega()` y `asignarRepartidor()`, además de la sobrecarga `asignarRepartidor(String nombre)` para asignación manual.
- **`modelo/PedidoComida`**: hereda de `Pedido` usando `super(...)`. Suma 10 minutos de preparación al tiempo de entrega y advierte mantener el pedido caliente al despachar.
- **`modelo/PedidoEncomienda`**: hereda de `Pedido`. Aplica un tiempo de entrega mayor por tratarse de carga, sin preparación previa.
- **`modelo/PedidoExpress`**: hereda de `Pedido` e implementa además `Rastreable`. Calcula el tiempo más corto del sistema y mantiene un historial interno propio que registra cada operación realizada sobre el pedido.
- **`gestores/ControladorDeEnvios`**: implementa `Rastreable`. Mantiene un `ArrayList<Pedido>` con los pedidos registrados y un historial general de entregas. Procesa y cancela pedidos operando sobre la clase base, sin conocer sus tipos concretos ni usar condicionales por tipo.
- **`Main`**: punto de entrada. Simula el ciclo completo del sistema con casos diferenciados.

## Funcionalidades del sistema

1. Registrar pedidos de distinto tipo en el controlador.
2. Asignar repartidor de forma automática según las reglas de cada tipo de pedido.
3. Asignar repartidor de forma manual mediante sobrecarga, respetando la asignación previa.
4. Calcular el tiempo estimado de entrega con la fórmula propia de cada tipo.
5. Mostrar el resumen del pedido con un formato común heredado de la superclase.
6. Despachar el pedido con un mensaje diferenciado según su categoría.
7. Cancelar un pedido validando que no haya sido cancelado antes.
8. Consultar el historial general de entregas y cancelaciones del sistema.
9. Consultar el historial interno de un pedido express, incluidos los intentos fallidos.

## Conceptos aplicados

- **Abstracción:** `Pedido` es una clase abstracta que no puede instanciarse, ya que un pedido genérico no existe en el negocio. Define `mostrarResumen()` implementado y `calcularTiempoEntrega()` abstracto.
- **Herencia:** `Pedido` → `PedidoComida`, `PedidoEncomienda`, `PedidoExpress`, reutilizando cinco atributos y el constructor mediante `super(...)`.
- **Interfaces:** `Despachable` y `Cancelable` implementadas por `Pedido` y heredadas por sus tres subclases; `Rastreable` implementada por dos clases sin relación de herencia entre sí (`PedidoExpress` y `ControladorDeEnvios`).
- **Sobrescritura:** las tres subclases redefinen con `@Override` los métodos heredados. Una misma llamada produce 24, 42 y 4 minutos según el tipo real del objeto.
- **Sobrecarga:** `asignarRepartidor()` y `asignarRepartidor(String nombre)`, para asignación automática y manual respectivamente.
- **Desacoplamiento:** `ControladorDeEnvios.procesarPedido()` atiende cualquier tipo de pedido sin un solo condicional por tipo. Agregar un `PedidoRefrigerado` no requiere modificar el controlador.
- **Encapsulamiento:** atributos `protected` en la superclase para permitir reutilización, `private` en el historial interno, con acceso controlado mediante getters.
- **Colecciones:** `ArrayList` y `List<String>` para el registro de pedidos e historiales. `verHistorial()` retorna una copia defensiva para proteger el registro original.

## Cómo clonar y ejecutar el proyecto

### Clonar el repositorio

```
git clone https://github.com/99slckr/SistemaSpeedFast.git
cd "SistemaSpeedFast/semana 3"
```

### Desde IntelliJ IDEA

1. Abrir la carpeta `semana 3` en IntelliJ IDEA (**File → Open** y seleccionar esa carpeta).
2. Abrir `src/cl/speedfast/Main.java`.
3. Hacer clic en el botón Run junto al método `main`.

### Desde la terminal

Desde la carpeta `semana 3`:

```
javac -d out src/cl/speedfast/interfaces/*.java src/cl/speedfast/modelo/*.java src/cl/speedfast/gestores/*.java src/cl/speedfast/Main.java
java -cp out cl.speedfast.Main
```

## Requisitos

- JDK 17 o superior.

## Autor

- Autor: Andrés Acuña
- Asignatura: Desarrollo Orientado a Objetos II
- Actividad Sumativa 1 - Semana 3