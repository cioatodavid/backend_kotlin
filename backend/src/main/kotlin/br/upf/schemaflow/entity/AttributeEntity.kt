package br.upf.schemaflow.entity

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "attributes")
data class AttributeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    val dataType: String,

    @ManyToOne
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    val entity: EntityEntity
)