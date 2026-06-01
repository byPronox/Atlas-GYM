package org.example.AtlasGym.run;

import org.openxava.util.*;

/**
 * Ejecuta esta clase para arrancar la aplicación.
 */

public class AtlasGym {

	public static void main(String[] args) throws Exception {
		DBServer.start("AtlasGym-db"); // Para usar tu propia base de datos comenta esta línea y configura src/main/webapp/META-INF/context.xml
		AppServer.run("AtlasGym"); // Usa AppServer.run("") para funcionar en el contexto raíz
	}

}
