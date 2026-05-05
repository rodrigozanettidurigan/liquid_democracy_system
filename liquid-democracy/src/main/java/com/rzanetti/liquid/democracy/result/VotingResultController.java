package com.rzanetti.liquid.democracy.result;

import com.rzanetti.liquid.democracy.result.dto.VotingResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VotingResultController {
    private final VotingResultService votingResultService;

    @GetMapping("/api/proposals/{proposalId}/results")
    public ResponseEntity<VotingResultResponse> getResult(@PathVariable Long proposalId) {
        return ResponseEntity.ok(votingResultService.getResult(proposalId));
    }
}
