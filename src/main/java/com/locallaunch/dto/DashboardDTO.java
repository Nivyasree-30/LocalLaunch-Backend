package com.locallaunch.dto;

public class DashboardDTO {

    private long businessCount;
    private long productCount;
    private long enquiryCount;

    public DashboardDTO() {
    }

    public DashboardDTO(
            long businessCount,
            long productCount,
            long enquiryCount) {

        this.businessCount = businessCount;
        this.productCount = productCount;
        this.enquiryCount = enquiryCount;
    }

    public long getBusinessCount() {
        return businessCount;
    }

    public long getProductCount() {
        return productCount;
    }

    public long getEnquiryCount() {
        return enquiryCount;
    }
}