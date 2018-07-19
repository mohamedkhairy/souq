package khairy.com.souqtask.register;

import java.io.Serializable;
import java.util.List;

import khairy.com.souqtask.data.main.City;
import khairy.com.souqtask.data.main.Country;

public class ListedDate implements Serializable{

    private List<Country> Country_List;
    private List<City> City_List;

    public ListedDate(List<Country> country_List, List<City> city_List) {
        this.Country_List = country_List;
        this.City_List = city_List;
    }

    public List<Country> getCountry_List() {
        return Country_List;
    }

    public List<City> getCity_List() {
        return City_List;
    }
}
