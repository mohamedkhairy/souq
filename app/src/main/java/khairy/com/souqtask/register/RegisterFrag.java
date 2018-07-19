package khairy.com.souqtask.register;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import khairy.com.souqtask.R;
import khairy.com.souqtask.fragment.MainView;

import static khairy.com.souqtask.register.Prefrance.PREFS_NAME;

public class RegisterFrag extends Fragment implements AdapterView.OnItemSelectedListener {


    public static final String city_bundle = "cityData";
    private List<String> citydata  , codeData , countryData , arCodeData, arCountryData , arCityData;
    ListedDate listedDate ;



    @BindView(R.id.codeSpinner)
    Spinner spinnerCode;
    @BindView(R.id.country_spinner)
    Spinner spinnerCountry;
    @BindView(R.id.city_spinner)
    Spinner spinnerCity;
    @BindView(R.id.mainlinear)
    LinearLayout linearLayout;
    @BindView(R.id.relativebtn)
    RelativeLayout relativebtn;
    @BindView(R.id.fullname)
    EditText fullname;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.codeplace)
    EditText coeplace;
    @BindView(R.id.email)
    EditText email;




    public static RegisterFrag registerFrag(ListedDate list) {
        RegisterFrag register = new RegisterFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(city_bundle, list);
        register.setArguments(bundle);
        return register;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_view, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this , view);

        if (savedInstanceState!=null) {
            restoreView(savedInstanceState);
        }
        getSpinerData(Locale.getDefault().getLanguage());

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name" , fullname.getText().toString());
        outState.putString("password" , password.getText().toString());
        outState.putString("codeplace" , coeplace.getText().toString());
        outState.putString("email" , email.getText().toString());
        outState.putInt("codespinner" , spinnerCode.getSelectedItemPosition());
        outState.putInt("cityspinner" , spinnerCity.getSelectedItemPosition());
        outState.putInt("countryspinner" , spinnerCountry.getSelectedItemPosition());
        }



    private void restoreView(Bundle bundle){

     fullname.setText(bundle.getString("name"));
     password.setText(bundle.getString("password" ));
     coeplace.setText(bundle.getString("codeplace"));
     email.setText(bundle.getString("email"));
     spinnerCode.setSelection(bundle.getInt("codespinner" , 0));
     spinnerCountry.setSelection(bundle.getInt("countryspinner" , 0));
     spinnerCity.setSelection(bundle.getInt("cityspinner" , 0));
    }

    @OnClick(R.id.relativebtn)
    public void onButtonClick() {
        getActivity().recreate();
    }

    @OnClick(R.id.term)
    public void onTermsClick() {

        AlertDialog ad = new AlertDialog.Builder(getContext()).create();
        ad.setCancelable(true);
        ad.setTitle(getContext().getString(R.string.terms_title));
        ad.setMessage(getContext().getString(R.string.terms_cond));
        ad.show();
    }

    @OnClick(R.id.register)
    public void onRegisterClick() {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mainView = new MainView();
        fragmentTransaction.replace(R.id.framContainer, mainView, MainView.class.getSimpleName());
        fragmentTransaction.commit();
    }


    public void getSpinerData(String lang){

        listedDate = (ListedDate) getArguments().getSerializable(city_bundle);
        citydata = new ArrayList<String>(){{add("City");}};
        codeData = new ArrayList<String>(){{add("Code");}};
        countryData = new ArrayList<String>(){{add("Country");}};
        arCityData = new ArrayList<String>(){{add("المدينة");}};
        arCodeData = new ArrayList<String>(){{add("الرمز");}};
        arCountryData = new ArrayList<String>(){{add("الدولة");}};
        for (int i  = 0 ; i < listedDate.getCity_List().size() ; i++) {
            citydata.add(listedDate.getCity_List().get(i).getTitleEN());
            arCityData.add(listedDate.getCity_List().get(i).getTitleAR());
        }
        for (int i  = 0 ; i < listedDate.getCountry_List().size() ; i++) {
            countryData.add(listedDate.getCountry_List().get(i).getCurrencyEN());
            codeData.add(listedDate.getCountry_List().get(i).getCodeEN());
            arCountryData.add(listedDate.getCountry_List().get(i).getCurrencyAR());
            arCodeData.add(listedDate.getCountry_List().get(i).getCodeAR());
        }
        ResetSpinner(lang);
    }


    private void ResetSpinner(String lang){

        if (lang.equals("ar")){
            spinner(arCityData , spinnerCity);
            spinner(arCodeData , spinnerCode);
            spinner(arCountryData , spinnerCountry);
        }else {
            spinner(citydata , spinnerCity);
            spinner(codeData , spinnerCode);
            spinner(countryData , spinnerCountry);

        }
    }



    private void spinner(List<String> list , Spinner spinner){
        spinner.setOnItemSelectedListener(this);

    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();

        if (item.equals(codeData.get(i)) || item.equals(arCodeData.get(i))) {

            if(i!=0) {
                String code = listedDate.getCountry_List().get(i).getCode();
                coeplace.setText(code);
            }else {
                coeplace.setText("");
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
