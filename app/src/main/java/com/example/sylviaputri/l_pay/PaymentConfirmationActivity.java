package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviaputri.l_pay.Model.TransaksiJualBeli;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class PaymentConfirmationActivity extends AppCompatActivity {
    public Button btnPaymentConfCancel;
    public Button btnPaymentConfMakePayment;

    public TextView txtKonfirmasiHarga;
    public TextView txtKonfirmasiNamaPenjual;
    public TextView txtImageToko;
    ValueEventListener valueEvent;

    private TransaksiJualBeli transaksiJualBeli;

    Intent intent;
    String idTransaksi;
    FirebaseDatabase database =  FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser curUser = mAuth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        intent = getIntent();
        idTransaksi = intent.getStringExtra("idTransaksi");
        mDatabase = database.getReference().child("transaksi").child("dummy").child(idTransaksi);

        btnPaymentConfCancel = (Button) findViewById(R.id.btnPaymentConfCancel);
        btnPaymentConfMakePayment = (Button) findViewById(R.id.btnPaymentConfMakePayment);
        txtKonfirmasiNamaPenjual = (TextView) findViewById(R.id.txtKonfirmasiNamaPenjual);
        txtKonfirmasiHarga = (TextView) findViewById(R.id.txtKonfirmasiHarga);
        txtImageToko = (TextView) findViewById(R.id.txtImageToko);

        btnPaymentConfCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentConfirmationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnPaymentConfMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");;
                String waktu = simpledateformat.format(Calendar.getInstance().getTime());
                mDatabase.child("waktu").setValue(waktu);
                mDatabase.child("telp_pembeli").setValue(curUser.getPhoneNumber());

                DatabaseReference mTransJualBeli = database.getReference().child("transaksi").child("pembeli").child("jualBeli").child(curUser.getUid()).child(idTransaksi);
                mTransJualBeli.child("image_toko").setValue(txtImageToko.getText().toString());
                mTransJualBeli.child("nama_toko").setValue(txtKonfirmasiNamaPenjual.getText().toString());
                mTransJualBeli.child("total").setValue((txtKonfirmasiHarga.getText().toString()).substring(3));
                mTransJualBeli.child("waktu").setValue(waktu);

                Intent intent = new Intent(PaymentConfirmationActivity.this, EnterPinActivity.class);
                startActivity(intent);
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String image_tokoGetValue = dataSnapshot.child("image_toko").getValue(String.class);
                String nama_tokoGetValue = String.valueOf(dataSnapshot.child("nama_toko").getValue());
                Long totalGetValue = (Long) dataSnapshot.child("total").getValue();
                txtKonfirmasiNamaPenjual.setText(nama_tokoGetValue);
                txtKonfirmasiHarga.setText("Rp " + totalGetValue.toString());
                txtImageToko.setText(image_tokoGetValue);

                final long ONE_MEGABYTE = 1024 * 1024;
                StorageReference ref = FirebaseStorage.getInstance().getReference();
                ref.child(image_tokoGetValue).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        ImageView imgFoto = (ImageView) findViewById(R.id.imgPaymentConfSellerLogo);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imgFoto.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
