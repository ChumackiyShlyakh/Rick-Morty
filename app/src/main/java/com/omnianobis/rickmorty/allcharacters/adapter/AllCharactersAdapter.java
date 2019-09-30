package com.omnianobis.rickmorty.allcharacters.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.omnianobis.rickmorty.R;
import com.omnianobis.rickmorty.allcharacters.viewmodel.DataItemViewModel;
import com.omnianobis.rickmorty.core.model.response.allcharacters.Result;
import com.omnianobis.rickmorty.databinding.ItemRepoBinding;
import com.omnianobis.rickmorty.person.fragment.PersonFragment;
import com.omnianobis.rickmorty.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AllCharactersAdapter extends RecyclerView.Adapter<AllCharactersAdapter.RecyclerHolder> {

    private List<Result> result = new ArrayList<>();
    private LayoutInflater layoutInflater;
    Context context;

    public void setFollowingResponse(List<Result> allCharactersResponses, Context context) {
        this.result = allCharactersResponses;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public AllCharactersAdapter.RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ItemRepoBinding itemRepoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_repo,
                viewGroup, false);
        return new AllCharactersAdapter.RecyclerHolder(itemRepoBinding);
    }

    @Override
    public void onBindViewHolder(AllCharactersAdapter.RecyclerHolder holder, int i) {
        DataItemViewModel dataItemViewModel = new DataItemViewModel(result.get(i));
        holder.itemRepoBinding.setRepo(dataItemViewModel);

        Glide.with(context).load(dataItemViewModel.getImageString()).into(holder.image);

        holder.itemRepoBinding.executePendingBindings();

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                PersonFragment fragment = new PersonFragment();
                Bundle args = new Bundle();
                args.putInt("idPerson", result.get(i).getId());
                fragment.setArguments(args);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_login,
                        fragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return result != null ? result.size() : 0;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //

        private ItemRepoBinding itemRepoBinding;
        ImageView image;
        private ItemClickListener itemClickListener;

        public RecyclerHolder(@NonNull ItemRepoBinding itemRepoBinding) {
            super(itemRepoBinding.getRoot());
            this.itemRepoBinding = itemRepoBinding;
            image = itemRepoBinding.ivPersona;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
}


