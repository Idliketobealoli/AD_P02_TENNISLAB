package models

import models.enums.TipoTarea
import java.util.*

class Adquisicion(): Tarea() {
    override var id: UUID = super.id
    lateinit var productoAdquirido: Producto

    constructor(
        id: UUID?,
        raqueta: Producto,
        user: User,
        productoAdquirido: Producto,
        precio: Double
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.raqueta = raqueta
        this.user = user
        this.productoAdquirido = productoAdquirido
        this.precio = precio
        this.tipoTarea = TipoTarea.ADQUISICION
    }

    constructor(
        id: UUID?,
        productoAdquirido: Producto,
        precio: Double
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.productoAdquirido = productoAdquirido
        this.precio = precio
        this.tipoTarea = TipoTarea.ADQUISICION
    }
}
