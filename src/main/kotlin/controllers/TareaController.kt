package controllers

import com.google.gson.GsonBuilder
import dto.AdquisicionDTO
import dto.EncordadoDTO
import dto.PersonalizacionDTO
import dto.TareaDTO
import services.AdquisicionService
import services.EncordadoService
import services.PersonalizacionService
import util.generateRespuesta
import java.util.UUID

object TareaController {
    val aService = AdquisicionService()
    val eService = EncordadoService()
    val pService = PersonalizacionService()

    fun findAllTareas(): String {
        val adquisiciones = aService.getAllAdquisiciones()
        val encordados = eService.getAllEncordados()
        val personalizaciones = pService.getAllPersonalizaciones()
        val tareas: MutableList<TareaDTO> = mutableListOf()
        adquisiciones.forEach { tareas.add(it) }
        encordados.forEach { tareas.add(it) }
        personalizaciones.forEach { tareas.add(it) }
        val result = GsonBuilder().setPrettyPrinting().create()
            .toJson(tareas.toList())
            ?: "Error at TareaController.findAllTareas"
        return generateRespuesta(result, "Error at TareaController.findAllTareas")
    }

    fun getTareaById(id: UUID): String {
        var busqueda: TareaDTO? = aService.getAdquisicionById(id)
        return if (busqueda != null) {
            AdquisicionController.getAdquisicionById(id)
        }
        else {
            busqueda = eService.getEncordadoById(id)
            if (busqueda != null) {
                EncordadoController.getEncordadoById(id)
            }
            else {
                busqueda = pService.getPersonalizacionById(id)
                if (busqueda != null) {
                    PersonalizacionController.getPersonalizacionById(id)
                }
                else {
                    generateRespuesta(
                        "Tarea with id $id not found.",
                        "Tarea with id $id not found."
                    )
                }
            }
        }
    }

    fun insertTarea(dto: TareaDTO): String {
        return when (dto) {
            is AdquisicionDTO -> AdquisicionController.insertAdquisicion(dto)
            is EncordadoDTO -> EncordadoController.insertEncordado(dto)
            is PersonalizacionDTO -> PersonalizacionController.insertPersonalizacion(dto)
            else -> { generateRespuesta(
                "Error at TareaController.insertTarea: DTO not supported.",
                "Error at TareaController.insertTarea: DTO not supported."
            ) }
        }
    }

    fun deleteTarea(dto: TareaDTO): String {
        return when (dto) {
            is AdquisicionDTO -> AdquisicionController.deleteAdquisicion(dto)
            is EncordadoDTO -> EncordadoController.deleteEncordado(dto)
            is PersonalizacionDTO -> PersonalizacionController.deletePersonalizacion(dto)
            else -> { generateRespuesta(
                "Error at TareaController.deleteTarea: DTO not supported.",
                "Error at TareaController.deleteTarea: DTO not supported."
            ) }
        }
    }


    fun insertTareaInit(dto: TareaDTO): String {
        return when (dto) {
            is AdquisicionDTO -> AdquisicionController.insertAdquisicionInit(dto)
            is EncordadoDTO -> EncordadoController.insertEncordadoInit(dto)
            is PersonalizacionDTO -> PersonalizacionController.insertPersonalizacionInit(dto)
            else -> { generateRespuesta(
                "Error at TareaController.insertTarea: DTO not supported.",
                "Error at TareaController.insertTarea: DTO not supported."
            ) }
        }
    }
}