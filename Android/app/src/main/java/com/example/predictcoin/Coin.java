package com.example.predictcoin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Coin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Coin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Coin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Coin.
     */
    // TODO: Rename and change types and number of parameters
    public static Coin newInstance(String param1, String param2) {
        Coin fragment = new Coin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin, container, false);

        // bitcoin
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.btc);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getActivity(), BTC_Coin.class);
               startActivity(intent);
            }
        });

        // ethereum
        ImageButton imageButton2 =(ImageButton) view.findViewById(R.id.eithereum);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), Ethereum_Coin.class);
                startActivity(intent2);
            }
        });

        // ada
        ImageButton imageButton3 = (ImageButton)  view.findViewById(R.id.ada);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity(), Ada_Coin.class);
                startActivity(intent3);
            }
        });

        // solana
        ImageButton imageButton4 = (ImageButton) view.findViewById(R.id.solana);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getActivity(), Solana_Coin.class);
                startActivity(intent4);
            }
        });

        // sandbox
        ImageButton imageButton5 = (ImageButton) view.findViewById(R.id.sandbox);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getActivity(), Sandbox_Coin.class);
                startActivity(intent5);
            }
        });

        // bora
        ImageButton imageButton6 = (ImageButton) view.findViewById(R.id.bora);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(getActivity(), Bora_Coin.class);
                startActivity(intent6);
            }
        });

        // ripple
        ImageButton imageButton7 = (ImageButton) view.findViewById(R.id.ripple);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(getActivity(), Ripple_Coin.class);
                startActivity(intent7);
            }
        });

        // milk
        ImageButton imageButton8 = (ImageButton) view.findViewById(R.id.milk);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent8 = new Intent(getActivity(), Milk_Coin.class);
                startActivity(intent8);
            }
        });

        return view;
    }

}