package com.example.criararquivotxt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Referenciando os componentes com as variáveis para manipulação
    private EditText editConteudo;
    private Button botaoCriar;
    private TextView textResultado;
    private Button botaoVisualizar;

    // Criando uma variável constante com nome do arquivo
    private static final String ARQUIVO_CONTEUDO = "ArquivoConteudo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Atribuindo as propriedades dos widget
        editConteudo = findViewById(R.id.editConteudo);
        botaoCriar = findViewById(R.id.botaoCriar);
        textResultado = findViewById(R.id.textResuldado);
        botaoVisualizar = findViewById(R.id.botaoVisualizar);

        // Criando método para salvar o conteúdo ao clicar no botão CRIAR
        botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Criando arquivo em xml com SharedPreferences
                // mode 0 -> arquivo poderá ser lido apenas por este app
                SharedPreferences conteudoEmXML = getSharedPreferences(ARQUIVO_CONTEUDO, 0);

                // Criando o ojeto para a edição
                SharedPreferences.Editor editor = conteudoEmXML.edit();

                // Validando existência do conteúdo
                if (editConteudo.getText().toString().equals("")) {

                    // Se o conteúdo for vazio, exibirá uma mensagem rápida
                    Toast.makeText(getApplicationContext(), "Campo vazio, favor digitar o conteúdo",
                            Toast.LENGTH_LONG).show();

                } else {

                    // Caso existir conteúdo, irá salvar o mesmo no arquivo
                    String conteudo = editConteudo.getText().toString();

                    // Salvando o valor do conteúdo em uma chave
                    editor.putString("conteudo", conteudo);

                    // Salvando os dados no arquivo
                    editor.commit();

                }
            }
        });

        // Criando método para visualizar o último conteúdo salvo ao clicar no botão VISUALIZAR
        botaoVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Recuperando os dados salvo no arquivo ao executar o app
                SharedPreferences conteudoEmXML = getSharedPreferences(ARQUIVO_CONTEUDO, 0);

                // Validando a existência de uma chave conteudo em conteudoEmXML
                if (conteudoEmXML.contains("conteudo")) {

                    // Recuperando os dados
                    String conteudo = conteudoEmXML.getString("conteudo", "Não é possivel visualizar");

                    // Setando o conteudo do arquivo no campo
                    textResultado.setText(conteudo);

                } else {

                    // Caso não encontre a chave, informará no campo
                    textResultado.setText("Não é possivel visualizar");

                }
            }
        });
    }
}