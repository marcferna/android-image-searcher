package com.codepath.marcferna.imagesearcher;

import com.codepath.marcferna.imagesearcher.model.GoogleImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by marc on 9/21/14.
 */
public class GoogleImageClient {

  public static final String url = "https://ajax.googleapis.com/ajax/services/search/images";

  private String searchValue;

  public GoogleImageClient(String search) {
    searchValue = search;
  }

  public void search(final Object object, final Method method){
    // make the API call

    JSONObject response = mockResponse();
    ArrayList<GoogleImage> images = parseResponse(response);
    try {
      method.invoke(object, images);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private ArrayList<GoogleImage> parseResponse(JSONObject response) {
    ArrayList<GoogleImage> results = new ArrayList();
    try {
      JSONArray imagesJSONArray = response.getJSONObject("responseData").getJSONArray("results");
      for (int i = 0; i < imagesJSONArray.length(); i++) {
        try {
          JSONObject imageJSON = imagesJSONArray.getJSONObject(i);
          GoogleImage photo = new GoogleImage();

          photo.width = imageJSON.getInt("width");
          photo.height = imageJSON.getInt("height");
          photo.googleId = imageJSON.getString("imageId");
          photo.url = imageJSON.getString("url");
          photo.title = imageJSON.getString("title");
          results.add(photo);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return results;
  }

  private JSONObject mockResponse() {
    JSONObject spoofedResponse = new JSONObject();
    try {
      spoofedResponse = new JSONObject("{\"responseData\": {\"results\": [{\"GsearchResultClass\": \"GimageSearch\",\"width\": \"450\",\"height\": \"450\",\"imageId\": \"Yt3TRC1vxzhazM\",\"tbWidth\": \"127\",\"tbHeight\": \"127\",\"unescapedUrl\": \"http://www.touchnote.com/files/assets/STAN009.jpg\",\"url\": \"http://www.touchnote.com/files/assets/STAN009.jpg\",\"visibleUrl\": \"www.touchnote.com\",\"title\": \"Touchnote - Personalised \u003cb\u003eFuzzy Monkey\u003c/b\u003e greeting cards design by Dan \u003cb\u003e...\u003c/b\u003e\",\"titleNoFormatting\": \"Touchnote - Personalised Fuzzy Monkey greeting cards design by Dan ...\",\"originalContextUrl\": \"http://www.touchnote.com/photo/card-design/Fuzzy+Monkey\",\"content\": \"Card Design \u003cb\u003eFuzzy Monkey\u003c/b\u003e\",\"contentNoFormatting\": \"Card Design Fuzzy Monkey\",\"tbUrl\": \"http://images.google.com/images?q\u003dtbn:Yt3TRC1vxzhazM:www.touchnote.com/files/assets/STAN009.jpg\"}]}, \"responseDetails\": null, \"responseStatus\": 200}");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return spoofedResponse;
  }

}



