package br.upf.schemaflow.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "entities")
data class EntityEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "schema_id", referencedColumnName = "id")
    val schema: SchemaEntity,

    @JsonManagedReference
    @OneToMany(mappedBy = "entity", cascade = [CascadeType.ALL], orphanRemoval = true)
    val attributes: List<AttributeEntity> = mutableListOf(),

    val positionX: Double,
    val positionY: Double

)
