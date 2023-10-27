package br.upf.schemaflow.dto

data class SchemaDTO(
    val id: Long?,
    var user: UserDTO,
    val name: String,
    val entities: List<EntityDTO> = listOf(),
)
