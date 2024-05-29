package ru.rodyk.histgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ApplicationAdapter;

public class MyTextInputListener implements Input.TextInputListener {
    String text_;
    @Override
    public void input(String text) {
        this.text_ = text;
    }

    @Override
    public void canceled() {
        text_ = "dkem";
    }
}
