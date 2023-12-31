package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.dto.AttributeResponseDTO
import br.upf.schemaflow.dto.EntityDTO
import br.upf.schemaflow.dto.EntityResponseDTO
import br.upf.schemaflow.service.AttributeService
import br.upf.schemaflow.service.EntityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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
    fun createAttribute(
        @PathVariable entityId: Long, @RequestBody attributeDTO: AttributeDTO
    ): ResponseEntity<AttributeResponseDTO> {
        val savedAttribute = attributeService.createAttribute(entityId, attributeDTO)
        return ResponseEntity.ok(savedAttribute)
    }

    @DeleteMapping("/{entityId}")
    fun deleteEntity(@PathVariable entityId: Long): ResponseEntity<String> {
        val message = entityService.deleteEntity(entityId)
        return ResponseEntity.ok(message)
    }

    @PutMapping("/{entityId}")
    fun updateEntity(
        @PathVariable entityId: Long, @RequestBody entityDTO: EntityDTO
    ): ResponseEntity<EntityResponseDTO> {
        val updatedEntity = entityService.updateEntity(entityId, entityDTO)
        return ResponseEntity.ok(updatedEntity)
    }
}