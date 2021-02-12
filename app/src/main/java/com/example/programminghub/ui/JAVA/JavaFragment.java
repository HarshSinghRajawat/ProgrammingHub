package com.example.programminghub.ui.JAVA;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.programminghub.DBSchema;
import com.example.programminghub.HubCursorAdapter;
import com.example.programminghub.R;

public class JavaFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int data_loader=0;
    HubCursorAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_java, container, false);

        ListView list=(ListView) root.findViewById(R.id.java_list);

        adapter=new HubCursorAdapter(getContext(),null);
        list.setAdapter(adapter);
        getLoaderManager().initLoader(data_loader,null,this);
        return root;
    }
    private void GetData(View root){
        String[] projection={DBSchema.java._ID,DBSchema.java._title,DBSchema.java._img,DBSchema.java._des,DBSchema.java._body};
        Cursor cursor =getActivity().getContentResolver().query(DBSchema.Java_Content_Uri,projection,null,null,null);
        display(cursor,root);
    }
    private void display(Cursor cursor,View root){
        ListView list=(ListView) root.findViewById(R.id.java_list);
        HubCursorAdapter adapter=new HubCursorAdapter(getContext(),cursor);
        list.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection={DBSchema.java._ID,DBSchema.java._title,DBSchema.java._img,DBSchema.java._des,DBSchema.java._body};
        return new CursorLoader(getContext(), DBSchema.Java_Content_Uri,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}