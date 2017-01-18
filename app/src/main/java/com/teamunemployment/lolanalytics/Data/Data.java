package com.teamunemployment.lolanalytics.Data;

import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Josiah Kendall
 * This is the data wrapper for our objects that get returned from the server. We need this for retorfit
 * because Google App Engine has to return an object, which gets put inside a "data:" variable.
 */
public class Data {

    public String role;
    public List<AdapterPojo> items;

    public void setRole(String role) {
        this.role = role;
    }

    public void setItems(List<AdapterPojo> items) {
        this.items = items;

    }

    public String getRole() {
        return role;
    }

    public List<AdapterPojo> getItems() {
        return items;
    }
}
