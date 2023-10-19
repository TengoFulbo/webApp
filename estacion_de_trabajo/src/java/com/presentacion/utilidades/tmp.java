package com.presentacion.utilidades;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JCheckBox;
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
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;

import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.excepciones.NoExisteExcepcion;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.excepciones.YaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.utilidades.estadoActividad;

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
		popupDialog.setSize(493, 397);
		popupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		popupDialog.setLocationRelativeTo(null);
		popupDialog.getContentPane().setLayout(null);

		JPanel popupPanel = new JPanel();
		popupPanel.setBounds(0, 0, 477, 364);
		popupDialog.getContentPane().add(popupPanel);
		popupPanel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(90, 287, 297, 2);
		popupPanel.add(separator);

		JButton aceptar = new JButton("Confirmar");
		aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aceptar.setBounds(4, 314, 224, 38);
		aceptar.putClientProperty("JButton.buttonType", "roundRect");
		popupPanel.add(aceptar);

		JButton rechazar = new JButton("Rechazar");
		rechazar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rechazar.setBounds(248, 314, 224, 38);
		rechazar.putClientProperty("JButton.buttonType", "roundRect");
		popupPanel.add(rechazar);
		
		JPanel center = new JPanel();
		center.setBounds(8, 7, 462, 271);
		popupPanel.add(center);
		center.setLayout(new BorderLayout(0, 0));
		
		IActividadController IAC = Fabrica.getInstance().getIActividadController();
		List<dataActividad> actividades = IAC.getAllActividades();

		String[] columnNames = { "Nombre", "Proveedor", "Departamento", "Precio Unitario" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
		
		JButton btnMnjAcc = new JButton("Aceptar/Rechazar Actividades");
		btnMnjAcc.setBounds(344, 480, 327, 47);
		btnMnjAcc.putClientProperty("JButton.buttonType", "roundRect");
		btnMnjAcc.putClientProperty("FlatLaf.styleClass", "h3");
		center.add(btnMnjAcc);

		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 130, 671, 337);
		center.add(scrollPane);

		table.setRowSorter(sorter);

		if (actividades != null) {
			for (dataActividad actividad : actividades) {
				String dept = actividad.getDepartamento().getNombre();
				String prov = actividad.getProveedor().getNickname();
				String nomb = actividad.getNombre();
				int preciouni = actividad.getCostoUni();
				model.addRow(new Object[] { nomb, prov, dept, preciouni });
			}
		}
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(e -> {
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow >= 0) {
		        String nombreActividad = (String) model.getValueAt(selectedRow, 0);
		        System.out.println("Actividad seleccionada: " + nombreActividad);
		    }
		});

		rechazar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            String nombreActividad = (String) model.getValueAt(selectedRow, 0);
		            System.out.println(nombreActividad + " ha sido rechazada");
		            try {
						IAC.modificarEstadoActividad(nombreActividad, estadoActividad.RECHAZADA);
					} catch (NoExisteExcepcion | ParametrosInvalidosExcepcion | YaExisteExcepcion e1) {
						// TODO Bloque catch generado automáticamente
						e1.printStackTrace();
					}
		        }
		        
			}
		});

		aceptar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            String nombreActividad = (String) model.getValueAt(selectedRow, 0);
		            System.out.println(nombreActividad + " ha sido aceptada");
		            try {
						IAC.modificarEstadoActividad(nombreActividad, estadoActividad.CONFIRMADA);
					} catch (NoExisteExcepcion | ParametrosInvalidosExcepcion | YaExisteExcepcion e1) {
						// TODO Bloque catch generado automáticamente
						e1.printStackTrace();
					}
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
