package com.presentacion.utilidades;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataActividad;
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
		popupDialog.setTitle("Actividad");
		popupDialog.setSize(799, 397);
		popupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		popupDialog.setLocationRelativeTo(null);
		popupDialog.getContentPane().setLayout(null);

		JPanel popupPanel = new JPanel();
		popupPanel.setBounds(0, 0, 490, 364);
		popupDialog.getContentPane().add(popupPanel);
		popupPanel.setLayout(null);

		JLabel nom = new JLabel("Nombre: ");
		nom.setBounds(7, 69, 128, 20);
		popupPanel.add(nom);

		JTextField nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(145, 69, 326, 20);
		popupPanel.add(nombre);

		JLabel des = new JLabel("Descripcion:");
		des.setBounds(7, 100, 128, 20);
		popupPanel.add(des);

		JTextField descripcion = new JTextField();
		descripcion.setColumns(10);
		descripcion.setBounds(145, 100, 326, 20);
		popupPanel.add(descripcion);

		JLabel prov = new JLabel("Proveedor:");
		prov.setBounds(7, 224, 128, 20);
		popupPanel.add(prov);

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
		separator.setBounds(83, 287, 297, 2);
		popupPanel.add(separator);

		JLabel dep = new JLabel("Departamento:");
		dep.setBounds(7, 38, 128, 20);
		popupPanel.add(dep);

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

		JLabel cosuni = new JLabel("Costo Unico:");
		cosuni.setBounds(7, 131, 128, 20);
		popupPanel.add(cosuni);

		JSpinner costoUnico = new JSpinner();
		costoUnico.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		costoUnico.setBounds(145, 131, 86, 20);
		popupPanel.add(costoUnico);

		JLabel cit = new JLabel("Ciudad:");
		cit.setBounds(7, 193, 128, 20);
		popupPanel.add(cit);

		JTextField ciudad = new JTextField();
		ciudad.setColumns(10);
		ciudad.setBounds(145, 193, 326, 20);
		popupPanel.add(ciudad);

		JLabel dur = new JLabel("Duracion:");
		dur.setBounds(7, 162, 128, 20);
		popupPanel.add(dur);

		JSpinner duracion = new JSpinner();
		duracion.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		duracion.setBounds(145, 162, 86, 20);
		popupPanel.add(duracion);

		JButton aceptar = new JButton("Aceptar");
		aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aceptar.setBounds(4, 314, 224, 38);
		aceptar.putClientProperty("JButton.buttonType", "roundRect");
		popupPanel.add(aceptar);

		JButton cancelar = new JButton("Cancelar");
		cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelar.setBounds(247, 314, 224, 38);
		cancelar.putClientProperty("JButton.buttonType", "roundRect");
		popupPanel.add(cancelar);
		
		JPanel treeConteiner = new JPanel();
		treeConteiner.setBounds(500, 34, 273, 252);
		popupDialog.getContentPane().add(treeConteiner);
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Elementos");
//		JCheckBoxTree checkboxTree = new JCheckBoxTree(rootNode);
//		checkboxTree.setRootVisible(false);
	
		ArrayList<String> listaDeElementos = new ArrayList<>(); // Tu lista de elementos
		for (String elemento : listaDeElementos) {
		    DefaultMutableTreeNode node = new DefaultMutableTreeNode(elemento);
		    rootNode.add(node);
		}

		// Agregar el árbol a la interfaz
		treeConteiner.setLayout(new BorderLayout());
//		treeConteiner.add(checkboxTree, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(598, 11, 74, 21);
		popupDialog.getContentPane().add(lblNewLabel);

		cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				popupDialog.dispose();
			}
		});

		aceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nombre.getText().isEmpty() || descripcion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(popupDialog, "Todos los campos deben estar llenos",
							"Campos incompletos", JOptionPane.ERROR_MESSAGE);
					return;
				}
				IActividadController IAC = Fabrica.getInstance().getIActividadController();
				try {
					Object duracionObj = duracion.getValue();
					int duracionInt = (int) duracionObj;
					Object precioObj = costoUnico.getValue();
					int precioInt = (int) precioObj;
					IAC.crearActividad(departamento.getSelectedItem().toString(),
							proveedor.getSelectedItem().toString(), nombre.getText(), descripcion.getText(),
							duracionInt, precioInt, ciudad.getText(), LocalDate.now());
					JOptionPane.showMessageDialog(popupDialog, "Actividad creada con EXITO", "Registro Completado",
							JOptionPane.INFORMATION_MESSAGE);
					popupDialog.dispose();
				} catch (UsuarioYaExisteExcepcion a) {
					JOptionPane.showMessageDialog(popupDialog,"Ya existe la actividad " + nombre.getText(), a.getMessage() , JOptionPane.ERROR_MESSAGE);
					return;
				} catch (ParametrosInvalidosExcepcion a) {
					JOptionPane.showMessageDialog(popupDialog,  "Parametros invalidos", a.getMessage(), JOptionPane.ERROR_MESSAGE);
					return;
				} catch (UsuarioNoExisteExcepcion a) {
					JOptionPane.showMessageDialog(popupDialog, "No existe la actividad " + nombre.getText(), a.getMessage(), JOptionPane.ERROR_MESSAGE);
					return;
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
