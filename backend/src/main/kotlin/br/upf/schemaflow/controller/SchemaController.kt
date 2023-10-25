package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.SchemaDTO
import br.upf.schemaflow.service.SchemaService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/schemas")
class SchemaController(
    private val schemaService: SchemaService
) {

    @PostMapping
    fun createSchema(@RequestBody schemaDTO: SchemaDTO): ResponseEntity<SchemaDTO> {
        val createdSchema = schemaService.createSchema(schemaDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchema)
    }

    @GetMapping("/{id}")
    fun getSchema(@PathVariable id: Long): ResponseEntity<SchemaDTO> {
        val schema = schemaService.getSchema(id)
        return ResponseEntity.ok(schema)
    }

    @PutMapping("/{id}")
    fun updateSchema(@PathVariable id: Long, @RequestBody schemaDTO: SchemaDTO): ResponseEntity<SchemaDTO> {
        val updatedSchema = schemaService.updateSchema(id, schemaDTO)
        return ResponseEntity.ok(updatedSchema)
    }

    @DeleteMapping("/{id}")
    fun deleteSchema(@PathVariable id: Long): ResponseEntity<Void> {
        schemaService.deleteSchema(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
