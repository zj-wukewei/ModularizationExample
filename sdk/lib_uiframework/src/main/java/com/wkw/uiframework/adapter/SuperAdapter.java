/*
 * Copyright 2016 wukewei. https://github.com/zj-wukewei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wkw.uiframework.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wkw.uiframework.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wukewei on 17/3/19.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class SuperAdapter<T extends LayoutResId> extends RecyclerView.Adapter<BindingViewHolder> {

    protected final LayoutInflater mLayoutInflater;

    protected List<T> mCollection = new ArrayList<>();
    protected Presenter mPresenter;
    protected Decorator mDecorator;


    public SuperAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE);
    }


    public interface Presenter {

    }


    public interface Decorator {
        void decorator(BindingViewHolder holder, int position, int viewType);
    }


    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingViewHolder(
            DataBindingUtil.inflate(mLayoutInflater, viewType, parent, false));
    }


    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Object item = mCollection.get(position);
        holder.getBinding().setVariable(BR.item, item);
        holder.getBinding().setVariable(BR.presenter, getPresenter());
        holder.getBinding().executePendingBindings();
        if (mDecorator != null) {
            mDecorator.decorator(holder, position, getItemViewType(position));
        }
    }


    @Override
    public int getItemViewType(int position) {
        return mCollection.get(position).getLayoutRes();
    }


    @Override
    public int getItemCount() {
        return mCollection.size();
    }


    public void remove(int position) {
        mCollection.remove(position);
        notifyItemRemoved(position);
    }


    public void clear() {
        mCollection.clear();
        notifyDataSetChanged();
    }


    public void add(T viewModel) {
        mCollection.add(viewModel);
        notifyDataSetChanged();
    }


    public void add(int position, T viewModel) {
        mCollection.add(position, viewModel);
        notifyDataSetChanged();
    }


    public void set(List<T> viewModels) {
        mCollection.clear();
        addAll(viewModels);
    }


    public void addAll(List<T> viewModels) {
        mCollection.addAll(viewModels);
        notifyDataSetChanged();
    }


    public List<T> getCollection() {
        return mCollection;
    }


    public void setDecorator(Decorator decorator) {
        mDecorator = decorator;
    }


    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }


    protected Presenter getPresenter() {
        return mPresenter;
    }
}
