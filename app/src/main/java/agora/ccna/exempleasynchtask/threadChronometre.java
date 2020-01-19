package agora.ccna.exempleasynchtask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/*
    Thread capable d'interagir avec les vues des Activity
    Hérite de AsynchTask avec 3 template:   < T , U , V >
            template T : type du paramètre passé à la méthode execute( T... x )
            template U : type du paramètre passé à la méthode publishProgress ( U... x )
            template V : type du paramètre retourné par la méthode doItBackground puis passé à la méthode onPostExecute( V... x)
     les types natifs sont interdits.
     Ici, on choisit <Integer , Integer , String >
             le premier Integer     valeur de départ du décompte
             le second  Integer     valeur intermédiaire passée à la méthode publishProgress
             le troisième String    texte final à affecter au textView
 */
public class threadChronometre extends AsyncTask <Integer , Integer , String>{

    //membre qui deviendra le textView de la classe principale à modifier
    TextView tv;
    //constructeur de la classe, on passe le textView cible.
    public threadChronometre(TextView t){
        tv = t;
    }
    //démarre lorsque la méthode 'execute' est appelée deopuids la classe principale.
    //le paramètre est celui passé lors de l'appel de la méthode 'execute(...)'
    //A la fin, elle retourne le string correspondant au texte désiré: "Décompte terminé"
    @Override
    protected String doInBackground(Integer... integers) {
        int nb = integers[0].intValue();
        int i = nb;
        while(i > 0){
            this.publishProgress(Integer.valueOf(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
        return "Décompte terminé";
    }
    //déclenchée à chaque appel de 'publishProcess' depuis 'doItBackGround'
    //permet d'afficher les valeurs intermédiares du décompte (la valeur de i qui se décrémente)
    @Override
    protected void onProgressUpdate(Integer... i){
        super.onProgressUpdate(i);
        tv.setText(Integer.toString(i[0]));
    }
    //exécutée lorsque le thread se termine (fin de 'doItBackGround')
    //le paramètre est le texte (String) retourné à la fin de doItBackGround
    @Override
    protected void onPostExecute(String r){
        super.onPostExecute(r);
        tv.setText(r);
    }
    //exécutée avant le début du démarrage du thread. non utilisée ici
    @Override
    public void onPreExecute(){

    }
}
