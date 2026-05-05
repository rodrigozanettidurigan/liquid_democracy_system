package com.rzanetti.liquid.democracy.proposal;

import com.rzanetti.liquid.democracy.proposal.dto.CreateProposalRequest;
import com.rzanetti.liquid.democracy.proposal.dto.ProposalResponse;
import com.rzanetti.liquid.democracy.proposal.dto.UpdateProposalRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proposals")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponse> create(@RequestBody @Valid CreateProposalRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(proposalService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProposalResponse>> findAll() {
        return ResponseEntity.ok(proposalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(proposalService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProposalResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProposalRequest dto) {
        return ResponseEntity.ok(proposalService.update(id, dto));
    }

    @PatchMapping("/{id}/open")
    public ResponseEntity<ProposalResponse> open(@PathVariable Long id) {
        return ResponseEntity.ok(proposalService.open(id));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<ProposalResponse> close(@PathVariable Long id) {
        return ResponseEntity.ok(proposalService.close(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proposalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
