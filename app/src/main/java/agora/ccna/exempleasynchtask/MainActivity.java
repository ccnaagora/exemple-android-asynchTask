package agora.ccna.exempleasynchtask;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bt;
    TextView tv;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //instanciation des membres.
        ed = findViewById(R.id.ed);
        tv = findViewById(R.id.tv);
        bt = findViewById(R.id.bt);
        //gestion du click sur le bouton:
        //création du thread en passant en paramètre
        //          le nombre de décrémentation saisi dans la zone d'édition
        //          le textview qui affichera l'évolution du décompte.
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //création du thread en passant le textView en paramètre
                threadChronometre th = new threadChronometre(tv);
                //démarrage du thread: exécution de 'doItBackGround'
                //le paramètre est le nombre saisi dans l'Edittext
                th.execute(Integer.decode(ed.getText().toString()));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
