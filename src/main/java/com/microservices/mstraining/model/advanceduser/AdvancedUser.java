package com.microservices.mstraining.model.advanceduser;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
@Component
@Scope("prototype")
public class AdvancedUser {

    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<Datum> data;
    private Support support;

    public AdvancedUser generate(DataPojo data) {
        this.setPage(data.getPage());
        this.setPerPage(data.getPerPage());
        this.setTotal(data.getTotal());
        this.setTotalPages(data.getTotalPages());
        this.setData(data.getData());
        this.setSupport(data.getSupport());
        return this;
    }

}