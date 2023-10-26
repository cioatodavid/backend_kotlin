package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.AttributeDTO
import br.upf.schemaflow.dto.AttributeResponseDTO
import br.upf.schemaflow.service.AttributeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/attributes")
class AttributeController(
    private val attributeService: AttributeService
) {
    @GetMapping("/{attributeId}")
    fun getAttribute(@PathVariable attributeId: Long): AttributeResponseDTO {
        return attributeService.getAttributeById(attributeId)
    }

    @PutMapping("/{attributeId}")
    fun updateAttribute(
        @PathVariable attributeId: Long, @RequestBody attributeDTO: AttributeDTO
    ): AttributeResponseDTO {
        return attributeService.updateAttribute(attributeId, attributeDTO)
    }

    @DeleteMapping("/{attributeId}")
    fun deleteAttribute(@PathVariable attributeId: Long): String {
        return attributeService.deleteAttribute(attributeId)
    }


}