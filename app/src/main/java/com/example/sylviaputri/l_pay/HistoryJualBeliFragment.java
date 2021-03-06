package com.example.sylviaputri.l_pay;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sylviaputri.l_pay.Model.History;
import com.example.sylviaputri.l_pay.Model.HistoryWithdraw;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HistoryJualBeliFragment extends Fragment {
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    String myUID = currentFirebaseUser.getUid();

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference dbTransaksiJualBeli = database.getReference("transaksi").child("pembeli").child("jualBeli").child(myUID);
    DatabaseReference dbTransaksiWithdraw = database.getReference("transaksi").child("pembeli").child("withdraw").child(myUID);
    public List<History> listHistory;
    ValueEventListener valueEvent;

    private RecyclerView rcyHistory;
    private HistoryAdapter historyAdapter;

    public static HistoryJualBeliFragment newInstance() {
        HistoryJualBeliFragment fragment = new HistoryJualBeliFragment();
        return fragment;
    }

    public HistoryJualBeliFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listHistory = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();

        dbTransaksiJualBeli.addValueEventListener(valueEvent);
    }

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listHistory.clear();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                HashMap<String, Object> history = null;
                TextView noHistory = (TextView) getActivity().findViewById(R.id.txtHistoryJualBeliEmpty);
                if(!items.hasNext())  noHistory.setVisibility(View.VISIBLE);
                else noHistory.setVisibility(View.INVISIBLE);
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    history = (HashMap<String, Object>) item.getValue();
                    listHistory.add(new History(history.get("waktu").toString(), Integer.parseInt(history.get("total").toString()), history.get("telp_pembeli").toString(), item.getKey()));
                    historyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dbTransaksiJualBeli.addValueEventListener(valueEvent);
    }

    @Override
    public void onPause() {
        if (valueEvent != null && dbTransaksiJualBeli!=null) {
            dbTransaksiJualBeli.removeEventListener(valueEvent);
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (valueEvent != null && dbTransaksiJualBeli!=null) {
            dbTransaksiJualBeli.removeEventListener(valueEvent);
        }
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_jual_beli, container, false);

        rcyHistory = v.findViewById(R.id.rcyHistoryJualBeli);

        Context context = getActivity();
        historyAdapter = new HistoryAdapter(listHistory,context);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(context);
        rcyHistory.setLayoutManager(lm);
        rcyHistory.setItemAnimator(new DefaultItemAnimator());
        rcyHistory.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();

        return v;
    }

    public static class HistoryWithdrawAdapter extends RecyclerView.Adapter<HistoryWithdrawAdapter.HistoryWithdrawHolder>{

        private List<HistoryWithdraw> listHistoryWithdraw;
        private Context mContext;

        public HistoryWithdrawAdapter(List<HistoryWithdraw> listHistoryWithdraw, Context mContext){
            this.listHistoryWithdraw = listHistoryWithdraw;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public HistoryWithdrawAdapter.HistoryWithdrawHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_withdraw, viewGroup,false);
            return new HistoryWithdrawAdapter.HistoryWithdrawHolder(itemView);
        }


        @Override
        public void onBindViewHolder(@NonNull HistoryWithdrawAdapter.HistoryWithdrawHolder historyHolder, int i) {
            HistoryWithdraw historyWithdraw = listHistoryWithdraw.get(i);
            historyHolder.txtTotal.setText("Rp "+historyWithdraw.total);
            historyHolder.txtTanggal.setText(historyWithdraw.tglTransaksi);
            historyHolder.txtNoTransaksi.setText("" + historyWithdraw.idTransaksi);
        }

        @Override
        public int getItemCount() {
            return this.listHistoryWithdraw.size();
        }

        public class HistoryWithdrawHolder extends RecyclerView.ViewHolder {
            public TextView txtTanggal, txtTotal, txtNoTransaksi;

            public HistoryWithdrawHolder(View itemView) {
                super(itemView);
                txtTanggal = itemView.findViewById(R.id.txtHistoryWithdrawTanggal);
                txtTotal = itemView.findViewById(R.id.txtHistoryWithdrawTotal);
                txtNoTransaksi = itemView.findViewById(R.id.txtHistoryWithdrawNomorTransaksi);
            }
        }
    }
}
