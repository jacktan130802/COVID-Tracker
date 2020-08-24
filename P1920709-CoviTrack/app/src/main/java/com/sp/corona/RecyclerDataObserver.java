
package com.sp.corona;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerDataObserver extends RecyclerView.AdapterDataObserver {

    private RecyclerView recyclerView;
    private ViewGroup emptyView;
    private ViewGroup progressView;

    public RecyclerDataObserver(@NonNull RecyclerView recyclerView, @NonNull ViewGroup emptyView, @NonNull ViewGroup progressView) {
        this.recyclerView = recyclerView;
        this.emptyView = emptyView;
        this.progressView = progressView;
        showProgress();
    }

    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressView.setVisibility(View.GONE);
    }

    public void checkIfEmpty() {
        if (recyclerView.getAdapter() != null) {
            if (recyclerView.getAdapter().getItemCount() == 0)
                emptyView.setVisibility(View.VISIBLE);
            else
                emptyView.setVisibility(View.GONE);
            progressView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onChanged() {
        checkIfEmpty();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        checkIfEmpty();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        checkIfEmpty();
    }
}
