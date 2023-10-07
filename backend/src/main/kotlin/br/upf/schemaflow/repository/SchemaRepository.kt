package br.upf.schemaflow.repository

import br.upf.schemaflow.entity.SchemaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SchemaRepository : JpaRepository<SchemaEntity, Long> {
}