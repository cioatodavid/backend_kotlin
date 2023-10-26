package br.upf.schemaflow.service

import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.repository.AttributeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AttributeService(
    private val attributeRepository: AttributeRepository,
) {
    @Transactional
    fun createAttribute(attributeDTO: AttributeDTO) {

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
