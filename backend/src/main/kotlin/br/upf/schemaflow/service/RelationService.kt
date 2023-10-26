package br.upf.schemaflow.service

import br.upf.schemaflow.converter.RelationConverter
import br.upf.schemaflow.dto.RelationDTO
import br.upf.schemaflow.repository.RelationRepository
import org.springframework.stereotype.Service

@Service
class RelationService(
    private val relationRepository: RelationRepository,
    private val relationConverter: RelationConverter
) {
    fun createRelation(relationDTO: RelationDTO) {
        relationRepository.save(relationConverter.convertToEntity(relationDTO))
    }
}
