package com.assurity.tech;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Acceptance tests for URL
 */
public class AcceptanceTest {
    String url ="https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false";
    String inputJson=null;

    @Before
    public void setUP(){
        try {
            inputJson = URLReader.read (url);
            ArgumentValidator.checkArgumentNullOrEmpty (inputJson);
        } catch (Exception e) {
            e.printStackTrace ( );
        }
    }

    /**
     * Test Acceptance Criteria:
     * Name = "Carbon credits"
     * CanRelist = true
     * The Promotions element with Name = "Gallery" has a Description that contains the text "2x larger image"
     */
    @Test
    public void APITest2() {
        try {
            JSONObject myResponse = new JSONObject (inputJson);
            System.out.println ("Validating JSON Response");
            //Validate if Name is correct.
            Assert.assertEquals (myResponse.getString ("Name").toString ( ), "Carbon credits");
            //Validate if CanRelist attribute is true.
            Assert.assertEquals (myResponse.get ("CanRelist"), true);
            //Loop through all the items in the promotions array and validate if the criteria is satisfied.
            JSONArray promotions = myResponse.getJSONArray ("Promotions");
            ArgumentValidator.checkArgumentNullOrEmpty (promotions);
            boolean criteria=false;
            for (int i = 0; i < promotions.length ( ); i++) {
                JSONObject obj = promotions.getJSONObject (i);
                if (obj.getString ("Name").equals ("Gallery")){
                    if(obj.getString ("Description").contains ("2x larger image")){
                        System.out.println ("API acceptance criteria succeeded" );
                        criteria=true;
                    }
                }
            }
            if (!criteria){
                Assert.fail ("API acceptance criteria failed");
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }

    @Test
    public void APITest1(){
        Assert.assertEquals (isJSONValid (inputJson),true);
    }

    private boolean isJSONValid (String test) {
    try {
            JSONObject json = new JSONObject (test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                JSONArray json1 = new JSONArray (test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}
