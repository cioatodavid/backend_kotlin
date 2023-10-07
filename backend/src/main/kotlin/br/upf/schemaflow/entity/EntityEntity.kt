package br.upf.schemaflow.entity

import jakarta.persistence.*

@Entity
@Table(name = "entities")
data class EntityEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "schema_id", referencedColumnName = "id")
    val schema: SchemaEntity,

    @OneToMany(mappedBy = "entity", cascade = [CascadeType.ALL])
    val attributes: List<AttributeEntity> = listOf(),

    @OneToMany(mappedBy = "fromEntity", cascade = [CascadeType.ALL])
    val outgoingRelations: List<RelationEntity> = listOf(),

    @OneToMany(mappedBy = "toEntity", cascade = [CascadeType.ALL])
    val incomingRelations: List<RelationEntity> = listOf(),

    val positionX: Double,
    val positionY: Double

)
