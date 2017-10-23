package com.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.utilidades.util.util;
import com.validate.field.Comportamiento;
import com.validate.field.validate;

//VS4E -- DO NOT REMOVE THIS LINE!
public class pruebas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton jButton0;
	private JTextField jTextField0;
	private JTextField jTextField1;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBox0;
	private JButton jButton1;
	Border def;
	private JButton jButton2;
	private JButton jButton3;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public pruebas() {
		initComponents();
		validate.addComportamiento(getJTextField0(),3,util.NUMBER_AND_LETTER,
											getJTextField1(),3,util.NUMBER);
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJTextField0(), new Constraints(new Leading(202, 110, 10, 10), new Leading(70, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(326, 105, 10, 10), new Leading(70, 12, 12)));
		add(getJComboBox0(), new Constraints(new Leading(324, 108, 12, 12), new Leading(116, 10, 10)));
		add(getJButton0(), new Constraints(new Leading(164, 12, 12), new Leading(115, 12, 12)));
		add(getJButton1(), new Constraints(new Leading(36, 10, 10), new Leading(67, 12, 12)));
		add(getJButton2(), new Constraints(new Leading(431, 10, 10), new Leading(32, 12, 12)));
		add(getJButton3(), new Constraints(new Leading(248, 10, 10), new Leading(26, 12, 12)));
		setSize(568, 323);
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("restaurar marco");
			jButton3.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton3ActionActionPerformed(event);
				}
			});
		}
		return jButton3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Prueba Marco");
			jButton2.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton2ActionActionPerformed(event);
				}
			});
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Enviar");
			jButton1.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton1ActionActionPerformed(event);
				}
			});
		}
		return jButton1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			jComboBox0.setDoubleBuffered(false);
			jComboBox0.setBorder(null);
		}
		return jComboBox0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Vaciar");
			jButton0.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton0ActionActionPerformed(event);
				}
			});
		}
		return jButton0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pruebas frame = new pruebas();
				frame.setDefaultCloseOperation(pruebas.EXIT_ON_CLOSE);
				frame.setTitle("pruebas");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void jButton0ActionActionPerformed(ActionEvent event) {
	validate.restablecerCampos(jTextField0,jTextField1,getJComboBox0());
	}

	private void jButton1ActionActionPerformed(ActionEvent event) {
	if (validate.ValidarCamposVaciosEspaciosJcombos("No se aceptan msj vacios", "No se aceptan espacios en los inicios", "Selecione una opcion", 
			getJTextField0(),getJTextField1(),getJComboBox0())) {
		JOptionPane.showMessageDialog(null, "Se envio correctamente");		
	}
	}

	private void jButton2ActionActionPerformed(ActionEvent event) {
		
		Border bordeRojo = BorderFactory.createLineBorder(Color.RED,2);
		  def=jComboBox0.getBorder();
		
		
			jComboBox0.setBorder(bordeRojo);
	
		

		
		
		
	}

	private void jButton3ActionActionPerformed(ActionEvent event) {
		jComboBox0.setBorder(def);
	}

}
