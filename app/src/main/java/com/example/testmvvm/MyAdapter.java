package com.example.testmvvm;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testmvvm.BR;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVH> {
    List<Uri> uris;

    MyAdapter(List<Uri> uris) {
        this.uris = uris;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item, parent, false);
        return new MyVH(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        final User user = new User();
        user.url = uris.get(position);
        user.name = position + "";
        holder.getBinding().setVariable(com.example.testmvvm.BR.user, user);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return uris.size();
    }

    class MyVH extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        public MyVH(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
