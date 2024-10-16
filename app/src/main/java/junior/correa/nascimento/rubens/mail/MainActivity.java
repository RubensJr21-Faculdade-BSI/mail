package junior.correa.nascimento.rubens.mail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        Button btnEnviar = findViewById(R.id.btnEnviar);
        // Definicao da acao do click do botao
        btnEnviar.setOnClickListener(v -> {
            // Obtendo dados digitados pelo usuario

            // Obtendo elemento onde o usuário digitou o e-mail
            EditText etEmail = findViewById(R.id.etEmail);
            String email = etEmail.getText().toString();

            // Obtendo elemento onde o usuário digitou o assunto
            EditText etAssunto = findViewById(R.id.etAssunto);
            String assunto = etAssunto.getText().toString();

            // Obtendo elemento onde o usuário digitou o conteúdo o e-mail
            EditText etTexto = findViewById(R.id.etTexto);
            String texto = etTexto.getText().toString();

            // Cria uma intenção de enviar algo
            Intent i = new Intent(Intent.ACTION_SENDTO);

            // Especifica que é ncessário ser um aplicativo de e-mail
            i.setData(Uri.parse("mailto:"));

            // É necessário criar uma lista de strings contendo o email passado, já que o parâmetro só aceita lista
            String[] emails = new String[]{email};

            /* Essa é a forma que o professor indicou de como passar atributos de uma tela pra outro */

            // Aqui é especificado, no objeto da Intent, o endereço de email a qual será encaminhado o e-mail
            i.putExtra(Intent.EXTRA_EMAIL, emails);
            // Aqui é especificado, no objeto da Intent, o assunto do email
            i.putExtra(Intent.EXTRA_SUBJECT, assunto);
            // Aqui é especificado, no objeto da Intent, o conteúdo do email
            i.putExtra(Intent.EXTRA_TEXT, texto);

            try {
                startActivity(Intent.createChooser(i, "Escolha o APP"));
            }
            catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
            }

        });
    }
}