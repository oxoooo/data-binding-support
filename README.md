Data Binding Support Library
==========

[![](https://img.shields.io/github/tag/oxoooo/data-binding-support.svg?style=flat-square&label=jitpack.io)](https://jitpack.io/#oxoooo/data-binding-support)
[![](https://img.shields.io/github/license/oxoooo/data-binding-support.svg?style=flat-square)](LICENSE)
[![](https://img.shields.io/github/issues/oxoooo/data-binding-support.svg?style=flat-square)](https://github.com/oxoooo/data-binding-support/issues)

Provides useful subclasses from [Support Library](https://developer.android.com/tools/support-library/index.html)
that work with [Data Binding](https://developer.android.com/tools/data-binding/guide.html).

## Download

```gradle
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    // ... support library ...
    // ...
    compile 'com.github.oxoooo:data-binding-support:1.1.0'
}
```

## Usage

### `BindingAppCompatActivity` and `BindingFragment`

```java
public class MainActivity extends BindingAppCompatActivity<MainActivityBinding> {

    @Override
    protected int provideContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.main_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // do anything you want, for example:
        binding.setUser(user);
    }

}
```

```java
public class MainFragment extends BindingFragment<ProfileFragmentBinding> {

    @Override
    public int provideContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.profile_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // do anything you want, for example:
        binding.setUser(user);
    }

}
```

### `BindingRecyclerView`

```java
public class PhotoAdapter extends BindingRecyclerView.ListAdapter<Photo, PhotoAdapter.ViewHolder> {

    public PhotoAdapter(Context context, ObservableList<Photo> data) {
        super(context, data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setItem(data.get(position));
    }

    public class ViewHolder extends BindingRecyclerView.ViewHolder<PhotoItemBinding> {

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, R.layout.photo_item, parent);
        }

    }

}
```

```java
private final ObservableList photos = new ObservableArrayList();

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /* ... */

    recyclerView.setAdapter(new PhotoAdapter(this, photos));

    // Just add your items in the ObservableList, BindingRecyclerView.ListAdapter will take care of the
    // binding for you.
    photos.addAll(/* ... */);
}

@Override
protected void onDestroy() {
    super.onDestroy();

    /* ... */

    // Remember to release the adapter, because it has a reference to the `photos` ObservableList.
    recyclerView.setAdapter(null);
}
```

## License

[MIT License](LICENSE)
