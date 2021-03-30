package com.example.motokarkotlin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    ChatAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.chat_fragment, container, false);



        ArrayList<String> chatNames = new ArrayList<>();

        chatNames.add("Masha");
        chatNames.add("Joshua CK");
        chatNames.add("Jerome");

        RecyclerView chat_list = view.findViewById(R.id.chat_list);
        chat_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChatAdapter(getActivity(), chatNames);
        chat_list.setAdapter(adapter);

        return view;
    }
}
