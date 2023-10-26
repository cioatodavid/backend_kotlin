package br.upf.schemaflow.service

import br.upf.schemaflow.converter.AttributeConverter
import br.upf.schemaflow.converter.EntityConverter
import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.dto.EntityResponseDTO
import br.upf.schemaflow.repository.AttributeRepository
import br.upf.schemaflow.repository.EntityRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AttributeService(
    private val attributeRepository: AttributeRepository,
    private val attributeConverter: AttributeConverter,
    private val entityRepository: EntityRepository,
) {
    @Transactional
    fun createAttribute(entityId: Long, attributeDTO: AttributeDTO): AttributeDTO {
        if (attributeDTO.id != null) throw IllegalArgumentException("Attribute ID must be null")

        val entityEntity = entityRepository.findById(entityId).orElseThrow()
        val attributeEntity = attributeConverter.convertToEntity(attributeDTO)
        attributeEntity.entity = entityEntity
        val savedAttribute = attributeRepository.save(attributeEntity)
        return attributeConverter.convertToDTO(savedAttribute)
    }

    fun getAttributeById(id: Long) {

    }

    @Transactional
    fun updateAttribute(id: Long, attributeDTO: AttributeDTO) {

    }

    @Transactional
    fun deleteAttribute(id: Long) {

    }
}
