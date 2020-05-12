package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchDistrictAll extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner distSpinner;
    Spinner divSpinner;
    Button searchbtn;
    String sp1;
    String sp2;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_search_district_all);
        this.divSpinner = (Spinner) findViewById(R.id.spinner5);
        this.distSpinner = (Spinner) findViewById(R.id.spinner6);
        this.distSpinner.setPrompt("জেলা নির্বাচন করুন");
        this.divSpinner.setOnItemSelectedListener(this);
        this.distSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SearchDistrictAll.this.sp2 = String.valueOf(SearchDistrictAll.this.distSpinner.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.searchbtn = (Button) findViewById(R.id.serbtnn);
        this.searchbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SearchDistrictAll.this, DoctorSearchAll.class);
                intent.putExtra("District", SearchDistrictAll.this.sp2);
                SearchDistrictAll.this.startActivity(intent);
            }
        });
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.sp1 = String.valueOf(this.divSpinner.getSelectedItem());
        if (this.sp1.contentEquals("বিভাগ নির্বাচন করুন")) {
            List<String> list = new ArrayList<>();
            list.add("জেলা নির্বাচন করুন");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter);
        }
        if (this.sp1.contentEquals("খুলনা")) {
            List<String> list2 = new ArrayList<>();
            list2.add("জেলা নির্বাচন করুন");
            list2.add("বাগেরহাট");
            list2.add("চুয়াডাঙ্গা");
            list2.add("যশোর");
            list2.add("ঝিনাইদহ");
            list2.add("খুলনা");
            list2.add("কুষ্টিয়া");
            list2.add("মাগুরা");
            list2.add("মেহেরপুর");
            list2.add("নড়াইল");
            list2.add("সাতক্ষিরা");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list2);
            dataAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter2);
            Toast.makeText(this, String.valueOf(this.distSpinner.getSelectedItem()), 0).show();
        }
        if (this.sp1.contentEquals("চট্টগ্রাম")) {
            List<String> list3 = new ArrayList<>();
            list3.add("জেলা নির্বাচন করুন");
            list3.add("বান্দরবান");
            list3.add("ব্রাহ্মণবাড়িয়া");
            list3.add("চাঁদপুর");
            list3.add("চট্টগ্রাম");
            list3.add("কুমিল্লা");
            list3.add("কক্সবাজার");
            list3.add("ফেনী");
            list3.add("খাগড়াছড়ি");
            list3.add("লক্ষ্মীপুর");
            list3.add("নোয়াখালী");
            list3.add("রাঙ্গামাটি");
            ArrayAdapter<String> dataAdapter22 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list3);
            dataAdapter22.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter22.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter22);
        }
        if (this.sp1.contentEquals("ঢাকা")) {
            List<String> list4 = new ArrayList<>();
            list4.add("জেলা নির্বাচন করুন");
            list4.add("ঢাকা");
            list4.add("ফরিদপুর");
            list4.add("গাজীপুর");
            list4.add("গোপালগঞ্জ");
            list4.add("কিশোরগঞ্জ");
            list4.add("মাদারীপুর");
            list4.add("মানিকগঞ্জ");
            list4.add("মুন্সীগঞ্জ");
            list4.add("নারায়ণগঞ্জ");
            list4.add("নরসিংদী");
            list4.add("রাজবাড়ী");
            list4.add("শরীয়তপুর");
            list4.add("টাঙ্গাইল");
            ArrayAdapter<String> dataAdapter23 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list4);
            dataAdapter23.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter23.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter23);
        }
        if (this.sp1.contentEquals("বরিশাল")) {
            List<String> list5 = new ArrayList<>();
            list5.add("জেলা নির্বাচন করুন");
            list5.add("বরগুনা");
            list5.add("বরিশাল");
            list5.add("ভোলা");
            list5.add("ঝালকাঠি");
            list5.add("পটুয়াখালী");
            list5.add("পিরোজপুর");
            ArrayAdapter<String> dataAdapter24 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list5);
            dataAdapter24.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter24.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter24);
        }
        if (this.sp1.contentEquals("রংপুর")) {
            List<String> list6 = new ArrayList<>();
            list6.add("জেলা নির্বাচন করুন");
            list6.add("দিনাজপুর");
            list6.add("গাইবান্ধা");
            list6.add("কুড়িগ্রাম");
            list6.add("লালমনিরহাট");
            list6.add("নীলফামারী");
            list6.add("পঞ্চগড়");
            list6.add("রংপুর");
            list6.add("ঠাকুরগাঁও");
            ArrayAdapter<String> dataAdapter25 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list6);
            dataAdapter25.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter25.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter25);
        }
        if (this.sp1.contentEquals("রাজশাহী")) {
            List<String> list7 = new ArrayList<>();
            list7.add("জেলা নির্বাচন করুন");
            list7.add("বগুড়া");
            list7.add("জয়পুরহাট");
            list7.add("নওগাঁ");
            list7.add("নাটোর");
            list7.add("চাঁপাই_নবাবগঞ্জ");
            list7.add("পাবনা");
            list7.add("রাজশাহী");
            list7.add("সিরাজগঞ্জ");
            ArrayAdapter<String> dataAdapter26 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list7);
            dataAdapter26.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter26.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter26);
        }
        if (this.sp1.contentEquals("সিলেট")) {
            List<String> list8 = new ArrayList<>();
            list8.add("জেলা নির্বাচন করুন");
            list8.add("হবিগঞ্জ");
            list8.add("মৌলভীবাজার");
            list8.add("সুনামগঞ্জ");
            list8.add("সিলেট");
            ArrayAdapter<String> dataAdapter27 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list8);
            dataAdapter27.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter27.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter27);
        }
        if (this.sp1.contentEquals("ময়মনসিংহ")) {
            List<String> list9 = new ArrayList<>();
            list9.add("জেলা নির্বাচন করুন");
            list9.add("জামালপুর");
            list9.add("ময়মনসিংহ");
            list9.add("নেত্রকোনা");
            list9.add("শেরপুর");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list9);
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            arrayAdapter.notifyDataSetChanged();
            this.distSpinner.setAdapter(arrayAdapter);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
