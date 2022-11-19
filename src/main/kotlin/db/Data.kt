package db

import models.*
import models.enums.PedidoEstado
import models.enums.Profile
import models.enums.TipoProducto
import util.encode
import java.time.LocalDate
import java.util.*


fun getUsers() = listOf(
    User(
        id = UUID.randomUUID(),
        nombre = "Luis",
        apellido = "Martinez",
        telefono = "632950281",
        email = "email@email.com",
        password = encode("estacontraseñanoestaensha512"),
        perfil = Profile.WORKER.toString()
    ),
    User(
        id = UUID.randomUUID(),
        nombre = "Maria",
        apellido = "Martinez",
        telefono = "632120281",
        email = "email2@email.com",
        password = encode("contra"),
        perfil = Profile.CLIENT.toString()
    ),
    User(
        id = UUID.randomUUID(),
        nombre = "Admin",
        apellido = "Administrador",
        telefono = "000000000",
        email = "admin@email.com",
        password = encode("admin"),
        perfil = Profile.ADMIN.toString()
    )
)

/*
TODO: Meter mas usuarios y modificar/meter datos iniciales con las cosas del modelo que corresponden
fun getProducts() = listOf(
    Producto(
        id = UUID.randomUUID(),
        tipoProducto = TipoProducto.RAQUETAS,
        marca = "unaMarcaDePrueba",
        modelo = "unModeloDePrueba",
        precio = 12.3,
        stock = 5
    ),
    Producto(
        id = UUID.randomUUID(),
        tipoProducto = TipoProducto.ANTIVIBRADORES,
        marca = "antivibradorBranding",
        modelo = "antivibradorModel",
        precio = 5.3,
        stock = 23
    ),
    Producto(
        id = UUID.randomUUID(),
        tipoProducto = TipoProducto.FUNDAS,
        marca = "fundaBrand",
        modelo = "fundaModel",
        precio = 30.0,
        stock = 10
    )
)

fun getPersonalizadora() = listOf(
    Personalizadora(
        id = UUID.randomUUID(),
        measuresManeuverability = false,
        measuresBalance = true,
        measuresRigidity = true
    ),
    Personalizadora(
        id = UUID.randomUUID(),
        measuresManeuverability = true,
        measuresBalance = false,
        measuresRigidity = true
    ),
    Personalizadora(
        id = UUID.randomUUID(),
        measuresManeuverability = false,
        measuresBalance = true,
        measuresRigidity = false
    )
)

fun getMaquina() = listOf(
    Maquina(
        id = UUID.randomUUID(),
        modelo = "unModelitoMaquinon",
        marca = "unaMarcaMaquinona",
        fechaAdquisicion = LocalDate.now(),
        numeroSerie = "1123abc45de"
    ),
    Maquina(
        id = UUID.randomUUID(),
        modelo = "unModelitoMaquinon2",
        marca = "unaMarcaMaquinona2",
        fechaAdquisicion = LocalDate.now(),
        numeroSerie = "2123abc45de2"
    ),
    Maquina(
        id = UUID.randomUUID(),
        modelo = "unModelitoMaquinon3",
        marca = "unaMarcaMaquinona3",
        fechaAdquisicion = LocalDate.now(),
        numeroSerie = "3123abc45de3"
    )
)

fun getEncordadora() = listOf(
    Encordadora(
        id = UUID.randomUUID(),
        isManual = false,
        maxTension = 12.7,
        minTension = 7.3
    ),
    Encordadora(
        id = UUID.randomUUID(),
        isManual = true,
        maxTension = 20.0,
        minTension = 10.0
    ),
    Encordadora(
        id = UUID.randomUUID(),
        isManual = true,
        maxTension = 15.2,
        minTension = 5.2
    ),
)

fun getPedidos() = listOf(
    Pedido(
        id = UUID.randomUUID(),
        tareas = listOf<Tarea>(),
        client = User(),
        turnos = listOf<Turno>(),
        state = PedidoEstado.PROCESO,
        fechaEntrada = LocalDate.now(),
        fechaProgramada = LocalDate.of(2023, 3, 10),
        fechaSalida = LocalDate.of(2023, 3, 1),
        fechaEntrega = LocalDate.of(2023, 4, 1)
    )
)

 */
