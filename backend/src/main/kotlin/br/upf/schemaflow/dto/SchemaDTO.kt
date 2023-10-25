package br.upf.schemaflow.dto

data class SchemaDTO(
    val id: Long?,
    val username: String,
    val name: String,
    var entities: List<Long> = mutableListOf(),
    var relations: List<Long> = mutableListOf()
)
