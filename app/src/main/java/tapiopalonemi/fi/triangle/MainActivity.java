package tapiopalonemi.fi.triangle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends AppCompatActivity {

    MainView triView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        triView = findViewById(R.id.mainView);
    }
}
