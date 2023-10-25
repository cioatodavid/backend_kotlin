package br.upf.schemaflow.converter

import br.upf.schemaflow.dto.RelationDTO
import br.upf.schemaflow.entity.RelationEntity
import org.springframework.stereotype.Component

@Component
class RelationConverter(
    private val entityConverter: EntityConverter
) {
    fun convertToEntity(relationDTO: RelationDTO): RelationEntity {
        return RelationEntity(
            id = relationDTO.id ?: 0,
            type = relationDTO.type,
            cardinality = relationDTO.cardinality,
            fromEntity = entityConverter.convertToEntity(relationDTO.fromEntity),
            toEntity = entityConverter.convertToEntity(relationDTO.toEntity)
        )
    }

    fun convertToDTO(relationEntity: RelationEntity): RelationDTO {
        return RelationDTO(
            id = relationEntity.id,
            type = relationEntity.type,
            cardinality = relationEntity.cardinality,
            fromEntity = entityConverter.convertToDTO(relationEntity.fromEntity),
            toEntity = entityConverter.convertToDTO(relationEntity.toEntity)
        )
    }
}
