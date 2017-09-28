package com.example.root.dogfinder.services;

        import com.example.root.dogfinder.Constants;
        import com.example.root.dogfinder.models.Dog;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.ArrayList;

        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.HttpUrl;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.Response;

public class YelpService {

    public static void findDogs(String location, Callback callback) {
//        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.YELP_CONSUMER_KEY, Constants.YELP_CONSUMER_SECRET);
//        consumer.setTokenWithSecret(Constants.YELP_TOKEN, Constants.YELP_TOKEN_SECRET);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new SigningInterceptor(consumer))
//                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .header("Authorization", Constants.YELP_TOKEN)
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<Dog> processResults(Response response) {
        ArrayList<Dog> dogs = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject dogJSON = businessesJSON.getJSONObject(i);
                    String name = dogJSON.getString("name");
                    String phone = dogJSON.optString("display_phone", "Phone not available");
                    String website = dogJSON.getString("url");
                    double rating = dogJSON.getDouble("rating");
                    String imageUrl = dogJSON.getString("image_url");
                    double latitude = dogJSON
                            .getJSONObject("coordinates").getDouble("latitude");
                    double longitude = dogJSON
                            .getJSONObject("coordinates").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = dogJSON.getJSONObject("location")
                            .getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }

                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = dogJSON.getJSONArray("categories");

                    for (int y = 0; y < categoriesJSON.length(); y++) {
                        JSONObject jsonObject=categoriesJSON.getJSONObject(y);
                        String category=jsonObject.getString("alias");
                        categories.add(category);
                        //categories.add(categoriesJSON.getJSONArray(y).get(0).toString());
                    }
                    Dog dog = new Dog(name, phone, website, rating,
                            imageUrl, address, latitude, longitude, categories);
                    dogs.add(dog);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dogs;
    }
}
