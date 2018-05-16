package anotacoes.gustavo.com.anotaes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{


    private EditText edtAnotacoes;
    private ImageView ivSalvar;
    private static final String FILE_NAME = "arquivo_text.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Impedindo que o teclado mova os elementos de interface
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN );

        edtAnotacoes = findViewById(R.id.edtAnotacoes);
        ivSalvar = findViewById(R.id.ivSalvar);

        ivSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String typedText = edtAnotacoes.getText().toString();
                saveText( typedText );

                Toast.makeText( getApplicationContext(), "Anotações Salvas!" , Toast.LENGTH_SHORT).show();

            }
        });

        if ( catchFileValor() != null )
        {
            edtAnotacoes.setText(  catchFileValor());
        }


    }

    private void saveText(String text)
    {
        try
        {

            // Abrindo e criando o arquivo que ira armazenar as anotações
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter( openFileOutput( FILE_NAME, Context.MODE_PRIVATE) ); // Definindo nome do arquivo através de uma constante e indicando o "modo"
            outputStreamWriter.write( text );                                                                                   // Definindo o valor que será salvo no arquivo
            outputStreamWriter.close();                                                                                         // Fechando o arquivo

        }catch ( IOException exception)
        {
            Log.v( "MainActivity", exception.toString() );
        }

    }


    private String catchFileValor()
    {
        String output = "";

        try
        {

            // Abrindo e recuperando dados de acordo com o arquivo criado
            InputStream file = openFileInput( FILE_NAME );
            if( file != null )
            {
                 //Lendo o arquivo
                InputStreamReader reader = new InputStreamReader( file );

                //Gerar Buffer do arquivo lido
                BufferedReader buffer = new BufferedReader( reader );

                //Recuperando textos do arquivo
                String readedLine = "";
                while ( (readedLine = buffer.readLine())  != null)
                {
                    output += readedLine;
                }
                //Fechando o arquivo
                file.close();

            }



        }catch ( IOException exception )
        {
            Log.v( "MainActivity", exception.toString() );
        }

        return output;
    }

}
