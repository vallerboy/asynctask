package pl.akademiakodu.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.button);

        String text = "Witaj swiecie";
        progressBar.setMax(text.trim().length());
        new FakeTask().execute(text);
    }

    // 1. Jaki parametr przyjmuje doInBackground
    // 2. Co przymuje onProgressUpdate
    // 3. Co zwraca metoda doInBackground i co przyjmuje onPostExecute
    private class FakeTask extends AsyncTask<String, Integer, Void>{
        // Inny wątek
        @Override
        protected Void doInBackground(String ... arg) {
            for(int i = 0; i <= arg[0].trim().length(); i++) {
                publishProgress(i);


            }
            return null;
        }

        // Główny wątek
        @Override
        protected void onProgressUpdate(Integer ... values) {

             progressBar.setProgress(values[0]);

        }
        // Główny wątek
        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Rozpoczynam pobieranie", Toast.LENGTH_SHORT).show();
        }
        // Główny wątek
        @Override
        protected void onPostExecute(Void integer) {
            Toast.makeText(MainActivity.this, "Zakończyłem pobieranie", Toast.LENGTH_SHORT).show();
        }
    }
}
