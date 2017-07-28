package cc.metapro.nfc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Boollean on 2017/7/15.
 */

public class RecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private CardAdapter mAdapter;
    private List<Card> mCards;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_recyclerview, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mItemTouchHelper = new ItemTouchHelper(new MyCallBack());
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void updateUI() {

        CardLab cardLab = CardLab.get(getActivity());
        List<Card> cards = cardLab.getCards();

        if (mAdapter == null) {
            mAdapter = new CardAdapter(cards);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCards(cards);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String DIALOG_SIMULATE="DialogSimulate";
        private TextView mMainTitle;
        private TextView mSubTitle;
        private Button mButton;
        private Card mCard;

        public CardHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mMainTitle = itemView.findViewById(R.id.main_title);
            mSubTitle = itemView.findViewById(R.id.sub_title);
            mButton=itemView.findViewById(R.id.simulate);
            mButton.setText(R.string.simulate);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager manager=getFragmentManager();
                    MyDialogFragment dialogFragment=new MyDialogFragment();
                    dialogFragment.show(manager,DIALOG_SIMULATE);
                }
            });
        }

        @Override
        public void onClick(View view) {
            Intent intent = NFCCardActivity.newIntent(getActivity(), mCard.getId());
            startActivity(intent);
        }

        public void bindCard(Card card) {
            mCard = card;
            mMainTitle.setText(mCard.getTitle());
            mSubTitle.setText(mCard.getMessage());
        }
    }

    private class CardAdapter extends RecyclerView.Adapter<CardHolder> {

        public CardAdapter(List<Card> cards) {
            mCards = cards;
        }

        @Override
        public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.card_list_view, parent, false);
            return new CardHolder(view);
        }

        @Override
        public void onBindViewHolder(CardHolder holder, int position) {
            Card card = mCards.get(position);
            holder.bindCard(card);
        }

        @Override
        public int getItemCount() {
            return mCards.size();
        }

        public void setCards(List<Card> cards) {
            mCards = cards;
        }
    }

    private class MyCallBack extends ItemTouchHelper.Callback {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = 0;
            int swipeFlags = 0;

            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                dragFlags = 0;
                swipeFlags = ItemTouchHelper.START;
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition();
            int to = target.getAdapterPosition();

            Card moveItem = mCards.get(from);
            mCards.remove(from);
            mCards.add(to, moveItem);

            mAdapter.notifyItemMoved(from, to);
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            CardLab.get(getActivity()).deleteCard(mCards.get(position));
            mCards.remove(position);
            mAdapter.notifyItemRemoved(position);

        }
    }
}
