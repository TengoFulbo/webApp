package com.presentacion.vistas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.List;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDeepOceanIJTheme;
import com.presentacion.utilidades.PlaceholderTextField;
import turismouy.svcentral.Fabrica;
import turismouy.svcentral.datatypes.dataActividad;
import turismouy.svcentral.datatypes.dataCategoria;
import turismouy.svcentral.datatypes.dataDepartamento;
import turismouy.svcentral.datatypes.dataPaquete;
import turismouy.svcentral.datatypes.dataSalida;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IUsuarioController;
import turismouy.svcentral.utilidades.log;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class categoriasVista extends JPanel {

	private String selectedTheme;
	private PlaceholderTextField textBuscador;
	private String selectedPaquete;
	private String selectedSalida;

	public categoriasVista(String tema) {

		this.selectedTheme = selectedTheme;
		this.setSize(874, 577);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 874, 577);
		add(panel);
		panel.setLayout(null);

		JPanel center = new JPanel();
		center.setOpaque(false);
		center.setBounds(101, 11, 671, 527);
		panel.add(center);
		center.setLayout(null);

		JLabel titulo = new JLabel("Categorias");
		titulo.setBounds(0, 0, 671, 40);
		titulo.putClientProperty("FlatLaf.styleClass", "h1");
		center.add(titulo);

		JLabel descripcion = new JLabel("Lista y crea las Categorias que se manejen en el sistema");
		descripcion.setBounds(0, 39, 671, 40);
		center.add(descripcion);

		JButton btnNewCategoria = new JButton("Crear una nueva Categoria");
		btnNewCategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewCategoria.setBounds(0, 480, 671, 47);
		btnNewCategoria.putClientProperty("JButton.buttonType", "roundRect");
		btnNewCategoria.putClientProperty("FlatLaf.styleClass", "h3");
		center.add(btnNewCategoria);

		ICategoriaController ICC = Fabrica.getInstance().getICategoriaController();
		List<dataCategoria> categorias = ICC.listarCategorias();

		String[] columnNames = { "Nombre"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			boolean[] columnEditables = new boolean[] { false };
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 90, 671, 377);
		center.add(scrollPane);

		table.setRowSorter(sorter);

		if (categorias != null) {
			for (dataCategoria cat : categorias) {
				String nomb = cat.getNombre();
				model.addRow(new Object[] { nomb });
			}
		}

		JLabel wave = new JLabel("");
		wave.setIcon(new ImageIcon(principalVista.class.getResource("/com/presentacion/res/wave.png")));
		wave.setBounds(274, 435, 600, 344);
		add(wave);

		btnNewCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarCrearCategoria();

				model.setRowCount(0);

				List<dataCategoria> categorias = ICC.listarCategorias();

				if (categorias != null) {
					for (dataCategoria cat : categorias) {
						String nomb = cat.getNombre();
						model.addRow(new Object[] { nomb });
					}
				}
			}
		});

		setupTheme();
	}

	private void mostrarCrearCategoria() {
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

		IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();
		List<dataDepartamento> departamentos = IDC.listarDepartamentos();

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

	private void setupTheme() {
		if ("Light".equals(selectedTheme)) {
			applyLightTheme();
		} else if ("Dark".equals(selectedTheme)) {
			applyDarkTheme();
		} else if ("Cyan Light".equals(selectedTheme)) {
			applyCyanLightTheme();
		} else if ("Material Deep Ocean".equals(selectedTheme)) {
			applyDeepOceanTheme();
		} else if ("Github Dark".equals(selectedTheme)) {
			applyGithubDarkTheme();
		} else if ("AtomOne Dark".equals(selectedTheme)) {
			applyAODTheme();
		}
	}

	private void applyLightTheme() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}

	private void applyDarkTheme() {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}

	private void applyCyanLightTheme() {
		try {
			UIManager.setLookAndFeel(new FlatCyanLightIJTheme());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}

	private void applyDeepOceanTheme() {
		try {
			UIManager.setLookAndFeel(new FlatMaterialDeepOceanIJTheme());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}

	private void applyGithubDarkTheme() {
		try {
			UIManager.setLookAndFeel(new FlatGitHubDarkIJTheme());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}

	private void applyAODTheme() {
		try {
			UIManager.setLookAndFeel(new FlatAtomOneDarkIJTheme());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}
}
