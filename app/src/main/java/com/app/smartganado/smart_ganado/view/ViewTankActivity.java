package com.app.smartganado.smart_ganado.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.dao.CattleDAO;
import com.app.smartganado.smart_ganado.model.dao.TanksDAO;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.view.adapter.CattleAdapter;
import com.app.smartganado.smart_ganado.view.adapter.TankAdapter;

public class ViewTankActivity extends AppCompatActivity {

    private TankAdapter adapter;
    private ListView tankListView;
    private UserApp user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tank);
        tankListView = findViewById(R.id.listaTanques);


        adapter = new TankAdapter(getApplicationContext(), TanksDAO.getTankList());
        tankListView.setAdapter(adapter);

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");

        Log.i("server", "llego USER: " + user.toString());
        TanksDAO.getTanks(user.getPhone(), adapter);//Getting cattles's by user_phone

        tankListView.setOnItemClickListener(tankListListener);
    }

    private AdapterView.OnItemClickListener tankListListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Tank tank = (Tank) tankListView.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), NewTankActivity.class);
            intent.putExtra("Info", tank);
            startActivity(intent);
        }
    };


    public void onClickNewTank(View view) {
        Intent intent = new Intent(getApplicationContext(), NewTankActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
