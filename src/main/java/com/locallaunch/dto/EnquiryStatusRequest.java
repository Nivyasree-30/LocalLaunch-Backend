package com.locallaunch.dto;

import com.locallaunch.entity.EnquiryStatus;
import jakarta.validation.constraints.NotNull;

public class EnquiryStatusRequest {

    @NotNull(message = "Status is required")
    private EnquiryStatus status;

    public EnquiryStatusRequest() {
    }

    public EnquiryStatus getStatus() {
        return status;
    }

    public void setStatus(EnquiryStatus status) {
        this.status = status;
    }
}