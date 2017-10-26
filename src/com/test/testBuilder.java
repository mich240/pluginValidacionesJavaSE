package com.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.validate.field.validacion;

public class testBuilder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1216463612247330986L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private JComboBox<String> comboBox;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testBuilder frame = new testBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testBuilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(26, 30, 86, 20);
		textField.setColumns(10);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 30, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(248, 30, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(216, 113, 118, 66);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodo();
			}
		});
		btnNewButton.setBounds(38, 142, 89, 23);
		contentPane.add(btnNewButton);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(68, 72, 118, 20);
		comboBox.addItem("g");
		comboBox.addItem("g");
		contentPane.add(comboBox);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setDate(new Date());
		dateChooser.setBounds(227, 206, 95, 20);
		
		contentPane.add(dateChooser);
	}
	private void metodo() {
		System.out.println(validacion.field(textArea,textField,textField_2,textField_1,comboBox));
	System.out.println(comboBox.getSelectedIndex());
	}
}
