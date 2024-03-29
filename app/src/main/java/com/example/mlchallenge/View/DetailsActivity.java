package com.example.mlchallenge.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mlchallenge.Model.Product;
import com.example.mlchallenge.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This activity shows the detials of the clicked product
 */
public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.imageDetails)
    ImageView imageDetails;
    @BindView(R.id.titleDetails)
    TextView titleDetails;
    @BindView(R.id.priceDetails)
    TextView priceDetails;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.shipping)
    ImageView shipping;
    @BindView(R.id.mercadoPago)
    ImageView mercadoPago;
    @BindView(R.id.spinner)
    Spinner avaliableQuantity;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.cart)
    Button cart;
    @BindView(R.id.conditionQuantity)
    TextView conditionQuantity;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        List<Integer> spinnerArray = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        Product product = (Product) extras.getSerializable("product");
        ButterKnife.bind(this);

        for (int i = 1; i <= product.getAvaliableQuantity(); i++) {
            spinnerArray.add(i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerArray
        );
        avaliableQuantity.setAdapter(adapter);
        String condition = product.getCondition().equals("new") ? "Nuevo" : "Usado";
        conditionQuantity.setText(condition + " - " + product.getSoldQuantity() + " vendidos");
        titleDetails.setText(product.getTitle());
        priceDetails.setText("$" + product.getPriceFormated());
        address.setText(product.getAddress().getStateName() + ", " + product.getAddress().getCityName());

        if (product.getShipping().isFreeShipping()) {
            shipping.setVisibility(View.VISIBLE);
        }

        if (product.getAcceptsMercadoPago()) {
            mercadoPago.setVisibility(View.VISIBLE);
        }

        buy.setOnClickListener(v -> {
            if (mToast != null) {
                mToast.cancel();
            }

            mToast = Toast.makeText(DetailsActivity.this, "Comprar", Toast.LENGTH_SHORT);
            mToast.show();
        });

        cart.setOnClickListener(v -> {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(DetailsActivity.this, "Agregar al carrito", Toast.LENGTH_SHORT);
            mToast.show();
        });

        Glide.with(this)
                .load(product.getThumbnail())
                .into(imageDetails);
    }
}
