package br.upf.schemaflow.repository

import br.upf.schemaflow.entity.EntityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EntityRepository : JpaRepository<EntityEntity, Long> {
}