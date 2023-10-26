package br.upf.schemaflow.controller

import br.upf.schemaflow.dto.AttributeResponseDTO
import br.upf.schemaflow.service.AttributeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/attributes")
class AttributeController(
    private val attributeService: AttributeService
) {
    @GetMapping("/{attributeId}")
    fun getAttribute(@PathVariable attributeId: Long): AttributeResponseDTO {
        return attributeService.getAttributeById(attributeId)
    }

}