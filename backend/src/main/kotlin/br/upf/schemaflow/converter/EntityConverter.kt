package br.upf.schemaflow.converter

import br.upf.schemaflow.dto.EntityDTO
import br.upf.schemaflow.entity.EntityEntity
import br.upf.schemaflow.repository.SchemaRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class EntityConverter(
    @Lazy private val attributeConverter: AttributeConverter,
    private val schemaRepository: SchemaRepository,
) {
    fun convertToEntity(entityDTO: EntityDTO): EntityEntity {
        return EntityEntity(
            id = entityDTO.id ?: 0,
            name = entityDTO.name,
            positionX = entityDTO.positionX,
            positionY = entityDTO.positionY,
            schema = schemaRepository.findById(entityDTO.schemaId).get(),
            attributes = entityDTO.attributes.map { attributeConverter.convertToEntity(it) }
        )
    }

    fun convertToDTO(entityEntity: EntityEntity): EntityDTO {
        return EntityDTO(
            id = entityEntity.id,
            name = entityEntity.name,
            positionX = entityEntity.positionX,
            positionY = entityEntity.positionY,
            schemaId = entityEntity.schema.id,
            attributes = entityEntity.attributes.map { attributeConverter.convertToDTO(it) }
        )
    }
}
