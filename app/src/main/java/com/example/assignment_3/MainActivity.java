package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView dayDrop, monthDrop, yearDrop;
    private EditText name,email,mobile,address,address_city,address_State_Province,zip_code;
    private TextView out_name, out_email,out_mobile,out_address,out_address_city,out_address_state_province,out_zip,date_of_birth,out_q_1,out_q_2,out_q_3;
    private CheckBox q1_1, q_1_12, q_1_13, q_1_14,q_1_15;
    private RadioGroup RG_1, RG_2;
    private LinearLayout in_layout,ou_layout;
    private Button submit_btn,clear_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.home));

        dayDrop = findViewById(R.id.day);
        monthDrop = findViewById(R.id.month);
        yearDrop = findViewById(R.id.year);

        name = findViewById(R.id.s_name);
        email = findViewById(R.id.s_email);
        mobile = findViewById(R.id.s_mobile);
        address = findViewById(R.id.s_address);
        address_city = findViewById(R.id.s_address_city);
        address_State_Province = findViewById(R.id.s_address_State_Province);
        zip_code = findViewById(R.id.s_zip_code);

        out_name = findViewById(R.id.output_s_name);
        out_email = findViewById(R.id.output_s_email);
        out_mobile = findViewById(R.id.output_s_mobile);
        out_address = findViewById(R.id.output_s_street_address);
        out_address_city = findViewById(R.id.output_s_city);
        out_address_state_province = findViewById(R.id.output_s_state);
        out_zip = findViewById(R.id.output_s_zip_code);
        date_of_birth = findViewById(R.id.output_s_dob);

        out_q_1 = findViewById(R.id.out_q_1);
        out_q_2 = findViewById(R.id.out_q_2);
        out_q_3 = findViewById(R.id.out_q_3);


        // Initialize the RadioGroups
        RG_1 = findViewById(R.id.membership_type);
        RG_2 = findViewById(R.id.preferred_way_contact);

        // Initialize the CheckBoxes
        q1_1 = findViewById(R.id.friend);
        q_1_12 = findViewById(R.id.google);
        q_1_13 = findViewById(R.id.blog_post);
        q_1_14 = findViewById(R.id.n_articles);
        q_1_15 = findViewById(R.id.others);


        in_layout = findViewById(R.id.input_layout);
        ou_layout = findViewById(R.id.output_layout);

        submit_btn = findViewById(R.id.submit);
        clear_btn = findViewById(R.id.clear);


        // Array of days from 1 to 31
        String[] days = new String[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = String.valueOf(i + 1);
        }

        // Array of Months
        String[] months = new String[] {
                "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"
        };


        // Array of years from 1990 to 2024
        String[] years = new String[35];
        int startYear = 1990;
        for (int i = 0; i < years.length; i++) {
            years[i] = Integer.toString(startYear + i);
        }


        // Set up dropdowns using the helper function
        setupDropdown(dayDrop, days);
        setupDropdown(monthDrop, months);
        setupDropdown(yearDrop, years);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_name = name.getText().toString();
                String input_email = email.getText().toString();
                String input_mobile = mobile.getText().toString();
                String input_address = address.getText().toString();
                String input_address_city = address_city.getText().toString();
                String input_address_State_Province = address_State_Province.getText().toString();
                String input_zip_code = zip_code.getText().toString();
                String input_day = dayDrop.getText().toString();
                String input_month = monthDrop.getText().toString();
                String input_year = yearDrop.getText().toString();


                // Validate inputs
                if(!validateUserName()){
                    return;
                }
                else if(!validateUseremail()){
                    return;
                }
                else if(!validateUserMobile()){
                    return;
                }
                else if (input_address.isEmpty()) {
                    address.setError("Empty!!");
                    address.requestFocus();
                }

                else if (input_address_city.isEmpty()) {
                    address_city.setError("Empty!!");
                    address_city.requestFocus();
                }
                else if (input_address_State_Province.isEmpty()) {
                    address_State_Province.setError("Empty!!");
                    address_State_Province.requestFocus();
                }
                else if (input_zip_code.isEmpty()) {
                    zip_code.setError("Empty!!");
                    zip_code.requestFocus();
                }
                else if (input_day.isEmpty()) {
                    dayDrop.setError("Empty!!");
                    dayDrop.requestFocus();
                }
                else if (input_month.isEmpty()) {
                    monthDrop.setError("Empty!!");
                    monthDrop.requestFocus();
                }
                else if (input_year.isEmpty()) {
                    yearDrop.setError("Empty!!");
                    yearDrop.requestFocus();
                }
                else {
                    // All validations passed, display the output layout and hide the input layout
                    in_layout.setVisibility(View.GONE);
                    ou_layout.setVisibility(View.VISIBLE);

                    // Set output values to the respective TextViews
                    out_name.setText(input_name);
                    out_email.setText(input_email);
                    out_mobile.setText(input_mobile);
                    out_address.setText(input_address);
                    out_address_city.setText(input_address_city);
                    out_address_state_province.setText(input_address_State_Province);
                    out_zip.setText(input_zip_code);
                    String s = input_day + " / " + input_month + " / " + input_year;
                    date_of_birth.setText(s);

                    // Get checked checkboxes and display them
                    StringBuilder checkBoxOutput = new StringBuilder("Heard about us from: ");
                    if (q1_1.isChecked()) checkBoxOutput.append("Friend, ");
                    if (q_1_12.isChecked()) checkBoxOutput.append("Google, ");
                    if (q_1_13.isChecked()) checkBoxOutput.append("Blog Post, ");
                    if (q_1_14.isChecked()) checkBoxOutput.append("News Articles, ");
                    if (q_1_15.isChecked()) checkBoxOutput.append("Others");

                    out_q_1.setText(checkBoxOutput.toString());

                    // Get selected radio buttons and display them
                    int selectedMembership = RG_1.getCheckedRadioButtonId();
                    RadioButton selectedMembershipBtn = findViewById(selectedMembership);
                    if (selectedMembershipBtn != null) {
                        out_q_2.setText("Membership: " + selectedMembershipBtn.getText().toString());
                    }

                    int selectedContact = RG_2.getCheckedRadioButtonId();
                    RadioButton selectedContactBtn = findViewById(selectedContact);
                    if (selectedContactBtn != null) {
                        out_q_3.setText("Preferred Contact: " + selectedContactBtn.getText().toString());
                    }

                    Toast.makeText(MainActivity.this, "Details Saved!", Toast.LENGTH_SHORT).show();

                    clear_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            out_name.setText("");
                            out_email.setText("");
                            out_mobile.setText("");
                            out_address.setText("");
                            out_address_city.setText("");
                            out_address_state_province.setText("");
                            out_zip.setText("");
                            date_of_birth.setText("");
                            out_q_1.setText("");
                            out_q_2.setText("");
                            out_q_3.setText("");

                            // Optionally, show the input layout again and hide the output layout
                            in_layout.setVisibility(View.VISIBLE);
                            ou_layout.setVisibility(View.GONE);

                            Toast.makeText(MainActivity.this, "Details Cleared!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    private void setupDropdown(AutoCompleteTextView dropdownView, String[] data) {
        ArrayAdapter<String > adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                data
        );
        dropdownView.setAdapter(adapter);
        dropdownView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, dropdownView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateUserName() {
        String user_name = name.getText().toString();

        if (user_name.isEmpty()) {
            name.requestFocus();
            name.setError("name cannot be empty");
            return false;
        } else if (!user_name.matches("^[a-zA-Z][a-zA-Z\\s]*$")) {
            name.requestFocus();
            name.setError("Name can only contain alphabets");
            return false;
        } else {
            name.setError(null);
            return true;
        }

    }

    public boolean validateUseremail () {
        String user_email = email.getText().toString();
        if (user_email.isEmpty()) {
            email.requestFocus();
            email.setError("Email cannot be empty");
            return false;
        }
        // Check if the email format is valid
        else if (!user_email.matches("^[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*@(gmail\\.com|outlook\\.com|yahoo\\.com)$") && !user_email.matches("^cse_[0-9]{16}@lus\\.ac\\.bd$")) {
            email.requestFocus();
            email.setError("Invalid email format");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validateUserMobile() {
        String user_mobile = mobile.getText().toString();

        if (user_mobile.isEmpty()) {
            mobile.requestFocus();
            mobile.setError("mobile cannot be empty");
            return false;
        }

        else if (!user_mobile.matches("^\\+880\\d{10}$")) {
            mobile.requestFocus();
            mobile.setError("number start with +880...");
            return false;
        }
        else {
            mobile.setError(null);
            return true;
        }
    }
}
