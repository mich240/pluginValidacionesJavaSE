package com.validate.field;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

public class validate {

	private static String msjVacios = "No se aceptan campos vacios.";
	private static String msjBlanco = "No se aceptan espacios en blanco al inicio del campo.";
	private static String msjPrimerIndex = "Seleccione una opción valida.";
	private static String msjSoloNumero = "Solo se permiten números en este campo.";
	private static String msjSoloLetra = "Solo se permiten letras en este campo.";
	private static String msjEmail= "El email es incorrecto por favor verifique.";
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
	
	
//	 public static  boolean isDate(String fechax) {
//	        try {
//	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
//	            Date fecha = formatoFecha.parse(fechax);
//	            
//	            	
//	            	
//	            	
//	            
//	            
//	        } catch (Exception e) {
//	            JOptionPane.showMessageDialog(null, "No es el formato adecuado... dd-MM-yyyy");
//	        	return false;
//	            
//	        }
//	        return true;
//	    }
	
	

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
	

	private static boolean isNumeric(Character caracter) {
		if (Character.isDigit(caracter))
			return true;
		else
			return false;

	}
	private static boolean isLetter(Character caracter) {
		if ((Character.isLetter(caracter)) || caracter==' ' || caracter==8 ) /* aceptamos el ingreso de espacios o el backspace*/
			return true;
		else
			return false;

	}
	
	 private static boolean isEmail(String correo) {
	        Pattern pat = null;
	        Matcher mat = null;
	        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");	       
	        mat = pat.matcher(correo);
	        if (mat.find()) {
	  //System.out.println("[" + mat.group() + "]");
	            return true;
	        }else{
	            return false;
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
					}else {
						if (!esEmail) {
							esEmail = true;
							errorGlobal--; /** lado bueno */
						}
					}
				}
		 });
		 
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

	public static boolean field(Object... fields) {
		/**
		 * una variable para cada validacion por ahora solo 3 campos:vacios, campos con
		 * el primero caracter en blanco y comoBox con el primer indice seleccionado.
		 */
		boolean vacio = false, blanco = false, primeroIndex = false;
		int numeroErrores = 0;/**
								 * contador de errores, este contador va a aumentar 1 valor por cada error
								 * diferente.
								 */
		for (Object each : fields) {
			/** verifico q el campo sea uno de texto */
			if (each instanceof JTextComponent) {

				if (((JTextComponent) each).getText().isEmpty()) {
					/**
					 * si mi campo de texto esta vacio cambio vacio a true y aumento un valor a
					 * numeroErrores pero si otro campo me llega a dar este error ya no incremento
					 * numeroErrores
					 */
					if (!vacio) {
						vacio = true;
						numeroErrores++;
					}
					addEventError(((JTextComponent) each));

				} else if (String.valueOf(((JTextComponent) each).getText().charAt(0)).equals(" ")) {
					/**
					 * si el primer caracter es un campo en blanco cambio blanco a true y aumento un
					 * valor en numeroErrores. si caigo aqui existe la posibilidad de tener 2
					 * errores de tipo distinto y si otro campo da este error no debe aumentar a
					 * numeroErrores
					 */
					if (!blanco) {
						blanco = true;
						numeroErrores++;
					}
					addEventError(((JTextComponent) each));

				}

			}

			if (each instanceof JComboBox) {

				if (((JComboBox<?>) each).getSelectedIndex() == 0) {
					/**
					 * yo usare el primer index de un combo para validar los jcombox. si tengo
					 * seleccionado el primero index de un combo me da error de "seleccione una
					 * opcion" cambio primeroIndex a true y aumento un valor a numeroErrores, asi
					 * serian 3 errores dinstintos y si otro Objeto da este error no aumento nada a
					 * numeroErrores.
					 */
					if (!primeroIndex) {
						primeroIndex = true;
						numeroErrores++;
					}
					addEventError(((JComboBox<?>) each));

				}

			}
		}
		/**
		 * despues de haber recorrido todos los Objetos verifico si hay errores. si son
		 * mas de 1 errores entonces tengo multiple fallas es decir campos vacios,
		 * campos en blanco al inicio y comboBox no seleccionados.
		 */
		if ((numeroErrores + errorGlobal) > 1) {
			JOptionPane.showMessageDialog(null, getMsjMultipleErrors(), "Multiple Errores", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (numeroErrores == 1) {
			/**
			 * si solo tube un error significa q puedo filtrarlo y mostrar un msj mas
			 * personalizado a los campos q tubieron ese error.
			 */
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

	// private static int cantidad = 0;
	// private static int tipo = 0;
	// private static Component componente = null;
	// private static Border borderErros =
	// BorderFactory.createLineBorder(Color.RED,
	// 6);

	// public static void addComportamiento(Object... Campo_Limite_TipoValidacion) {
	// int a = 0, b = 1, c = 2;
	// /**
	// * recorro cada campo el primero es un campoTexto el segundo es el limite y el
	// * tercero es lo q voy a validar
	// */
	// for (int i = 0; i < Campo_Limite_TipoValidacion.length; i++) {
	// /**
	// * al conteo de 3 entro al if es decir entro asi al 3 luego al 6 luego al 9
	// */
	// if (i % 3 == 0) {
	// /**
	// * es decir que hago brincos de 3 en tres, y es asi xq ahora uso el metodo
	// * cargaValida para cada elemente si los parametros son correctos serian como
	// * dije arriba: un texto - limte - validacion
	// */
	// cargaValida(Campo_Limite_TipoValidacion[a]);// texto
	// /**
	// * basicamente la carga valida me identifica que atributo le voy a dar a cada
	// * uno de esos campos sera 3 los q enviare y los guardare en mis variables
	// * globales y se les asignara el atributo correspondiente todo esto suce en un
	// * solo cliclo de for es decir q por cada ciclo yo cambio esos campos ya q son
	// * globales.
	// */
	// cargaValida(Campo_Limite_TipoValidacion[b]);// limite
	// cargaValida(Campo_Limite_TipoValidacion[c]);// validacion
	// /**
	// * como dije antes una ves q defino las 3 variables globales con estas locales
	// * le doy sus respectivos atributos
	// */
	// atribuirValores();
	// /**
	// * es mi forma de aumentar los valores de las varibles incrementales el caso
	// es
	// * q ellas deben sufir de 3 en tres asi q lo q hice fue igualar a al c puesto
	// q
	// * c es el ultimo osea 3 (en primera instancia) le sumo 1 y queda en 4 y en
	// base
	// * a eso aumento b y c quedando para los proximos valores 4,5,6 como 6 es
	// * divisible entre 3 entra en el if en esa vuelta, y se toman los siguientes 3
	// * campos text-limite-validacion.
	// */
	// a = c + 1;
	// b = a + 1;
	// c = b + 1;
	// }
	// }
	// }

	// private static void cargaValida(Object valor) {
	// /**
	// * si es un componente de texto lo almaceno en componente y si es un numero
	// hay
	// * 2 posibles opciones: si el valor es mayor a 1 es el tipo de cantidad q
	// deseo
	// * limitar en el campo. de resto es el tipo de validacion puesto q para el
	// tipo
	// * de validacion use valores negativos para q no interfiran por si una persona
	// * quiere colocar como cantidad limite un 1 o 2 xq es posible.
	// */
	// if (valor instanceof JTextComponent) {
	//
	// componente = (Component) valor;
	//
	// } else if (valor instanceof Integer) {
	//
	// if ((Integer) valor > 1) {
	//
	// cantidad = (Integer) valor;
	//
	// } else {
	// tipo = (Integer) valor;
	// }
	// }
	// }

	// private static void atribuirValores() {
	// /**
	// * estas son las variables q se especifican en la cargavalida separando los 3
	// * elementos de lso campos
	// */
	// final Component component = componente;
	// final int cant = cantidad;
	// final int tip = tipo;
	//
	// /**
	// * tomamos al componente y a�adimos el KeyReleased. el es metodo q se ejecuta
	// al
	// * quitar el dedo del boton presionado
	// */
	// component.addKeyListener(new KeyAdapter() {
	//
	// public void keyReleased(KeyEvent event) {
	//
	// if (component instanceof JTextComponent) {/// nos aseguramos de
	// /// q el componente
	// /// es un campo de
	// /// texto
	//
	// if (((JTextComponent) component).getText().length() > cant) {
	// /**
	// * si el tama�o de los caracteres del campo es mayor a la cantidad limite q
	// * estableci, guardo en 1 variable toda la cadena desde 0 hasta la cantidad q
	// * estableci digamos q fue 8. eso tomaria la cadena de ese componente de 0 a 8
	// y
	// * se lo seteo al campo eso sucede al despresionar la tecla del caracter q se
	// * este digitando en ese momento lo q hace q sea dinamico y inmediatamente q
	// se
	// * tipee se seteara de forma correcta el valor de 0 a 8 tambien te salva de la
	// * posibilidad de q la persona se quede presionando la tecla hasta llenar el
	// * campo al soltarlo se enviara la cadena correcta 0 a 8 ejemplo.
	// **/
	// String au = null, aux = ((JTextComponent) component).getText();// aqui
	// // tomo
	// // la
	// // cadena
	// // base
	// au = aux.substring(0, cant);// aqui tomo el valor desde
	// // mi cadena base
	// ((JTextComponent) component).setText(au);// y aqui la
	// // seteo de
	// // forma
	// // limitada
	// ((JTextComponent) component).getToolkit()
	// .beep();/**
	// * hago el sonido de beep para q el usuario se entere del error
	// */
	// }
	// /**
	// * ahora viene la valicacion del tipo. use 2 posibles valores 0: es para
	// validar
	// * q solo se permitan numeros. -1: es para validar q solo se permitan letras.
	// * -2: el -2 en realidad esta demas puesto q solo valido si es 0 o -1 si no
	// son
	// * esos valores es xq automaticamente quiero q la validacion sea alfanumerica
	// es
	// * decir q pasar cualquier valor diferente de -1 y 0 hara q sea alfanumerico
	// en
	// * mi variable contante definida en util uso NUMBER_AND_LETTER con -2
	// */
	// if (tip == 0) {
	// String texto = "";
	// for (int i = 0; i < ((JTextComponent) component).getText().length(); i++) {
	// /**
	// * recorro cada caracter de la cadena de texto de mi campo, a medida q recorro
	// * la cadena obtengo el caracter correspondiente y verifico si es numerico con
	// * el metodo isNumeric
	// */
	// if (isNumeric(new Character(((JTextComponent)
	// component).getText().charAt(i))))
	// texto += ((JTextComponent) component).getText().charAt(i);
	// /**
	// * si el caracter es numerico lo agrego a mi variable texto de esa manera a
	// * medida q verifico caracteres cuando encuentre uno q no es numerico no lo
	// * guardara en este texto. al final seteo ese texto al campo limpio de
	// * caracteres no numericos. lo hago asi xq si el usaurio presiona muchas
	// teclas
	// * a la ves y se pasa una letra por alli, haciendolo de esta manera al soltar
	// el
	// * todas la teclas se lanza este metodo y limpiaria toda la cadena.
	// */
	// else
	// ((JTextComponent) component).getToolkit()
	// .beep();/**
	// * si no es numerico uso el sonido del beep para avisarle al usuario
	// */
	// }
	// ((JTextComponent) component).setText(texto);
	// // devuelvo el texto limpio de letras
	// }
	// if (tip == -1) {
	// String texto = "";
	// for (int i = 0; i < ((JTextComponent) component).getText().length(); i++) {
	// /**
	// * basicamente hace lo mismo q el del numerico de esta forma admito todo los
	// * caracteres excepto numeros asi q se pueden tipear letras espacios simbolos
	// */
	// if (!isNumeric(new Character(((JTextComponent)
	// component).getText().charAt(i))))// tomo
	// // los
	// // caracteres
	// // no
	// // numericos
	//
	// texto += ((JTextComponent) component).getText().charAt(i);// solo
	// // agregos
	// // esos
	// // caracteres
	// // no
	// // numericos
	// else
	// ((JTextComponent) component).getToolkit().beep();
	// }
	// ((JTextComponent) component).setText(texto);// y se los
	// // seteo.
	// }
	// }
	// }
	// });
	// }
	//
	// public static boolean field(String messageVacios, String messageEspacios,
	// String messageCombos, Object... campos) {
	// /** recorro cada objeto q paso por parametros */
	// for (final Object a : campos) {
	// /** detecto que sea del tipo texto */
	// if (a instanceof JTextComponent) {
	//
	// if (((JTextComponent) a).getBorder() != bordeRojo) {
	// /**
	// * si no tiene el borde de error guardo el borde que es el
	// * borde defecto
	// */
	// borde_por_defecto = ((JTextComponent) a).getBorder();
	// }
	// /**
	// * agrego el listener keypressed que verifica si ya tiene el
	// * borde rojo y si lo tiene le da el borde defecto. esto es si
	// * el campo dio error como muestra el borde rojo cuando se
	// * presione una tecla ahi en ese campo se restaura el borde
	// */
	// ((JTextComponent) a).addKeyListener(new KeyAdapter() {
	// public void keyPressed(KeyEvent event) {
	// if (((JTextComponent) a).getBorder() == bordeRojo) {
	// ((JTextComponent) a).setBorder(borde_por_defecto);
	// }
	// }
	// });
	// /**
	// * ahora valido si el campo esta vacio si es asi le seteo el
	// * borde a rojo le hago focus a ese campo y returno false para
	// * que interrumpa el ciclo
	// */
	// if (((JTextComponent) a).getText().isEmpty()) {
	// JOptionPane.showMessageDialog(null, messageVacios);
	// ((JTextComponent) a).setBorder(bordeRojo);
	// ((JTextComponent) a).requestFocus();
	// return false;
	// /**
	// * si eso de arriba esta bien evaluo si el primer caracter
	// * es un espacio en blanco de esta manera valido q no se
	// * permiten espacio en blanco al inicio
	// */
	// } else if (String.valueOf(((JTextComponent)
	// a).getText().charAt(0)).equals(" ")) {
	// JOptionPane.showMessageDialog(null, messageEspacios);
	// ((JTextComponent) a).setText("");
	// ((JTextComponent) a).setBorder(bordeRojo);
	// ((JTextComponent) a).requestFocus();
	//
	// return false;
	// }
	// }
	// /**
	// * ahora valido el combo box, verifico q el objeto sea del combo y
	// * verifico si tiene el borde rojo y si no guardo el de defecto
	// **/
	// else if (a instanceof JComboBox) {
	// if (((JComboBox<?>) a).getBorder() != bordeRojo) {
	// borde_por_defectoCombo = ((JComboBox<?>) a).getBorder();
	// }
	// ((JComboBox<?>) a).addActionListener(new ActionListener() {
	// /**
	// * agrego un actionPerformed para q cuando selecione el item
	// * correcto vuelva el borde defecto
	// */
	// public void actionPerformed(ActionEvent event) {
	// if (((JComboBox<?>) a).getBorder() == bordeRojo) {
	// ((JComboBox<?>) a).setBorder(borde_por_defectoCombo);
	// }
	// }
	// });
	// /**
	// * y la validacion de este combo es si tiene el SeletedIndex 0
	// * seleccionado la idea es colocar esta primera opcion como una
	// * por defecto q define o describe lo q debe hacer el combo.
	// * ejemplo: "Seleccione una opcion" de esa forma se puede
	// * validar aqui
	// **/
	// if (((JComboBox<?>) a).getSelectedIndex() == 0) {
	// JOptionPane.showMessageDialog(null, messageCombos);
	// ((JComboBox<?>) a).setBorder(bordeRojo);
	// ((JComboBox<?>) a).requestFocus();
	// return false;
	// }
	// }
	// }
	// /**
	// * si pasa por todas las condicionales y todo esta bien y no se
	// * interrumpe el ciclo nunca por algun false se envia true lo que
	// * asegura q todo esta correcto.
	// */
	// return true;
	// }

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