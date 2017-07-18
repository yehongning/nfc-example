package cc.metapro.nfc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


/**
 * Created by Boollean on 2017/7/16.
 */

public class CardFragment extends Fragment {

    private static final String ARG_CARD_ID = "crime_id";

    private Card mCard;
    private EditText mTitleField;
    private EditText mNoField;
    private EditText mMessageField;
    private Button mButton;

    public static CardFragment newInstance(UUID cardId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CARD_ID, cardId);

        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID cardId = (UUID) getArguments().getSerializable(ARG_CARD_ID);

        mCard = CardLab.get(getActivity()).getCard(cardId);
    }

    @Override
    public void onPause() {
        super.onPause();
        CardLab.get(getActivity()).updateCard(mCard);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_detail, container, false);

        mTitleField = v.findViewById(R.id.card_title);
        mTitleField.setText(mCard.getTitle());

        mNoField = v.findViewById(R.id.card_card_no);
        mNoField.setText(mCard.getNo());

        mMessageField = v.findViewById(R.id.card_message);
        mMessageField.setText(mCard.getMessage());

        mButton = v.findViewById(R.id.card_confirm);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCard.setTitle(mTitleField.getText().toString());
                mCard.setNo(mNoField.getText().toString());
                mCard.setMessage(mMessageField.getText().toString());

                Toast.makeText(getContext(), R.string.confirm , Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
