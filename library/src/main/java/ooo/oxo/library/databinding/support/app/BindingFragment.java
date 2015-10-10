package ooo.oxo.library.databinding.support.app;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BindingFragment<V extends ViewDataBinding> extends Fragment {

    protected V binding;

    @LayoutRes
    public abstract int provideContentView(@Nullable Bundle savedInstanceState);

    @Nullable
    public V onCreateBinding(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return DataBindingUtil.inflate(inflater, provideContentView(savedInstanceState),
                container, false);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        binding = onCreateBinding(inflater, container, savedInstanceState);
        return binding == null ? null : binding.getRoot();
    }

}
