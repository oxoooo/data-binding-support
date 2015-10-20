/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 XiNGRZ <xxx@oxo.ooo>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ooo.oxo.library.databinding.support.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.concurrent.atomic.AtomicInteger;

public class BindingRecyclerView {

    public static abstract class ListAdapter<T, VH extends ViewHolder> extends RecyclerView.Adapter<VH> {

        protected final LayoutInflater inflater;
        protected final ObservableList<T> data;

        private final AtomicInteger refs = new AtomicInteger();

        private final ObservableList.OnListChangedCallback<ObservableList<T>> callback =
                new ObservableList.OnListChangedCallback<ObservableList<T>>() {
                    @Override
                    public void onChanged(ObservableList<T> sender) {
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onItemRangeChanged(ObservableList<T> sender,
                                                   int positionStart, int itemCount) {
                        notifyItemRangeChanged(positionStart, itemCount);
                    }

                    @Override
                    public void onItemRangeInserted(ObservableList<T> sender,
                                                    int positionStart, int itemCount) {
                        notifyItemRangeInserted(positionStart, itemCount);
                    }

                    @Override
                    public void onItemRangeMoved(ObservableList<T> sender,
                                                 int fromPosition, int toPosition, int itemCount) {
                        for (int i = 0; i < itemCount; i++) {
                            notifyItemMoved(fromPosition + i, toPosition + i);
                        }
                    }

                    @Override
                    public void onItemRangeRemoved(ObservableList<T> sender,
                                                   int positionStart, int itemCount) {
                        notifyItemRangeRemoved(positionStart, itemCount);
                    }
                };

        public ListAdapter(Context context, ObservableList<T> data) {
            this.inflater = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            if (refs.getAndIncrement() == 0) {
                data.addOnListChangedCallback(callback);
            }
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            if (refs.decrementAndGet() == 0) {
                data.removeOnListChangedCallback(callback);
            }
        }

    }

    public static class ViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

        public final V binding;

        public ViewHolder(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup parent) {
            this(DataBindingUtil.<V>inflate(inflater, layoutId, parent, false));
        }

        public ViewHolder(V binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
