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

public class SigninFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f_fragment_signin, container, false);
        Button checkButton = view.findViewById(R.id.checkButton),sendButton = view.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity context = getActivity();
                EditText email = (EditText) context.findViewById(R.id.EmailFieldSignIn);
                EditText password = (EditText) context.findViewById(R.id.PasswordFieldSignIn);
                String emailText = email.getText().toString(),passwordText = password.getText().toString();
                if(!(emailText.isEmpty() || passwordText.isEmpty())){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoadingFragment()).commit();
                    new Auther(getActivity()).signIn(emailText,passwordText).addOnCompleteListener(getActivity(), new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(!task.isSuccessful()) context.getSupportFragmentManager().beginTransaction().replace(R.id.container, new SigninFragment()).commit();
                        }
                    });
                }
            }
        });
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment()).commit();
            }
        });
        return view;
    }
}