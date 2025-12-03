package dominio;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Banana extends SuperObjeto{
	
	public OBJ_Banana() {
		nombre = "Banana";
		try {
			imagen = ImageIO.read(getClass().getResourceAsStream("/Objetos/Bananas.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
