package br.upf.schemaflow.service

import br.upf.schemaflow.converter.AttributeConverter
import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.repository.AttributeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AttributeService(
    private val attributeRepository: AttributeRepository,
    private val attributeConverter: AttributeConverter
) {
    @Transactional
    fun createAttribute(attributeDTO: AttributeDTO): AttributeDTO {
        // Convert DTO to Entity
        val attributeEntity = attributeConverter.convertToEntity(attributeDTO)

        // Save AttributeEntity
        val savedAttribute = attributeRepository.save(attributeEntity)

        // Convert Entity to DTO and return
        return attributeConverter.convertToDTO(savedAttribute)
    }

    fun getAttributeById(id: Long): AttributeDTO {
        val attributeEntity = attributeRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Attribute not found with id: $id") }
        return attributeConverter.convertToDTO(attributeEntity)
    }

    @Transactional
    fun updateAttribute(id: Long, attributeDTO: AttributeDTO): AttributeDTO {
        val existingAttribute = attributeRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Attribute not found with id: $id") }

        // Update the fields of existingAttribute with the fields from attributeDTO
        // ...

        val updatedAttribute = attributeRepository.save(existingAttribute)
        return attributeConverter.convertToDTO(updatedAttribute)
    }

    @Transactional
    fun deleteAttribute(id: Long) {
        attributeRepository.deleteById(id)
    }
}
