package com.utilidades.util;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class util {
	
public static int Numerico=0;
public static int Alfabeto=-1;
public static int AlfaNumerico=-2;


public static void VaciarCampos(Object... campos) {
	for (final Object a : campos) {
		if (a instanceof JTextComponent) {
			((JTextComponent) a).setText("");
		}
		if (a instanceof JComboBox) {
			((JComboBox<?>) a).setSelectedIndex(0);
		}
	}
}

}
