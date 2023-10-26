package br.upf.schemaflow.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate


@Entity
@Table(name = "schemas")
data class SchemaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var name: String,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: UserEntity,

    @JsonManagedReference
    @OneToMany(mappedBy = "schema", cascade = [CascadeType.ALL])
    var entities: List<EntityEntity> = mutableListOf(),

    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "relation_id", referencedColumnName = "id")
    var relations: List<RelationEntity> = mutableListOf(),


    @CreatedDate
    val createdAt: Long = System.currentTimeMillis(),

    @LastModifiedDate
    val updatedAt: Long = System.currentTimeMillis()
)
