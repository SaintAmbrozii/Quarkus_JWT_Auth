package org.acme.tsg.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.ToString;

@RegisterForReflection
@ToString
public class UserDTO {

    public Long id;

    public String name;

    public String lastname;

    public String email;

    public String password;

    public String address;

    public String contract;

    public String phone;

    public Long lastUpload;

    public boolean api;

    public boolean isAggregated;



}
