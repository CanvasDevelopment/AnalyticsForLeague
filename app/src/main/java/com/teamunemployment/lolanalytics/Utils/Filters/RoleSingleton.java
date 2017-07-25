package com.teamunemployment.lolanalytics.Utils.Filters;

import javax.inject.Singleton;

/**
 * Created by Josiah Kendall
 */

@Singleton
public class RoleSingleton {
    int role;

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
}
