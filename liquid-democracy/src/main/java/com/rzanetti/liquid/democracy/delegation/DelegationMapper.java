package com.rzanetti.liquid.democracy.delegation;

import com.rzanetti.liquid.democracy.delegation.dto.DelegationResponse;
import com.rzanetti.liquid.democracy.topic.Topic;
import com.rzanetti.liquid.democracy.user.User;

public class DelegationMapper {
    private DelegationMapper() {
    }

    public static Delegation toEntity(User delegator, User delegate, Topic topic) {
        return new Delegation(delegator, delegate, topic);
    }

    public static DelegationResponse toResponse(Delegation delegation) {
        return new DelegationResponse(
                delegation.getId(),
                delegation.getDelegator().getId(),
                delegation.getDelegator().getName(),
                delegation.getDelegate().getId(),
                delegation.getDelegate().getName(),
                delegation.getTopic().getId(),
                delegation.getTopic().getName(),
                delegation.getStatus(),
                delegation.getCreatedAt(),
                delegation.getRevokedAt()
        );
    }
}
