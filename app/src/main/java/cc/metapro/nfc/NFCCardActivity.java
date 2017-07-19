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
import android.view.Menu;
import android.widget.EditText;

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

    private TextInputLayout aTextInputLayout;
    private TextInputLayout bTextInputLayout;
    private TextInputLayout cTextInputLayout;

    private EditText mTitleField;
    private EditText mNoField;
    private EditText mMessageField;

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

        final UUID cardId = (UUID) getIntent().getSerializableExtra(EXTRA_CARD_ID);
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
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCard.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bTextInputLayout = findViewById(R.id.b_textinputlayout);
        mNoField = bTextInputLayout.getEditText();
        mNoField.setText(mCard.getNo());
        mNoField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCard.setNo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cTextInputLayout = findViewById(R.id.c_textinputlayout);
        cTextInputLayout.setHint("备注");
        mMessageField = cTextInputLayout.getEditText();
        mMessageField.setText(mCard.getMessage());
        mMessageField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCard.setMessage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        titles.add(getString(R.string.original_information));
        titles.add(getString(R.string.card_information));
        titles.add(getString(R.string.transaction_information));

        mFragments.add(0, new TabLeftFragment());
        mFragments.add(1, new TabMiddleFragment());
        mFragments.add(2, new TabRightFragment());

        TabLayout tabLayout = findViewById(R.id.tabs);

        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments,
                titles, NFCCardActivity.this);
        mViewPager = findViewById(R.id.vp_viewpager);
        mViewPager.setAdapter(mMyPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);
    }
}