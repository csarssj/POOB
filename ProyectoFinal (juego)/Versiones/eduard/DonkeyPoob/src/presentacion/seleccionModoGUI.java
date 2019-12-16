package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import aplicacion.*;

public class seleccionModoGUI extends JFrame{
	
	private JPanelB panelPantalla;
	private JPanel	panelBotones, panelLogo;
	private myButton single;
	private myButton  coop;
	private myButton  jVsCpu;
	private myButton  volverBoton,volverBoton2J,volverBotonPc;
	private myButton  jugarBoton1J;
	private myButton  jugarBoton2J;
	private myButton  jugarBotonPc;
	private myButton  cancelarBoton;
	private JCheckBox barrilVerde;
	private JCheckBox barrilAmarillo;
	private JCheckBox barrilRojo;
	private JCheckBox barrilAzul;
	private JCheckBox barrilVerde2;
	private JCheckBox barrilAmarillo2;
	private JCheckBox barrilRojo2;
	private JCheckBox barrilAzul2;
	private JCheckBox barrilVerde3;
	private JCheckBox barrilAmarillo3;
	private JCheckBox barrilRojo3;
	private JCheckBox barrilAzul3;
	private JCheckBox sorpresas;
	private JCheckBox sorpresas2;
	private JCheckBox sorpresas3;
	private JLabel logo;
	private Container contenedor;
	private JComboBox<String> selecColorPlayer,selecColorPeach,selecColorPeachVS,selecColorPeachCPU,selecTipo,selecColorPlayerJu2,selecColorPlayerJu1CPU,selecColorPlayerJu1VS,selecColorPlayerCPU,selecPlayerJu1,selecPlayerJu1CPU,selecPlayerJu1VS,selecPlayerCPU,selecPlayerJu2;
	private JTextField nameJ1,nameP1,nameJu1;
	private JTextField nameJ2;
	private JPanel cards;
	private static final String []personaje = { "Luigi","Mario"};
	private static final String []tipos = { "Temeroso", "Protector","Mimo"};
	private String[] coloresPeach = {"Rosado","Amarillo","Verde"};
	private String[] coloresPersonaje = {"Original","Amarillo","Azul","Morado","Verde","Rojo"};
	private String[] op = {"Si","No"};
	
	public seleccionModoGUI(){
		super("Seleccion Modo");
		contenedor = getContentPane();
		setResizable(false);
		prepareElementos();
		prepareAcciones();
		prepareAccionesConfig();
	}
	
	public void prepareElementos(){
		ajusteFrame();
		prepareElementosPantalla();
		prepareElementosConfig1J();
		prepareElementosConfig2J();
		prepareElementosConfigPc();
	}
	
	public void ajusteFrame(){
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		int height=250;
		int width=700;
		setSize(width,height);
		int centerX = size.width/2;
		int centerY = size.height/2;
		int halfWidth = width/2;
		int halfHeight = height-125;
		setLocation(centerX - halfWidth, centerY - halfHeight);
	}
	
	public void ajusteFrameConfig(){
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		int height=700;
		int width=600;
		setSize(width,height);
		int centerX = size.width/2;
		int centerY = size.height/2;
		int halfWidth = width/2;
		int halfHeight = height-340;
		setLocation(centerX - halfWidth, centerY - halfHeight);
	}
	
	public void prepareElementosPantalla(){
		setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());
		panelPantalla = new JPanelB();
		panelPantalla.setLayout(new GridLayout(2,1));
		panelPantalla.setBackground(new ImageIcon(getClass().getResource("/resources/Inicio.png")));
		contenedor.add(panelPantalla);
		
		panelLogo = new JPanel(new GridBagLayout());
		logo = new JLabel(new ImageIcon(getClass().getResource("/resources/FUENTE.png")));
		panelLogo.add(logo);
		panelPantalla.add(panelLogo);
		panelLogo.setOpaque(false);
		
		panelBotones = new JPanel(new GridLayout(2,3));
		panelBotones.setOpaque(false);
		Icon icono1j = new ImageIcon(getClass().getResource("/resources/1j.png"));
		single = new myButton(icono1j);
		single.setTransparent();
		Icon icono2j = new ImageIcon(getClass().getResource("/resources/2j.png"));
		coop = new myButton(icono2j);
		coop.setTransparent();
		Icon iconoJVC = new ImageIcon(getClass().getResource("/resources/jvc.png"));
		jVsCpu = new myButton(iconoJVC);
		jVsCpu.setTransparent();
		Icon iconot = new ImageIcon(getClass().getResource("/resources/Inicio.png"));
		myButton t = new myButton(iconot);
		t.setTransparent();
		Icon iconoCancelar = new ImageIcon(getClass().getResource("/resources/volver.png"));
		cancelarBoton = new myButton(iconoCancelar);
		cancelarBoton.setTransparent();
		
		panelBotones.add(single);
		panelBotones.add(coop);
		panelBotones.add(jVsCpu);
		panelBotones.add(t);
		panelBotones.add(cancelarBoton);
		
		panelPantalla.add(panelBotones);
		
		cards = new JPanel(new CardLayout());
		cards.add(panelPantalla, "Principal");
		contenedor.add(cards);
	}
private void preparebarrilesJugador1(JPanel contenido) {
		
		JLabel barriles = new JLabel("Elementos:");
		barriles.setForeground(Color.WHITE);
		barriles.setBounds(355, 15, 80, 18);
		contenido.add(barriles);
		
		barrilVerde = new JCheckBox("Verde");
		barrilAzul = new JCheckBox("Azul");
		barrilAmarillo = new JCheckBox("Amarillo");
		barrilRojo = new JCheckBox("rojo");
		sorpresas = new JCheckBox("Sorpresas");
		
		barrilVerde.setBounds(355, 35, 80, 20);
		barrilAzul.setBounds(355, 55, 80, 20);
		barrilAmarillo.setBounds(355, 75, 80, 20);
		barrilRojo.setBounds(355, 95, 80, 20);
		sorpresas.setBounds(355, 115, 80, 20);
		barrilVerde.setOpaque(false);
		barrilAzul.setOpaque(false);
		barrilAmarillo.setOpaque(false);
		barrilRojo.setOpaque(false);
		sorpresas.setOpaque(false);
		
		contenido.add(barrilVerde);
		contenido.add(barrilAzul);
		contenido.add(barrilAmarillo);
		contenido.add(barrilRojo);
		contenido.add(sorpresas);
	}
	
	private void preparebarrilesJugador2(JPanel contenido) {
		JLabel barriles = new JLabel("Elementos;");
		barriles.setForeground(Color.WHITE);
		barriles.setBounds(25, 175, 80, 18);
		contenido.add(barriles);
		
		barrilVerde2 = new JCheckBox("Verde");
		barrilAzul2 = new JCheckBox("Azul");
		barrilAmarillo2 = new JCheckBox("Amarillo");
		barrilRojo2 = new JCheckBox("rojo");
		sorpresas2 = new JCheckBox("Sorpresas");
		
		barrilVerde2.setBounds(105, 175, 80, 20);
		barrilAzul2.setBounds(185, 175, 80, 20);
		barrilAmarillo2.setBounds(265, 175, 80, 20);
		barrilRojo2.setBounds(345, 175, 80, 20);
		barrilVerde2.setBounds(425, 175, 80, 20);
		barrilVerde2.setOpaque(false);
		barrilAzul2.setOpaque(false);
		barrilAmarillo2.setOpaque(false);
		barrilRojo2.setOpaque(false);
		sorpresas2.setOpaque(false);
		
		contenido.add(barrilVerde2);
		contenido.add(barrilAzul2);
		contenido.add(barrilAmarillo2);
		contenido.add(barrilRojo2);
		contenido.add(sorpresas2);
	}
private void preparebarrilesJugadorPC(JPanel contenido) {
		
		JLabel barriles = new JLabel("Elementos:");
		barriles.setForeground(Color.WHITE);
		barriles.setBounds(25, 175, 80, 18);
		contenido.add(barriles);
		
		barrilVerde3 = new JCheckBox("Verde");
		barrilAzul3 = new JCheckBox("Azul");
		barrilAmarillo3 = new JCheckBox("Amarillo");
		barrilRojo3 = new JCheckBox("rojo");
		sorpresas3 = new JCheckBox("Sorpresas");
		
		barrilVerde3.setBounds(105, 175, 80, 20);
		barrilAzul3.setBounds(185, 175, 80, 20);
		barrilAmarillo3.setBounds(265, 175, 80, 20);
		barrilRojo3.setBounds(345, 175, 80, 20);
		sorpresas3.setBounds(425, 175, 80, 20);
		barrilVerde3.setOpaque(false);
		barrilAzul3.setOpaque(false);
		barrilAmarillo3.setOpaque(false);
		barrilRojo3.setOpaque(false);
		sorpresas3.setOpaque(false);
		
		contenido.add(barrilVerde3);
		contenido.add(barrilAzul3);
		contenido.add(barrilAmarillo3);
		contenido.add(barrilRojo3);
		contenido.add(sorpresas3);
	}
	
	public void prepareElementosConfig1J(){
		JPanelB configPanel = new JPanelB();
		configPanel.setLayout(new GridLayout(3,1));
		configPanel.setBackground(new ImageIcon(getClass().getResource("/resources/Inicio.png")));
		cards.add(configPanel,"Configuracion1J");
		JPanel panelCButton = new JPanel();
		panelCButton.setOpaque(false);
		
		JPanel panelConfigLogo= new JPanel(new GridBagLayout());
		logo = new JLabel(new ImageIcon(getClass().getResource("/resources/FUENTE.png")));
		panelConfigLogo.add(logo);
		panelConfigLogo.setOpaque(false);
		
		JPanel combo = new JPanel();
		combo.setLayout(null);
		combo.setOpaque(false);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setForeground(Color.WHITE);
		nombre.setBounds(27, 40, 78, 14);
		combo.add(nombre);
		nameJ1 = new JTextField();
		nameJ1.setBounds(135, 37, 121, 23);
		combo.add(nameJ1);
		nameJ1.setColumns(10);
		
		JLabel personajes = new JLabel("Selecciones personaje");
		personajes.setForeground(Color.WHITE);
		personajes.setBounds(27, 85, 78, 14);
		combo.add(personajes);
		selecPlayerJu1 = new JComboBox<>(personaje);
		selecPlayerJu1.setBounds(135, 82, 121, 17);
		combo.add(selecPlayerJu1);
		
		JLabel personaje = new JLabel("ColorPersonaje");
		personaje.setForeground(Color.WHITE);
		personaje.setBounds(27, 120, 78, 14);
		combo.add(personaje);
		selecColorPlayer = new JComboBox<>(coloresPersonaje);
		selecColorPlayer.setBounds(135, 120, 121, 17);
		combo.add(selecColorPlayer);

		JLabel peach = new JLabel("Color Peach");
		peach.setForeground(Color.WHITE);
		peach.setBounds( 27, 155, 78, 14);
		combo.add(peach);
		selecColorPeach = new JComboBox<>(coloresPeach);
		selecColorPeach.setBounds(135, 155, 121, 17);
		combo.add(selecColorPeach);
		
		preparebarrilesJugador1(combo);
		
		Icon iconoCancelar = new ImageIcon(getClass().getResource("/resources/volver.png"));
		volverBoton = new myButton(iconoCancelar);
		volverBoton.setTransparent();
		
		Icon iconoJ = new ImageIcon(getClass().getResource("/resources/jugar.png"));
		jugarBoton1J = new myButton(iconoJ);
		jugarBoton1J.setTransparent();
		
		panelCButton.add(jugarBoton1J);
		panelCButton.add(volverBoton);
		
		configPanel.add(panelConfigLogo);
		configPanel.add(combo);
		configPanel.add(panelCButton);
		configPanel.setOpaque(false);
		
	}
	
	public void prepareElementosConfig2J(){
		
		JPanelB configPanel = new JPanelB();
		configPanel.setLayout(new GridLayout(3,1));
		configPanel.setBackground(new ImageIcon(getClass().getResource("/resources/Inicio.png")));
		cards.add(configPanel,"Configuracion2J");
		JPanel panelCButton = new JPanel();
		panelCButton.setOpaque(false);
		
		JPanel panelConfigLogo= new JPanel(new GridBagLayout());
		logo = new JLabel(new ImageIcon(getClass().getResource("/resources/FUENTE.png")));
		panelConfigLogo.add(logo);
		panelConfigLogo.setOpaque(false);
		
		JPanel combo = new JPanel();
		combo.setLayout(null);
		combo.setOpaque(false);
		
		JLabel nombre = new JLabel("Nombre J1");
		nombre.setForeground(Color.WHITE);
		nombre.setBounds(27, 40, 85, 14);
		combo.add(nombre);
		nameP1 = new JTextField();
		nameP1.setBounds(145, 37, 121, 23);
		combo.add(nameP1);
		nameP1.setColumns(10);
		
		JLabel nombre2 = new JLabel("Nombre J2");
		nombre2.setForeground(Color.WHITE);
		nombre2.setBounds( 286,40,85, 14 );
		combo.add(nombre2);
		nameJ2 = new JTextField();
		nameJ2.setBounds(414,37,121,23);
		combo.add(nameJ2);
		nameJ2.setColumns(10);
		
		JLabel personajes = new JLabel("Personaje J1");
		personajes.setForeground(Color.WHITE);
		personajes.setBounds(27, 85, 85, 14);
		combo.add(personajes);
		selecPlayerJu1VS = new JComboBox<>(personaje);
		selecPlayerJu1VS.setBounds(145, 82, 121, 17);
		combo.add(selecPlayerJu1VS);
		
		JLabel color = new JLabel("Color personaje J1");
		color.setForeground(Color.WHITE);
		color.setBounds(27, 120, 85, 14);
		combo.add(color);
		selecColorPlayerJu1VS= new JComboBox<>(coloresPersonaje);
		selecColorPlayerJu1VS.setBounds(145, 120, 121, 17);
		combo.add(selecColorPlayerJu1VS);
		
		JLabel personaje2 = new JLabel("Personaje J2");
		personaje2.setForeground(Color.WHITE);
		personaje2.setBounds(286, 85, 85, 14);
		combo.add(personaje2);
		selecPlayerJu2 = new JComboBox<>(personaje);
		selecPlayerJu2.setBounds(414, 82, 121, 17);
		combo.add(selecPlayerJu2);
		

		JLabel color2 = new JLabel("Color personaje J2");
		color2.setForeground(Color.WHITE);
		color2.setBounds(286, 120, 85, 14);
		combo.add(color2);
		selecColorPlayerJu2 = new JComboBox<>(coloresPersonaje);
		selecColorPlayerJu2.setBounds(414, 120, 121, 17);
		combo.add(selecColorPlayerJu2);
		
		JLabel peach = new JLabel("Color Peach");
		peach.setForeground(Color.WHITE);
		peach.setBounds( 27, 155, 78, 14);
		combo.add(peach);
		selecColorPeachVS = new JComboBox<>(coloresPeach);
		selecColorPeachVS.setBounds(145, 155, 121, 17);
		combo.add(selecColorPeachVS);
		
		preparebarrilesJugador2(combo); 
		
		Icon iconoCancelar = new ImageIcon(getClass().getResource("/resources/volver.png"));
		volverBoton2J = new myButton(iconoCancelar);
		volverBoton2J.setTransparent();
		
		Icon iconoJ = new ImageIcon(getClass().getResource("/resources/jugar.png"));
		jugarBoton2J = new myButton(iconoJ);
		
		jugarBoton2J.setTransparent();
		
		panelCButton.add(jugarBoton2J);
		panelCButton.add(volverBoton2J);
		
		configPanel.add(panelConfigLogo);
		configPanel.add(combo);
		configPanel.add(panelCButton);
		configPanel.setOpaque(false);
		
		
	}
	
	public void prepareElementosConfigPc(){
		JPanelB configPanel = new JPanelB();
		configPanel.setLayout(new GridLayout(3,1));
		configPanel.setBackground(new ImageIcon(getClass().getResource("/resources/Inicio.png")));
		cards.add(configPanel,"ConfiguracionPc");
		JPanel panelCButton = new JPanel();
		panelCButton.setOpaque(false);
		
		JPanel panelConfigLogo= new JPanel(new GridBagLayout());
		logo = new JLabel(new ImageIcon(getClass().getResource("/resources/FUENTE.png")));
		panelConfigLogo.add(logo);
		panelConfigLogo.setOpaque(false);
		
		JPanel combo = new JPanel();
		combo.setLayout(null);
		combo.setOpaque(false);
		
		JLabel nombre = new JLabel("Nombre J1");
		nombre.setForeground(Color.WHITE);
		nombre.setBounds(27, 40, 85, 14);
		combo.add(nombre);
		nameJu1 = new JTextField();
		nameJu1.setBounds(145, 37, 121, 23);
		combo.add(nameJu1);
		nameJu1.setColumns(10);
		
		JLabel nombre2 = new JLabel("Tipo maquina");
		nombre2.setForeground(Color.WHITE);
		nombre2.setBounds( 286,40,85, 14 );
		combo.add(nombre2);
		selecTipo = new JComboBox<>(tipos);
		selecTipo.setBounds(414,37,121,23);
		combo.add(selecTipo);
		
		
		JLabel personajes = new JLabel("Personaje J1");
		personajes.setForeground(Color.WHITE);
		personajes.setBounds(27, 85, 85, 14);
		combo.add(personajes);
		selecPlayerJu1CPU = new JComboBox<>(personaje);
		selecPlayerJu1CPU.setBounds(145, 82, 121, 17);
		combo.add(selecPlayerJu1CPU);
		
		JLabel color = new JLabel("Color personaje J1");
		color.setForeground(Color.WHITE);
		color.setBounds(27, 120, 85, 14);
		combo.add(color);
		selecColorPlayerJu1CPU = new JComboBox<>(coloresPersonaje);
		selecColorPlayerJu1CPU.setBounds(145, 120, 121, 17);
		combo.add(selecColorPlayerJu1CPU);
		
		JLabel personaje2 = new JLabel("Personaje J2");
		personaje2.setForeground(Color.WHITE);
		personaje2.setBounds(286, 85, 85, 14);
		combo.add(personaje2);
		selecPlayerCPU = new JComboBox<>(personaje);
		selecPlayerCPU.setBounds(414, 82, 121, 17);
		combo.add(selecPlayerCPU);
		

		JLabel color2 = new JLabel("Color personaje J2");
		color2.setForeground(Color.WHITE);
		color2.setBounds(286, 120, 85, 14);
		combo.add(color2);
		selecColorPlayerCPU = new JComboBox<>(coloresPersonaje);
		selecColorPlayerCPU.setBounds(414, 120, 121, 17);
		combo.add(selecColorPlayerCPU);
		
		JLabel peach = new JLabel("Color Peach");
		peach.setForeground(Color.WHITE);
		peach.setBounds( 27, 155, 78, 14);
		combo.add(peach);
		selecColorPeachCPU = new JComboBox<>(coloresPeach);
		selecColorPeachCPU.setBounds(145, 155, 121, 17);
		combo.add(selecColorPeachCPU);
		
		preparebarrilesJugadorPC(combo);
		
		Icon iconoCancelar = new ImageIcon(getClass().getResource("/resources/volver.png"));
		volverBotonPc = new myButton(iconoCancelar);
		volverBotonPc.setTransparent();
		
		Icon iconoJ = new ImageIcon(getClass().getResource("/resources/jugar.png"));
		jugarBotonPc = new myButton(iconoJ);
		
		jugarBotonPc.setTransparent();
		
		panelCButton.add(jugarBotonPc);
		panelCButton.add(volverBotonPc);
		
		configPanel.add(panelConfigLogo);
		configPanel.add(combo);
		configPanel.add(panelCButton);
		configPanel.setOpaque(false);
		
	}
	public void prepareAcciones(){
		
		single.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				abrirConfig1J();
			}
		});
		
		cancelarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		coop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				abrirConfig2J();
			}
		});
		
		jVsCpu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				abrirConfigPc();
			}
		});
	}
	
	private void prepareAccionesConfig() {
		volverBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal();
			}
		});
		
		volverBoton2J.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal();
			}
		});
		volverBotonPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal();
			}
		});
		
		jugarBoton1J.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juegue1J();
			}
		});
		
		jugarBoton2J.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				juegue2J();
			}
		});
		jugarBotonPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jueguePc();
			}
		});
	}
	
	public void abrirConfig1J(){
		setTitle("Configuracion");
		ajusteFrameConfig();
		CardLayout c1 = (CardLayout)(cards.getLayout());
		c1.show(cards,"Configuracion1J");
	}
	
	public void abrirConfig2J(){
		setTitle("Configuracion");
		System.out.println("AbrirConfig2JSeleccionModo");
		ajusteFrameConfig();
		CardLayout c1 = (CardLayout)(cards.getLayout());
		c1.show(cards,"Configuracion2J");
	}
	
	public void abrirConfigPc(){
		//System.out.println("abirConfigPCSeleccion");
		setTitle("Configuracion");
		ajusteFrameConfig();
		CardLayout c1 = (CardLayout)(cards.getLayout());
		c1.show(cards,"ConfiguracionPc");
	}
	
	public void juegue1J() {
		dispose();
		JPanel j;
		try {
			j = new Painter(nameJ1.getText(),(String)selecPlayerJu1.getSelectedItem(),(String)selecColorPlayer.getSelectedItem(),(String)selecColorPeach.getSelectedItem(),barrilVerde.isSelected(), barrilAzul.isSelected(), barrilAmarillo.isSelected(), barrilRojo.isSelected(), sorpresas.isSelected());
			j.setVisible(true);
		} catch (DonkeyException e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}

		
	}
	
	public void juegue2J(){
		dispose();
		JPanel j;
		try {
			j = new Painter("JugadorVsJugador",nameP1.getText(),nameJ2.getText(),(String)selecPlayerJu1CPU.getSelectedItem(),(String)selecColorPlayerJu1CPU.getSelectedItem(),(String)selecPlayerCPU.getSelectedItem(),(String)selecColorPlayerCPU.getSelectedItem(),(String)selecColorPeachVS.getSelectedItem(),null,barrilVerde2.isSelected(), barrilAzul2.isSelected(), barrilAmarillo2.isSelected(), barrilRojo2.isSelected(), sorpresas2.isSelected());
			j.setVisible(true);
		} catch (DonkeyException e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}

		
	}
	
	public void jueguePc(){
		dispose();
		JPanel j;
		try {
			j = new Painter("JugadorVsPc",nameJu1.getText(),".",(String)selecPlayerJu1CPU.getSelectedItem(),(String)selecColorPlayerJu1CPU.getSelectedItem(),(String)selecPlayerCPU.getSelectedItem(),(String)selecColorPlayerCPU.getSelectedItem(),(String)selecColorPeachCPU.getSelectedItem(),(String)selecTipo.getSelectedItem(),barrilVerde3.isSelected(), barrilAzul3.isSelected(), barrilAmarillo3.isSelected(), barrilRojo3.isSelected(), sorpresas3.isSelected());
			j.setVisible(true);
		} catch (DonkeyException e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
		//j.setRival((String)selecTipo.getSelectedItem());		
		
		//jugar(1,true);
	}
	
	
	private void principal() {
		setTitle("Configuracion");
		CardLayout c1 = (CardLayout)(cards.getLayout());
		c1.show(cards,"Principal");
		ajusteFrame();
	}
}