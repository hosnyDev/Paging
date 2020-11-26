package com.hosnydevtest.paging.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hosnydevtest.paging.R;
import com.hosnydevtest.paging.data.RetrofitClient;
import com.hosnydevtest.paging.model.Items;
import com.hosnydevtest.paging.model.StackApiResponse;
import com.hosnydevtest.paging.viewModel.ItemViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = findViewById(R.id.progressbar);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ItemViewModel itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        ItemAdapter adapter = new ItemAdapter(this);

        itemViewModel.pagedListLiveData.observe(this, items -> {
            adapter.submitList(items);
            progressBar.setVisibility(View.GONE);
        });
        recyclerView.setAdapter(adapter);

    }
}