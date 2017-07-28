package cc.metapro.nfc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Boollean on 2017/7/16.
 */

public class NFCCardActivity extends AppCompatActivity {
    private static final String EXTRA_CARD_ID = "cc.metapro.nfc.card_id";
    private final List<String> titles = new ArrayList<String>();
    private Card mCard;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private TabLeftFragment mLeftFragment;
    private TabMiddleFragment mMiddleFragment;
    private TabRightFragment mRightFragment;

    private TextInputLayout aTextInputLayout;
    private TextInputLayout bTextInputLayout;

    private EditText mTitleField;
    private EditText mMessageField;

    private Button mButton;
    private ViewPager mViewPager;
    private MyPagerAdapter mMyPagerAdapter;

    public static Intent newIntent(Context packageContext, UUID cardId) {
        Intent intent = new Intent(packageContext, NFCCardActivity.class);
        intent.putExtra(EXTRA_CARD_ID, cardId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_detail_input_view);

        UUID cardId = (UUID) getIntent().getSerializableExtra(EXTRA_CARD_ID);
        mCard = CardLab.get(this).getCard(cardId);

        Toolbar toolbar = findViewById(R.id.toolbar_card_detail);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        aTextInputLayout = findViewById(R.id.a_textinputlayout);
        mTitleField = aTextInputLayout.getEditText();
        mTitleField.setText(mCard.getTitle());

        bTextInputLayout = findViewById(R.id.b_textinputlayout);
        bTextInputLayout.setHint("备注");
        mMessageField = bTextInputLayout.getEditText();
        mMessageField.setText(mCard.getMessage());

        titles.add(getString(R.string.original_information));
        titles.add(getString(R.string.card_information));
        titles.add(getString(R.string.transaction_information));

        mLeftFragment = new TabLeftFragment();
        mMiddleFragment = new TabMiddleFragment();
        mRightFragment = new TabRightFragment();
        mFragments.add(0, mLeftFragment);
        mFragments.add(1, mMiddleFragment);
        mFragments.add(2, mRightFragment);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mMyPagerAdapter =new MyPagerAdapter(getSupportFragmentManager(), mFragments,
                titles, NFCCardActivity.this);
        mViewPager = findViewById(R.id.vp_viewpager);
        mViewPager.setAdapter(mMyPagerAdapter);
        mViewPager.setCurrentItem(1);

        tabLayout.setupWithViewPager(mViewPager);

        mButton = findViewById(R.id.save);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCard.setTitle(mTitleField.getText().toString());
                mCard.setMessage(mMessageField.getText().toString());
                CardLab.get(NFCCardActivity.this).updateCard(mCard);

                mCard.setOriginalInformation(mLeftFragment.getTextInformation());
                mCard.setCardInformation(mMiddleFragment.getTextInformation());
                mCard.setTransactionInformation(mRightFragment.getTextInformation());

                Toast.makeText(NFCCardActivity.this, R.string.confirm, Toast.LENGTH_SHORT).show();
            }
        });
    }
}