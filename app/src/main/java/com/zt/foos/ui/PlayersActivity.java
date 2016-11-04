package com.zt.foos.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zt.foos.R;
import com.zt.foos.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class PlayersActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Player>> {
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.playersEmpty)
    TextView empty;

    @BindView(R.id.editName)
    EditText playerName;

    private final Realm realm = Realm.getDefaultInstance();

    private final PlayersAdapter adapter = new PlayersAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players);
        ButterKnife.bind(this);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        realm.where(Player.class).findAllAsync().addChangeListener(this);
    }

    @OnClick(R.id.btnAdd)
    public void addPlayer(View v) {
        String name = playerName.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            Player player = new Player();
            player.setName(name);

            realm.beginTransaction();
            try {
                realm.insert(player);
            }
            finally {
                realm.commitTransaction();
            }
        }
    }

    @Override
    public void onChange(RealmResults<Player> results) {
        if (results.size() > 0) {
            recycler.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
            adapter.setPlayers(realm.copyFromRealm(results));
        }
        else {
            empty.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }
    }
}
