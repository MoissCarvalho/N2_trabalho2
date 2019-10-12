package com.example.n2_trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView id;
    private TextView name;
    private TextView year;
    private TextView color;
    private TextView pantone_value;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadPessoa download = new DownloadPessoa();

        id = (TextView)findViewById(R.id.id);
        name = (TextView)findViewById(R.id.name);
        year = (TextView)findViewById(R.id.year);
        color = (TextView)findViewById(R.id.color);
        pantone_value = (TextView)findViewById(R.id.pantone_value);

        download.execute();
    }

    private class DownloadPessoa extends AsyncTask<Void, Void, Pessoa> {

        @Override
        protected void onPreExecute(){
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Pessoa doInBackground(Void... arams) {
            Conversor util = new Conversor();
            return util.getInformacao("https://reqres.in/api/user/3");
        }

        @Override
        protected void onPostExecute(Pessoa pessoa){
            id.setText(" id: " + pessoa.getId());
            name.setText(pessoa.getName());
            year.setText(pessoa.getYear());
            color.setText(pessoa.getColor());
            pantone_value.setText(pessoa.getPantone_value());

            load.dismiss(); //deleta o dialog
        }
    }
}
