package br.upf.schemaflow.repository

import br.upf.schemaflow.entity.AttributeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttributeRepository : JpaRepository<AttributeEntity, Long> {}