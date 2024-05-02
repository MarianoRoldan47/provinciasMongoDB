package provinciasMongoDB.vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTextField;

import provinciasMongoDB.controllers.ControladorCcaa;
import provinciasMongoDB.controllers.ControladorProvincia;
import provinciasMongoDB.model.Ccaa;
import provinciasMongoDB.model.Provincia;
import provinciasMongoDB.utils.UtilsBBDD;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelProvincia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCode;
	private JTextField jtfNombre;
	private JComboBox<Ccaa> jcbCcaa;

	/**
	 * Create the panel.
	 */
	public PanelProvincia() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 278, 100, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Gestión de provincia");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Code: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfCode = new JTextField();
		jtfCode.setEnabled(false);
		GridBagConstraints gbc_jtfCode = new GridBagConstraints();
		gbc_jtfCode.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCode.gridx = 1;
		gbc_jtfCode.gridy = 3;
		add(jtfCode, gbc_jtfCode);
		jtfCode.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 5);
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 1;
		gbc_jtfNombre.gridy = 5;
		add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ccaa: ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 7;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		jcbCcaa = new JComboBox<Ccaa>();
		GridBagConstraints gbc_jcbCcaa = new GridBagConstraints();
		gbc_jcbCcaa.insets = new Insets(0, 0, 5, 5);
		gbc_jcbCcaa.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbCcaa.gridx = 1;
		gbc_jcbCcaa.gridy = 7;
		add(jcbCcaa, gbc_jcbCcaa);
		
		JButton btnGestionCcaa = new JButton("GESTION CCAA ");
		btnGestionCcaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilsBBDD.crearJDialog(new PanelCcaa(ControladorCcaa.getCcaa(Integer.parseInt(((Ccaa)jcbCcaa.getSelectedItem()).getCode()))), "Gestion ccaa");
			}
		});
		GridBagConstraints gbc_btnGestionCcaa = new GridBagConstraints();
		gbc_btnGestionCcaa.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionCcaa.gridx = 2;
		gbc_btnGestionCcaa.gridy = 7;
		add(btnGestionCcaa, gbc_btnGestionCcaa);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.gridwidth = 3;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 0;
		gbc_btnGuardar.gridy = 9;
		add(btnGuardar, gbc_btnGuardar);
		
		cargarCcaa();
	}
	
	private void guardar() {
		Provincia p = new Provincia() ;
		
		p.setCode(jtfCode.getText());
		p.setLabel(jtfNombre.getText());
		p.setParent_code(((Ccaa)jcbCcaa.getSelectedItem()).getCode());
		
		ControladorProvincia.updateDocument(p);
	}
	
	private void cargarCcaa() {
		List<Ccaa> c = ControladorCcaa.getAllProvincias();
		
		for (Ccaa ccaa : c) {
			jcbCcaa.addItem(ccaa);
		}
	}
	
	public void mostrarEntidad(Provincia p) {
		if (p != null) {
			jtfCode.setText(p.getCode());
			jtfNombre.setText(p.getLabel());
			for (int i = 0; i < jcbCcaa.getItemCount(); i++) {
				if (jcbCcaa.getItemAt(i).getCode().equals(p.getParent_code())) {
					jcbCcaa.setSelectedIndex(i);
				}
			}
		}
	}
	
	

}
