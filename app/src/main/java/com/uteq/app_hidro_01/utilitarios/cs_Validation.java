package com.uteq.app_hidro_01.utilitarios;

import android.widget.EditText;

public class cs_Validation {


    public void showError(EditText input, String s){
        input.requestFocus();
        input.setError(s);
    }
}
