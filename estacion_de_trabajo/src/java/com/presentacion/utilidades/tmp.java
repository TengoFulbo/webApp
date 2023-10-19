package com.presentacion.utilidades;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IUsuarioController;

public class tmp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tmp window = new tmp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tmp() {
		initialize();
		JDialog popupDialog = new JDialog();
		popupDialog.setResizable(false);
		popupDialog.setTitle("Categoria");
		popupDialog.setSize(505, 200);
		popupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		popupDialog.setLocationRelativeTo(null);
		popupDialog.getContentPane().setLayout(null);

		JPanel popupPanel = new JPanel();
		popupPanel.setBounds(0, 0, 490, 170);
		popupDialog.getContentPane().add(popupPanel);
		popupPanel.setLayout(null);

		JLabel nom = new JLabel("Nombre: ");
		nom.setBounds(4, 23, 128, 20);
		popupPanel.add(nom);

		JTextField nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(142, 23, 326, 20);
		popupPanel.add(nombre);

		JComboBox<String> proveedor = new JComboBox<>();
		DefaultComboBoxModel<String> proveedoresModel = new DefaultComboBoxModel<>();
		proveedor.setBounds(145, 224, 326, 20);

		IUsuarioController IUC = Fabrica.getInstance().getIUsuarioController();
		List<dataUsuario> provedoreslist = IUC.listarUsuarios();

		if (provedoreslist == null) {
			JOptionPane.showMessageDialog(popupDialog, "No existen proveedores para la actividad en el sistema",
					"Error", JOptionPane.ERROR_MESSAGE);
			popupDialog.dispose();
			return;
		} else {
			for (dataUsuario proveedornick : provedoreslist) {
				if (proveedornick.getisProveedor()) {
					proveedoresModel.addElement(proveedornick.getNickname());
				}
			}
			proveedor.setModel(proveedoresModel);
			popupPanel.add(proveedor);
		}

		JSeparator separator = new JSeparator();
		separator.setBounds(89, 82, 297, 2);
		popupPanel.add(separator);

		JComboBox<String> departamento = new JComboBox<>();
		DefaultComboBoxModel<String> departamentoModel = new DefaultComboBoxModel<>();
		departamento.setBounds(145, 37, 326, 22);

		IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
		List<dataDepartamento> departamentos = IDC.listarDepartamentos();

		if (departamentos == null) {
			JOptionPane.showMessageDialog(popupDialog, "No existen departamentos en los cuales crear las actividades",
					"Error", JOptionPane.ERROR_MESSAGE);
			popupDialog.dispose();
			return;
		} else {
			for (dataDepartamento dept : departamentos) {
				departamentoModel.addElement(dept.getNombre());
				departamento.setModel(departamentoModel);
				popupPanel.add(departamento);
			}
		}

		JButton aceptar = new JButton("Aceptar");
		aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aceptar.setBounds(10, 109, 224, 38);
		aceptar.putClientProperty("JButton.buttonType", "roundRect");
		popupPanel.add(aceptar);

		JButton cancelar = new JButton("Cancelar");
		cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelar.setBounds(253, 109, 224, 38);
		cancelar.putClientProperty("JButton.buttonType", "roundRect");
		popupPanel.add(cancelar);

		cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				popupDialog.dispose();
			}
		});

		aceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(popupDialog, "Todos los campos deben estar llenos",
							"Campos incompletos", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ICategoriaController IAC = Fabrica.getInstance().getICategoriaController();
				
				try {
					IAC.crearCategoria(nombre.getText());
					JOptionPane.showMessageDialog(popupDialog, "Categoria creada con EXITO", "Registro Completado",
							JOptionPane.INFORMATION_MESSAGE);
					popupDialog.dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		popupDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

		popupDialog.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
