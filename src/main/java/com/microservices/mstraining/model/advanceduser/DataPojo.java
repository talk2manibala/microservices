package com.microservices.mstraining.model.advanceduser;

import lombok.Data;

import java.util.List;

@Data
public class DataPojo {

    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<Datum> data;
    private Support support;

}
