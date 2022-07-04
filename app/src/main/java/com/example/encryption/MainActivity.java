package com.example.encryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.encryption.databinding.ActivityMainBinding;
import com.example.encryption.encryption.EncHelper;

public class MainActivity extends AppCompatActivity {

    private static final String ALIS_KEY = "ALIS_KEY";


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        binding.enCrypt.setOnClickListener(view -> {
            try {
                String s = EncHelper.getInstance().encryptText(ALIS_KEY, binding.editText.getText().toString());
                binding.textViewResult.setText(s);
            } catch (Exception e) {
                Log.i("error_enCrypt", e.toString());

            }
        });

        binding.deCrypt.setOnClickListener(view -> {
            try {

                byte[] a = Base64.decode(binding.editText.getText().toString(), Base64.DEFAULT);

                String s = EncHelper.getInstance().decryptText(ALIS_KEY, a);
                binding.textViewResult.setText(s);
            } catch (Exception e) {
                Log.i("error_deCrypt", e.toString());
            }
        });

    }


}