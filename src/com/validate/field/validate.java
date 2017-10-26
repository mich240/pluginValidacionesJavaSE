package com.validate.field;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

/**
 * Esta clase sirve para ayudar a validar los campos en formularios echos en
 * java SE utilizando distintos tipos de metodos puede agregar comportamientos a
 * los campos y complementarlos con una validacion final que indica si los datos
 * estan correctos.
 * 
 * <p>
 * 
 * Existen varios metodos
 * <code>isNumeric()</code>,<code>isLetter()</code>,<code>isEmail()</code>
 * ,<code>isAll()</code> que me permitiran agregar un comportamiento a mis
 * campos (llamese campos a todo Objeto que extienda de
 * <code>JTextComponent</code>) para que acepten cierto tipos de datos. estos
 * metodos deben ser inicializados en el constructor de la clase que sea el
 * formulario y agregar los atributos correspondientes a cada campos pasando los
 * <b>parametros</b> correspondientes <code>JTextComponent field</code> que es
 * el campo como tal y <code>int characterLimit</code> que es limite de
 * caracteres que quiero que este campo tenga.
 * <p>
 * luego de eso en la validacion final en el boton que envia los datos para que
 * sean registrados debes validar los campos, es decir que no esten vacios que
 * no tengan espacios al inicio y que los <code>JComboBox</code> no esten en la
 * primera seleccion. para esto yo cree el metodo <code>field()</code>
 * 
 * el cual tiene como <b>parametro:</b> <code>Object... fields</code> una cadena
 * de Objetos ilimitada, en su funcionamiento hago las respectiva
 * <code>instanceof</code> para validar los campos de texto y los
 * <code>JComboBox</code> este metodo retorna <code>true</code> si todos los
 * campos estan bien, incluidos el comportamiento de
 * <code>isNumeric()</code>,<code>isLetter()</code>,<code>isEmail()</code>
 * ,<code>isAll()</code> agregado a los campos.
 * 
 * <p>
 * los mensajes de error ya estan predefinidos pero se pueden establecer con los
 * respectivos metodos <code>Getters y Setters</code> ejemplo:
 * <code>setMsjMultipleErrors,getMsjSoloNumero,setMsjPrimerIndex.</code>
 * <p>
 * como metodo final esta <code>restoreField()</code> que se encarga de
 * restablecer los campos a su valor defecto. tambien recive un
 * <code>Object... fields</code> como <b>parametro.</b>
 * 
 * 
 * @author Metallis
 * @author Michel Mastrolonardo
 */
public class validate {

	private static String msjVacios = "No se aceptan campos vacios.";
	private static String msjBlanco = "No se aceptan espacios en blanco al inicio del campo.";
	private static String msjPrimerIndex = "Seleccione una opción valida.";
	private static String msjSoloNumero = "Solo se permiten números en este campo.";
	private static String msjSoloLetra = "Solo se permiten letras en este campo.";
	private static String msjEmail = "El email es incorrecto por favor verifique.";
	private static String msjMultipleErrors = "Existen multiples errores en su formulario por favor verifique e intente nuevamente.";
	private static boolean esNumero = true;
	private static boolean esLetras = true;
	private static boolean esEmail = true;
	private static int errorGlobal = 0;
	private static Border borderErros = BorderFactory.createCompoundBorder(new LineBorder(Color.red, 2, false),
			new LineBorder(new Color(255, 183, 183), 4, false));

	public static String getMsjEmail() {
		return msjEmail;
	}

	public static void setMsjEmail(String msjEmail) {
		validate.msjEmail = msjEmail;
	}

	public static String getMsjVacios() {
		return msjVacios;
	}

	public static String getMsjMultipleErrors() {
		return msjMultipleErrors;
	}

	public static void setMsjMultipleErrors(String msjMultipleErrors) {
		validate.msjMultipleErrors = msjMultipleErrors;
	}

	public static String getMsjSoloNumero() {
		return msjSoloNumero;
	}

	public static void setMsjSoloNumero(String msjSoloNumero) {
		validate.msjSoloNumero = msjSoloNumero;
	}

	public static String getMsjSoloLetra() {
		return msjSoloLetra;
	}

	public static void setMsjSoloLetra(String msjSoloLetra) {
		validate.msjSoloLetra = msjSoloLetra;
	}

	public static String getMsjBlanco() {
		return msjBlanco;
	}

	public static String getMsjPrimerIndex() {
		return msjPrimerIndex;
	}

	public static void setMsjVacios(String msjVacios) {
		validate.msjVacios = msjVacios;
	}

	public static void setMsjBlanco(String msjBlanco) {
		validate.msjBlanco = msjBlanco;
	}

	public static void setMsjPrimerIndex(String msjPrimerIndex) {
		validate.msjPrimerIndex = msjPrimerIndex;
	}

	private static boolean isNumeric(Character caracter) {
		if (Character.isDigit(caracter))
			return true;
		else
			return false;

	}

	private static boolean isLetter(Character caracter) {
		if ((Character.isLetter(caracter)) || caracter == ' '
				|| caracter == 8) /* aceptamos el ingreso de espacios o el backspace */
			return true;
		else
			return false;

	}

	private static boolean isEmail(String correo) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile(
				"^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(correo);
		if (mat.find()) {
			// System.out.println("[" + mat.group() + "]");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * si el tamaño de los caracteres del campo es mayor a la cantidad limite q
	 * estableci, guardo en 1 variable toda la cadena desde 0 hasta la cantidad q
	 * estableci digamos q fue 8. eso tomaria la cadena de ese campo de 0 a 8 y se
	 * lo seteo al campo eso sucede al despresionar la tecla del caracter q se este
	 * digitando en ese momento lo q hace q sea dinamico e inmediatamente q se tipee
	 * se seteara de forma correcta el valor de 0 a 8 tambien te salva de la
	 * posibilidad de q la persona se quede presionando la tecla hasta llenar el
	 * campo al soltarlo se enviara la cadena correcta 0 a 8 ejemplo.
	 **/
	private static void aplicarLimite(JTextComponent field, int characterLimit) {
		String nuevoTexto = null;
		if (field.getText().length() > characterLimit) {

			nuevoTexto = field.getText().substring(0, characterLimit);
			field.setText(nuevoTexto);
			field.getToolkit().beep();
		}
	}

	private static void addEventError(final JTextComponent field) {
		if (field.getBorder() != borderErros) {
			final Border borderDefecto = field.getBorder();
			field.setBorder(borderErros);
			field.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (field.getBorder() == borderErros) {
						field.setBorder(borderDefecto);
						field.removeKeyListener(this);
					}
				}
			});
		}
	}

	private static void addEventError(final JComboBox<?> field) {

		if (field.getBorder() != borderErros) {
			final Border borderDefecto = field.getBorder();
			field.setBorder(borderErros);
			field.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (field.getBorder() == borderErros) {
						field.setBorder(borderDefecto);
						field.removeActionListener(this);
					}
				}
			});

		}

	}

	public static void isEmail(final JTextComponent field, final int characterLimit) {

		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarLimite(field, characterLimit);
				if (!isEmail(field.getText())) {
					addEventError(field);
					if (esEmail) {
						esEmail = false;/** lado malo */
						errorGlobal++;
					}
				} else {
					if (!esEmail) {
						esEmail = true;
						errorGlobal--; /** lado bueno */
					}
				}
			}
		});

	}

	public static void isNumber(final JTextComponent field, final int characterLimit) {
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarLimite(field, characterLimit);
				for (int i = 0; i < field.getText().length(); i++) {

					if (!isNumeric(new Character(field.getText().charAt(i)))) {
						addEventError(field);
						field.getToolkit().beep();
						if (esNumero) {
							esNumero = false;/** lado malo */
							errorGlobal++;
						}

						break;
					} else {
						if (!esNumero) {
							esNumero = true;
							errorGlobal--; /** lado bueno */
						}

					}

				}

			}
		});
	}

	public static void isLetter(final JTextComponent field, final int characterLimit) {
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				aplicarLimite(field, characterLimit);

				for (int i = 0; i < field.getText().length(); i++) {

					if (!isLetter(new Character(field.getText().charAt(i)))) {
						addEventError(field);
						field.getToolkit().beep();
						if (esLetras) {
							esLetras = false;/** lado malo */
							errorGlobal++;
						}

						break;
					} else {
						if (!esLetras) {
							esLetras = true;
							errorGlobal--; /** lado bueno */
						}

					}

				}

			}
		});
	}

	public static void isAll(final JTextComponent field, final int characterLimit) {
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				aplicarLimite(field, characterLimit);

			}
		});
	}

	/**
	 * <code>boolean vacio = false, blanco = false, primeroIndex = false;</code> una
	 * variable para cada validacion por ahora solo 3 campos: vacios, campos con el
	 * primero caracter en blanco y comoBox con el primer indice seleccionado.
	 * <p>
	 * <code>int numeroErrores = 0;</code> contador de errores, este contador va a
	 * aumentar 1 valor por cada error diferente.
	 * <p>
	 * <code>each instanceof JTextComponent</code> verifico q el campo sea uno de
	 * texto.
	 * <p>
	 * si mi campo de texto esta vacio cambio vacio a true y aumento un valor a
	 * <code>numeroErrores</code> pero si otro campo me llega a dar este error ya no
	 * incremento <code>numeroErrores</code>
	 * <p>
	 * si el primer caracter es un campo en blanco cambio blanco a true y aumento un
	 * valor en <code>numeroErrores</code>. si caigo aqui existe la posibilidad de
	 * tener 2 errores de tipo distinto y si otro campo da este error no debe
	 * aumentar a <code>numeroErrores</code>
	 * <p>
	 * yo usare el primer index de un <code>JcomboBox</code> para validar los
	 * <code>JcomboBox</code>. si tengo seleccionado el primero index de un
	 * <code>JcomboBox</code> me da error de "seleccione una opcion" cambio
	 * primeroIndex a true y aumento un valor a <code>numeroErrores</code>, asi
	 * serian 3 errores dinstintos y si otro Objeto da este error no aumento nada a
	 * <code>numeroErrores</code>.
	 * <p>
	 * despues de haber recorrido todos los Objetos verifico si hay errores. si son
	 * mas de 1 errores entonces tengo multiple fallas es decir campos vacios,
	 * campos en blanco al inicio , comboBox no seleccionados o error de solo
	 * numeros, solo letras y Emails.
	 * <p>
	 * si solo tube un error significa que puedo filtrarlo y mostrar un msj mas
	 * personalizado a los campos q tubieron ese error.
	 * <p> si todo esta bien retorno <code>true.</code>
	 */

	public static boolean field(Object... fields) {
		boolean vacio = false, blanco = false, primeroIndex = false;
		int numeroErrores = 0;
		for (Object each : fields) {
			if (each instanceof JTextComponent) {
				if (((JTextComponent) each).getText().isEmpty()) {
					if (!vacio) {
						vacio = true;
						numeroErrores++;
					}
					addEventError(((JTextComponent) each));
				} else if (String.valueOf(((JTextComponent) each).getText().charAt(0)).equals(" ")) {
					if (!blanco) {
						blanco = true;
						numeroErrores++;
					}
					addEventError(((JTextComponent) each));
				}
			}
			if (each instanceof JComboBox) {
				if (((JComboBox<?>) each).getSelectedIndex() == 0) {
					if (!primeroIndex) {
						primeroIndex = true;
						numeroErrores++;
					}
					addEventError(((JComboBox<?>) each));
				}
			}
		}
		if ((numeroErrores + errorGlobal) > 1) {
			JOptionPane.showMessageDialog(null, getMsjMultipleErrors(), "Multiple Errores", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (numeroErrores == 1) {
			if (vacio) {
				JOptionPane.showMessageDialog(null, getMsjVacios(), "Campos vacios", JOptionPane.ERROR_MESSAGE);
			}
			if (blanco) {
				JOptionPane.showMessageDialog(null, getMsjBlanco(), "Campos en blanco", JOptionPane.ERROR_MESSAGE);
			}
			if (primeroIndex) {
				JOptionPane.showMessageDialog(null, getMsjPrimerIndex(), "No hay selección", JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
		if (errorGlobal == 1) {
			if (!esNumero) {
				JOptionPane.showMessageDialog(null, getMsjSoloNumero(), "Solo números", JOptionPane.ERROR_MESSAGE);
			}
			if (!esLetras) {
				JOptionPane.showMessageDialog(null, getMsjSoloLetra(), "Solo letras", JOptionPane.ERROR_MESSAGE);
			}

			if (!esEmail) {
				JOptionPane.showMessageDialog(null, getMsjEmail(), "Verifique el email", JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
		return true;
	}

	/**
	 * retablece los campos a su valor defecto, para los campos de texto es un
	 * simple <code>setText("")</code> y para los <code>JComboBox</code> es un
	 * <code>setSelectedIndex(0).</code>
	 */
	public static void restoreField(Object... campos) {
		for (final Object a : campos) {
			if (a instanceof JTextComponent) {
				((JTextComponent) a).setText("");
			}
			if (a instanceof JComboBox) {
				((JComboBox<?>) a).setSelectedIndex(0);
			}
		}
	}

	/***
	 * uso futuro
	 *
	 *
	 * // Convertir object en integer: @SuppressWarnings("unused") private static
	 * int ConvertirObjectToInt(Object Obj) { int NumInt =
	 * Integer.parseInt(ConvertirObjectToString(Obj)); return NumInt; }
	 * 
	 * // Convertir objeto en double: @SuppressWarnings("unused") private double
	 * ConvertirObjectToDouble(Object Obj) { String Str = Obj.toString(); double
	 * NumDouble = Double.valueOf(Str).doubleValue(); return NumDouble; }
	 * 
	 * // Convierte un object en boolean: @SuppressWarnings({ "unused",
	 * "static-access" }) private boolean ConvertirObjectToBoolean(Object Obj) {
	 * String CadBooleana = this.ConvertirObjectToString(Obj); Boolean booleano =
	 * new Boolean(CadBooleana); return booleano; }
	 * 
	 * // Convierte un object en string: private static String
	 * ConvertirObjectToString(Object Obj) { String Str = ""; if (Obj != null) { Str
	 * = Obj.toString(); } return Str; }
	 ****/

}