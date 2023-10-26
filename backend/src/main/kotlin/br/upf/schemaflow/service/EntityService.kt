package br.upf.schemaflow.service

import br.upf.schemaflow.converter.EntityConverter
import br.upf.schemaflow.dto.EntityDTO
import br.upf.schemaflow.dto.EntityResponseDTO
import br.upf.schemaflow.repository.AttributeRepository
import br.upf.schemaflow.repository.EntityRepository
import br.upf.schemaflow.repository.SchemaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.awt.print.Pageable

@Service
class EntityService(
    private val entityRepository: EntityRepository,
    private val entityConverter: EntityConverter,
    private val schemaRepository: SchemaRepository,
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


    fun getEntityById(id: Long): EntityResponseDTO {
        val entityEntity = entityRepository.findById(id).orElseThrow()
        return entityConverter.convertToResponseDTO(entityEntity)
    }

    fun getAllEntities(page: Int, size: Int): Page<EntityResponseDTO> {
        val entities = entityRepository.findAll(PageRequest.of(page, size))
        return entities.map { entityConverter.convertToResponseDTO(it) }
    }

    @Transactional
    fun updateEntity(id: Long, entityDTO: EntityDTO): EntityResponseDTO {
        val entityEntity = entityRepository.findById(id).orElseThrow()
        entityEntity.name = entityDTO.name
        entityEntity.positionX = entityDTO.positionX
        entityEntity.positionY = entityDTO.positionY
        val savedEntity = entityRepository.save(entityEntity)
        return entityConverter.convertToResponseDTO(savedEntity)
    }

    @Transactional
    fun deleteEntity(id: Long): String {
        val entityEntity = entityRepository.getReferenceById(id)
        entityRepository.delete(entityEntity)
        return "Entity with id $id deleted successfully"
    }
}
