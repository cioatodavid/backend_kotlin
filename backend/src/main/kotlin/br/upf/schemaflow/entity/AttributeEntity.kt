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

    @Column
    val size: Int = 0,

    @Column
    val isPrimaryKey: Boolean = false,

    @Column
    val isForeignKey: Boolean = false,

    @Column
    val isNullable: Boolean = false,

    @Column
    val isUnique: Boolean = false,

    @Column
    val defaultValue: String? = null,

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    val entity: EntityEntity
)