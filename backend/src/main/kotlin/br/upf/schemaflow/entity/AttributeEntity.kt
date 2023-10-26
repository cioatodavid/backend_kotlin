package br.upf.schemaflow.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity

@Table(name = "attributes")
data class AttributeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var dataType: String? = "",

    @Column
    var size: Int? = 0,

    @Column
    var isPrimaryKey: Boolean? = false,

    @Column
    var isForeignKey: Boolean? = false,

    @Column
    var isNullable: Boolean? = false,

    @Column
    var isUnique: Boolean? = false,

    @Column
    var defaultValue: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JsonBackReference
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    var entity: EntityEntity?
)