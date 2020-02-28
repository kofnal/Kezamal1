package xyz.lavaliva.kezamal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class A2RegistraciaPolzovatelia extends AppCompatActivity {
    private static final String TAG = "A2";
    Button btRegistraciaUsera;
    EditText etFamilia, etImia, etOtchestvo, etRost, etObuv, etOdejda;

public static User user =new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2_registracia_polzovatelia);

        etFamilia=findViewById(R.id.etFamilia);
        etImia=findViewById(R.id.etImia);
        etOtchestvo=findViewById(R.id.etOtchestvo);
        etRost=findViewById(R.id.etRost);
        etObuv=findViewById(R.id.etObuv);
        etOdejda=findViewById(R.id.etOdejda);

        btRegistraciaUsera=findViewById(R.id.btRegistraciaUsera);
        btRegistraciaUsera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFamilia(etFamilia.getText().toString());
                user.setImia(etImia.getText().toString());
                user.setOtchestvo(etOtchestvo.getText().toString());
                user.setRost(etRost.getText().toString());
                user.setObuv(etObuv.getText().toString());
                user.setOdejda(etOdejda.getText().toString());
                System.out.println(etFamilia.getText().toString());


                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String mUserID="";
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                } else {
                    //login or register screen
                }
                mDatabase.child("users").child(mUserID).setValue(user);
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get Post object and use the values to update the UI
                        User post = dataSnapshot.getValue(User.class);
                        System.out.println("Saved user");

                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

                        System.out.println("NOT Saved user");
                        // ...
                    }
                };
                Intent intent = new Intent(A2RegistraciaPolzovatelia.this, A3PodverjdenieRegistracii.class);
                startActivity(intent);
                finish();
                mDatabase.addValueEventListener(postListener);
            }
        });

        spinerIsAdmin();
        spinerDoljnost();

}

    private void spinerIsAdmin() {
        String sp1polzovatel=getResources().getString(R.string.polzovatel);
        String sp1admin=getResources().getString(R.string.admin);
        String[] data = {sp1polzovatel, sp1admin};
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spAdmin);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        //spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                if(position==1) user.admin=true;

                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    private void spinerDoljnost() {
        String[] data =Doljnosti.getStringArr(this);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spDoljnost);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(3);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
String [] xArr= Doljnosti.getStringArr(A2RegistraciaPolzovatelia.this);
user.setUserDoljnost(xArr[position]);
System.out.println(user.getUserDoljnost()+" ImiaDoljnosti");
                Toast.makeText(getBaseContext(), " Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
