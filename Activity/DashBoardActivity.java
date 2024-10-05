package com.rich.stylespace.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.rich.stylespace.Adapter.PopularAdapter;
import com.rich.stylespace.Domain.PopularDomain;
import com.rich.stylespace.R;
import com.rich.stylespace.databinding.ActivityDashBoardBinding;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    ActivityDashBoardBinding binding;
    TextView nameUser;
    boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameUser = findViewById(R.id.nameUser);

        // Get the user name from the intent
        String userName = getIntent().getStringExtra("USER_NAME");

        // Set the user name to the TextView
        if (userName != null && !userName.isEmpty()) {
            nameUser.setText(userName);
            isLoggedIn = true; // Update login status
        } else {
            nameUser.setText("User");
        }

        // Set the click listener for the TextView only if the user is not logged in
        if (!isLoggedIn) {
            nameUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        statusBarColor();
        initRecyclerView();
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoardActivity.this, CartActivity.class));
            }
        });
    }

    private void statusBarColor() {
        Window window = DashBoardActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DashBoardActivity.this,R.color.dark_blue));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Ergonomic Chair", "item1_display",15,4,70,"Experience ultimate comfort and support with our Ergonomic Chair,\n" +
                "designed to enhance your productivity and well-being. Modern design complements any workspace. Perfect for long hours of work or study,\n " +
                "our Ergonomic Chair is the ideal addition to your home.\n"));
        items.add(new PopularDomain("Modern Sofa", "item2_display",20,4.2,120,"Transform your living space with our Modern Sectional Sofa,\n " +
                "a perfect blend of style and comfort. This versatile sofa boasts plush cushions and a sturdy frame,\n " +
                "offering ample seating for family gatherings or entertaining guests. The contemporary design,\n " +
                "featuring clean lines and premium fabric, fits seamlessly into any decor.\n " +
                "The modular sections can be arranged to suit your room layout, providing both flexibility and functionality.\n " +
                "Relax and unwind on our Modern Sectional Sofa, the ultimate centerpiece for your home.\n"));
        items.add(new PopularDomain("Lush Fiddle Leaf", "item3_display",50,4.8,20,"Bring a touch of nature indoors with our Lush Fiddle Leaf Fig,\n " +
                "a stunning house plant known for its large, glossy leaves and elegant appearance.\n " +
                "This low-maintenance plant thrives in bright, indirect light and requires minimal watering,\n " +
                "making it perfect for busy individuals or those new to plant care.\n"));
        items.add(new PopularDomain("Luxury King Size Bed", "item4_display",5,4.5,750,"Indulge in the ultimate sleep experience with our Luxury King Size Bed,\n " +
                "designed for superior comfort and support. This bed features a high-quality mattress\n " +
                "with advanced memory foam technology that conforms to your body, relieving pressure points\n" +
                "and ensuring a restful night’s sleep. The sturdy frame and elegant upholstered headboard\n " +
                "add a touch of sophistication to your bedroom décor. With its spacious dimensions,\n " +
                "our Luxury King Size Bed provides ample room for relaxation, making it the perfect retreat after a long day.\n " +
                "Upgrade your sleep quality and style with this exquisite bed.\n"));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new PopularAdapter(items));
    }
}