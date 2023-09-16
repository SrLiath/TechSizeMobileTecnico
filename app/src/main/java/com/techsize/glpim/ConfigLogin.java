package com.techsize.glpim;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigLogin extends AppCompatActivity {

    private ImageButton ButtonConfReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_login);
        ButtonConfReturn = findViewById(R.id.ButtonConfReturn);
        ButtonConfReturn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigLogin.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // Aqui você pode configurar o layout e a lógica da atividade ConfigLogin
    }
}
