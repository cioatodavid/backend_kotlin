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
    fun updateAttribute(id: Long, attributeDTO: AttributeDTO): AttributeResponseDTO {
        val attributeEntity = attributeRepository.findById(id).orElseThrow()
        attributeDTO.name.let { attributeEntity.name = it }
        attributeDTO.dataType.let { attributeEntity.dataType = it }
        attributeDTO.size.let { attributeEntity.size = it }
        attributeDTO.isPrimaryKey.let { attributeEntity.isPrimaryKey = it }
        attributeDTO.isForeignKey.let { attributeEntity.isForeignKey = it }
        attributeDTO.isNullable.let { attributeEntity.isNullable = it }
        attributeDTO.isUnique.let { attributeEntity.isUnique = it }
        attributeDTO.defaultValue.let { attributeEntity.defaultValue = it }

        val savedAttribute = attributeRepository.save(attributeEntity)
        return attributeConverter.convertToResponseDTO(savedAttribute)
    }

    @Transactional
    fun deleteAttribute(id: Long): String {
        val attributeEntity = attributeRepository.findById(id).orElseThrow()
        attributeRepository.delete(attributeEntity)
        return "Attribute with id $id deleted successfully"
    }
}
