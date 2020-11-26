package com.hosnydevtest.paging.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.hosnydevtest.paging.data.dataSource.ItemDataSource;
import com.hosnydevtest.paging.data.dataSource.ItemDataSourceFactory;
import com.hosnydevtest.paging.model.Items;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Items>> pagedListLiveData;
    public LiveData<PageKeyedDataSource<Integer, Items>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getMutableLiveData();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        pagedListLiveData = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }
}
