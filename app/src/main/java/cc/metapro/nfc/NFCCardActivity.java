package cc.metapro.nfc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.UUID;


/**
 * Created by Boollean on 2017/7/16.
 */

public class NFCCardActivity extends AppCompatActivity {
    private static final String EXTRA_CARD_ID = "cc.metapro.nfc.card_id";
    private List<Card> mCards;

    public static Intent newIntent(Context packageContext, UUID cardId) {
        Intent intent = new Intent(packageContext, NFCCardActivity.class);
        intent.putExtra(EXTRA_CARD_ID, cardId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_viewpager);

        Toolbar toolbar = findViewById(R.id.toolbar_card_detail);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        UUID cardId = (UUID) getIntent().getSerializableExtra(EXTRA_CARD_ID);

        mCards = CardLab.get(this).getCards();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.card_fragment_container, CardFragment.newInstance(cardId))
                .commit();
    }
}

