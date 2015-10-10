Data Binding Support Library
==========

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
    compile 'com.github.oxoooo:data-binding-support:1.0.0'
}
```

## Usage

### `BindingAppCompatActivity` and `BindingFragment`

```java
public class MainActivity extends BindingAppCompatActivity {

    @Override
    protected int provideContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
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
public class MainFragment extends BindingFragment {

    @Override
    public int provideContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
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
        return new ViewHolder(inflater, R.layout.photo_item, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setItem(data.get(position));
    }

    public class ViewHolder extends BindingRecyclerView.ViewHolder<PhotoItemBinding> {

        public ViewHolder(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup parent) {
            super(inflater, layoutId, parent);
        }

    }

}
```

```java
ObservableList photos = new ObservableArrayList();
recyclerView.setAdapter(new PhotoAdapter(this, photos));

// Just add your items in the ObservableList, BindingRecyclerView.ListAdapter will take care of the
// binding for you.
photos.addAll(/* ... */);
```

## License

[MIT License](LICENSE)
