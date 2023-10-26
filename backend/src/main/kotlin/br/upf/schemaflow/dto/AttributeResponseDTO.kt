package br.upf.schemaflow.dto

data class AttributeResponseDTO(
    val id: Long?,
    val name: String,
    val dataType: String,
    val size: Int,
    val defaultValue: String?,
    val isPrimaryKey: Boolean,
    val isForeignKey: Boolean,
    val isNullable: Boolean,
    val isUnique: Boolean
)