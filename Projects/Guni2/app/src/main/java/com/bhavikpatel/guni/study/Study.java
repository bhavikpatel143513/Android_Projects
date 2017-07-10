package com.bhavikpatel.guni.study;

import java.util.ArrayList;

/**
 * Created by BHAVIK PATEL on 15-Jun-17.
 */

public class Study {

    public static class Material{
        public static class PDF{
            private String sub,title,author,address,type;

            public PDF(String sub, String title, String author,String address,String type) {
                this.sub = sub;
                this.title = title;
                this.author = author;
                this.address = address;
                this.type = type;
            }

            public PDF() {
            }

            public String getSub() {
                return sub;
            }

            public void setSub(String sub) {
                this.sub = sub;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
        public static class Video{
            private String sub,title,address,type;

            public Video(String sub, String title, String address, String type) {
                this.sub = sub;
                this.title = title;
                this.address = address;
                this.type = type;
            }

            public Video() {
            }

            public String getSub() {
                return sub;
            }

            public void setSub(String sub) {
                this.sub = sub;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
        public static class Photo{
            private String sub,title,type,address;

            public Photo(String sub, String title, String type, String address) {
                this.sub = sub;
                this.title = title;
                this.type = type;
                this.address = address;
            }

            public Photo() {
            }

            public String getSub() {
                return sub;
            }

            public void setSub(String sub) {
                this.sub = sub;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public static class Result{
        private String date,examName,sub;
        private float obtainedMarks,totalMarks;

        public Result(String date, String examName, String sub, float obtainedMarks, float totalMarks) {
            this.date = date;
            this.examName = examName;
            this.sub = sub;
            this.obtainedMarks = obtainedMarks;
            this.totalMarks = totalMarks;
        }

        public Result() {
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getExamName() {
            return examName;
        }

        public void setExamName(String examName) {
            this.examName = examName;
        }

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }

        public float getObtainedMarks() {
            return obtainedMarks;
        }

        public void setObtainedMarks(float obtainedMarks) {
            this.obtainedMarks = obtainedMarks;
        }

        public float getTotalMarks() {
            return totalMarks;
        }

        public void setTotalMarks(float totalMarks) {
            this.totalMarks = totalMarks;
        }
    }

    public static class Attendence{
        private String sub,date;
        private int noOfClass,present;

        public Attendence(String sub, String date, int noOfClass, int present) {
            this.sub = sub;
            this.date = date;
            this.noOfClass = noOfClass;
            this.present = present;
        }

        public Attendence() {
        }

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getNoOfClass() {
            return noOfClass;
        }

        public void setNoOfClass(int noOfClass) {
            this.noOfClass = noOfClass;
        }

        public int getPresent() {
            return present;
        }

        public void setPresent(int present) {
            this.present = present;
        }

        public static float getPercent(ArrayList<Attendence> list){
            int present,total;
            present = total = 0;
            for(Attendence a : list){
                present += a.getPresent();
                total += a.getNoOfClass();
            }
            return (float)present/total;
        }
    }

    public static class AttendenceGroup{
        String title,att;

        public AttendenceGroup(String title, String att) {
            this.title = title;
            this.att = att;
        }

        public AttendenceGroup() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAtt() {
            return att;
        }

        public void setAtt(String att) {
            this.att = att;
        }
    }

    public static class Syllabus{
        private String sub,title;

        public Syllabus(String sub, String title) {
            this.sub = sub;
            this.title = title;
        }

        public Syllabus() {
        }

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Subject{
        private String title,type;

        public Subject(String title, String type) {
            this.title = title;
            this.type = type;
        }

        public Subject() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
