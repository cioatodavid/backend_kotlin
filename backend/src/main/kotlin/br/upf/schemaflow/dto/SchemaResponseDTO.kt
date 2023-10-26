package br.upf.schemaflow.dto

data class SchemaResponseDTO(
    val id: Long?,
    val name: String,
    val entities: List<EntityResponseDTO> = listOf(),
)
