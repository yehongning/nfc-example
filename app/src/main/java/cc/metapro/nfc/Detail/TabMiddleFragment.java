package cc.metapro.nfc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ASUS on 2017/7/18.
 */

public class TabMiddleFragment extends Fragment {
    public TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_tab_middle, container, false);
        mTextView = v.findViewById(R.id.card_tab_2);
        mTextView.setBackgroundColor(Color.BLACK);
        mTextView.setText("The card's information will show here.");

        return v;
    }

    public String getTextInformation() {
        return mTextView.getText().toString();
    }
}
