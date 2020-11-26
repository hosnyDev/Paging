package com.hosnydevtest.paging.model;

import java.util.List;

public class StackApiResponse {

    public List<Items> items;
    public boolean has_more;
    public int backoff, quota_max, quota_remaining;

}



