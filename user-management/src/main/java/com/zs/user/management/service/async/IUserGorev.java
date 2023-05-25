package com.zs.user.management.service.async;


import com.zs.user.management.model.UserGorev;
import com.zs.user.management.model.UserGorevView;

import java.util.List;

public interface IUserGorev {
    public UserGorev create(UserGorev dto);

    public List<UserGorevView> getAllMimPersonel();

    public void addAraGorev();

}
