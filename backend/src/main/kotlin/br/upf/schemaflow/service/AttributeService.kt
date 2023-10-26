package br.upf.schemaflow.service

import br.upf.schemaflow.converter.AttributeConverter
import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.dto.AttributeResponseDTO
import br.upf.schemaflow.repository.AttributeRepository
import br.upf.schemaflow.repository.EntityRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.slf4j.LoggerFactory

@Service
class AttributeService(
    private val attributeRepository: AttributeRepository,
    private val attributeConverter: AttributeConverter,
    private val entityRepository: EntityRepository,
) {

    @Transactional
    fun createAttribute(entityId: Long, attributeDTO: AttributeDTO): AttributeResponseDTO {
        attributeDTO.entityId = entityId
        val attributeEntity = attributeConverter.convertToEntity(attributeDTO)
        val savedAttribute = attributeRepository.save(attributeEntity)
        return attributeConverter.convertToResponseDTO(savedAttribute)
    }


    fun getAttributeById(id: Long): AttributeResponseDTO {
        val attributeEntity = attributeRepository.findById(id).orElseThrow()
        return attributeConverter.convertToResponseDTO(attributeEntity)
    }


    @Transactional
    fun updateAttribute(id: Long, attributeDTO: AttributeResponseDTO) {

    }

    @Transactional
    fun deleteAttribute(id: Long) {

    }
}
