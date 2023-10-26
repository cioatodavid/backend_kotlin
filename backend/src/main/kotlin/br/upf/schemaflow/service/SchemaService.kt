package br.upf.schemaflow.service

import br.upf.schemaflow.converter.EntityConverter
import br.upf.schemaflow.converter.SchemaConverter
import br.upf.schemaflow.dto.*
import br.upf.schemaflow.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchemaService(
    private val schemaRepository: SchemaRepository,
    private val schemaConverter: SchemaConverter,
) {
    fun createSchema(schemaDTO: SchemaDTO): SchemaResponseDTO {
        if (schemaDTO.id != null) {
            throw Exception("Schema id must be null")
        }
        val schemaEntity = schemaConverter.convertToEntity(schemaDTO)
        val savedSchemaEntity = schemaRepository.save(schemaEntity)
        return schemaConverter.convertToResponseDTO(savedSchemaEntity)
    }


    fun getSchemaById(id: Long): SchemaResponseDTO {
        val schemaEntity = schemaRepository.getReferenceById(id)
        return schemaConverter.convertToResponseDTO(schemaEntity)
    }

    @Transactional
    fun updateSchema(id: Long, string: String): SchemaResponseDTO {
        if (!schemaRepository.existsById(id)) {
            throw Exception("Schema not found")
        } else if (string.isBlank()) {
            throw Exception("Schema name cannot be blank")
        }
        val schemaEntity = schemaRepository.getReferenceById(id)
        schemaEntity.name = string
        val savedSchemaEntity = schemaRepository.save(schemaEntity)
        return schemaConverter.convertToResponseDTO(savedSchemaEntity)
    }

    @Transactional
    fun deleteSchema(id: Long) {
        if (schemaRepository.existsById(id)) {
            schemaRepository.deleteById(id)
        } else {
            throw Exception("Schema not found")
        }
    }
}
