package com.controller

import com.model.Products
import com.repository.ElectronicsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ElectronicsController(@Autowired val electronicsRepository: ElectronicsRepository) {
    @GetMapping("/electronics")
    fun getAllTutorials(): ResponseEntity<Products> {
        return ResponseEntity<Products>(Products(electronicsRepository.findAll()), HttpStatus.OK)
    }
}