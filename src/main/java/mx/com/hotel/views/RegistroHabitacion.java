package mx.com.hotel.views;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import mx.com.hotel.jdbc.controller.HabitacionController;
import mx.com.hotel.modelo.Habitacion;

import javax.swing.JScrollPane;


public class RegistroHabitacion extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private JPanel contentPane;
	 private JLabel labelExit;
	 private JLabel labelAtras;
	 private JTable tabla;
	 private JTable tabla2;
	 private JTable tabla3;
	 private DefaultTableModel modelo;
	 private DefaultTableModel modelo2;
	 private DefaultTableModel modelo3;
	 private static JComboBox<Habitacion> SellectRoom;
	 private HabitacionController habitacionController;


	 int xMouse, yMouse;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHabitacion frame = new RegistroHabitacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public RegistroHabitacion() {
		this.habitacionController = new HabitacionController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHabitacion.class.getResource("/imagenes/lOGO-50PX.png")));
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
		
		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 590, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Guardar();
				dispose();
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 320, 635);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel logo = new JLabel("");
		logo.setBounds(80, 53, 150, 150);
		panel.add(logo);
		logo.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(60, 245, 201, 2);
		panel.add(separator);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(60, 560, 201, 2);
		panel.add(separator2);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);
		
		String textoDescripcion = "<html><body><center> <b> Hotel Alura </b> <br> <br> Habitaciones disponibles para reservar a los clientes </center></body></html>";
	    JLabel labelDescripcion = new JLabel(textoDescripcion);
	    panel.add(labelDescripcion);
	    labelDescripcion.setFont(new Font("Roboto", Font.PLAIN, 20));
	    labelDescripcion.setBounds(25, 290, 280, 120);
	    contentPane.add(panel);
	    
	    JLabel labelImg = new JLabel();
	    labelImg.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas.png")));
	    labelImg.setBounds(130, 455, 280, 55);
	    panel.add(labelImg);
	    contentPane.add(panel);
	    
	    String textoTitulo = "<html><body><center><h1> Habitaciones </h1></center></body></html>";
	    JLabel labelTitulo = new JLabel(textoTitulo);
	    labelTitulo.setFont(new Font("Roboto", Font.PLAIN, 20));
	    labelTitulo.setBounds(540, 40, 250, 30);
	    contentPane.add(labelTitulo);
	    
	    String textoHabitacion1 = "<html><body><center><h2> Individual </h2></center></body></html>";
	    JLabel labelHabitacion1 = new JLabel(textoHabitacion1);
	    labelHabitacion1.setFont(new Font("Roboto", Font.PLAIN, 20));
	    labelHabitacion1.setBounds(570, 100, 250, 30);
	    contentPane.add(labelHabitacion1);
	    
	    tabla = new JTable();
	    tabla.setFont(new Font("Roboto", Font.PLAIN, 16));
	    tabla.setRowHeight(20);
	    JScrollPane scroll =new JScrollPane(tabla);
	    scroll.setBounds(370, 160, 490, 50);
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.addColumn("CAMAS");
        modelo.addColumn("BAÑO");
        modelo.addColumn("COCINA");
        modelo.addColumn("TELEVISION");
        modelo.addColumn("BALCON");
        modelo.addColumn("PRECIO");
        
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().invalidate();
        getContentPane().validate();
        
        var tablaHabitaciones = this.habitacionController.listarHabitaciones().get(0);
        
        modelo.addRow(new Object[] {tablaHabitaciones.getCamas(), tablaHabitaciones.getBaño(),
        		tablaHabitaciones.getCocina(), tablaHabitaciones.getTelevision(), tablaHabitaciones.getBalcon(), tablaHabitaciones.getPrecio()});
        
        centrarTablas(tabla);
        
	    String textoHabitacion2 = "<html><body><center><h2> Familiar </h2></center></body></html>";
	    JLabel labelHabitacion2 = new JLabel(textoHabitacion2);
	    labelHabitacion2.setFont(new Font("Roboto", Font.PLAIN, 20));
	    labelHabitacion2.setBounds(575, 230, 250, 30);
	    contentPane.add(labelHabitacion2);
	    
	    tabla2 = new JTable();
	    tabla2.setFont(new Font("Roboto", Font.PLAIN, 16));
	    tabla2.setRowHeight(20);
	    JScrollPane scroll2 =new JScrollPane(tabla2);
	    scroll2.setBounds(370, 290, 490, 50);
        modelo2 = (DefaultTableModel) tabla2.getModel();
        modelo2.addColumn("CAMAS");
        modelo2.addColumn("BAÑO");
        modelo2.addColumn("COCINA");
        modelo2.addColumn("TELEVISION");
        modelo2.addColumn("BALCON");
        modelo2.addColumn("PRECIO");
        
        getContentPane().add(scroll2, BorderLayout.CENTER);
        getContentPane().invalidate();
        getContentPane().validate();
        
        var tablaHabitaciones2 = this.habitacionController.listarHabitaciones().get(1);
        
        modelo2.addRow(new Object[] {tablaHabitaciones2.getCamas(), tablaHabitaciones2.getBaño(),
        		tablaHabitaciones2.getCocina(), tablaHabitaciones2.getTelevision(), tablaHabitaciones2.getBalcon(), tablaHabitaciones2.getPrecio()});
         
        centrarTablas(tabla2);
        
	    String textoHabitacion3 = "<html><body><center><h2> Suit </h2></center></body></html>";
	    JLabel labelHabitacion3 = new JLabel(textoHabitacion3);
	    labelHabitacion3.setFont(new Font("Roboto", Font.PLAIN, 20));
	    labelHabitacion3.setBounds(590, 370, 250, 30);
	    contentPane.add(labelHabitacion3);
	    
	    tabla3 = new JTable();
	    tabla3.setFont(new Font("Roboto", Font.PLAIN, 16));
	    tabla3.setRowHeight(20);
	    JScrollPane scroll3 =new JScrollPane(tabla3);
	    scroll3.setBounds(370, 430, 490, 50);
        modelo3 = (DefaultTableModel) tabla3.getModel();
        modelo3.addColumn("CAMAS");
        modelo3.addColumn("BAÑO");
        modelo3.addColumn("COCINA");
        modelo3.addColumn("TELEVISION");
        modelo3.addColumn("BALCON");
        modelo3.addColumn("PRECIO");
        
        getContentPane().add(scroll3, BorderLayout.CENTER);
        getContentPane().invalidate();
        getContentPane().validate();
        
        var tablaHabitaciones3 = this.habitacionController.listarHabitaciones().get(2);
        
        modelo3.addRow(new Object[] {tablaHabitaciones3.getCamas(), tablaHabitaciones3.getBaño(),
        		tablaHabitaciones3.getCocina(), tablaHabitaciones3.getTelevision(), tablaHabitaciones3.getBalcon(), tablaHabitaciones3.getPrecio()});
        
        centrarTablas(tabla3);
        
        SellectRoom = new JComboBox<>();
        SellectRoom.setBounds(370, 520, 289, 38);
        SellectRoom.setBackground(SystemColor.text);
        SellectRoom.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        SellectRoom.setFont(new Font("Roboto", Font.PLAIN, 16));
        SellectRoom.addItem(new Habitacion(0,"Elige una habitacion",0));     
        var habitaciones = this.habitacionController.listar();
        habitaciones.forEach(habitacion -> SellectRoom.addItem(habitacion));
		contentPane.add(SellectRoom);
		
		final JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel();
		labelExit.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		labelExit.setForeground(SystemColor.black);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		final JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
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
		
		labelAtras = new JLabel();
		labelAtras = new JLabel("<");
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
	}
	
	public void Guardar() {
		var tHabitacion = (Habitacion) SellectRoom.getSelectedItem();
		ReservasView reservas = new ReservasView(tHabitacion.getPrecio(), tHabitacion.getId());
		System.out.println(tHabitacion.getId());
		reservas.setVisible(true);
	}
	
	private void centrarTablas(JTable tabla) {

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);

		for (int x = 0; x < tabla.getColumnCount(); x++) {
			tabla.getColumnModel().getColumn(x).setCellRenderer(tcr);
		}

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
