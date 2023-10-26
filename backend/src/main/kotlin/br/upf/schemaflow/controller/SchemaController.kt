package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.EntityDTO
import br.upf.schemaflow.dto.EntityResponseDTO
import br.upf.schemaflow.dto.SchemaDTO
import br.upf.schemaflow.dto.SchemaResponseDTO
import br.upf.schemaflow.service.EntityService
import br.upf.schemaflow.service.SchemaService
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/schemas")
class SchemaController(
    private val schemaService: SchemaService, private val entityService: EntityService
) {
    @PostMapping
    fun createSchema(@RequestBody schemaDTO: SchemaDTO): ResponseEntity<SchemaResponseDTO> {
        val savedSchema = schemaService.createSchema(schemaDTO)
        return ResponseEntity.ok(savedSchema)
    }

    @GetMapping("/{schemaId}")
    fun getSchema(@PathVariable schemaId: Long): ResponseEntity<SchemaResponseDTO> {
        val getSchema = schemaService.getSchemaById(schemaId)
        return ResponseEntity.ok(getSchema)
    }

    @DeleteMapping("/{schemaId}")
    fun deleteSchema(@PathVariable schemaId: Long): ResponseEntity<Unit> {
        schemaService.deleteSchema(schemaId)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{schemaId}")
    fun updateSchema(
        @PathVariable schemaId: Long, @RequestBody body: Map<String, String>
    ): ResponseEntity<SchemaResponseDTO> {
        val updatedSchema = schemaService.updateSchema(schemaId, body["name"] ?: "")
        return ResponseEntity.ok(updatedSchema)
    }


    @PostMapping("/{schemaId}/entities")
    fun createEntity(
        @PathVariable schemaId: Long, @RequestBody entityDTO: EntityDTO
    ): ResponseEntity<EntityResponseDTO> {
        val savedEntity = entityService.createEntity(schemaId, entityDTO)
        return ResponseEntity.ok(savedEntity)
    }
}