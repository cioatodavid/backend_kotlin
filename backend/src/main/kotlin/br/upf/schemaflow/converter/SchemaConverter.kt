package br.upf.schemaflow.converter

import br.upf.schemaflow.dto.SchemaDTO
import br.upf.schemaflow.dto.SchemaResponseDTO
import br.upf.schemaflow.entity.SchemaEntity

import org.springframework.stereotype.Component

@Component
class SchemaConverter(
    private val userConverter: UserConverter,
    private val entityConverter: EntityConverter,
) {
    fun convertToEntity(schemaDTO: SchemaDTO): SchemaEntity {
        return SchemaEntity(
            id = schemaDTO.id ?: 0,
            name = schemaDTO.name,
            user = userConverter.convertToEntity(schemaDTO.user),
            entities = schemaDTO.entities.map { entityConverter.convertToEntity(it) },
        )
    }

    fun convertToDTO(schemaEntity: SchemaEntity): SchemaDTO {
        return SchemaDTO(
            id = schemaEntity.id,
            name = schemaEntity.name,
            user = userConverter.convertToDTO(schemaEntity.user),
            entities = schemaEntity.entities.map { entityConverter.convertToDTO(it) },
        )
    }

    fun convertToResponseDTO(schemaEntity: SchemaEntity): SchemaResponseDTO {
        return SchemaResponseDTO(
            id = schemaEntity.id,
            name = schemaEntity.name,
            entities = schemaEntity.entities.map { entityConverter.convertToResponseDTO(it) },
        )
    }
}
