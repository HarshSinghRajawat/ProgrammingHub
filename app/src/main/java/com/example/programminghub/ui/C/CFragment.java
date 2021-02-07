package com.example.programminghub.ui.C;

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
import androidx.loader.content.Loader;

import com.example.programminghub.DBSchema;
import com.example.programminghub.HubCursorAdapter;
import com.example.programminghub.R;

public class CFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_c, container, false);
        GetData(root);
        return root;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
    private void GetData(View root){
        String[] projection={DBSchema.c._ID,DBSchema.c._title,DBSchema.c._body};
        Cursor cursor =getActivity().getContentResolver().query(DBSchema.C_Content_Uri,projection,null,null,null);

        display(cursor,root);

    }
    private void display(Cursor cursor,View root){
        ListView list=(ListView) root.findViewById(R.id.c_list);
        HubCursorAdapter adapter=new HubCursorAdapter(getContext(),cursor);
        list.setAdapter(adapter);
    }
}