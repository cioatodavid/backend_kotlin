package br.upf.schemaflow.entity

import jakarta.persistence.*


@Entity
@Table(name = "field_relations")
data class FieldRelationEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "from_attribute_id", referencedColumnName = "id")
    val fromAttribute: AttributeEntity,

    @ManyToOne
    @JoinColumn(name = "to_attribute_id", referencedColumnName = "id")
    val toAttribute: AttributeEntity,

    @ManyToOne
    @JoinColumn(name = "relation_id", referencedColumnName = "id")
    val relation: RelationEntity
)
