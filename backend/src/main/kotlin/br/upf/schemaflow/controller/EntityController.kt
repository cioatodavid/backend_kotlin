package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.dto.EntityResponseDTO
import br.upf.schemaflow.service.AttributeService
import br.upf.schemaflow.service.EntityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/entities")
class EntityController(
    private val entityService: EntityService,
    private val attributeService: AttributeService
) {
    @GetMapping
    fun getAllEntities(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Any> {
        val entities = entityService.getAllEntities(page, size)
        return ResponseEntity.ok(entities)
    }

    @GetMapping("/{entityId}")
    fun getEntity(@PathVariable entityId: Long): ResponseEntity<EntityResponseDTO> {
        val entity = entityService.getEntityById(entityId)
        return ResponseEntity.ok(entity)
    }


    @PostMapping("/{entityId}/attributes")
    fun createAttribute(@PathVariable entityId: Long, attributeDTO: AttributeDTO): ResponseEntity<AttributeDTO> {
        val newAttribute = attributeService.createAttribute(entityId, attributeDTO)
        return ResponseEntity.ok(newAttribute)
    }
}