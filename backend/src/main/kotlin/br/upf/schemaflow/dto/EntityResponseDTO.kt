package br.upf.schemaflow.dto


data class EntityResponseDTO(
    val id: Long?,
    val name: String,
    val positionX: Double,
    val positionY: Double,
    var attributes: List<AttributeDTO>? = listOf(),
)
