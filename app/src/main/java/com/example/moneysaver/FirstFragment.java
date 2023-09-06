package com.example.moneysaver;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment implements OnItemClickListener{

    public static List<NewsModel> newsModelList = new ArrayList<>();

    public static String NEWS_TITLE = "movie title";

    public static String NEWS = "movie";

    public FirstFragment() {super(R.layout.fragment_first);}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeList();

        CustomAdapter adapter = new CustomAdapter(newsModelList, this);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);
    }

    private void initializeList() {
        newsModelList.add(new NewsModel(
                "Sosoaca este pe scandal! incredibil cea facut", R.drawable.a
        ));

        newsModelList.add(new NewsModel(
                "Sosoaca este pe scandal! incredibil cea facut", R.drawable.a
        ));
    }

    @Override
    public void onItemClick(NewsModel item) {
        Bundle bundle = new Bundle();

        bundle.putString(NEWS_TITLE, item.getTitle());
        bundle.putParcelable(NEWS, item);

        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout, secondFragment)
                .addToBackStack(null);
        fragmentTransaction.commit();
    }
}