package br.upf.schemaflow.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "entities")
data class EntityEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column
    val positionX: Double,

    @Column
    val positionY: Double,

    @JsonManagedReference
    @OneToMany(mappedBy = "entity", cascade = [CascadeType.ALL])
    val attributes: List<AttributeEntity> = mutableListOf(),

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "schema_id", referencedColumnName = "id")
    val schema: SchemaEntity,


    )
