package com.example.flowershop.controller;

import com.example.flowershop.model.FlowerDTO;
import com.example.flowershop.service.FlowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {
    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping
    public List<FlowerDTO> getAllFlowers() {
        return flowerService.getAllFlowers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowerDTO> getFlowerById(@PathVariable Long id) {
        Optional<FlowerDTO> flower = flowerService.getFlowerById(id);
        return flower.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FlowerDTO createFlower(@RequestBody FlowerDTO flowerDTO) {
        return flowerService.createFlower(flowerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlowerDTO> updateFlower(@PathVariable Long id, @RequestBody FlowerDTO flowerDTO) {
        try {
            FlowerDTO updatedFlower = flowerService.updateFlower(id, flowerDTO);
            return ResponseEntity.ok(updatedFlower);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlower(@PathVariable Long id) {
        flowerService.deleteFlower(id);
        return ResponseEntity.noContent().build();
    }
}
