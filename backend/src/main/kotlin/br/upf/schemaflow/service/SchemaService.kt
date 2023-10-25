package br.upf.schemaflow.service

import br.upf.schemaflow.dto.*
import br.upf.schemaflow.entity.*
import br.upf.schemaflow.repository.*
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchemaService(
    private val schemaRepository: SchemaRepository,
    private val entityRepository: EntityRepository,
    private val attributeRepository: AttributeRepository,
    private val userRepository: UserRepository,
    private val relationRepository: RelationRepository
) {

    @Transactional
    fun createSchema(schemaDTO: SchemaDTO): SchemaDTO {
        val schema = convertToEntity(schemaDTO)
        val savedSchema = schemaRepository.save(schema)
        return convertToDTO(savedSchema)
    }

    @Transactional
    fun updateSchema(id: Long, schemaDTO: SchemaDTO): SchemaDTO {
        val existingSchema = schemaRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Schema not found with id: $id") }

        updateSchemaEntity(existingSchema, schemaDTO)
        val savedSchema = schemaRepository.save(existingSchema)
        return convertToDTO(savedSchema)
    }

    fun getSchema(id: Long): SchemaDTO {
        val schema = schemaRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Schema not found with id: $id") }
        return convertToDTO(schema)
    }

    @Transactional
    fun deleteSchema(id: Long) {
        val schema = schemaRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Schema not found with id: $id") }
        schemaRepository.delete(schema)
    }

    private fun convertToEntity(schemaDTO: SchemaDTO): SchemaEntity {
        val user = userRepository.findByUsername(schemaDTO.user.username)
            .orElseThrow { EntityNotFoundException("User not found with username: ${schemaDTO.user.username}") }

        val entities = schemaDTO.entities.map { convertToEntity(it) }
        val relations = schemaDTO.relations.map { convertToEntity(it) }

        return SchemaEntity(
            id = schemaDTO.id,
            name = schemaDTO.name,
            user = user,
            entities = entities,
            relations = relations
        )
    }

    private fun convertToEntity(entityDTO: EntityDTO): EntityEntity {
        val attributes = entityDTO.attributes.map { convertToEntity(it) }
        return EntityEntity(
            id = entityDTO.id,
            name = entityDTO.name,
            positionX = entityDTO.positionX,
            positionY = entityDTO.positionY,
            schema = schemaRepository.findById(entityDTO.schema.id)
                .orElseThrow { EntityNotFoundException("Schema not found with id: ${entityDTO.schema.id}") },
            attributes = attributes
        )
    }

    private fun convertToEntity(attributeDTO: AttributeDTO): AttributeEntity {
        return AttributeEntity(
            id = attributeDTO.id!!,
            name = attributeDTO.name,
            dataType = attributeDTO.dataType,
            size = attributeDTO.size!!,
            isPrimaryKey = attributeDTO.isPrimaryKey,
            isForeignKey = attributeDTO.isForeignKey,
            isNullable = attributeDTO.isNullable,
            isUnique = attributeDTO.isUnique,
            defaultValue = attributeDTO.defaultValue,
            entity = entityRepository.findById(attributeDTO.entity.id)
                .orElseThrow { EntityNotFoundException("Entity not found with id: ${attributeDTO.entity.id}") }
        )
    }

    private fun convertToEntity(relationDTO: RelationDTO): RelationEntity {
        val fromEntity = entityRepository.findById(relationDTO.fromEntity?.id ?: 0)
            .orElseThrow { EntityNotFoundException("Entity not found with id: ${relationDTO.fromEntity?.id}") }
        val toEntity = entityRepository.findById(relationDTO.toEntity?.id ?: 0)
            .orElseThrow { EntityNotFoundException("Entity not found with id: ${relationDTO.toEntity?.id}") }

        return RelationEntity(
            id = relationDTO.id!!,
            type = relationDTO.type,
            cardinality = relationDTO.cardinality,
            fromEntity = fromEntity,
            toEntity = toEntity
        )
    }

    private fun updateSchemaEntity(existingSchema: SchemaEntity, schemaDTO: SchemaDTO) {
        existingSchema.name = schemaDTO.name
        // Update other fields as necessary
    }

    private fun convertToDTO(schema: SchemaEntity): SchemaDTO {
        val userDTO = UserDTO(
            id = schema.user.id,
            username = schema.user.username,
        )

        val entitiesDTO = schema.entities.map { convertToDTO(it) }
        val relationsDTO = schema.relations.map { convertToDTO(it) }

        return SchemaDTO(
            id = schema.id,
            name = schema.name,
            user = userDTO,
            entities = entitiesDTO,
            relations = relationsDTO,
        )
    }

    private fun convertToDTO(entity: EntityEntity): EntityDTO {
        val attributesDTO = entity.attributes.map { convertToDTO(it) }
        val schemaDTO = SchemaReferenceDTO(
            id = entity.schema.id,
        )

        return EntityDTO(
            id = entity.id,
            name = entity.name,
            positionX = entity.positionX,
            positionY = entity.positionY,
            attributes = attributesDTO,
            schema = schemaDTO
        )
    }

    private fun convertToDTO(attribute: AttributeEntity): AttributeDTO {
        val entityReferenceDTO = EntityReferenceDTO(
            id = attribute.entity.id!!,
            name = attribute.entity.name
        )
        return AttributeDTO(
            id = attribute.id,
            name = attribute.name,
            dataType = attribute.dataType,
            size = attribute.size,
            isPrimaryKey = attribute.isPrimaryKey,
            isForeignKey = attribute.isForeignKey,
            isNullable = attribute.isNullable,
            isUnique = attribute.isUnique,
            defaultValue = attribute.defaultValue,
            entity = entityReferenceDTO
        )
    }

    private fun convertToDTO(relation: RelationEntity): RelationDTO {
        val fromEntityDTO = EntityReferenceDTO(
            id = relation.fromEntity.id!!,
            name = relation.fromEntity.name
        )

        val toEntityDTO = EntityReferenceDTO(
            id = relation.toEntity.id!!,
            name = relation.toEntity.name
        )

        return RelationDTO(
            id = relation.id,
            type = relation.type,
            cardinality = relation.cardinality,
            fromEntity = fromEntityDTO,
            toEntity = toEntityDTO
        )
    }
}
