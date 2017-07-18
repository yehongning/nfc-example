package cc.metapro.nfc;

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

public class TabMiddleFragment extends Fragment{
    private TextView mTextView;

    public static TabMiddleFragment newInstance() {
        TabMiddleFragment fragment = new TabMiddleFragment();
        return fragment;
}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_tab_2, container, false);

        mTextView=v.findViewById(R.id.card_tab_2);
        return v;
    }
}
