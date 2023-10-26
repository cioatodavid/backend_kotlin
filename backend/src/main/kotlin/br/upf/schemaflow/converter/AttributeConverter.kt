package br.upf.schemaflow.converter

import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.entity.AttributeEntity
import br.upf.schemaflow.repository.EntityRepository
import org.springframework.stereotype.Component

@Component
class AttributeConverter(
    private val entityRepository: EntityRepository
) {
    fun convertToEntity(attributeDTO: AttributeDTO): AttributeEntity {
        return AttributeEntity(
            id = attributeDTO.id ?: 0,
            name = attributeDTO.name,
            dataType = attributeDTO.dataType,
            size = attributeDTO.size,
            isPrimaryKey = attributeDTO.isPrimaryKey,
            isForeignKey = attributeDTO.isForeignKey,
            isNullable = attributeDTO.isNullable,
            isUnique = attributeDTO.isUnique,
            defaultValue = attributeDTO.defaultValue,
            entity = attributeDTO.entityId.let { entityRepository.findById(it!!).orElse(null) }
        )
    }

    fun convertToDTO(attributeEntity: AttributeEntity): AttributeDTO {
        return AttributeDTO(
            id = attributeEntity.id,
            name = attributeEntity.name,
            dataType = attributeEntity.dataType,
            size = attributeEntity.size,
            isPrimaryKey = attributeEntity.isPrimaryKey,
            isForeignKey = attributeEntity.isForeignKey,
            isNullable = attributeEntity.isNullable,
            isUnique = attributeEntity.isUnique,
            defaultValue = attributeEntity.defaultValue ?: "",
            entityId = attributeEntity.entity.id
        )
    }
}
