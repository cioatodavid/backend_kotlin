package br.upf.schemaflow.converter

import br.upf.schemaflow.dto.EntityDTO
import br.upf.schemaflow.dto.EntityResponseDTO
import br.upf.schemaflow.entity.EntityEntity
import br.upf.schemaflow.repository.SchemaRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Component
class EntityConverter(
    @Lazy private val attributeConverter: AttributeConverter,
) {
    fun convertToEntity(entityDTO: EntityDTO): EntityEntity {
        return EntityEntity(
            id = entityDTO.id ?: 0,
            name = entityDTO.name,
            positionX = entityDTO.positionX,
            positionY = entityDTO.positionY,
            schema = null,
            attributes = entityDTO.attributes?.map { attributeConverter.convertToEntity(it) }
        )
    }

    fun convertToDTO(entityEntity: EntityEntity): EntityDTO {
        return EntityDTO(
            id = entityEntity.id,
            name = entityEntity.name,
            positionX = entityEntity.positionX,
            positionY = entityEntity.positionY,
            attributes = entityEntity.attributes?.map { attributeConverter.convertToDTO(it) },
            schema = null
        )
    }

    fun convertToResponseDTO(entityEntity: EntityEntity): EntityResponseDTO {
        return EntityResponseDTO(
            id = entityEntity.id,
            name = entityEntity.name,
            positionX = entityEntity.positionX,
            positionY = entityEntity.positionY,
            attributes = entityEntity.attributes?.map { attributeConverter.convertToResponseDTO(it) }
        )
    }
}
