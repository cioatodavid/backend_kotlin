package br.upf.schemaflow.repository

import br.upf.schemaflow.entity.FieldRelationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FieldRelationRepository : JpaRepository<FieldRelationEntity, Long> {
}