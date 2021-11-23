package com.nilbrains.nilamdinserver.entity.req;

import lombok.Data;

@Data
public class ChangeUserPass {
    String uid;
    String oldPass;
    String newPass;
    String repeatPass;
}
