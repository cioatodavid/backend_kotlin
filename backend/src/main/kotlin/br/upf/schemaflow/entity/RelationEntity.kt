package br.upf.schemaflow.entity

import jakarta.persistence.*

@Entity
@Table(name = "relations")
data class RelationEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "from_entity_id", referencedColumnName = "id")
    val fromEntity: EntityEntity,

    @ManyToOne
    @JoinColumn(name = "to_entity_id", referencedColumnName = "id")
    val toEntity: EntityEntity,

    @OneToMany(mappedBy = "relation", cascade = [CascadeType.ALL])
    val fieldRelations: List<FieldRelationEntity> = listOf()
)