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

}
