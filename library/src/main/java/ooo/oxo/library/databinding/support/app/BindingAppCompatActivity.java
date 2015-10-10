package ooo.oxo.library.databinding.support.app;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

public abstract class BindingAppCompatActivity<V extends ViewDataBinding> extends AppCompatActivity {

    protected V binding;

    @LayoutRes
    protected abstract int provideContentView(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, provideContentView(savedInstanceState));
    }

}
