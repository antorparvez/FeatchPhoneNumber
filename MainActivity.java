package com.antorparvez.featchphonenumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> phoneNumberList = new ArrayList<>();
    List<String> myContactList = new ArrayList<>();
    List<String> matchContact= new ArrayList<>();


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference().child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchMyContacts();
        loadPhoneNumberList();
    }


    private void loadPhoneNumberList() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap :snapshot.getChildren()){
                    Call_User_Model model = snap.getValue(Call_User_Model.class);
                    String phoneNumber = model.getPhone();
                    phoneNumberList.add(phoneNumber);
                }

                List<String> commonPhone = new ArrayList<>(phoneNumberList);
                commonPhone.retainAll(myContactList);
                matchContact = commonPhone;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





    private void fetchMyContacts() {
        myContactList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null && !cursor.isClosed()) {
            cursor.getCount();
            while (cursor.moveToNext()) {
                int hasPhoneNumber = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (hasPhoneNumber == 1) {
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll("\\s+", "");
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                    if (Patterns.PHONE.matcher(number).matches()) {
                        boolean hasPlus = String.valueOf(number.charAt(0)).equals("+");
                        number = number.replaceAll("[\\D]", "");
                        if (hasPlus) {
                            number = "+" + number;
                        }
                        Contact contact = new Contact(number, name);
                        if (!myContactList.contains(contact))
                            myContactList.add(contact.getPhoneNumber());
                    }
                }
            }
            cursor.close();
        }
    }
}