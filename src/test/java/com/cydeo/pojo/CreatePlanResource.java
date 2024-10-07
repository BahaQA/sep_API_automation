package com.cydeo.pojo;

import lombok.Data;

@Data
public class CreatePlanResource {

    private  Integer customerId;
    private  Integer programId;
    private String priceId;
    private String paymentMethod;


}
