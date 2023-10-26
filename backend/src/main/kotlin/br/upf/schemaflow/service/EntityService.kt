package br.upf.schemaflow.service

import br.upf.schemaflow.converter.EntityConverter
import br.upf.schemaflow.dto.EntityDTO
import br.upf.schemaflow.dto.EntityResponseDTO
import br.upf.schemaflow.repository.EntityRepository
import br.upf.schemaflow.repository.SchemaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EntityService(
    private val entityRepository: EntityRepository,
    private val entityConverter: EntityConverter,
    private val schemaRepository: SchemaRepository
) {
    @Transactional
    fun createEntity(schemaId: Long, entityDTO: EntityDTO): EntityResponseDTO {
        if (entityDTO.id != null) throw IllegalArgumentException("Entity ID must be null")
        if (entityDTO.schema != null) throw IllegalArgumentException("Entity schema must be null")
        if (entityDTO.attributes?.isNotEmpty() == true) throw IllegalArgumentException("Entity attributes must be null")

        val schemaEntity = schemaRepository.findById(schemaId).orElseThrow()
        val entityEntity = entityConverter.convertToEntity(entityDTO)
        entityEntity.schema = schemaEntity
        val savedEntity = entityRepository.save(entityEntity)
        return entityConverter.convertToResponseDTO(savedEntity)
    }


    fun getEntityById(id: Long) {

    }

    @Transactional
    fun updateEntity(id: Long, entityDTO: EntityDTO) {

    }

    @Transactional
    fun deleteEntity(id: Long) {
        entityRepository.deleteById(id)
    }
}
