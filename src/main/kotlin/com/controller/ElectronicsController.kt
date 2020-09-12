package com.controller

import com.model.Product
import com.model.Products
import com.repository.ElectronicsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products/electronics")
class ElectronicsController(@Autowired val electronicsRepository: ElectronicsRepository) {
    @GetMapping("/list")
    fun listAll(): ResponseEntity<Products> {
        return ResponseEntity<Products>(Products(electronicsRepository.findAll()), HttpStatus.OK)
    }

    @GetMapping(value = ["/{id}"])
    fun byId(@PathVariable("id") id: String): ResponseEntity<Product> {
        val result = electronicsRepository.findById(id)
        if (result.isPresent) return ResponseEntity<Product>(result.get(), HttpStatus.OK)
        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)
    }

    @GetMapping(value = ["/code/{value}"])
    fun byCode(@PathVariable("value") value: String): ResponseEntity<Product> {
        val result = electronicsRepository.findByCode(value)
        if (result.isPresent) return ResponseEntity<Product>(result.get(), HttpStatus.OK)
        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)
    }
}