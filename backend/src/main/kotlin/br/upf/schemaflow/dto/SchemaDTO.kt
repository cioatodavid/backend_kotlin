package br.upf.schemaflow.dto

data class SchemaDTO(
    val id: Long?,
    val user: UserDTO,
    val name: String,
    val entities: List<EntityDTO> = listOf(),
)
