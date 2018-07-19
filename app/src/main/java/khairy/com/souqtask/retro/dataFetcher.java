package khairy.com.souqtask.retro;


import java.util.List;


import khairy.com.souqtask.data.main.Category;
import khairy.com.souqtask.data.main.City;
import khairy.com.souqtask.data.main.Country;
import retrofit2.Call;
import retrofit2.http.GET;


public interface dataFetcher {

        @GET("GetCategories?categoryId=0&countryId=1" )
        Call<List<Category>> getCategories();

        @GET("GetCountries" )
        Call<List<Country>> getCountry();

        @GET("GetCities?countryId=1" )
        Call<List<City>> getCity();


}
