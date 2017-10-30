package com.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import com.validation.field.validation;

//VS4E -- DO NOT REMOVE THIS LINE!
public class test extends JFrame {

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
	private JPasswordField jPasswordField0;
	private JTextArea jTextArea0;
	private JScrollPane jScrollPane0;
	private JTextField jTextField2;
	private JDateChooser jDateChooser0;
	private JFormattedTextField jFormattedTextField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	public test() {
		initComponents();

		
		validation.isNumber(getJTextField0(),8);
		validation.isEmail(getJTextField1(),15);
		validation.isNumber(getJTextArea0(), 50);
		validation.isEmail(getJTextField2(), 50);
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJButton0(), new Constraints(new Leading(164, 12, 12), new Leading(115, 12, 12)));
		add(getJButton1(), new Constraints(new Leading(36, 10, 10), new Leading(67, 12, 12)));
		add(getJButton2(), new Constraints(new Leading(431, 10, 10), new Leading(32, 12, 12)));
		add(getJButton3(), new Constraints(new Leading(248, 10, 10), new Leading(26, 12, 12)));
		add(getJComboBox0(), new Constraints(new Leading(36, 108, 12, 12), new Leading(132, 51, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Leading(158, 132, 10, 10), new Leading(151, 80, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(202, 110, 10, 10), new Leading(70, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(326, 135, 10, 10), new Leading(70, 27, 12, 12)));
		add(getJPasswordField0(), new Constraints(new Leading(325, 140, 10, 10), new Leading(160, 31, 10, 10)));
		add(getJTextField2(), new Constraints(new Leading(360, 125, 10, 10), new Leading(113, 12, 12)));
		add(getJDateChooser0(), new Constraints(new Leading(26, 149, 10, 10), new Leading(26, 12, 12)));
		add(getJFormattedTextField0(), new Constraints(new Leading(230, 91, 10, 10), new Leading(111, 12, 12)));
		setSize(568, 426);
	}

	private JFormattedTextField getJFormattedTextField0() {
		if (jFormattedTextField0 == null) {
			
				
				jFormattedTextField0 = new JFormattedTextField();
			
			
		}
		return jFormattedTextField0;
	}

	private JDateChooser getJDateChooser0() {
		if (jDateChooser0 == null) {
			jDateChooser0 = new JDateChooser();
			jDateChooser0.setDateFormatString("dd-MM-yyyy");
			jDateChooser0.setDate(new Date());
			JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser0.getDateEditor();
			editor.setEditable(false);
			
			
			
			jDateChooser0.setMaxSelectableDate(new Date(253370782875740L));
			jDateChooser0.setMinSelectableDate(new Date(-62135751524260L));
		}
		return jDateChooser0;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setAutoscrolls(true);
		}
		return jTextField2;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTextArea0());
		}
		return jScrollPane0;
	}

	private JTextArea getJTextArea0() {
		if (jTextArea0 == null) {
			jTextArea0 = new JTextArea();
			jTextArea0.setAutoscrolls(true);
		}
		return jTextArea0;
	}

	private JPasswordField getJPasswordField0() {
		if (jPasswordField0 == null) {
			jPasswordField0 = new JPasswordField();
			jPasswordField0.setAutoscrolls(true);
		}
		return jPasswordField0;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			jComboBox0.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jComboBox0ActionActionPerformed(event);
				}
			});
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
				test frame = new test();
				frame.setDefaultCloseOperation(test.EXIT_ON_CLOSE);
				frame.setTitle("pruebas");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void jButton0ActionActionPerformed(ActionEvent event) {
	validation.restoreField(jTextField0,jTextField1,getJComboBox0(),getJTextArea0(),getJPasswordField0(),jTextField2);
	}

	private void jButton1ActionActionPerformed(ActionEvent event) {
	
		
		if (validation.field(jFormattedTextField0,jTextField0,getJTextField1(),jComboBox0,jPasswordField0,getJTextArea0(),jTextField2)) {
			JOptionPane.showMessageDialog(null, "Se envio correctamente");
		}
	}

	private void jButton2ActionActionPerformed(ActionEvent event) {
		
		Border bordeRojo = BorderFactory.createCompoundBorder(new LineBorder(Color.red, 1, false),
				new LineBorder(new Color(255, 183, 183), 3, false));
		  def=jTextField0.getBorder();
		
		
			jTextField0.setBorder(bordeRojo);
	
		

		
		
		
	}

	private void jButton3ActionActionPerformed(ActionEvent event) {
		jTextField0.setBorder(def);
	}

	private void jComboBox0ActionActionPerformed(ActionEvent event) {
	
		
	}


}
