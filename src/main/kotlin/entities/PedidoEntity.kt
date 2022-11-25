package entities

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date
import java.util.*

object PedidoTable: UUIDTable("PEDIDOS") {
    //val tareas = reference("tareas_ids", TareaTable)
    val client = reference("client_id", UserTable)
    //val turnos = reference("turnos_ids", TurnoTable)
    val state = varchar("state", 255)
    val fechaEntrada = date("fecha_entrada")
    val fechaProgramada = date("fecha_programada")
    val fechaSalida = date("fecha_salida")
    val fechaEntrega = date("fecha_entrega")
    val precio = double("precio").default(0.0)
}

class PedidoDao(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<PedidoDao>(PedidoTable)
    //val tareas by TareaDao referrersOn TareaTable.pedido
    var client by UserDao referencedOn PedidoTable.client
    //val turnos by TurnoDao referrersOn TurnoTable.pedido
    var state by PedidoTable.state
    var fechaEntrada by PedidoTable.fechaEntrada
    var fechaProgramada by PedidoTable.fechaProgramada
    var fechaSalida by PedidoTable.fechaSalida
    var fechaEntrega by PedidoTable.fechaEntrega
    var precio by PedidoTable.precio
}