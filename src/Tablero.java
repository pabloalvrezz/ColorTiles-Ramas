import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public class Tablero extends JPanel  {

	//Atributos de la clase
	private int puntos, vidas, porcentaje, columnas, filas, ancho, alto;
	
	// Matriz que controlara el estado del tablero
	private Color[][] tab;
	
	// Colores necesarios para el juego
	Color White = new Color(255, 255, 255);
	Color Grey = new Color(61,61,61);
	Color Red = new Color(255, 45, 0);
	Color Blue = new Color(0, 0, 255);
	Color Green = new Color(0, 255, 0);
	Color Yellow = new Color(255, 255, 0);
	Color Pink = new Color(255, 0,255);
	Color Orange = new Color(243, 156, 18);

	// Constructores
	
	//Constructor con parametros para inicializar los atributos de la clase
	Tablero(int a, int b,int c, int d) {
	
		tab = new Color[a][b];
		setVidas(d);
		setPuntos(0);
		setPorcentaje(c);
		setColumnas(a);
		setFilas(b);
		setAncho(a*20);
		setAlto(b*20);
		setBackground(Grey);

		for (int i = 0; i < b;i++) {
			for(int j = 0 ;j < a; j++) {
				tab[j][i] = paintAleat();
			}
		}
		
		// 'Escuchador' de ratón
		addMouseListener(new MouseHandler());
	}
	
	//Constructor por defecto de la clase
	Tablero() {
		this(22,23,60,5);
	}
	
    // Getters y setters de los atributos de la clase
	
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int p) {
		porcentaje = p;
	}
	
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int p) {
		vidas = p;
	}
	
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int p) {
		puntos = p;
	}

	public int getColumnas() {
		return columnas;
	}
	public void setColumnas(int p) {
		columnas = p;
	}
	
	public int getFilas() {
		return filas;
	}
	public void setFilas(int p) {
		filas = p;
	}
	
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int p) {
		ancho = p;
	}
	
	public int getAlto() {
		return alto;
	}
	public void setAlto(int p) {
		alto = p;
	}
	
	// Métodos de la clase que implementan el juego: básicamente hacer una
	// jugada, dibujar el estado del tablero y comprobar si la partida se acabó
	
	//Metodo que genera colores pseudoaleatorios
	public  Color paintAleat() {
		double random = Math.random();
		double random2 = Math.random();
		
		if (random2 > porcentaje/100.0) return White;
			
		if (random < (1/6.0)) return Red;
		else 	
			if (random > (1/6.0) && random < (2/6.0)) return Blue;
			else 
				if (random > (2/6.0) && random <(3/6.0)) return Green;
				else 
					if (random > (3/6.0) && random <(4/6.0)) return Yellow;
					else 
						if (random > (4/6.0) && random <(5/6.0)) return Pink;
						else return Orange;
	}
	
	// Método paint, sin parametros, que pinta tanto el tablero como el "marcador" (vidas y puntos).
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Aquí iría el código para pintar el estado del tablero
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < getFilas();i++) {
			for(int j = 0 ;j < getColumnas(); j++) {
				g.setColor(tab[j][i]);
				g.fillRect(x,y,17,17);
				x +=20;
			}
			y += 20;
			x = 0;	
		}
		
		// Codigo para pintar el contador de vidas y puntos
		if (getVidas()>=5) g.setColor(Green);
		else if (getVidas()<3) g.setColor(Red);
		else g.setColor(Yellow);
		g.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		g.drawString("Vidas: "+getVidas(),10,getAlto()+17);
		g.setColor(White);
		g.drawString("Puntos: "+getPuntos(),100,getAlto()+17);
	}
	
	// Comprobar el color de la casilla en cada dirección, y realizando jugada si es posible.
	public int hacerJugada(int cx, int cy) {
		
		int p = 0;
		int ix, iy, dx, dy, ax, ay, ex, ey;
		ix = dx = ax = ex = cx;
		iy = dy = ay = ey = cy;
			
		if (tab[cx][cy]==White) 
                {
			Color arriba = White, abajo = White, izquierda = White, derecha = White;
			int x = cx-1;
			while(x>=0 && x<getColumnas() && izquierda==White) {
				if(tab[x][cy]!=White) {
					izquierda = tab[x][cy];
					ix = x;
					iy = cy;
				}
				x--;
			}
			x = cx+1;
			while(x>=0 && x<getColumnas() && derecha==White) {
				if(tab[x][cy]!=White) {
					derecha = tab[x][cy];
					dx = x;
					dy = cy;
				}
				x++;
			}
			x = cy-1;
			while(x>=0 && x<getFilas() && abajo==White) {
				if(tab[cx][x]!=White) {
					abajo = tab[cx][x];
					ax = cx;
					ay = x;
				}
				x--;
			}
			x = cy+1;
			while(x>=0 && x<getFilas() && arriba==White) {
				if(tab[cx][x]!=White) {
					arriba = tab[cx][x];
					ex = cx;
					ey = x;
				}
				x++;
			}
			
			if(izquierda != White && izquierda==arriba && arriba==derecha && derecha==abajo) {
				tab[ix][iy] = tab[dx][dy] = tab[ax][ay] = tab[ex][ey] = White;
				return 10;
			}
			if (izquierda != White && izquierda == arriba && arriba == derecha) {
				tab[ix][iy] = tab[dx][dy] = tab[ex][ey] = White;
				return 5;
			}
			if (izquierda != White && izquierda == abajo && abajo == derecha) {
				tab[ix][iy] = tab[ax][ay] = tab[dx][dy] = White;
				return 5;
			}
			if (izquierda != White && izquierda == arriba && arriba == abajo) {
				tab[ix][iy] = tab[ax][ay] = tab[ex][ey] = White;
				return 5;
			}
			if (derecha != White && derecha == arriba && arriba == abajo) {
				tab[dx][dy] = tab[ax][ay] = tab[ex][ey] = White;
				return 5;
			}
			if (izquierda != White && izquierda == arriba) {
				tab[ix][iy] = tab[ex][ey] = White;
				p+=2;
			}
			if (izquierda != White && izquierda == abajo) {
				tab[ix][iy] = tab[ax][ay] = White;
				p+=2;
			}
			if (izquierda != White && izquierda == derecha) {
				tab[ix][iy] = tab[dx][dy] = White;
				p+=2;
			}
			if (derecha != White && derecha == abajo) {
				tab[dx][dy] = tab[ax][ay] = White;
				p+=2;
			}
			if (derecha != White && derecha == arriba) {
				tab[dx][dy] = tab[ex][ey] = White;
				p+=2;
			}
			if (abajo != White && abajo == arriba) {
				tab[ex][ey] = tab[ax][ay] = White;
				p+=2;
			}
			
			if (p==4) return 10;
			else return p;
		}
		
		return -1;
		
	}
	
	// Metodo para comprobar si la partida se ha acabado, bien porque al usuario no le queden vidas
	// o porque se hayan acabado las combinaciones de casillas
	public boolean comprobarFin() {
		if (vidas <= 0) return true;
		else {
			for (int i = 0; i < getFilas();i++) {
				for(int j = 0 ;j < getColumnas(); j++) {
					
					Color arriba = White, abajo = White, izquierda = White, derecha = White;
					int x = j-1;
					while(x>=0 && x<getColumnas() && izquierda==White) {
						if(tab[x][i]!=White) {
							izquierda = tab[x][i];
						}
						x--;
					}
					x = j+1;
					while(x>=0 && x<getColumnas() && derecha==White) {
						if(tab[x][i]!=White) {
							derecha = tab[x][i];
						}
						x++;
					}
					x = i-1;
					while(x>=0 && x<getFilas() && abajo==White) {
						if(tab[j][x]!=White) {
							abajo = tab[j][x];
						}
						x--;
					}
					x = i+1;
					while(x>=0 && x<getFilas() && arriba==White) {
						if(tab[j][x]!=White) {
							arriba = tab[j][x];
						}
						x++;
					}
					
					if(izquierda != White && izquierda==arriba && arriba==derecha && derecha==abajo) {
						return false;
					}
					if (izquierda != White && izquierda == arriba && arriba == derecha) {
						return false;
					}
					if (izquierda != White && izquierda == abajo && abajo == derecha) {
						return false;
					}
					if (izquierda != White && izquierda == arriba && arriba == abajo) {
						return false;
					}
					if (derecha != White && derecha == arriba && arriba == abajo) {
						return false;
					}
					if (izquierda != White && izquierda == arriba) {
						return false;
					}
					if (izquierda != White && izquierda == abajo) {
						return false;
					}
					if (izquierda != White && izquierda == derecha) {
						return false;
					}
					if (derecha != White && derecha == abajo) {
						return false;
					}
					if (derecha != White && derecha == arriba) {
						return false;
					}
					if (abajo != White && abajo == arriba) {
						return false;
					}
					
				}
			}
			return true;
		}
	}
	
	// Clase privada para capturar los clicks del raton 
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked (MouseEvent e) {

			// Se comprueba si se ha hecho click dentro del tablero y en tal caso se hace jugada			
			if ((vidas != 0) && (e.getX()> 0 && e.getX() < getAncho()) && (e.getY() > 0 && e.getY() < getAlto())) {
				int z = hacerJugada((int)(e.getX()/20.0),(int)(e.getY()/20.0));
				if (z>0) setPuntos(getPuntos()+z);
				else if (z==0 && getVidas()>0) setVidas(getVidas()-1);
			}

			// Se repinta el tablero por si se realizo jugada valida
			repaint();
			
			//Se llama al metodo comprobarFin() para en tal caso mostrar el mensaje de victoria/derrota
			if (comprobarFin()) {
				if (getVidas()==0) {
					JOptionPane.showMessageDialog(null, String.format("¡Has perdido!\nSe te han acabado las vidas."));
				} else {
					JOptionPane.showMessageDialog(null, String.format("¡Has ganado!\nNo quedan mas jugadas posibles."));
				}
			}

		}
	}
}

