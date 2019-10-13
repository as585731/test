package com.itheima.domain;

import java.util.List;

public class QueryVo {
    private User user;
    List<Integer> ids;

    public List<Integer> getList() {
        return ids;
    }

    public void setList(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
