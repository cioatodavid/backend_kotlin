package br.upf.schemaflow.dto

data class RelationDTO(
    val id: Long?,
    val type: String,
    val cardinality: String,
    val fromEntity: EntityDTO,
    val toEntity: EntityDTO
)
