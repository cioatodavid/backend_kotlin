package br.upf.schemaflow.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "relations")
data class RelationEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val type: String = "one-to-one",

    //types
    //one-to-one
    //one-to-many
    //many-to-one
    //many-to-many

    @Column
    val cardinality: String,

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "from_entity_id", referencedColumnName = "id")
    val fromEntity: EntityEntity,

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "to_entity_id", referencedColumnName = "id")
    val toEntity: EntityEntity
)