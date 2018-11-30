package juego;

public class Animacion {
	
	private int[][] animaciones;

	public Animacion(int dimension){
		cargarAnimaciones(dimension);
	}

	public void cargarAnimaciones(int dimension) {
		animaciones = new int[dimension][dimension];
	}
	
	public void agregarAnimaciones(int fila, int columna){
		animaciones[fila][columna]=1;
	}
	
	public int obtenerAnimaciones(int fila, int columna){
		return animaciones[fila][columna];
	}
	
	public void reiniciarAnimaciones(){
		for(int i = 0; i < animaciones.length; i++){
			for(int j = 0; j < animaciones[i].length;j++){
				animaciones[i][j] = 0;
			}
		}
	}
}
