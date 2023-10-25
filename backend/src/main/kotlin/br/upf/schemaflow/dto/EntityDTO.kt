package br.upf.schemaflow.dto

data class EntityDTO(
    val id: Long?,
    val name: String,
    val positionX: Double,
    val positionY: Double,
    val attributes: List<AttributeDTO>,
    val schema: SchemaReferenceDTO
)
