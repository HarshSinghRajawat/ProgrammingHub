package com.example.programminghub.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.programminghub.ContentProvider;
import com.example.programminghub.DBSchema;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.programminghub.DBSchema;
import com.example.programminghub.DBhelper;
import com.example.programminghub.R;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    //private HomeViewModel homeViewModel;
    private static final int data_loader=1;

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });*/
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        GetData(root);
        return root;
    }
    private void GetData(View root){
        DBhelper mhelper=new DBhelper(getContext());
        String[] projection={DBSchema.cpp._ID,DBSchema.cpp._title,DBSchema.cpp._body};
        ContentProvider contentProvider=new ContentProvider();
        //Cursor cursor=db.rawQuery("SELECT * FROM "+ DBSchema.cpp.Table_name,null);
        Cursor cursor =getActivity().getContentResolver().query(DBSchema.Cpp_Content_Uri,projection,null,null,null);

        try{
            TextView display=(TextView) root.findViewById(R.id.text_home);
            display.setText("Number of rows in Database- "+cursor.getCount());
        }finally {
            cursor.close();
        }

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection={DBSchema.cpp._ID,DBSchema.cpp._title,DBSchema.cpp._body};
        return new CursorLoader(getContext(), DBSchema.Cpp_Content_Uri,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        //update through adapter
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        //mCursorAdapter.swapCursor(null);
    }
}