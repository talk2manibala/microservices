package com.microservices.mstraining.model.advanceduser;

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
public class Support {
    private String url;
    private String text;

    public Support generate(Support support) {
        this.setUrl(support.url);
        this.setText(support.text);
        return this;
    }

}