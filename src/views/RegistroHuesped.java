package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {
	private static Date fechaEntrada;
    private static Date fechaSalida;
    private static float valorReserva;
    private static String formaPago;

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuesped frame = new RegistroHuesped(fechaEntrada, fechaEntrada, valorReserva, formaPago);
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
	public RegistroHuesped(Date fechaEntrada, Date fechaSalida, float valorReserva, String formaPago) {
		
		String[] nacionalidades = {
	            "Afgano/a", "Alemán/a", "Árabe", "Argentino/a", "Australiano/a", "Belga", "Boliviano/a",
	            "Brasileño/a", "Camboyano/a", "Canadiense", "Chileno/a", "Chino/a", "Colombiano/a",
	            "Coreano/a", "Costarricense", "Cubano/a", "Danés/a", "Ecuatoriano/a", "Egipcio/a",
	            "Salvadoreño/a", "Escocés/a", "Español/a", "Estadounidense", "Estonio/a", "Etíope",
	            "Filipino/a", "Finlandés/a", "Francés/a", "Galés/a", "Griego/a", "Guatemalteco/a",
	            "Haitiano/a", "Holandés/a", "Hondureño/a", "Indonesio/a", "Inglés/a", "Iraquí", "Iraní",
	            "Irlandés/a", "Israelí", "Italiano/a", "Japonés/a", "Jordano/a", "Laosiano/a", "Letón/a",
	            "Malayo/a", "Marroquí", "Mexicano/a", "Nicaragüense", "Noruego/a", "Neozelandés/a",
	            "Panameño/a", "Paraguayo/a", "Peruano/a", "Polaco/a", "Portugués/a", "Puertorriqueño/a",
	            "Dominicano/a", "Rumano/a", "Ruso/a", "Sueco/a", "Suizo/a", "Tailandés/a", "Taiwanés/a",
	            "Turco/a", "Ucraniano/a", "Uruguayo/a", "Venezolano/a", "Vietnamita",
	            };
		
		RegistroHuesped.fechaEntrada = fechaEntrada;
		RegistroHuesped.fechaSalida = fechaSalida;
		RegistroHuesped.valorReserva = valorReserva;
		RegistroHuesped.formaPago = formaPago;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color(12, 138, 199));
			     labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 135, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 204, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);
		
		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 278, 285, 36);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);
		
		txtNacionalidad = new JComboBox();
		txtNacionalidad.setBounds(560, 350, 289, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(nacionalidades));
		contentPane.add(txtNacionalidad);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 119, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);
		
		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 256, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);
		
		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);
		
		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 424, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);
		
		JLabel lblTitulo = new JLabel("REGISTRO HUESPED");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);
		
		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);
		
		txtNreserva = new JTextField();
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 495, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNreserva);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);
		
		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);
		
		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);
		
		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);
		
		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);
		
		JPanel btnGuardar = new JPanel();
		btnGuardar.setBounds(723, 560, 122, 35);
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    // Obtén los valores ingresados en los campos
			    String nombre = txtNombre.getText().trim();
			    String apellido = txtApellido.getText().trim();
			    Date fechaNacimientoSN = txtFechaN.getDate();
			    SimpleDateFormat formatoDeseado = new SimpleDateFormat("yyyy-MM-dd");
			    String fechaNacimiento = formatoDeseado.format(fechaNacimientoSN);
			    String nacionalidad = txtNacionalidad.getSelectedItem().toString().trim();
			    String telefono = txtTelefono.getText().trim();

			    // Verifica que todos los campos estén llenos
			    if (nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento == null || nacionalidad.isEmpty() || telefono.isEmpty()) {
			        JOptionPane.showMessageDialog(
			                null,
			                "Por favor, complete todos los campos antes de guardar los datos.",
			                "Campos Incompletos",
			                JOptionPane.WARNING_MESSAGE);
			    } else {
			        try {
			            // Configura la conexión a la base de datos (reemplaza con tus propios valores)
			            String url = "jdbc:mysql://localhost:3306/bd_hotel";
			            String usuario = "root";
			            String contraseña = "";

			            // Establece la conexión
			            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

			            // Consulta para obtener el último ID de reserva
			            String consultaUltimoID = "SELECT MAX(idReserva) FROM huespedes";
			            Statement stmt = conexion.createStatement();
			            ResultSet rs = stmt.executeQuery(consultaUltimoID);

			            int ultimoID = 0;
			            if (rs.next()) {
			                ultimoID = rs.getInt(1);
			            }

			            // Genera el nuevo ID de reserva sumando 1 al último ID
			            int nuevoID = ultimoID + 1;

			            // Genera el número de reserva con el formato "ALR-fecha actual-número generado"
			            
			            String idReserva = "ALR-" + nuevoID;

			            // Define la consulta SQL para insertar los datos en la tabla huéspedes
			            String sqlHuespedes = "INSERT INTO huespedes (Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, idReserva) VALUES (?, ?, ?, ?, ?, ?)";

			            // Prepara la sentencia SQL para huespedes
			            PreparedStatement pstmtHuespedes = conexion.prepareStatement(sqlHuespedes);

			            // Establece los valores de los parámetros en la consulta para huespedes
			            pstmtHuespedes.setString(1, nombre);
			            pstmtHuespedes.setString(2, apellido);
			            pstmtHuespedes.setString(3, fechaNacimiento.toString());
			            pstmtHuespedes.setString(4, nacionalidad);
			            pstmtHuespedes.setString(5, telefono);
			            pstmtHuespedes.setInt(6, nuevoID);

			            // Ejecuta la consulta para insertar los datos en la tabla huespedes
			            int filasAfectadasHuespedes = pstmtHuespedes.executeUpdate();

			            // Define la consulta SQL para insertar los datos en la tabla reserva
			            String sqlReserva = "INSERT INTO reserva (id, FechaEntrada, FechaSalida, Valor, FormaPago) VALUES (?, ?, ?, ?, ?)";

			            // Prepara la sentencia SQL para reserva
			            PreparedStatement pstmtReserva = conexion.prepareStatement(sqlReserva);
			            
			            // Aplicacion de formato a la fecha
			            String fechaEntradaF = formatoDeseado.format(fechaEntrada);
			            String fechaSalidaF = formatoDeseado.format(fechaSalida);

			            // Establece los valores de los parámetros en la consulta para reserva
			            pstmtReserva.setInt(1, nuevoID);
			            pstmtReserva.setString(2, fechaEntradaF.toString());
			            pstmtReserva.setString(3, fechaSalidaF.toString());
			            pstmtReserva.setFloat(4, valorReserva);
			            pstmtReserva.setString(5, formaPago);

			            // Ejecuta la consulta para insertar los datos en la tabla reserva
			            int filasAfectadasReserva = pstmtReserva.executeUpdate();

			            // Cierra la conexión y las sentencias preparadas
			            pstmtHuespedes.close();
			            pstmtReserva.close();
			            conexion.close();

			            if (filasAfectadasHuespedes > 0 && filasAfectadasReserva > 0) {
			                txtNreserva.setText(idReserva);
			                JOptionPane.showMessageDialog(null,
			                        "Datos guardados exitosamente.",
			                        "Guardado Exitoso",
			                        JOptionPane.INFORMATION_MESSAGE);
			            } else {
			                JOptionPane.showMessageDialog(null,
			                        "Error al guardar los datos.",
			                        "Error",
			                        JOptionPane.ERROR_MESSAGE);
			            }
			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null,
			                    "Error al conectar con la base de datos.",
			                    "Error de Conexión",
			                    JOptionPane.ERROR_MESSAGE);
			        }
			    }
			}
		});
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnGuardar);
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnGuardar.add(labelGuardar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
		
		JPanel btnExit = new JPanel();
		btnExit.setBounds(857, 0, 53, 36);
		contentPane.add(btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnExit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnExit.setLayout(null);
		btnExit.setBackground(Color.white);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnExit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
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
}}