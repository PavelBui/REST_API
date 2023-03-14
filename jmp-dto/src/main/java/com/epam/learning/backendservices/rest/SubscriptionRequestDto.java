package com.epam.learning.backendservices.rest;

public class SubscriptionRequestDto {

    private Long id;

    private Long userId;

    public SubscriptionRequestDto() {}

    public SubscriptionRequestDto(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
