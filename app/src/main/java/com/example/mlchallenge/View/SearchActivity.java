package com.example.mlchallenge.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mlchallenge.Adapter.RecyclerAdapter;
import com.example.mlchallenge.Base.BaseActivity;
import com.example.mlchallenge.Interface.SearchView;
import com.example.mlchallenge.Model.Product;
import com.example.mlchallenge.Model.SearchInteractor;
import com.example.mlchallenge.Presenter.SearchPresenter;
import com.example.mlchallenge.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView, android.widget.SearchView.OnQueryTextListener {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.errorText)
    TextView errorText;
    @BindView(R.id.recyclerResults) @Nullable
    RecyclerView recyclerView;

    List<Product> products ;
    RecyclerAdapter adapter ;



    @NonNull
    @Override
    protected SearchPresenter createPresenter(@NonNull Context context) {
        return new SearchPresenter(this, new SearchInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        adapter = new RecyclerAdapter(products) ;
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.widget.SearchView searchView = (android.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSearch() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearch() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showSearchList(List<Product> products) {
        adapter.setData(products);
        adapter.notifyDataSetChanged();

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linear = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linear);
}

    @Override
    public void fetchSearchList(String value) {
        mPresenter.fetchData(value);
    }

    @Override
    public void showDataFetchErrors() {
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        fetchSearchList(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}