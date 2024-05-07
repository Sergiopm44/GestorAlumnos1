package Gestor.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class userConfig {

	public static final String CONFIGURACION = "Z:\\Programacion\\GestorAlumnos1\\src\\main\\java\\Gestor\\utils\\configuracion.txt";

	public static void escribirConfig(int theme) {

		try {

			BufferedWriter bwConfig = new BufferedWriter(new FileWriter(CONFIGURACION));

			bwConfig.write(theme + "\n");

			bwConfig.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static Config insertarConfig() {

		try {

			BufferedReader brConfig = new BufferedReader(new FileReader(CONFIGURACION));

			int theme = Integer.parseInt(brConfig.readLine());

			brConfig.close();

			return new Config(theme);

		} catch (NumberFormatException e) {

// Pongo el light mode como default

			e.printStackTrace();

			return new Config(0);

		} catch (IOException e) {

// Pongo el light mode como default

			e.printStackTrace();

			return new Config(0);

		}

	}

	public static class Config {

		private int theme;

		public Config(int theme) {

			super();

			this.theme = theme;

		}

		public int getTheme() {

			return theme;

		}

		public void setTheme(int theme) {

			this.theme = theme;

		}

	}

}