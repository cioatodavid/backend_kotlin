package br.upf.schemaflow.service

import br.upf.schemaflow.converter.SchemaConverter
import br.upf.schemaflow.dto.*
import br.upf.schemaflow.repository.*
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchemaService(
    private val schemaRepository: SchemaRepository,
    private val schemaConverter: SchemaConverter,
) {
    @Transactional
    fun createSchema(schemaDTO: SchemaDTO): SchemaDTO {
        val schemaEntity = schemaConverter.convertToEntity(schemaDTO)
        val savedSchema = schemaRepository.save(schemaEntity)
        return schemaConverter.convertToDTO(savedSchema)
    }

    fun getSchemaById(id: Long): SchemaDTO {
        val schemaEntity = schemaRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Schema not found with id: $id") }
        return schemaConverter.convertToDTO(schemaEntity)
    }

    @Transactional
    fun updateSchema(id: Long, schemaDTO: SchemaDTO): SchemaDTO {
        val existingSchema = schemaRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Schema not found with id: $id") }

        // Update the fields of existingSchema with the fields from schemaDTO
        // ...

        val updatedSchema = schemaRepository.save(existingSchema)
        return schemaConverter.convertToDTO(updatedSchema)
    }

    @Transactional
    fun deleteSchema(id: Long) {
        schemaRepository.deleteById(id)
    }
}
