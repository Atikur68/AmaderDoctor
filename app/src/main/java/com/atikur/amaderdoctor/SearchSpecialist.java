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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchSpecialist extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner distSpinner;
    private Spinner divSpinner;
    private Button searchbtn;
    private String sp1;
    private String sp2;
    private String sp3;
    private Spinner specialSpinner;
    private List<String> special_list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_search_specialist);
        this.divSpinner = (Spinner) findViewById(R.id.spinner);
        this.distSpinner = (Spinner) findViewById(R.id.spinner2);
        this.specialSpinner = (Spinner) findViewById(R.id.spinner3);


        specialSearch();



        this.distSpinner.setPrompt("জেলা নির্বাচন করুন");
        this.divSpinner.setOnItemSelectedListener(this);

        this.distSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SearchSpecialist.this.sp2 = String.valueOf(SearchSpecialist.this.distSpinner.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.specialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SearchSpecialist.this.sp3 = String.valueOf(SearchSpecialist.this.specialSpinner.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.searchbtn = (Button) findViewById(R.id.serbtn);
        this.searchbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SearchSpecialist.this, DoctorsSearchResult.class);
                intent.putExtra("District", SearchSpecialist.this.sp2);
                intent.putExtra("Specialist", SearchSpecialist.this.sp3);
                SearchSpecialist.this.startActivity(intent);
            }
        });
    }

    private void specialSearch() {
        special_list.add("বিশেষজ্ঞ নির্বাচন করুন");
        special_list.add("চক্ষু-বিশেষজ্ঞ");
        special_list.add("বিশুরোগ-বিশেষজ্ঞ");
        special_list.add("মেডিসিন-বিশেষজ্ঞ");
        special_list.add("স্নায়ুরোগ-বিশেষজ্ঞ");
        special_list.add("হৃদরোগ-বিশেষজ্ঞ");
        special_list.add("মনোরোগ-বিশেষজ্ঞ");
        special_list.add("মেরুদণ্ড-বিশেষজ্ঞ");
        special_list.add("স্ত্রী-রোগ-বিশেষজ্ঞ");
        special_list.add("ডায়াবেটিস-বিশেষজ্ঞ");
        special_list.add("মুখ-দাঁত-রোগ-বিশেষজ্ঞ");
        special_list.add("ইউরোলজী-বিশেষজ্ঞ");
        special_list.add("পরিপাকতন্ত্র-বিশেষজ্ঞ");
        special_list.add("কিডনী-রোগ-বিশেষজ্ঞ");
        special_list.add("প্যারালাইসিস-বিশেষজ্ঞ");
        special_list.add("হাড়-জোড়া-রোগ-বিশেষজ্ঞ");
        special_list.add("নাক-কান-গলা-বিশেষজ্ঞ");
        special_list.add("চর্ম-এলার্জি-বিশেষজ্ঞ");
        special_list.add("যৌন-রোগ-বিশেষজ্ঞ");
        special_list.add("ল্যাপারোসকপিক-সার্জন");
        special_list.add("কনসালটেন্ট-সনোলজিষ্ট");
        special_list.add("রেডিওলজী-এন্ড-ইমেজিং-বিশেষজ্ঞ");
        special_list.add("জেনারেল-সার্জন");
        special_list.add("মাইক্রোবায়োলজী-বিশেষজ্ঞ");


        RequestQueue newRequestQueue = Volley.newRequestQueue(this);

        StringRequest r0 = new StringRequest(Request.Method.POST, "https://amaderdoctor.timitbd.com/specialistList.php", new Response.Listener<String>() {
            public void onResponse(String ServerResponse) {
                if (ServerResponse.contains("userall")) {
                    try {
                        JSONArray heroArray = new JSONObject(ServerResponse).getJSONArray("userall");

                        special_list.clear();
                        for (int i = 0; i < heroArray.length(); i++) {
                            JSONObject heroObject = heroArray.getJSONObject(i);

                            special_list.add(heroObject.getString("specialist"));

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                    Toast.makeText(SearchSpecialist.this, "সঠিক তথ্য দিন ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SearchSpecialist.this, "আপনার ইন্টারনেট সংযোগটি যাচাই করুন ", Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };
        Volley.newRequestQueue(this).add(r0);
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.sp1 = String.valueOf(this.divSpinner.getSelectedItem());
        this.sp3 = String.valueOf(this.specialSpinner.getSelectedItem());


        if (this.sp3.contentEquals("বিশেষজ্ঞ নির্বাচন করুন")) {



            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , special_list);
            dataAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            this.specialSpinner.setAdapter(dataAdapter2);
         //   Toast.makeText(this, String.valueOf(this.specialSpinner.getSelectedItem()), Toast.LENGTH_SHORT).show();

        }

        if (this.sp1.contentEquals("বিভাগ নির্বাচন করুন")) {
            List<String> list = new ArrayList<>();
            list.add("জেলা নির্বাচন করুন");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1 , list);
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
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , list2);
            dataAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            this.distSpinner.setAdapter(dataAdapter2);
          //  Toast.makeText(this, String.valueOf(this.distSpinner.getSelectedItem()), Toast.LENGTH_SHORT).show();
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
            ArrayAdapter<String> dataAdapter22 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , list3);
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
            ArrayAdapter<String> dataAdapter23 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , list4);
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
            ArrayAdapter<String> dataAdapter24 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , list5);
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
            ArrayAdapter<String> dataAdapter25 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , list6);
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
            ArrayAdapter<String> dataAdapter26 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , list7);
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
            ArrayAdapter<String> dataAdapter27 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , list8);
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
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , list9);
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            arrayAdapter.notifyDataSetChanged();
            this.distSpinner.setAdapter(arrayAdapter);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
