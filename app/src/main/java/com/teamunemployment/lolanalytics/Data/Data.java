package com.teamunemployment.lolanalytics.Data;

import com.teamunemployment.lolanalytics.Jungle.Model.AdapterPojo;

import java.util.List;

/**
 * This is the data wrapper for our objects that get returned from the server. We need this for retorfit
 * because Google App Engine has to return an object, which gets
 */

public class Data {
    public List<AdapterPojo> items;
}
