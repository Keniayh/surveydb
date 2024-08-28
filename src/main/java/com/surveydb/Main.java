package com.surveydb;

import javax.swing.*;

import com.surveydb.UI.AdminUI;
import com.surveydb.UI.LoginIU;
import com.surveydb.UI.UserUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        new LoginIU().showMenu();
    }
}
