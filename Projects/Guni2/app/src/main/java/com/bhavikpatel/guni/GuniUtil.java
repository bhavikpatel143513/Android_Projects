package com.bhavikpatel.guni;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BHAVIK PATEL on 03-Jul-17.
 */

public class GuniUtil {
    /**
     * Created by BHAVIK PATEL on 01-Jul-17.
     */
    public static class GuniColor{

        public int COLOR_GUNI_THEME_BACKGROUND;
        public int COLOR_GUNI_THEME_BORDER;
        public int COLOR_GUNI_THEME_TEXT;
        public int COLOR_GUNI_THEME_TRANSPARENT;
        public int COLOR_GUNI_THEME_SEMI_TRANSPARENT;
        public int COLOR_GUNI_THEME_LINE;
        public int COLOR_GUNI_THEME_HINT_TEXT;

        public int COLOR_GUNI_THEME_BUTTON_BACKGROUND;
        public int COLOR_GUNI_THEME_BUTTON_BORDER;
        public int COLOR_GUNI_THEME_BUTTON_TEXT;

        public int COLOR_GUNI_THEME_LIST_ITEM_BORDER;
        public int COLOR_GUNI_THEME_LIST_ITEM_BACKGROUND;
        public int COLOR_GUNI_THEME_LIST_ITEM_SELECTED;
        public int COLOR_GUNI_THEME_LIST_ITEM_HEADER_BACKGROUND_BENIFIT;
        public int COLOR_GUNI_THEME_LIST_ITEM_HEADER_BACKGROUND_LOSS;
        public int COLOR_GUNI_THEME_LIST_ITEM_CONTENT_BORDER;
        public int COLOR_GUNI_THEME_LIST_ITEM_CONTENT_BACKGROUND;

        public static final int BACKGROUND = 0;
        public static final int BORDER = 1;
        public static final int TEXT = 2;
        public static final int TRANSPARENT = 3;
        public static final int SEMI_TRANSPARENT = 4;
        public static final int LINE = 5;
        public static final int HINT_TEXT = 6;
        public static final int BUTTON_BACKGROUND = 7;
        public static final int BUTTON_BORDER = 8;
        public static final int BUTTON_TEXT = 9;
        public static final int LIST_ITEM_BORDER = 10;
        public static final int LIST_ITEM_BACKGROUND = 11;
        public static final int LIST_ITEM_SELECTED = 12;
        public static final int LIST_ITEM_HEADER_BACKGROUND_BENIFIT = 13;
        public static final int LIST_ITEM_HEADER_BACKGROUND_LOSS = 14;
        public static final int LIST_ITEM_CONTENT_BORDER = 15;
        public static final int LIST_ITEM_CONTENT_BACKGROUND = 16;

        public GuniColor(Context context) {
            COLOR_GUNI_THEME_BACKGROUND = ContextCompat.getColor(context,R.color.guniThemeBackground);
            COLOR_GUNI_THEME_TEXT = ContextCompat.getColor(context,R.color.guniThemeText);
            COLOR_GUNI_THEME_TRANSPARENT = ContextCompat.getColor(context,R.color.guniThemeTransparent);
            COLOR_GUNI_THEME_BORDER = ContextCompat.getColor(context,R.color.guniThemeBorder);
            COLOR_GUNI_THEME_SEMI_TRANSPARENT = ContextCompat.getColor(context,R.color.guniThemeSemiTransparent);
            COLOR_GUNI_THEME_LINE = ContextCompat.getColor(context,R.color.guniThemeLine);
            COLOR_GUNI_THEME_HINT_TEXT = ContextCompat.getColor(context,R.color.guniThemeHintText);

            COLOR_GUNI_THEME_BUTTON_BACKGROUND = ContextCompat.getColor(context,R.color.guniThemeButtonBackground);
            COLOR_GUNI_THEME_BUTTON_BORDER = ContextCompat.getColor(context,R.color.guniThemeButtonBorder);
            COLOR_GUNI_THEME_BUTTON_TEXT = ContextCompat.getColor(context,R.color.guniThemeButtonText);

            COLOR_GUNI_THEME_LIST_ITEM_BORDER = ContextCompat.getColor(context,R.color.guniThemeListItemBorder);
            COLOR_GUNI_THEME_LIST_ITEM_BACKGROUND = ContextCompat.getColor(context,R.color.guniThemeListItemBackground);
            COLOR_GUNI_THEME_LIST_ITEM_SELECTED = ContextCompat.getColor(context,R.color.guniThemeListItemSelected);
            COLOR_GUNI_THEME_LIST_ITEM_HEADER_BACKGROUND_BENIFIT = ContextCompat.getColor(context,R.color.guniThemeListItemHeaderBackgroundBenefit);
            COLOR_GUNI_THEME_LIST_ITEM_HEADER_BACKGROUND_LOSS = ContextCompat.getColor(context,R.color.guniThemeListItemHeaderBackgroundLoss);
            COLOR_GUNI_THEME_LIST_ITEM_CONTENT_BORDER = ContextCompat.getColor(context,R.color.guniThemeListItemContentBorder);
            COLOR_GUNI_THEME_LIST_ITEM_CONTENT_BACKGROUND = ContextCompat.getColor(context,R.color.guniThemeListItemContentBackground);
        }

        public static int getColor(Context context,int color){
            switch (color){
                case BACKGROUND:
                    return ContextCompat.getColor(context,R.color.guniThemeBackground);
                case TEXT:
                    return ContextCompat.getColor(context,R.color.guniThemeText);
                case TRANSPARENT:
                    return ContextCompat.getColor(context,R.color.guniThemeTransparent);
                case BORDER:
                    return ContextCompat.getColor(context,R.color.guniThemeBorder);
                case SEMI_TRANSPARENT:
                    return ContextCompat.getColor(context,R.color.guniThemeSemiTransparent);
                case LINE:
                    return ContextCompat.getColor(context,R.color.guniThemeLine);
                case HINT_TEXT:
                    return ContextCompat.getColor(context,R.color.guniThemeHintText);

                case BUTTON_BACKGROUND:
                    return ContextCompat.getColor(context,R.color.guniThemeButtonBackground);
                case BUTTON_BORDER:
                    return ContextCompat.getColor(context,R.color.guniThemeButtonBorder);
                case BUTTON_TEXT:
                    return ContextCompat.getColor(context,R.color.guniThemeButtonText);

                case LIST_ITEM_BORDER:
                    return ContextCompat.getColor(context,R.color.guniThemeListItemBorder);
                case LIST_ITEM_BACKGROUND:
                    return ContextCompat.getColor(context,R.color.guniThemeListItemBackground);
                case LIST_ITEM_SELECTED:
                    return ContextCompat.getColor(context,R.color.guniThemeListItemSelected);
                case LIST_ITEM_HEADER_BACKGROUND_BENIFIT:
                    return ContextCompat.getColor(context,R.color.guniThemeListItemHeaderBackgroundBenefit);
                case LIST_ITEM_HEADER_BACKGROUND_LOSS:
                    return ContextCompat.getColor(context,R.color.guniThemeListItemHeaderBackgroundLoss);
                case LIST_ITEM_CONTENT_BORDER:
                    return ContextCompat.getColor(context,R.color.guniThemeListItemContentBorder);
                case LIST_ITEM_CONTENT_BACKGROUND:
                    return ContextCompat.getColor(context,R.color.guniThemeListItemContentBackground);
                default:return Color.CYAN;
            }
        }
    }
    public abstract static class GuniSelectEditMultiple<TypeOfList>{

        //IMPORTANT used to decide which modes should be fired
        public boolean inSelectionMode = false;
        public boolean selectionModeFired = false;
        public boolean unSelectionModeFired = false;
        public boolean selectFirstFired = false;
        public boolean unSelectFirstFired = false;
        public boolean selectLastFired = false;
        public boolean unSelectLastFired = false;
        public boolean selecting = false;

        //used to fire event on wish
        public static final int SELECTION_MODE = 0;
        public static final int UNSELECTION_MODE = 1;
        public static final int SELECT_FIRST = 2;
        public static final int UNSELECT_FIRST = 3;
        public static final int SELECT_LAST = 4;
        public static final int UNSELECT_lAST = 5;
        public static final int SELECTING = 6;
        public static final int UNSELECTING = 7;

        public ArrayList<Integer> itemsSelected = new ArrayList<>();
        private ArrayList<TypeOfList> list;
        private HashMap<Integer,ArrayList<GuniViewAnimationManager>> eventMap = new HashMap<>();

        public GuniSelectEditMultiple(ArrayList<TypeOfList> list) {
            this.list = list;
            eventMap.put(SELECTION_MODE,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(UNSELECTION_MODE,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(SELECT_FIRST,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(UNSELECT_FIRST,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(SELECT_LAST,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(UNSELECT_lAST,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(SELECTING,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(UNSELECTING,new ArrayList<GuniViewAnimationManager>());
        }

        //inner class
        class GuniViewAnimationManager {

            private View view;
            private Animation animation;
            private boolean hideOnEnd = true;

            public GuniViewAnimationManager(View view, Animation animation, final boolean hideOnEnd) {
                this.view = view;
                this.animation = animation;
                this.hideOnEnd = hideOnEnd;
                if(this.animation != null){
                    this.animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            GuniViewAnimationManager.this.view.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Log.i("BHAVIK","IN GUNI MANAGER's ANIMATION LISTENER");
                            if(hideOnEnd){
                                GuniViewAnimationManager.this.view.setVisibility(View.GONE);
                            }else {
                                GuniViewAnimationManager.this.view.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }

           /* @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("BHAVIK","IN GUNI MANAGER's ANIMATION LISTENER");
                if(hideOnEnd){
                    view.setVisibility(View.GONE);
                }else {
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }*/

            // getters and setters
            public View getView() {
                return view;
            }
            public void setView(View view) {
                this.view = view;
            }
            public Animation getAnimation() {
                return animation;
            }
            public void setAnimation(Animation animation) {
                this.animation = animation;
            }
            public boolean isHideOnEnd() {
                return hideOnEnd;
            }
            public void setHideOnEnd(boolean hideOnEnd) {
                this.hideOnEnd = hideOnEnd;
            }
        }


        public void selectionModeFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(SELECTION_MODE)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void unSelectionModeFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(UNSELECTION_MODE)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void selectFirstFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(SELECT_FIRST)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void unSelectFirstFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(UNSELECT_FIRST)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void selectLastFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(SELECT_LAST)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void unSelectLastFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(UNSELECT_lAST)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void selectFired(View view){
            for(GuniViewAnimationManager animationManager : eventMap.get(SELECTING)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void unSelectFired(View view){
            for(GuniViewAnimationManager animationManager : eventMap.get(UNSELECTING)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }



        public void onSelectionMode(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(SELECTION_MODE).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onUnSelectionMode(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(UNSELECTION_MODE).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onSelectFirst(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(SELECT_FIRST).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onUnSelectFirst(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(UNSELECT_FIRST).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onSelectLast(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(SELECT_LAST).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onUnSelectLast(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(UNSELECT_lAST).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onSelect(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(SELECTING).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onUnSelect(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(UNSELECTING).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };



        public void onClick(int position,View view) {
            //setes which events to fire
            configure(position);

            //start event firing
            if(selectionModeFired) {
                selectionModeFired();
            }
            if(unSelectionModeFired) {
                unSelectionModeFired();
            }
            if(selectFirstFired) {
                selectFirstFired();
            }
            if(unSelectFirstFired) {
                unSelectFirstFired();
            }
            if(selectLastFired) {
                selectLastFired();
            }
            if(unSelectLastFired) {
                unSelectLastFired();
            }if(selecting){
                selectFired(view);
            }
            if(!selecting){
                unSelectFired(view);
            }
        }

        private void configure(int position) {

            selectionModeFired = unSelectionModeFired = selectFirstFired = unSelectFirstFired = selectLastFired = unSelectLastFired = selecting = false;
            if (itemsSelected.size() == 0) {
                selectionModeFired = true;
                selectFirstFired = true;
                selecting = true;
            }
            if (itemsSelected.size() == 1 && !itemsSelected.contains(position)) {
                unSelectFirstFired = true;
                selecting =true;
            }
            if (itemsSelected.size() == list.size() - 1 && !itemsSelected.contains(position)) {
                selectLastFired = true;
                selecting = true;
            }
            if (itemsSelected.size() == list.size()) {
                unSelectLastFired = true;
            }
            if (itemsSelected.size() == 2 && itemsSelected.contains(position)) {
                selectFirstFired = true;
            }
            if (itemsSelected.size() == 1 && itemsSelected.contains(position)) {
                unSelectFirstFired = true;
                unSelectionModeFired = true;
            }
            if (!itemsSelected.contains(position)){
                selecting = true;

                itemsSelected.add(new Integer(position));
            }else itemsSelected.remove(new Integer(position));



            if(itemsSelected.size() > 0 ){
                inSelectionMode = true;
                Log.i("BHAVIK","inSelectionMode = true;");
            }else {
                inSelectionMode = false;
                Log.i("BHAVIK","inSelectionMode = false;");
            }
        }

        public void fireEvent(int event){
            switch (event){
                case SELECTION_MODE:
                    selectionModeFired();
                    itemsSelected.clear();
                    inSelectionMode = true;
                    break;
                case SELECT_FIRST:
                    //onEvent(event,true,null,-1);
                    break;
                case UNSELECTION_MODE:
                    unSelectionModeFired();
                    unSelectFirstFired();
                    unSelectLastFired();
                    itemsSelected.clear();
                    inSelectionMode = false;
                    break;
                case UNSELECT_FIRST:
                    //onEvent(event,false,null,-1);
                    break;
                case SELECT_LAST:
                    selectLastFired();
                    unSelectFirstFired();
                    itemsSelected.clear();
                    for(int i = 0 ; i < list.size() ; i++){
                        itemsSelected.add(i);
                    }
                    inSelectionMode = true;
                    break;
                case UNSELECT_lAST:
                    //onEvent(event,false,null,-1);
                    break;
                case SELECTING:
                    //onEvent(event,true,null,-1);
                    break;
                case UNSELECTING:
                    //onEvent(event,false,null,-1);
                    break;
                default:break;
            }
        }
    }
    public abstract static class GuniSelectEditSingle{
        public int itemSelected = -1;
        public View itemSelectedView;
        public boolean selectionMode = false;
        public boolean unSelectionMode = false;
        public boolean selecting = false;
        public boolean unSelecting = false;

        public static final int SELECTION_MODE = 0;
        public static final int UNSELECTION_MODE = 1;
        public static final int SELECTING = 2;
        public static final int UNSELECTING = 3;

        private HashMap<Integer,ArrayList<GuniViewAnimationManager>> eventMap = new HashMap<>();

        class GuniViewAnimationManager {

            private View view;
            private Animation animation;
            private boolean hideOnEnd = true;

            public GuniViewAnimationManager(View view, Animation animation, final boolean hideOnEnd) {
                this.view = view;
                this.animation = animation;
                this.hideOnEnd = hideOnEnd;
                if(this.animation != null){
                    this.animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            GuniViewAnimationManager.this.view.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if(hideOnEnd){
                                Log.i("BHAVIK","HIDE TRUE IN GUNI MANAGER's ANIMATION LISTENER");
                                GuniViewAnimationManager.this.view.setVisibility(View.GONE);
                            }else {
                                Log.i("BHAVIK","HIDE FALSE IN GUNI MANAGER's ANIMATION LISTENER");
                                GuniViewAnimationManager.this.view.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }

            // getters and setters
            public View getView() {
                return view;
            }
            public void setView(View view) {
                this.view = view;
            }
            public Animation getAnimation() {
                return animation;
            }
            public void setAnimation(Animation animation) {
                this.animation = animation;
            }
            public boolean isHideOnEnd() {
                return hideOnEnd;
            }
            public void setHideOnEnd(boolean hideOnEnd) {
                this.hideOnEnd = hideOnEnd;
            }
        }

        public GuniSelectEditSingle() {
            eventMap.put(SELECTION_MODE,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(UNSELECTION_MODE,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(SELECTING,new ArrayList<GuniViewAnimationManager>());
            eventMap.put(UNSELECTING,new ArrayList<GuniViewAnimationManager>());
        }

        public void selectionModeFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(SELECTION_MODE)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void unSelectionModeFired(){
            for(GuniViewAnimationManager animationManager : eventMap.get(UNSELECTION_MODE)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void selectFired(View view){
            for(GuniViewAnimationManager animationManager : eventMap.get(SELECTING)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }
        public void unSelectFired(View view){
            for(GuniViewAnimationManager animationManager : eventMap.get(UNSELECTING)){
                if(animationManager.getAnimation() != null) {
                    animationManager.getView().startAnimation(animationManager.getAnimation());
                }
                else if(animationManager.isHideOnEnd()){
                    animationManager.getView().setVisibility(View.GONE);
                }else {
                    animationManager.getView().setVisibility(View.VISIBLE);
                }
            }
        }


        public void onSelectionMode(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(SELECTION_MODE).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onUnSelectionMode(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(UNSELECTION_MODE).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onSelect(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(SELECTING).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };
        public void onUnSelect(View view,Animation animation,boolean hideOnEnd){
            eventMap.get(UNSELECTING).add(new GuniViewAnimationManager(view,animation,hideOnEnd));
        };


        public void onClick(View view,int position){
            configure(position);
            if(selectionMode){
                selectionModeFired();
            }
            if(unSelectionMode){
                unSelectionModeFired();
            }
            if(unSelecting){
                if(itemSelected == position) {
                    unSelectFired(view);
                    itemSelected = -1;
                    itemSelectedView = null;
                }
                else {
                    unSelectFired(itemSelectedView);
                }
            }
            if(selecting){
                selectFired(view);
                itemSelected = position;
                itemSelectedView = view;
            }
        }
        private void configure(int position){
            selectionMode = unSelectionMode = selecting = unSelecting = false;
            if(itemSelected == -1){
                selectionMode = true;
                selecting = true;
            }else if(itemSelected == position){
                unSelectionMode = true;
                unSelecting = true;
            }else {
                unSelecting = true;
                selecting = true;
            }
        }
        public void fireEvent(int event,View view){
            switch (event){
                case SELECTION_MODE:
                    break;
                case UNSELECTION_MODE:
                    itemSelected = -1;
                    itemSelectedView = null;
                    unSelectionModeFired();
                    break;
                case SELECTING:
                    selectFired(view);
                    break;
                case UNSELECTING:
                    unSelectFired(view);
                    break;
                default:break;
            }
        }
    }
}


























