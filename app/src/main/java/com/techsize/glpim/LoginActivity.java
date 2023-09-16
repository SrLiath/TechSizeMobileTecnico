package com.techsize.glpim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;

    private Button buttonConfig;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonConfig = findViewById(R.id.buttonConfig);

        apiService = ApiClient.getApiService();
        buttonConfig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ConfigLogin.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();

                Call<ApiResponse> call = apiService.loginUser(login, password);
                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            handleResponse(response.body(), login, password);
                        } else {
                            Toast.makeText(LoginActivity.this, "Erro no login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Erro na requisição", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void handleResponse(ApiResponse apiResponse, String login, String password) {
        if (apiResponse.isSuccess()) {
            saveCredentialsAndToken(login, password, apiResponse.getSessionToken());
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            showInvalidCredentialsPopup();
        }
    }

    private void showInvalidCredentialsPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Credenciais Inválidas")
                .setMessage("As credenciais fornecidas são inválidas. Por favor, verifique seus dados.")
                .setPositiveButton("OK", null)
                .show();
    }

    private void saveCredentialsAndToken(String login, String password, String sessionToken) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("login", login);
        editor.putString("password", password);
        editor.putString("sessionToken", sessionToken);
        editor.apply();
    }
}
