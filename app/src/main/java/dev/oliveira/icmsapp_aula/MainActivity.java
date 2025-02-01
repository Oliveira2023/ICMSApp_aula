package dev.oliveira.icmsapp_aula;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextEstado;
    private EditText mEditTextValor;
    private TextView mTextViewPorcentagem;
    private TextView mTextViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mEditTextEstado = findViewById(R.id.editTextEstado);
        mEditTextValor = findViewById(R.id.editTextValor);
        mTextViewPorcentagem = findViewById(R.id.textViewPorcentagem);
        mTextViewTotal = findViewById(R.id.textViewTotal);
    }
    public void calcular(View view) {

        String estado = "";

        try {
            estado = mEditTextEstado.getText().toString();
        }catch (NullPointerException e) {
            var cause = e.getCause();
            if (cause != null) {
                cause.printStackTrace();
            }else {
                e.printStackTrace();
            }

        }


        String valorString = mEditTextValor.getText().toString();
        if (valorString.isEmpty()) {
            valorString = "0";
            showAlertDialog("Digite um valor");
        }
        float valorMercadoria = Float.parseFloat(valorString);

        float porcentagem = 0;


        switch (estado) {
            case "AC":
                porcentagem = 0.07f;
                break;
            case "SP":
                porcentagem = 0.18f;
                break;
            case "RO":
                porcentagem = 0.12f;
                break;
            default:
                porcentagem = 0.18f;
                break;
        }
        float total = valorMercadoria + (valorMercadoria * porcentagem);

        mTextViewPorcentagem.setText(String.valueOf(porcentagem) + "%");
        mTextViewTotal.setText(String.format("R$ %.2f", total));


    }
    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}