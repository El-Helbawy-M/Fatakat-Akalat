package com.example.fatakat.Layout.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fatakat.Core.Services.Auther;
import com.example.fatakat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class RegisterFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_fragment_register, container, false);
        Button checkButton = view.findViewById(R.id.checkButton),sendButton = view.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity context = getActivity();
                EditText email = (EditText) context.findViewById(R.id.EmailFieldRegister),password = (EditText) context.findViewById(R.id.PasswordFieldRegister),name = (EditText) context.findViewById(R.id.NameFiled);
                String emailText = email.getText().toString(),passwordText = password.getText().toString(),nameText = name.getText().toString();

                if(!(emailText.isEmpty() || passwordText.isEmpty())){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoadingFragment()).commit();
                    new Auther(getActivity()).register(nameText,emailText,passwordText).addOnCompleteListener(context, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            context.getSupportFragmentManager().beginTransaction().replace(R.id.container,new RegisterFragment()).commit();
                        }
                    });
                }
            }
        });
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new SigninFragment()).commit();
            }
        });
        return view;
    }

}