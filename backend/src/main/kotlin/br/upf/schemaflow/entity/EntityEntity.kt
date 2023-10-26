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
    var name: String,

    @Column
    var positionX: Double,

    @Column
    var positionY: Double,

    @JsonManagedReference
    @OneToMany(mappedBy = "entity", cascade = [CascadeType.ALL])
    var attributes: List<AttributeEntity>? = mutableListOf(),

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "schema_id", referencedColumnName = "id")
    var schema: SchemaEntity?,


    )
