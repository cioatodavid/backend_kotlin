package br.upf.schemaflow.dto

data class AttributeDTO(
    val id: Long?,
    val name: String,
    val dataType: String,
    val size: Int? = 0,
    val defaultValue: String? = null,
    val isPrimaryKey: Boolean = false,
    val isForeignKey: Boolean = false,
    val isNullable: Boolean = false,
    val isUnique: Boolean = false,
    val entityId: Long
)