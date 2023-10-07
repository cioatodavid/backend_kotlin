package br.upf.schemaflow.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@Entity
@Table(name = "schemas")
data class SchemaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: UserEntity,

    @OneToMany(mappedBy = "schema", cascade = [CascadeType.ALL])
    val entities: List<EntityEntity> = listOf(),

    @CreatedDate
    val createdAt: Long = System.currentTimeMillis(),

    @LastModifiedDate
    val updatedAt: Long = System.currentTimeMillis()
)
