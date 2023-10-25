package br.upf.schemaflow.service

import br.upf.schemaflow.converter.EntityConverter
import br.upf.schemaflow.dto.EntityDTO
import br.upf.schemaflow.repository.EntityRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EntityService(
    private val entityRepository: EntityRepository,
    private val entityConverter: EntityConverter,
    private val attributeService: AttributeService
) {
    @Transactional
    fun createEntity(entityDTO: EntityDTO): EntityDTO {

        // Save child entities (attributes)
        val savedAttributes = entityDTO.attributes.map { attributeService.createAttribute(it) }
        entityDTO.attributes = savedAttributes
        // Convert DTO to Entity
        val entityEntity = entityConverter.convertToEntity(entityDTO)


        // Save EntityEntity
        val savedEntity = entityRepository.save(entityEntity)

        // Convert Entity to DTO and return
        return entityConverter.convertToDTO(savedEntity)
    }

    fun getEntityById(id: Long): EntityDTO {
        val entityEntity = entityRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $id") }
        return entityConverter.convertToDTO(entityEntity)
    }

    @Transactional
    fun updateEntity(id: Long, entityDTO: EntityDTO): EntityDTO {
        val existingEntity = entityRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity not found with id: $id") }

        // Update the fields of existingEntity with the fields from entityDTO
        // ...

        val updatedEntity = entityRepository.save(existingEntity)
        return entityConverter.convertToDTO(updatedEntity)
    }

    @Transactional
    fun deleteEntity(id: Long) {
        entityRepository.deleteById(id)
    }
}
