package khairy.com.souqtask;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import khairy.com.souqtask.fragment.MainView;
import khairy.com.souqtask.register.Connection;
import khairy.com.souqtask.register.Prefrance;

import static khairy.com.souqtask.register.Prefrance.PREFS_NAME;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private SharedPreferences prefs;

    @Override
    protected void attachBaseContext(Context newBase) {

    prefs = newBase.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String language = prefs.getString("lang" , "en");
        if (language.equals("ar")){
            newBase.setTheme(R.style.arTheme);
            Prefrance.savelang(newBase, "en");
        }
        else{
            newBase.setTheme(R.style.AppTheme);
            Prefrance.savelang(newBase , "ar");
        }
        setLocale(language , newBase);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initInstancesDrawer();

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment mainView = new MainView();
            fragmentTransaction.add(R.id.framContainer, mainView, MainView.class.getSimpleName());
            fragmentTransaction.commit();
        }
    }

    public void setLocale(String lang , Context context){

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, null);


    }

    private void initInstancesDrawer() {
        toolbar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.translate, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.replace :
                new Connection(getSupportFragmentManager());
                break;

            case android.R.id.home :
                getSupportFragmentManager().popBackStack(MainView.BACK_STACK_ROOT_TAG , FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
        }
        return true;
    }
}
