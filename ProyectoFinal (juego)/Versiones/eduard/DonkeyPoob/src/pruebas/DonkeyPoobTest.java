package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import aplicacion.*;


class DonkeyPoobTest {

	@Test
	void test() {
		
	}
	@Test
	void deberiaCrearBarriles() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoMovimiento>Objetos = game.getMOList();
		ArrayList<ObjetoEstatico>Objetos2 = game.getSOList();
		assertTrue(Objetos.size()==1);
		BarrilVerde barril = new BarrilVerde(120,200,20,25, Objetos2, true);
		Objetos.add(barril);
		assertTrue(Objetos.size()==2);
	}
	@Test
	void deberiaCrearSorpresas() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		assertTrue(Objetos.size()==17);
		game.initSurprise();
		assertTrue(Objetos.size()==19);
	}
	@Test
    public void deberiaColisionarConSorpresa() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		Mario mario = new Mario(120,200,35,20, Objetos);
		Cereza cereza =new Cereza(120,200,20,20);
		assertFalse(mario.checkSOCollisions(Objetos));
		Objetos.add(cereza);
		assertTrue(mario.checkSOCollisions(Objetos));
    }
	@Test
    public void deberiaColisionarConBarril() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		ArrayList<ObjetoMovimiento>Objetos2 = game.getMOList();
		BarrilVerde barril = new BarrilVerde(120,200,20,25, Objetos, true);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos2.add(mario);
    	assertFalse(mario.checkMOCollision(Objetos2));
		Objetos2.add(barril);
		assertTrue(mario.checkMOCollision(Objetos2));
    }
	@Test
    public void deberiaColisionarConEscalera() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		Escalera escalera = new Escalera(120,200,20,25);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos.add(mario);
    	assertFalse(mario.checkSOCollisions(Objetos));
		Objetos.add(escalera);
		assertTrue(mario.checkSOCollisions(Objetos));
    }
	@Test
    public void deberiaSubirEscalera() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		Escalera escalera = new Escalera(120,200,20,25);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos.add(mario);
    	assertFalse(mario.checkSOCollisions(Objetos));
		Objetos.add(escalera);
		assertTrue(mario.checkSOCollisions(Objetos));
		assertTrue(mario.getYPos()==200);
		mario.setJump(true);
		mario.act(1);
		assertTrue(mario.getYPos()<200);
    }
	@Test
    public void deberiaTomarSogaYSubir() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		Soga soga = new Soga(120,200,20,25);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos.add(mario);
		Objetos.add(soga);
		mario.checkSOCollisions(Objetos);
		assertTrue(mario.getYPos()==200);
		mario.act(1);
		assertTrue(mario.getYPos()<200);
    }
	@Test
    public void deberiaRestarVidaAlColisionarConBarril() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		ArrayList<ObjetoMovimiento>Objetos2 = game.getMOList();
		Barril barril = new Barril(120,200,20,25, Objetos, true);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos2.add(mario);
    	assertFalse(mario.checkMOCollision(Objetos2));
		Objetos2.add(barril);
		assertTrue(mario.checkMOCollision(Objetos2));
		if(mario.checkMOCollision(Objetos2)){
			game.life--;
		}
		assertTrue(game.life==2); 
    }
	@Test
    public void deberiaSumarVidaAlColisionarConBarril() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		ArrayList<ObjetoMovimiento>Objetos2 = game.getMOList();
		Barril barrilVerde = new BarrilVerde(120,200,20,25, Objetos, true);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos2.add(mario);
    	assertFalse(mario.checkMOCollision(Objetos2));
		Objetos2.add(barrilVerde);
		assertTrue(mario.checkMOCollision(Objetos2));
		if(mario.checkMOCollision(Objetos2)){
			game.life++;
		}
		assertTrue(game.life==4); 
    }
	@Test
	public void marioDeberiaGanarAlTocarLaPrincesar() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		Peach peach = new Peach(120,200,20,25);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos.add(mario);
    	assertFalse(mario.checkSOCollisions(Objetos));
		Objetos.add(peach);
		assertTrue(mario.checkSOCollisions(Objetos));
		
	}
	@Test
	public void DeberiaEstarElBarrilEnPlataforma() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		ArrayList<ObjetoMovimiento>Objetos2 = game.getMOList();
		Barril barrilVerde = new BarrilVerde(120,200,20,25, Objetos, true);
    	Objetos2.add(barrilVerde);
    	assertTrue(barrilVerde.standing());
		
	}
	@Test
	public void DeberiaInvertirControlesAlTocarELHongo() {
		DonkeyPoob game = new DonkeyPoob("normal","Mario",true, true, true, true, true);
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		Hongo hongo = new Hongo(120,200,20,25);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos.add(mario);
		Objetos.add(hongo);
		assertFalse(mario.getInvert());
		mario.checkSOCollisions(Objetos);
		assertTrue(mario.getInvert());
		
	}
	@Test
    public void mimoDeberiaCopiarMovimientos(){
		DonkeyPoob game = new DonkeyPoob("JugadorVsPc","Mario","Luigi",true, true, true, true, true);
		ArrayList<ObjetoMovimiento>Objetos2 = game.getMOList();
		ArrayList<ObjetoEstatico>Objetos = game.getSOList();
		Escalera escalera = new Escalera(120,200,20,25);
		Mario mario = new Mario(120,200,35,20, Objetos);
    	Objetos2.add(mario);
    	Jugador jugadorMaquina = new JugadorMimo(120,200,35,20, Objetos);
    	Objetos2.add(jugadorMaquina);
    	Objetos.add(escalera);
    	mario.checkSOCollisions(Objetos);
    	mario.setLeft(true);
		mario.act(1);
		//System.out.println(mario.getXPos());
    	
    }
	/*
	@Test
    public void deberiaLanzarExcepcionSiElJugadorNoTieneNombre(){
    	Arkanoid.nuevoArkanoid(true, true);
    	Arkanoid juego = Arkanoid.deme(true,true);
    	Jugador j = new Jugador("");
    	try {
    		juego.addJugador(j);
		} catch (ArkanoidException e) {
			assertTrue(e.getMessage().equals(ArkanoidException.PLAYER_NAME_ERROR));
		}
    }
	@Test
    public void deberiaLanzarExcepcionSiElJugadorYaExiste(){
    	Arkanoid.nuevoArkanoid(true, true);
    	Arkanoid juego = Arkanoid.deme(true,true);
    	Jugador c = new Curioso("Juan");
    	try {
			juego.addJugador(c);
			juego.addJugador(c);
		} catch (ArkanoidException e) {
			assertTrue(e.getMessage().equals(ArkanoidException.PLAYER_ALREADY_ERROR));
		}
    }
    */
}
