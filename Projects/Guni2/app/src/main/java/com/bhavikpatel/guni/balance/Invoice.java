package com.bhavikpatel.guni.balance;

/**
 * Created by BHAVIK PATEL on 14-Jun-17.
 */

public class Invoice {

    public static class Wallet {

    /*//final
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "title";
    private static final String DATE = "date";
    private static final String AMOUNT = "amount";*/


        private String title;
        private String description;
        private String date;
        private int amount,bookmark,type;

        public Wallet(String title, String description, String date, int amount, int bookmark,int type) {
            this.title = title;
            this.description = description;
            this.date = date;
            this.amount = amount;
            this.bookmark = bookmark;
            this.type = type;
        }

        public Wallet() {
        }

    /*public Wallet(JSONObject jo){
        try {
            this.amount = (int) jo.get(AMOUNT);
            this.date = (String) jo.get(DATE);
            this.description = (String) jo.get(DESCRIPTION);
            this.title = (String) jo.get(TITLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getBookmark() {
            return bookmark;
        }

        public void setBookmark(int bookmark) {
            this.bookmark = bookmark;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        /*public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(TITLE,this.title);
            jo.put(DESCRIPTION,this.description);
            jo.put(DATE,this.date);
            jo.put(AMOUNT,this.amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }*/
    }
    public static class Balance {

    /*//final
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "title";
    private static final String DATE = "date";
    private static final String AMOUNT = "amount";*/

        private String description;
        private String date;
        private int amount, bookmark;

        public Balance(String description, String date, int amount, int bookmark) {
            this.description = description;
            this.date = date;
            this.amount = amount;
            this.bookmark = bookmark;
        }

        public Balance() {
        }

    /*public Wallet(JSONObject jo){
        try {
            this.amount = (int) jo.get(AMOUNT);
            this.date = (String) jo.get(DATE);
            this.description = (String) jo.get(DESCRIPTION);
            this.title = (String) jo.get(TITLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getBookmark() {
            return bookmark;
        }

        public void setBookmark(int bookmark) {
            this.bookmark = bookmark;
        }
    }



    public static class Data {

    /*//final
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "title";
    private static final String DATE = "date";
    private static final String AMOUNT = "amount";*/

        private String description;
        private String date;
        private int amount,bookmark;

        public Data( String description, String date, int amount,int bookmark) {
            this.description = description;
            this.date = date;
            this.amount = amount;
            this.bookmark = bookmark;
        }

        public Data() {
        }

    /*public Wallet(JSONObject jo){
        try {
            this.amount = (int) jo.get(AMOUNT);
            this.date = (String) jo.get(DATE);
            this.description = (String) jo.get(DESCRIPTION);
            this.title = (String) jo.get(TITLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getBookmark() {
            return bookmark;
        }

        public void setBookmark(int bookmark) {
            this.bookmark = bookmark;
        }

        /*public JSONObject convertToJSONObject(){
        JSONObject jo = new JSONObject();
        try {
            jo.put(TITLE,this.title);
            jo.put(DESCRIPTION,this.description);
            jo.put(DATE,this.date);
            jo.put(AMOUNT,this.amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }*/
    }
}
