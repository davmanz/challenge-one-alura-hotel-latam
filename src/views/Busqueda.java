package views;

import views.ConexionMySql;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings({ "serial", "unused" })
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private int indicePestaña;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		/*********************************************************/
		
		//Guardan el indice de la pestania selecionada
		
		panel.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        indicePestaña = panel.getSelectedIndex();
		        txtBuscar.setText("");
		    }
		});

		/********************************************************/
				
		JPanel btnExit = new JPanel();
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnExit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnExit.setLayout(null);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(857, 0, 53, 36);
		header.add(btnExit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnExit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnBuscar = new JPanel();
		
		/************************************************************/
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
			    // Obtener el texto de búsqueda
			    String textoBusqueda = txtBuscar.getText().trim();

			    // Determinar la pestaña actual
			    int pestañaActual = indicePestaña;

			    // Limpiar la tabla antes de agregar nuevos datos
			    DefaultTableModel modelo = (DefaultTableModel) (pestañaActual == 0 ? tbReservas : tbHuespedes).getModel();
			    modelo.setRowCount(0);

			    // Realizar la consulta SQL correspondiente
			    String consultaSQL = (pestañaActual == 0) ?
			            "SELECT * FROM reserva WHERE id LIKE ?" :
			            "SELECT * FROM huespedes WHERE Apellido LIKE ?";

			    int resultadosEncontrados = 0; // Variable para rastrear el número de resultados encontrados

			    try (Connection conexion = ConexionMySql.obtenerConexion();
			         PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {
			        consulta.setString(1, "%" + textoBusqueda + "%");
			        ResultSet resultado = consulta.executeQuery();

			        // Agregar los resultados de la consulta a la tabla
			        while (resultado.next()) {
			            Object[] fila = new Object[modelo.getColumnCount()];
			            for (int i = 1; i <= modelo.getColumnCount(); i++) {
			                fila[i - 1] = resultado.getObject(i);
			            }
			            modelo.addRow(fila);
			            resultadosEncontrados++; // Incrementar el contador de resultados encontrados
			        }
			        
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }

			    // Mostrar un mensaje si no se encontraron resultados
			    if (resultadosEncontrados == 0) {
			        // Aquí puedes mostrar un JOptionPane, JLabel u otro componente para mostrar el mensaje de aviso
			        // Por ejemplo, puedes usar JOptionPane.showMessageDialog o configurar un JLabel en la interfaz
			        JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
			    }
			}
        });
		/************************************************************/
		btnBuscar.setLayout(null);
		btnBuscar.setBackground(new Color(12, 138, 199));
		btnBuscar.setBounds(748, 125, 122, 35);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnBuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		
		btnEditar.addMouseListener(new MouseAdapter() {
	
			public void mouseClicked(MouseEvent e) {

				int filaSeleccionada = (indicePestaña == 0) ? tbReservas.getSelectedRow() : tbHuespedes.getSelectedRow();

		        if (filaSeleccionada == -1) {
		            // Asegúrate de que se haya seleccionado una fila
		            JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String numeroReserva = (indicePestaña == 0) ?
		                tbReservas.getValueAt(filaSeleccionada, 0).toString() :
		                tbHuespedes.getValueAt(filaSeleccionada, 6).toString();

		        if (indicePestaña == 0) {
		            editarReserva(numeroReserva);
		        } else {
		            editarHuespedes(numeroReserva);
		        }
		    }

			private void editarHuespedes(String numeroReserva) {
			    String consultaSQL = "SELECT * FROM huespedes WHERE idReserva = ?";
			    try (Connection conexion = ConexionMySql.obtenerConexion();
			         PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {
			        consulta.setString(1, numeroReserva);
			        ResultSet resultado = consulta.executeQuery();

			        if (resultado.next()) {
			            JFrame ventanaEdicion = new JFrame("Editar Huésped");
			            ventanaEdicion.setSize(400, 300);

			            JPanel panel = new JPanel(new GridBagLayout());
			            GridBagConstraints gbc = new GridBagConstraints();
			            gbc.insets = new Insets(5, 5, 5, 5);

			            JTextField txtNombre = new JTextField(resultado.getString("Nombre"));
			            JTextField txtApellido = new JTextField(resultado.getString("Apellido"));
			            JTextField txtNacimiento = new JTextField(resultado.getString("FechaNacimiento"));
			            JTextField txtNacionalidad = new JTextField(resultado.getString("Nacionalidad"));
			            JTextField txtTelefono = new JTextField(resultado.getString("Telefono"));
			            JTextField txtIdReserva = new JTextField(resultado.getString("idReserva"));
			            txtIdReserva.setEnabled(false);

			            JButton btnGuardar = new JButton("Guardar Cambios");
			            btnGuardar.addActionListener(new ActionListener() {
			                @Override
			                public void actionPerformed(ActionEvent e) {
			                    try (Connection conexionActualizacion = ConexionMySql.obtenerConexion();
			                         PreparedStatement actualizacion = conexionActualizacion.prepareStatement(
			                             "UPDATE huespedes SET Nombre = ?, Apellido = ?, FechaNacimiento = ?, Nacionalidad = ?, Telefono = ?, idReserva = ? WHERE idReserva = ?")) {
			                        actualizacion.setString(1, txtNombre.getText());
			                        actualizacion.setString(2, txtApellido.getText());
			                        actualizacion.setString(3, txtNacimiento.getText());
			                        actualizacion.setString(4, txtNacionalidad.getText());
			                        actualizacion.setString(5, txtTelefono.getText());
			                        actualizacion.setString(6, txtIdReserva.getText());
			                        actualizacion.setString(7, numeroReserva);

			                        actualizacion.executeUpdate();

			                        JOptionPane.showMessageDialog(null, "Los cambios se han guardado correctamente.");
			                        ventanaEdicion.dispose();
			                    } catch (SQLException ex) {
			                        ex.printStackTrace();
			                        JOptionPane.showMessageDialog(null, "Error al guardar los cambios.");
			                    }
			                }
			            });

			            // Configura GridBagConstraints para centrar componentes
			            gbc.anchor = GridBagConstraints.WEST;
			            gbc.fill = GridBagConstraints.HORIZONTAL;

			            gbc.gridx = 0;
			            gbc.gridy = 0;
			            panel.add(new JLabel("Nombre:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtNombre, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 1;
			            panel.add(new JLabel("Apellido:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtApellido, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 2;
			            panel.add(new JLabel("Fecha de Nacimiento:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtNacimiento, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 3;
			            panel.add(new JLabel("Nacionalidad:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtNacionalidad, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 4;
			            panel.add(new JLabel("Teléfono:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtTelefono, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 5;
			            panel.add(new JLabel("ID de Reserva:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtIdReserva, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 6;
			            gbc.gridwidth = 2;
			            panel.add(btnGuardar, gbc);

			            ventanaEdicion.getContentPane().add(panel);
			            ventanaEdicion.setVisible(true);
			        }
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error al obtener los datos del huésped.");
			    }
			}


			private void editarReserva(String numeroReserva) {
			    String consultaSQL = "SELECT * FROM reserva WHERE id = ?";
			    try (Connection conexion = ConexionMySql.obtenerConexion();
			         PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {
			        consulta.setString(1, numeroReserva);
			        ResultSet resultado = consulta.executeQuery();

			        if (resultado.next()) {
			            JFrame ventanaEdicion = new JFrame("Editar Reserva");
			            ventanaEdicion.setSize(400, 300);

			            JPanel panel = new JPanel(new GridBagLayout());
			            GridBagConstraints gbc = new GridBagConstraints();
			            gbc.insets = new Insets(5, 5, 5, 5);

			            JTextField txtFechaEntrada = new JTextField(resultado.getString("FechaEntrada"));
			            JTextField txtFechaSalida = new JTextField(resultado.getString("FechaSalida"));
			            JTextField txtValor = new JTextField(resultado.getString("Valor"));
			            JTextField txtFormaPago = new JTextField(resultado.getString("FormaPago"));

			            JButton btnGuardar = new JButton("Guardar Cambios");
			            btnGuardar.addActionListener(new ActionListener() {
			                @Override
			                public void actionPerformed(ActionEvent e) {
			                    try (Connection conexionActualizacion = ConexionMySql.obtenerConexion();
			                         PreparedStatement actualizacion = conexionActualizacion.prepareStatement(
			                             "UPDATE reserva SET FechaEntrada = ?, FechaSalida = ?, Valor = ?, FormaPago = ? WHERE id = ?")) {
			                        actualizacion.setString(1, txtFechaEntrada.getText());
			                        actualizacion.setString(2, txtFechaSalida.getText());
			                        actualizacion.setString(3, txtValor.getText());
			                        actualizacion.setString(4, txtFormaPago.getText());
			                        actualizacion.setString(5, numeroReserva);
			                        actualizacion.executeUpdate();

			                        JOptionPane.showMessageDialog(null, "Los cambios se han guardado correctamente.");
			                        ventanaEdicion.dispose();
			                    } catch (SQLException ex) {
			                        ex.printStackTrace();
			                        JOptionPane.showMessageDialog(null, "Error al guardar los cambios.");
			                    }
			                }
			            });

			            // Configura GridBagConstraints para centrar componentes
			            gbc.anchor = GridBagConstraints.WEST;
			            gbc.fill = GridBagConstraints.HORIZONTAL;

			            gbc.gridx = 0;
			            gbc.gridy = 0;
			            panel.add(new JLabel("Fecha de Entrada:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtFechaEntrada, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 1;
			            panel.add(new JLabel("Fecha de Salida:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtFechaSalida, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 2;
			            panel.add(new JLabel("Valor:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtValor, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 3;
			            panel.add(new JLabel("Forma de Pago:"), gbc);

			            gbc.gridx = 1;
			            panel.add(txtFormaPago, gbc);

			            gbc.gridx = 0;
			            gbc.gridy = 4;
			            gbc.gridwidth = 2;
			            panel.add(btnGuardar, gbc);

			            ventanaEdicion.getContentPane().add(panel);
			            ventanaEdicion.setVisible(true);
			        }
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error al obtener los datos de la reserva.");
			    }
			}
		});	
		
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
	
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();

		btnEliminar.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int filaSeleccionada = (indicePestaña == 0) ? tbReservas.getSelectedRow() : tbHuespedes.getSelectedRow();

		        if (filaSeleccionada == -1) {
		            // Asegúrate de que se haya seleccionado una fila
		            JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar este registro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

		        if (confirmacion == JOptionPane.YES_OPTION) {
		            if (indicePestaña == 0) {
		                borrarReserva(filaSeleccionada);
		            } else {
		                borrarHuesped(filaSeleccionada);
		            }
		        }
		    }
		    // Función para borrar una reserva y su huésped asociado
		    private void borrarReserva(int filaSeleccionada) {
		        String numeroReserva = tbReservas.getValueAt(filaSeleccionada, 0).toString();
		        
		        // Realizar la eliminación en la base de datos
		        try (Connection conexion = ConexionMySql.obtenerConexion();
		             PreparedStatement eliminacionReserva = conexion.prepareStatement("DELETE FROM reserva WHERE id = ?");
		             PreparedStatement eliminacionHuesped = conexion.prepareStatement("DELETE FROM huespedes WHERE idReserva = ?")) {
		            eliminacionReserva.setString(1, numeroReserva);
		            eliminacionHuesped.setString(1, numeroReserva);

		            int filasReserva = eliminacionReserva.executeUpdate();
		            int filasHuesped = eliminacionHuesped.executeUpdate();
		         

		            if (filasReserva > 0 && filasHuesped > 0) {
		                JOptionPane.showMessageDialog(null, "El registro de reserva y su huésped se han eliminado correctamente.");
		                // Actualiza la tabla para reflejar los cambios
		                modelo.removeRow(filaSeleccionada);
		                modeloHuesped.removeRow(filaSeleccionada);
		            } else {
		                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro de reserva y su huésped.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al eliminar el registro de reserva y su huésped.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }

		    // Función para borrar un huésped y su reserva asociada
		    private void borrarHuesped(int filaSeleccionada) {
		        String numeroHuesped = tbHuespedes.getValueAt(filaSeleccionada, 0).toString();
		        		        
		        // Realizar la eliminación en la base de datos
		        try (Connection conexion = ConexionMySql.obtenerConexion();
		        		
		             PreparedStatement eliminacionHuesped = conexion.prepareStatement("DELETE FROM huespedes WHERE id = ?");
		             PreparedStatement eliminacionReserva = conexion.prepareStatement("DELETE FROM reserva WHERE id = (SELECT idReserva FROM huespedes WHERE id = ?)")){
		            
		        	eliminacionHuesped.setString(1, numeroHuesped);
		            eliminacionReserva.setString(1, numeroHuesped);
		            
		            int filasReserva = eliminacionReserva.executeUpdate();
		            int filasHuesped = eliminacionHuesped.executeUpdate();
		            
		            if (filasHuesped > 0 && filasReserva > 0) {
		                JOptionPane.showMessageDialog(null, "El registro de huésped y su reserva se han eliminado correctamente.");
		                
		                // Actualiza la tabla para reflejar los cambios
		                modelo.removeRow(filaSeleccionada);
		                modeloHuesped.removeRow(filaSeleccionada);
		            } else {
		                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro de huésped y su reserva.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al eliminar el registro de huésped y su reserva.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }

		});
		
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
