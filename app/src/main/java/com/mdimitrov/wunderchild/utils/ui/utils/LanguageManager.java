package com.mdimitrov.wunderchild.utils.ui.utils;

import android.content.Context;

import com.mdimitrov.wunderchild.R;

public class LanguageManager {
    public static class Intro{
        public static int getTextPicture(Context context , String language){
            if(language.equals(context.getString(R.string.en))){
                //todo
                return android.R.drawable.ic_dialog_alert;
            }else{
                //todo
                return android.R.drawable.ic_dialog_dialer;
            }
        }

        public static String getText(Context context , String language){
            if(language.equals(context.getString(R.string.en))){
                //todo
                return "Your kids talents that you may have not uncovered yet! . . . ";
            }else{
                //todo
                return "Открийте още сега какви таланти притежават Вашите деца . . . ";
            }
        }

        public static String getButtonText(Context context , String language){
            if(language.equals(context.getString(R.string.en))){
                return "Free talent test";
            }else{
                return "Открий таланта";
            }
        }
    }

    public static class Join {
        public static String getText(Context context , String language){
            if(language.equals(context.getString(R.string.en))){
                return "Get talent report delivered straight to your inbox";
            }else{
                //todo
                return "Получи доклад за талантите на твоите деца направо на пощата!";
            }
        }

        public static String getLoginText(Context context , String language){
            if(language.equals(context.getString(R.string.en))){
                return "Login";
            }else{
                return "Влез";
            }
        }

        public static String getSingUpText(Context context , String language){
            if(language.equals(context.getString(R.string.en))){
                return "Sing up";
            }else{
                return "Регистрирай се";
            }
        }
    }


}
