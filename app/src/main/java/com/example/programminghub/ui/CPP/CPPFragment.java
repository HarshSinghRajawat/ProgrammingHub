package com.example.programminghub.ui.CPP;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.programminghub.DBSchema;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.programminghub.HubCursorAdapter;
import com.example.programminghub.R;

public class CPPFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {



    private static final int data_loader=0;
    HubCursorAdapter adapter;

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cpp, container, false);

        ListView list=(ListView) root.findViewById(R.id.cpp_list);

        adapter=new HubCursorAdapter(getContext(),null);
        list.setAdapter(adapter);
        getLoaderManager().initLoader(data_loader,null,this);

        return root;
    }
    private void GetData(View root){
        String[] projection={DBSchema.cpp._ID,DBSchema.cpp._title,DBSchema.cpp._img,DBSchema.cpp._des,DBSchema.cpp._body};
        Cursor cursor =getActivity().getContentResolver().query(DBSchema.Cpp_Content_Uri,projection,null,null,null);

        display(cursor,root);

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection={DBSchema.cpp._ID,DBSchema.cpp._title,DBSchema.cpp._img,DBSchema.cpp._des,DBSchema.cpp._body};
        return new CursorLoader(getContext(), DBSchema.Cpp_Content_Uri,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
    private void display(Cursor cursor,View root){
        ListView list=(ListView) root.findViewById(R.id.cpp_list);
        HubCursorAdapter adapter=new HubCursorAdapter(getContext(),cursor);
        list.setAdapter(adapter);
    }
}