package eu.robo.veit.robojam;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GridActivity extends AppCompatActivity {
    Context context = this;

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Game.getPlayerTurn("" +((Button)v).getText());
            Toast.makeText(context, ((Button)v).getText(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button a1 = (Button) findViewById(R.id.buttona1);
        a1.setOnClickListener(clickListener);
        Button b1 = (Button) findViewById(R.id.buttonb1);
        b1.setOnClickListener(clickListener);
        Button c1 = (Button) findViewById(R.id.buttonc1);
        c1.setOnClickListener(clickListener);
        Button d1 = (Button) findViewById(R.id.buttond1);
        d1.setOnClickListener(clickListener);
        Button e1 = (Button) findViewById(R.id.buttone1);
        e1.setOnClickListener(clickListener);
        Button f1 = (Button) findViewById(R.id.buttonf1);
        f1.setOnClickListener(clickListener);
        Button g1 = (Button) findViewById(R.id.buttong1);
        g1.setOnClickListener(clickListener);
        Button h1 = (Button) findViewById(R.id.buttonh1);
        h1.setOnClickListener(clickListener);

        Button a2 = (Button) findViewById(R.id.buttona2);
        a2.setOnClickListener(clickListener);
        Button b2 = (Button) findViewById(R.id.buttonb2);
        b2.setOnClickListener(clickListener);
        Button c2 = (Button) findViewById(R.id.buttonc2);
        c2.setOnClickListener(clickListener);
        Button d2 = (Button) findViewById(R.id.buttond2);
        d2.setOnClickListener(clickListener);
        Button e2 = (Button) findViewById(R.id.buttone2);
        e2.setOnClickListener(clickListener);
        Button f2 = (Button) findViewById(R.id.buttonf2);
        f2.setOnClickListener(clickListener);
        Button g2 = (Button) findViewById(R.id.buttong2);
        g2.setOnClickListener(clickListener);
        Button h2 = (Button) findViewById(R.id.buttonh2);
        h2.setOnClickListener(clickListener);

        Button a3 = (Button) findViewById(R.id.buttona3);
        a3.setOnClickListener(clickListener);
        Button b3 = (Button) findViewById(R.id.buttonb3);
        b3.setOnClickListener(clickListener);
        Button c3 = (Button) findViewById(R.id.buttonc3);
        c3.setOnClickListener(clickListener);
        Button d3 = (Button) findViewById(R.id.buttond3);
        d3.setOnClickListener(clickListener);
        Button e3 = (Button) findViewById(R.id.buttone3);
        e3.setOnClickListener(clickListener);
        Button f3 = (Button) findViewById(R.id.buttonf3);
        f3.setOnClickListener(clickListener);
        Button g3 = (Button) findViewById(R.id.buttong3);
        g3.setOnClickListener(clickListener);
        Button h3 = (Button) findViewById(R.id.buttonh3);
        h3.setOnClickListener(clickListener);

        Button a4 = (Button) findViewById(R.id.buttona4);
        a4.setOnClickListener(clickListener);
        Button b4 = (Button) findViewById(R.id.buttonb4);
        b4.setOnClickListener(clickListener);
        Button c4 = (Button) findViewById(R.id.buttonc4);
        c4.setOnClickListener(clickListener);
        Button d4 = (Button) findViewById(R.id.buttond4);
        d4.setOnClickListener(clickListener);
        Button e4 = (Button) findViewById(R.id.buttone4);
        e4.setOnClickListener(clickListener);
        Button f4 = (Button) findViewById(R.id.buttonf4);
        f4.setOnClickListener(clickListener);
        Button g4 = (Button) findViewById(R.id.buttong4);
        g4.setOnClickListener(clickListener);
        Button h4 = (Button) findViewById(R.id.buttonh4);
        h4.setOnClickListener(clickListener);

        Button a5 = (Button) findViewById(R.id.buttona5);
        a5.setOnClickListener(clickListener);
        Button b5 = (Button) findViewById(R.id.buttonb5);
        b5.setOnClickListener(clickListener);
        Button c5 = (Button) findViewById(R.id.buttonc5);
        c5.setOnClickListener(clickListener);
        Button d5 = (Button) findViewById(R.id.buttond5);
        d5.setOnClickListener(clickListener);
        Button e5 = (Button) findViewById(R.id.buttone5);
        e5.setOnClickListener(clickListener);
        Button f5 = (Button) findViewById(R.id.buttonf5);
        f5.setOnClickListener(clickListener);
        Button g5 = (Button) findViewById(R.id.buttong5);
        g5.setOnClickListener(clickListener);
        Button h5 = (Button) findViewById(R.id.buttonh5);
        h5.setOnClickListener(clickListener);

        Button a6 = (Button) findViewById(R.id.buttona6);
        a6.setOnClickListener(clickListener);
        Button b6 = (Button) findViewById(R.id.buttonb6);
        b6.setOnClickListener(clickListener);
        Button c6 = (Button) findViewById(R.id.buttonc6);
        c6.setOnClickListener(clickListener);
        Button d6 = (Button) findViewById(R.id.buttond6);
        d6.setOnClickListener(clickListener);
        Button e6 = (Button) findViewById(R.id.buttone6);
        e6.setOnClickListener(clickListener);
        Button f6 = (Button) findViewById(R.id.buttonf6);
        f6.setOnClickListener(clickListener);
        Button g6 = (Button) findViewById(R.id.buttong6);
        g6.setOnClickListener(clickListener);
        Button h6 = (Button) findViewById(R.id.buttonh6);
        h6.setOnClickListener(clickListener);

        Button a7 = (Button) findViewById(R.id.buttona7);
        a7.setOnClickListener(clickListener);
        Button b7 = (Button) findViewById(R.id.buttonb7);
        b7.setOnClickListener(clickListener);
        Button c7 = (Button) findViewById(R.id.buttonc7);
        c7.setOnClickListener(clickListener);
        Button d7 = (Button) findViewById(R.id.buttond7);
        d7.setOnClickListener(clickListener);
        Button e7 = (Button) findViewById(R.id.buttone7);
        e7.setOnClickListener(clickListener);
        Button f7 = (Button) findViewById(R.id.buttonf7);
        f7.setOnClickListener(clickListener);
        Button g7 = (Button) findViewById(R.id.buttong7);
        g7.setOnClickListener(clickListener);
        Button h7 = (Button) findViewById(R.id.buttonh7);
        h7.setOnClickListener(clickListener);

        Button a8 = (Button) findViewById(R.id.buttona8);
        a8.setOnClickListener(clickListener);
        Button b8 = (Button) findViewById(R.id.buttonb8);
        b8.setOnClickListener(clickListener);
        Button c8 = (Button) findViewById(R.id.buttonc8);
        c8.setOnClickListener(clickListener);
        Button d8 = (Button) findViewById(R.id.buttond8);
        d8.setOnClickListener(clickListener);
        Button e8 = (Button) findViewById(R.id.buttone8);
        e8.setOnClickListener(clickListener);
        Button f8 = (Button) findViewById(R.id.buttonf8);
        f8.setOnClickListener(clickListener);
        Button g8 = (Button) findViewById(R.id.buttong8);
        g8.setOnClickListener(clickListener);
        Button h8 = (Button) findViewById(R.id.buttonh8);
        h8.setOnClickListener(clickListener);
        
    }

}
