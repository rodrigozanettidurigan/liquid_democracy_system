package com.rzanetti.liquid.democracy.vote;

import com.rzanetti.liquid.democracy.vote.dto.CreateVoteRequest;
import com.rzanetti.liquid.democracy.vote.dto.VoteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/api/proposals/{proposalId}/votes")
    public ResponseEntity<VoteResponse> create(
            @PathVariable Long proposalId,
            @RequestBody @Valid CreateVoteRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(voteService.create(proposalId, dto));
    }

    @GetMapping("/api/proposals/{proposalId}/votes")
    public ResponseEntity<List<VoteResponse>> findByProposalId(@PathVariable Long proposalId) {
        return ResponseEntity.ok(voteService.findByProposalId(proposalId));
    }

    @GetMapping("/api/votes/{id}")
    public ResponseEntity<VoteResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(voteService.findById(id));
    }

    @DeleteMapping("/api/votes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        voteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
