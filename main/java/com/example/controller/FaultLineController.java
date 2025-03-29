package com.example.controller;

import com.example.dto.fault.FaultLineDto;
import com.example.service.FaultLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fault-lines")
@RequiredArgsConstructor
@Tag(name = "Fault Line", description = "Fay hattı yönetimi API'leri")
public class FaultLineController {

    private final FaultLineService faultLineService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Yeni fay hattı oluştur")
    public ResponseEntity<FaultLineDto> create(@Valid @RequestBody FaultLineDto dto) {
        return new ResponseEntity<>(faultLineService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Fay hattı güncelle")
    public ResponseEntity<FaultLineDto> update(
            @Parameter(description = "Fay hattı ID") @PathVariable Long id,
            @Valid @RequestBody FaultLineDto dto) {
        return ResponseEntity.ok(faultLineService.update(id, dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fay hattı detaylarını getir")
    public ResponseEntity<FaultLineDto> getById(
            @Parameter(description = "Fay hattı ID") @PathVariable Long id) {
        return faultLineService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Fay hatlarını listele")
    public ResponseEntity<Page<FaultLineDto>> getAll(
            @Parameter(description = "Arama terimi") @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(faultLineService.findAll(search, pageable));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Fay hattı sil")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Fay hattı ID") @PathVariable Long id) {
        faultLineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 