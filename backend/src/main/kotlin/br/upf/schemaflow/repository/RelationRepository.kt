package br.upf.schemaflow.repository

import br.upf.schemaflow.entity.RelationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RelationRepository: JpaRepository<RelationEntity, Long> {
}