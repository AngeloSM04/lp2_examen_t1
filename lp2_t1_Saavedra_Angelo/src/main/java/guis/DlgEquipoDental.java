package guis;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import model.Dentista;
import model.EquipoDental;

import utils.JPAUtil;

import javax.swing.JTextArea;
import java.awt.Font;

public class DlgEquipoDental extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNroEquipo;
	private JLabel lblNombreEquipo;
	private JLabel lblCosto;
	private JLabel lblEstado;
	private JLabel lblFechaAdquisicion;
	private JLabel lblDentista;
	private JTextField txtNroEquipo;
	private JTextField txtNombre;
	private JTextField txtCosto;
	private JComboBox<String> cboEstados;
	private JTextField txtFechaAdquisicion;
	private JComboBox<Object> cboDentistas;
	private JButton btnBuscar;
	private JButton btnOK;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnListar;
	private JScrollPane scrollPane;
	private JTextArea txtSalida;

	// Tipo de operaci�n a procesar: Adicionar, Consultar, Modificar o Eliminar
	private int tipoOperacion;

	// Constantes para los tipos de operaciones
	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgEquipoDental dialog = new DlgEquipoDental();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgEquipoDental() {
		setResizable(false);
		setTitle("Mantenimiento | Equipo Dental");
		setBounds(100, 100, 810, 604);
		getContentPane().setLayout(null);

		lblNroEquipo = new JLabel("Nro Equipo:");
		lblNroEquipo.setBounds(10, 10, 149, 23);
		getContentPane().add(lblNroEquipo);

		lblNombreEquipo = new JLabel("Nombre :");
		lblNombreEquipo.setBounds(10, 35, 149, 23);
		getContentPane().add(lblNombreEquipo);

		lblDentista = new JLabel("Dentista :");
		lblDentista.setBounds(10, 145, 149, 23);
		getContentPane().add(lblDentista);

		lblEstado = new JLabel("Estado :");
		lblEstado.setBounds(10, 88, 149, 23);
		getContentPane().add(lblEstado);

		txtNroEquipo = new JTextField();
		txtNroEquipo.setBounds(174, 10, 86, 23);
		getContentPane().add(txtNroEquipo);
		txtNroEquipo.setEditable(false);
		txtNroEquipo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(174, 35, 251, 23);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		lblCosto = new JLabel("Costo :");
		lblCosto.setBounds(10, 62, 149, 23);
		getContentPane().add(lblCosto);
		
		txtCosto = new JTextField();
		txtCosto.setEditable(false);
		txtCosto.setColumns(10);
		txtCosto.setBounds(174, 62, 86, 23);
		getContentPane().add(txtCosto);

		String[] estados = { "N", "A", "R", "S" };
		cboEstados = new JComboBox<String>();
		cboEstados.setBounds(174, 88, 86, 23);
		getContentPane().add(cboEstados);
		for (String estado : estados) {
			cboEstados.addItem(estado);
		}
		
		lblFechaAdquisicion = new JLabel("Fecha de adquisici\u00F3n:");
		lblFechaAdquisicion.setBounds(10, 116, 162, 20);
		getContentPane().add(lblFechaAdquisicion);

		txtFechaAdquisicion = new JTextField();
		txtFechaAdquisicion.setEditable(false);
		txtFechaAdquisicion.setBounds(174, 114, 146, 26);
		getContentPane().add(txtFechaAdquisicion);
		txtFechaAdquisicion.setColumns(10);
		
		cboDentistas = new JComboBox<Object>();
		cboDentistas.setBounds(174, 143, 251, 26);
		getContentPane().add(cboDentistas);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(324, 10, 101, 23);
		getContentPane().add(btnBuscar);

		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(430, 145, 100, 23);
		getContentPane().add(btnOK);

		btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(555, 10, 100, 75);
		getContentPane().add(btnOpciones);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(664, 10, 120, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(664, 36, 120, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(664, 62, 120, 23);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 775, 337);
		getContentPane().add(scrollPane);

		txtSalida = new JTextArea();
		txtSalida.setEditable(false);
		txtSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scrollPane.setViewportView(txtSalida);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(345, 525, 115, 29);
		getContentPane().add(btnListar);

		habilitarEntradas(false);
		habilitarBotones(true);
		cargarDentistas();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnListar) {
			actionPerformedBtnListar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(arg0);
		}
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		buscar();
	}

	protected void actionPerformedBtnOK(ActionEvent arg0) {
		switch (tipoOperacion) {
		case ADICIONAR:
			adicionar();
			break;
		case MODIFICAR:
			modificar();
			break;
		case ELIMINAR:
			eliminar();
		}
	}

	protected void actionPerformedBtnOpciones(ActionEvent arg0) {
		limpiar();
	}

	protected void actionPerformedBtnListar(ActionEvent arg0) {
		listar();
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		habilitarEntradas(true);
		habilitarBotones(false);
		txtNombre.requestFocus();
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		tipoOperacion = MODIFICAR;
		txtNroEquipo.setEditable(true);
		habilitarBotones(false);
		txtNroEquipo.requestFocus();
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		tipoOperacion = ELIMINAR;
		txtNroEquipo.setEditable(true);
		habilitarBotones(false);
		txtNroEquipo.requestFocus();
	}

	void cargarDentistas() {
		  EntityManager em = JPAUtil.getEntityManager();
		    try {
		        String jpql = "SELECT den FROM Dentista den";
		        List<Dentista> dentista = em.createQuery(jpql, Dentista.class).getResultList();
		        cboDentistas.removeAllItems();
		        for (Dentista den : dentista) {
		            cboDentistas.addItem(den); 
		        }
		    } finally {
		        em.close();
		    }
	}

	void listar() {
	//	 String jpql = "select eq, den.dentista, esp.especialidad "
	  //              + "from EquipoDental eq "
	    //            + "join den.dentista eq "
	     //           + "join den.especialidad esp "
	   //             + "order by eq.nro_equipo";

		  //  EntityManager manager = JPAUtil.getEntityManager();
		  //
		  // List<Object[]> resultados = manager.createQuery(jpql, Object[].class).getResultList();

		  //   txtSalida.setText("");
		  //
		  //   try {
		  //     for (Object[] objects : resultados) {
	        	  //          EquipoDental equipoDent = (EquipoDental) objects[0];
		  //        String descDentista = (String) objects[1];
	            //        String descEspecialidad = (String) objects[2];

	            //       imprimir("Nro Equipo........: " + equipoDent.getNroEquipo());
		  //       imprimir("Nombre ...........: " + equipoDent.getNombre());
	            //        imprimir("Costo.........: " + equipoDent.getCosto());
		  //      imprimir("Fecha de adquisición.....: " + equipoDent.getFechaAdquisicion());
	            //      imprimir("Dentista...........: " + equipoDent.getDentista().getNombreCompleto()+" - "+descDentista);
		  //      imprimir("Correo....: " + (equipoDent.getDentista().getCorreo()));
		  //      imprimir("Especialidad....: " + (equipoDent.getDentista().getEspecialidad())); 
		  //       imprimir("*******************************************");
	            //      //!= null ? solicitud.getFechaReg().toString() : ""))
		  //      }
	  //   } finally {
	    	  //       manager.close();
		  //   }
	}

	void adicionar() {
		  EntityManager em = JPAUtil.getEntityManager();
		    try {
		        em.getTransaction().begin();
		        EquipoDental s = new EquipoDental();
		        s.setNombre(txtNombre.getText().trim());
		        s.setEstado((String) cboEstados.getSelectedItem());
		        s.setDentista((Dentista) cboDentistas.getSelectedItem());
		        s.setCosto(Double.parseDouble(txtCosto.getText()));
		      
		      
		        em.persist(s);
		        em.getTransaction().commit();

		        mensajeInfo("Solicitud registrada");
		        limpiar();
		    } catch (Exception e) {
		        em.getTransaction().rollback();
		        mensajeError("Error al registrar: " + e.getMessage());
		    } finally {
		        em.close();
		    }
	}
	
	void buscar() {
		 int nro;
		    try {
		        nro = Integer.parseInt(txtNroEquipo.getText().trim());
		    } catch (Exception e) {
		        mensajeError("Ingrese un número válido.");
		        return;
		    }
		    EntityManager em = JPAUtil.getEntityManager();
		    try {
		        EquipoDental s = em.find(EquipoDental.class, nro);
		        if (s == null) {
		            mensajeError("Solicitud no encontrada");
		            limpiar();
		            return;
		        }
		        txtNroEquipo.setText(String.valueOf(s.getNroEquipo()));
		        txtFechaAdquisicion.setText(s.getFechaAdquisicion()!= null ? s.getFechaAdquisicion().toString() : "");
		        cboDentistas.setSelectedItem(s.getDentista());
		        cboEstados.setSelectedItem(s.getEstado());
		        txtCosto.setText(Double.toString(s.getCosto()));
		        txtNombre.setText(s.getNombre());
		        

		        // Selecciona la actividad correcta en el combo
		        for (int i = 0; i < cboDentistas.getItemCount(); i++) {
		            Dentista act = (Dentista) cboDentistas.getItemAt(i);
		            if (act.getIdDentista() == s.getDentista().getIdDentista()) {
		                cboDentistas.setSelectedIndex(i);
		                break;
		            }
		        }
		        habilitarOk();
		    } finally {
		        em.close();
		    }
	}

	void modificar() {
		int nro;
	    try {
	        nro = Integer.parseInt(txtNroEquipo.getText().trim());
	    } catch (Exception e) {
	        mensajeError("Ingrese un número válido.");
	        return;
	    }
	    EntityManager em = JPAUtil.getEntityManager();
	    try {
	        em.getTransaction().begin();
	        EquipoDental s = em.find(EquipoDental.class, nro);
	        if (s == null) {
	            mensajeError("Solicitud no encontrada");
	            em.getTransaction().rollback();
	            return;
	        }
	        txtFechaAdquisicion.setText(s.getFechaAdquisicion()!= null ? s.getFechaAdquisicion().toString() : "");
	        cboDentistas.setSelectedItem(s.getDentista());
	        cboEstados.setSelectedItem(s.getEstado());
	        txtNombre.setText(s.getNombre());
	        txtCosto.setText(Double.toString(s.getCosto()));
	        
	        em.getTransaction().commit();

	        mensajeInfo("Solicitud actualizada");
	        limpiar();
	    } catch (Exception e) {
	        em.getTransaction().rollback();
	        mensajeError("Error al modificar: " + e.getMessage());
	    } finally {
	        em.close();
	    }
	}

	void eliminar() {
		 int nro;
		    try {
		        nro = Integer.parseInt(txtNroEquipo.getText().trim());
		    } catch (Exception e) {
		        mensajeError("Ingrese un número válido.");
		        return;
		    }
		    EntityManager em = JPAUtil.getEntityManager();
		    try {
		        em.getTransaction().begin();
		        EquipoDental s = em.find(EquipoDental.class, nro);
		        if (s == null) {
		            mensajeError("Solicitud no encontrada");
		            em.getTransaction().rollback();
		            return;
		        }
		        em.remove(s);
		        em.getTransaction().commit();

		        mensajeInfo("Solicitud eliminada");
		        limpiar();
		    } catch (Exception e) {
		        em.getTransaction().rollback();
		        mensajeError("Error al eliminar: " + e.getMessage());
		    } finally {
		        em.close();
		    }
	}

	// M�todos tipo void (con par�metros)
	void habilitarEntradas(boolean sino) {
		txtNombre.setEditable(sino);
		txtCosto.setEditable(sino);
		cboDentistas.setEnabled(sino);
		cboEstados.setEnabled(sino);
	}

	void habilitarBotones(boolean sino) {
		if (tipoOperacion == ADICIONAR)
			btnOK.setEnabled(!sino);
		else {
			btnBuscar.setEnabled(!sino);
			btnOK.setEnabled(false);
		}
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnEliminar.setEnabled(sino);
		btnOpciones.setEnabled(!sino);
	}

	void habilitarOk() {
		if (tipoOperacion == MODIFICAR) {
			habilitarEntradas(true);
			txtNroEquipo.setEditable(false);
			txtFechaAdquisicion.setEditable(true);
			btnBuscar.setEnabled(false);
			btnOK.setEnabled(true);
			txtNombre.requestFocus();
		}
		if (tipoOperacion == ELIMINAR) {
			txtNroEquipo.setEditable(false);
			btnBuscar.setEnabled(false);
			btnOK.setEnabled(true);
		}
	}

	void mensajeInfo(String msj) {
		mensaje(msj, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}

	void mensajeError(String msj) {
		mensaje(msj, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	void mensaje(String msj, String titulo, int tipo) {
		JOptionPane.showMessageDialog(this, msj, titulo, tipo);
	}

	void imprimir(String texto) {
		txtSalida.append(texto + "\n");
	}

	void imprimir() {
		imprimir("");
	}

	void limpiar() {
		txtNroEquipo.setText("");
		txtNombre.setText("");
		txtCosto.setText("");
		cboEstados.setSelectedIndex(0);
		txtFechaAdquisicion.setText("");
		if (cboDentistas.getItemCount() > 0)
			cboDentistas.setSelectedIndex(0);
		txtNroEquipo.setEditable(false);
		txtFechaAdquisicion.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);
	}
}