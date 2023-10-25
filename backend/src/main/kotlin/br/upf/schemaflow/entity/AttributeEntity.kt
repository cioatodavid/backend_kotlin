package br.upf.schemaflow.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "attributes")
data class AttributeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val dataType: String,

    val size: Int = 0,

    val isPrimaryKey: Boolean = false,

    val isForeignKey: Boolean = false,

    val isNullable: Boolean = false,

    val isUnique: Boolean = false,

    val defaultValue: String? = null,

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    val entity: EntityEntity
)