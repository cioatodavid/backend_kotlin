package br.upf.schemaflow.converter

import br.upf.schemaflow.dto.SchemaDTO
import br.upf.schemaflow.entity.SchemaEntity
import br.upf.schemaflow.repository.EntityRepository
import br.upf.schemaflow.repository.RelationRepository
import org.springframework.stereotype.Component

@Component
class SchemaConverter(
    private val entityConverter: EntityConverter,
    private val relationConverter: RelationConverter,
    private val userConverter: UserConverter,
    private val entityRepository: EntityRepository,
    private val relationRepository: RelationRepository
) {
    fun convertToEntity(schemaDTO: SchemaDTO): SchemaEntity {
        return SchemaEntity(
            id = schemaDTO.id ?: 0,
            name = schemaDTO.name,
            user = userConverter.convertToEntity(schemaDTO.username),
            entities = schemaDTO.entities.map { entityRepository.findById(it).get() },
            relations = schemaDTO.relations.map { relationRepository.findById(it).get() }
        )
    }

    fun convertToDTO(schemaEntity: SchemaEntity): SchemaDTO {
        return SchemaDTO(
            id = schemaEntity.id,
            name = schemaEntity.name,
            username = userConverter.convertToDTO(schemaEntity.user),
            entities = schemaEntity.entities.map { it.id },
            relations = schemaEntity.relations.map { it.id }
        )
    }
}
