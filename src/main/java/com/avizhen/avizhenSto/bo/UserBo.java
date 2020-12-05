package com.avizhen.avizhenSto.bo;

import com.avizhen.avizhenSto.entity.User;

/**
 * Created by Александр on 05.11.2020.
 */
public interface UserBo {
    User findByuserId(int id);
}
