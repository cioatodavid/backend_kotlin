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
        val schemaEntity = schemaConverter.convertToEntity(schemaDTO)
        val savedSchema = schemaRepository.save(schemaEntity)
        return schemaConverter.convertToResponseDTO(savedSchema)
    }



    fun getSchemaById(id: Long) : SchemaResponseDTO {
        val schemaEntity = schemaRepository.getReferenceById(id)
        return schemaConverter.convertToResponseDTO(schemaEntity)
    }

    @Transactional
    fun updateSchema(id: Long, schemaDTO: SchemaDTO) {

    }

    @Transactional
    fun deleteSchema(id: Long) {
        schemaRepository.deleteById(id)
    }
}
