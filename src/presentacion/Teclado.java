package presentacion;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	
	public boolean arriba, abajo, izquierda, derecha;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode(); //Retorna la tecla que presionemos
		
		if(tecla == KeyEvent.VK_W) {
			arriba = true;
		}
		
		if(tecla == KeyEvent.VK_S) {
			abajo = true;
		}
		
		if(tecla == KeyEvent.VK_A) {
			izquierda = true;
		}
		
		if(tecla == KeyEvent.VK_D) {
			derecha = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int tecla = e.getKeyCode();
		
		if(tecla == KeyEvent.VK_W) {
			arriba = false;
		}
		
		if(tecla == KeyEvent.VK_S) {
			abajo = false;
		}
		
		if(tecla == KeyEvent.VK_A) {
			izquierda = false;
		}
		
		if(tecla == KeyEvent.VK_D) {
			derecha = false;
		}
	}
}
