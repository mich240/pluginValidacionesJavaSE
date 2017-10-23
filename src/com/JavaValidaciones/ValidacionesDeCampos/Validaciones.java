package com.JavaValidaciones.ValidacionesDeCampos;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

public class Validaciones {
	private static int cantidad = 0;
	private static int tipo = 0;
	private static Component componente = null;
	private static Border bordeRojo = BorderFactory.createLineBorder(Color.RED,2);
	private static Border borde_por_defecto;


	public static void ValidacionesGenerales(Object... Campo_Limite_TipoValidacion) {
		int a = 0, b = 1, c = 2;
		for (int i = 0; i < Campo_Limite_TipoValidacion.length; i++) {
			if (i % 3 == 0) {
				cargaValida(Campo_Limite_TipoValidacion[a]);
				cargaValida(Campo_Limite_TipoValidacion[b]);
				cargaValida(Campo_Limite_TipoValidacion[c]);
				atribuirValores();
				a = c + 1;
				b = a + 1;
				c = b + 1;
			}
		}
	}
	private static void cargaValida(Object valor) {
		if (valor instanceof JTextComponent) {
			componente = (Component) valor;
		} else if (valor instanceof Integer) {
			if ((Integer) valor > 1) {
				cantidad = (Integer) valor;
			} else {
				tipo = (Integer) valor;
			}
		}
	}
	
	private static void atribuirValores() {
		final Component component = componente;
		final int cant = cantidad;
		final int tip = tipo;
		component.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {

				if (component instanceof JTextComponent) {
					if (((JTextComponent) component).getText().length() > cant) {
						String au = null, aux = ((JTextComponent) component)
								.getText();
						au = aux.substring(0, cant);
						((JTextComponent) component).setText(au);
						((JTextComponent) component).getToolkit().beep();
					}
					if (tip == 0) {
						String texto = "";
						for (int i = 0; i < ((JTextComponent) component).getText().length(); i++) {					
							if (isNumeric(new Character(((JTextComponent) component).getText().charAt(i)))) 
								texto += ((JTextComponent) component).getText().charAt(i);
							else ((JTextComponent) component).getToolkit().beep();	
						}
						((JTextComponent) component).setText(texto);
						
					}
					if (tip == -1) {
						String texto = "";
						for (int i = 0; i < ((JTextComponent) component)
								.getText().length(); i++) {
							if (!isNumeric(new Character(
									((JTextComponent) component).getText()
											.charAt(i))))
								texto += ((JTextComponent) component).getText()
										.charAt(i);
							else ((JTextComponent) component).getToolkit().beep();	
						}
						((JTextComponent) component).setText(texto);
					}
				}
			}
		});
	}
	private static boolean isNumeric(Character caracter) {
		char c = caracter.charValue();
		if (!(Character.isDigit(c))) {
			return false;
		} else {
			return true;
		}
	}


	public static boolean ValidarCamposVaciosEspacios(String messageVacios,String messageEspacios, Object... campos) {
		for (final Object a : campos) {
			if (a instanceof JTextComponent) {
				if (((JTextComponent) a).getBorder() != bordeRojo) {
					borde_por_defecto = ((JTextComponent) a).getBorder();
				}
				((JTextComponent) a).addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						if (((JTextComponent) a).getBorder() == bordeRojo) {
							((JTextComponent) a).setBorder(borde_por_defecto);
						}
					}
				});
				if (((JTextComponent) a).getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, messageVacios);
					((JTextComponent) a).setBorder(bordeRojo);
					((JTextComponent) a).requestFocus();
					return false;
				} else if (String.valueOf(
						((JTextComponent) a).getText().charAt(0)).equals(" ")) {
					JOptionPane.showMessageDialog(null, messageEspacios);
					((JTextComponent) a).setText("");
					((JTextComponent) a).setBorder(bordeRojo);
					((JTextComponent) a).requestFocus();

					return false;
				}
			}
		}
		return true;
	}
	
	
	public static boolean ValidarCamposVaciosEspaciosJcombos(String messageVacios, String messageEspacios, String messageCombos,Object... campos) {
		for (final Object a : campos) {
			if (a instanceof JTextComponent) {
				if (((JTextComponent) a).getBorder() != bordeRojo) {
					borde_por_defecto = ((JTextComponent) a).getBorder();
				}
				((JTextComponent) a).addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						if (((JTextComponent) a).getBorder() == bordeRojo) {
							((JTextComponent) a).setBorder(borde_por_defecto);
						}
					}
				});
				if (((JTextComponent) a).getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, messageVacios);
					((JTextComponent) a).setBorder(bordeRojo);
					((JTextComponent) a).requestFocus();
					return false;
				} else if (String.valueOf(
						((JTextComponent) a).getText().charAt(0)).equals(" ")) {
					JOptionPane.showMessageDialog(null, messageEspacios);
					((JTextComponent) a).setText("");
					((JTextComponent) a).setBorder(bordeRojo);
					((JTextComponent) a).requestFocus();

					return false;
				}
			} 
			
			else if (a instanceof JComboBox) {
				if (((JComboBox<?>) a).getBorder() != bordeRojo) {
					borde_por_defecto = ((JComboBox<?>) a).getBorder();
				}
				((JComboBox<?>) a).addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						if (((JComboBox<?>) a).getBorder() == bordeRojo) {
							((JComboBox<?>) a).setBorder(borde_por_defecto);
						}
					}
				});
				if (((JComboBox<?>) a).getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, messageCombos);
					((JComboBox<?>) a).setBorder(bordeRojo);
					((JComboBox<?>) a).requestFocus();
					return false;
				}
			}		
		}
		return true;
	}
	
	/***uso futuro
	 *
	 *
	 // Convertir object en integer:
	@SuppressWarnings("unused")
	private static int ConvertirObjectToInt(Object Obj) {
		int NumInt = Integer.parseInt(ConvertirObjectToString(Obj));
		return NumInt;
	}

	// Convertir objeto en double:
	@SuppressWarnings("unused")
	private double ConvertirObjectToDouble(Object Obj) {
		String Str = Obj.toString();
		double NumDouble = Double.valueOf(Str).doubleValue();
		return NumDouble;
	}

	// Convierte un object en boolean:
	@SuppressWarnings({ "unused", "static-access" })
	private boolean ConvertirObjectToBoolean(Object Obj) {
		String CadBooleana = this.ConvertirObjectToString(Obj);
		Boolean booleano = new Boolean(CadBooleana);
		return booleano;
	}

	// Convierte un object en string:
	private static String ConvertirObjectToString(Object Obj) {
		String Str = "";
		if (Obj != null) {
			Str = Obj.toString();
		}
		return Str;
	}****/
	 
}