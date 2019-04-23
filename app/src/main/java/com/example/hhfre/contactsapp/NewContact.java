package com.example.hhfre.contactsapp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class NewContact extends AppCompatActivity {

    Button btn_personal, btn_business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        btn_personal = findViewById(R.id.btn_personal);
        btn_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextPage = new Intent(v.getContext(), AddPersonalContact.class);
                startActivity(goToNextPage);
            }
        });

        btn_business = findViewById(R.id.btn_business);
        btn_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextPage = new Intent(v.getContext(), AddBusinessContact.class);
                startActivity(goToNextPage);
            }
        });

    }
}
