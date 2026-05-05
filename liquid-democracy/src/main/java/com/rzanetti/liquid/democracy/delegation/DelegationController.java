package com.rzanetti.liquid.democracy.delegation;

import com.rzanetti.liquid.democracy.delegation.dto.CreateDelegationRequest;
import com.rzanetti.liquid.democracy.delegation.dto.DelegationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/delegations")
@RequiredArgsConstructor
public class DelegationController {
    private final DelegationService delegationService;

    @PostMapping
    public ResponseEntity<DelegationResponse> create(@RequestBody @Valid CreateDelegationRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(delegationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DelegationResponse>> findAll() {
        return ResponseEntity.ok(delegationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DelegationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(delegationService.findById(id));
    }

    @PatchMapping("/{id}/revoke")
    public ResponseEntity<DelegationResponse> revoke(@PathVariable Long id) {
        return ResponseEntity.ok(delegationService.revoke(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        delegationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
