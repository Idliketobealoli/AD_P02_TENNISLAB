package repositories.personalizadora

import entities.EncordadoraDao
import entities.MaquinaDao
import entities.PersonalizadoraDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import mappers.fromPersonalizadoraDaoToPersonalizadora
import models.Personalizadora
import models.enums.TipoMaquina
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class PersonalizadoraRepositoryImpl(
    private val personalizadoraDao: UUIDEntityClass<PersonalizadoraDao>,
    private val maquinaDao: UUIDEntityClass<MaquinaDao>
): IPersonalizadoraRepository {
    override suspend fun readAll(): Flow<Personalizadora> = newSuspendedTransaction(Dispatchers.IO) {
        personalizadoraDao.all().map { it.fromPersonalizadoraDaoToPersonalizadora(maquinaDao)}.asFlow()
    }

    override suspend fun findById(id: UUID): Deferred<Personalizadora?> = suspendedTransactionAsync(Dispatchers.IO) {
        personalizadoraDao.findById(id)?.fromPersonalizadoraDaoToPersonalizadora(maquinaDao)
    }

    override suspend fun create(entity: Personalizadora): Deferred<Personalizadora> = suspendedTransactionAsync(Dispatchers.IO) {
        val existe = personalizadoraDao.findById(entity.id)
        existe?.let { update(entity, it) } ?: run { insert(entity) }
    }

    fun insert(entity: Personalizadora): Personalizadora {
        return personalizadoraDao.new(entity.id) {
            measuresManeuverability = entity.measuresManeuverability
            measuresBalance = entity.measuresBalance
            measuresRigidity = entity.measuresRigidity
        }.fromPersonalizadoraDaoToPersonalizadora(entity.modelo, entity.marca, entity.fechaAdquisicion, entity.numeroSerie)
    }

    private fun update(entity: Personalizadora, existe: PersonalizadoraDao): Personalizadora {
        return existe.apply {
            measuresManeuverability = entity.measuresManeuverability
            measuresBalance = entity.measuresBalance
            measuresRigidity = entity.measuresRigidity
        }.fromPersonalizadoraDaoToPersonalizadora(entity.modelo, entity.marca, entity.fechaAdquisicion, entity.numeroSerie)
    }

    override suspend fun delete(entity: Personalizadora): Deferred<Boolean> = suspendedTransactionAsync(Dispatchers.IO) {
        val existe = personalizadoraDao.findById(entity.id) ?: return@suspendedTransactionAsync false
        val maquina = maquinaDao.findById(entity.id) ?: return@suspendedTransactionAsync false
        if (TipoMaquina.parseTipoMaquina(maquina.tipoMaquina) != TipoMaquina.PERSONALIZADORA)
            return@suspendedTransactionAsync false
        else {
            maquina.delete()
            existe.delete()
            true
        }
    }
}