package com.microservices.mstraining.service;

import com.microservices.mstraining.model.advanceduser.AdvancedUser;
import com.microservices.mstraining.model.advanceduser.Datum;
import com.microservices.mstraining.model.advanceduser.Support;
import com.microservices.mstraining.model.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvancedUserService {

    public static ArrayList<AdvancedUser> users = new ArrayList<>();

    public AdvancedUser createUser(AdvancedUser user) {
        List<Datum> data = new ArrayList<>();
        Datum datum = Datum.builder()
                .id(user.getData().get(0).getId())
                .firstName(user.getData().get(0).getFirstName())
                .lastName(user.getData().get(0).getLastName())
                .email(user.getData().get(0).getEmail())
                .avatar(user.getData().get(0).getAvatar())
                .build();
        data.add(datum);

        Support support = Support.builder().url(user.getSupport().getUrl()).text(user.getSupport().getText()).build();
        AdvancedUser newUser = AdvancedUser.builder()
                .page(user.getPage())
                .perPage(user.getPerPage())
                .total(user.getTotal())
                .totalPages(user.getTotalPages())
                .data(data)
                .support(support)
                .build();
        // newUser.setId(++id); // Add id like this also work
        // newUser.setId(users.size()+1); // This will also work
        users.add(newUser);
        return newUser;
    }

    // get user :  user/1
    public AdvancedUser getUser(int id) {
        AdvancedUser user = users.stream().filter(x->x.getData().get(0).getId() ==id).findFirst().orElse(null);
        if (user==null) throw new UserNotFoundException("User with id "+id+"not found");
        return user;
    }

}
