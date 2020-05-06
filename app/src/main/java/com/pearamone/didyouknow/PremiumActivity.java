package com.pearamone.didyouknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PremiumActivity extends AppCompatActivity implements IAPHelper.IAPHelperListener {

    Button premiumButton;
    private String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoWQeeZFSWrpJG4oSfsNFSkXGlbhaGvu5HxmqqnEasyk5JIQQubOEgmk3pk9ny8pF5u+9CnlNvnXjPYC3VNr6xlRybBPedXeD+neviY3J5k19tMrNsZI0xfg1o+wHjitNZ6A2btBZdCojSdijWNfBBbEzBQQGVT35cJzSZoCSVAf/IUoFmRicToxyHxKHZTTaxCS/2Jm6210Py17Q/zUwHVjZKJYHRS3H/2HZgRtLwnLCtv4xxx0Ek9lnLcBWvD6Zc/bQiMeaOqSQdbMlI7BsdwNoXuz3fS4iUvoi4E69gQJsVfjmnc65uuGloRf+rDpd71wUAv+SVRtA34CBb38tjQIDAQAB";
    IAPHelper iapHelper;
    HashMap<String, SkuDetails> skuDetailsHashMap = new HashMap<>();
    final String VIP = "vip_status";
    final String TEST = "android.test.purchased"; //This id can be used for testing purpose
    private List<String> skuList = Arrays.asList(VIP, TEST);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);
        iapHelper = new IAPHelper(this, this, skuList);

        premiumButton = findViewById(R.id.upgrade_button);
        premiumButton.setOnClickListener(v -> {
            launch(VIP);
            Log.i("PremiumActivity", "Buy VIP button pressed");
        });


    }
    private void createSharedVIP() {
        SharedPreferences preferences = getSharedPreferences("MyShared", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("VIPStatus", 1);
        editor.commit();
    }

    @Override
    public void onSkuListResponse(HashMap<String, SkuDetails> skuDetails) {
        skuDetailsHashMap = skuDetails;
    }

    @Override
    public void onPurchasehistoryResponse(List<Purchase> purchasedItems) {
        if (purchasedItems != null) {
            for (Purchase purchase : purchasedItems) {
                //Update UI and backend according to purchased items if required
                // Like in this project I am updating UI for purchased items
                String sku = purchase.getSku();
                switch (sku) {
                    case VIP:
                        createSharedVIP();
                        Intent intent = new Intent(PremiumActivity.this, FirstMenu.class);
                        startActivity(intent);
                        Log.i("PremiumActivity", "You are now a premium user and can enjoy the ad-free app, and lots of free themes!");
                        break;
                }
            }
        }
    }

    private void launch(String sku){
        if(!skuDetailsHashMap.isEmpty())
            iapHelper.launchBillingFLow(skuDetailsHashMap.get(sku));
    }

    @Override
    public void onPurchaseCompleted(Purchase purchase) {
        if(Security.verifyPurchase(base64EncodedPublicKey, purchase.getOriginalJson(), purchase.getSignature())){
            //Purchase is completed, you can update your local database and UI here
            Toast.makeText(getApplicationContext(), "Purchase Successful", Toast.LENGTH_SHORT).show();
            updatePurchase(purchase);
        }
        else{
            Toast.makeText(getApplicationContext(), "Purchase verification failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePurchase(Purchase purchase){
        String sku = purchase.getSku();
        switch (sku) {
            case VIP:
                createSharedVIP();
                Intent intent = new Intent(PremiumActivity.this, FirstMenu.class);
                startActivity(intent);
                Log.i("PremiumActivity", "You are now a premium user and can enjoy the ad-free app, and lots of free themes!");
                break;
        }
    }
}

