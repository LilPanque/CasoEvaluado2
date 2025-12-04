package casoevaluado2;

import javax.swing.JOptionPane;

public class CasoEvaluado2 {

    static Seat[][] avion = new Seat[5][4];

    public static void main(String[] args) {

        precargarAvion();

        JOptionPane.showMessageDialog(null, "MAPA INICIAL");
        verMapa();

        JOptionPane.showMessageDialog(null, "RESERVA NORMAL");
        reservar(0, 0, "Juan Perez", "P12345", "Costa Rica");

        JOptionPane.showMessageDialog(null, "RESERVA CLIENTE FRECUENTE");
        reservarConDescuento(1, 1, "Maria Gomez", "X99887", "Mexico");

        JOptionPane.showMessageDialog(null, "CONSULTA DE ASIENTO");
        consultarPasajero(0, 0);

        JOptionPane.showMessageDialog(null, "MAPA ACTUALIZADO");
        verMapa();

        JOptionPane.showMessageDialog(null, "RESUMEN DEL VUELO");
        resumenVuelo();
    }


    public static void precargarAvion() {
        String[] clases = {"Primera", "Business", "Economica"};
        double[] precios = {500, 300, 100};

        for (int fila = 0; fila < 5; fila++) {
            for (int col = 0; col < 4; col++) {

                String codigo = (fila + 1) + "" + (char)('A' + col);

                String clase;
                double precio;

                if (fila == 0) {
                    clase = clases[0];
                    precio = precios[0];
                } else if (fila == 1) {
                    clase = clases[1];
                    precio = precios[1];
                } else {
                    clase = clases[2];
                    precio = precios[2];
                }

                avion[fila][col] = new Seat(codigo, clase, precio);
            }
        }
    }



    public static void verMapa() {

        String mapa = "";

        for (int fila = 0; fila < 5; fila++) {

            mapa += "Fila " + (fila + 1) + ": ";

            for (int col = 0; col < 4; col++) {
                mapa += avion[fila][col].codigo + "  ";
            }

            mapa += "\n";

            for (int col = 0; col < 4; col++) {
                mapa += avion[fila][col].estado + "  ";
            }

            mapa += "\n";

            for (int col = 0; col < 4; col++) {
                mapa += avion[fila][col].clase + "  ";
            }

            mapa += "\n";

            for (int col = 0; col < 4; col++) {
                mapa += "$" + avion[fila][col].precioBase + "  ";
            }

            mapa += "\n-------------------------------------\n";
        }

        JOptionPane.showMessageDialog(null, mapa);
    }



    public static void reservar(int fila, int col, String nombre, String pasaporte, String nacionalidad) {

        Seat s = avion[fila][col];

        if (!s.estado.equals("Libre")) {
            JOptionPane.showMessageDialog(null, "El asiento " + s.codigo + " no esta libre.");
            return;
        }

        s.nombre = nombre;
        s.pasaporte = pasaporte;
        s.nacionalidad = nacionalidad;
        s.precioPagado = s.precioBase;
        s.estado = "Ocupado";

        JOptionPane.showMessageDialog(null,
                "Asiento " + s.codigo + " reservado por " + nombre +
                "\nPrecio: $" + s.precioPagado);
    }



    public static void reservarConDescuento(int fila, int col, String nombre, String pasaporte, String nacionalidad) {

        Seat s = avion[fila][col];

        if (!s.estado.equals("Libre")) {
            JOptionPane.showMessageDialog(null, "El asiento " + s.codigo + " no esta libre.");
            return;
        }

        double precioFinal = s.precioBase;

        if (s.clase.equals("Economica")) precioFinal *= 0.90;
        if (s.clase.equals("Business")) precioFinal *= 0.85;
        if (s.clase.equals("Primera")) precioFinal *= 0.82;

        s.nombre = nombre;
        s.pasaporte = pasaporte;
        s.nacionalidad = nacionalidad;
        s.precioPagado = precioFinal;
        s.estado = "Ocupado";

        JOptionPane.showMessageDialog(null,
                "Asiento " + s.codigo +
                "\nPasajero: " + nombre +
                "\nPrecio final: $" + precioFinal);
    }



    public static void consultarPasajero(int fila, int col) {

        Seat s = avion[fila][col];

        if (!s.estado.equals("Ocupado")) {
            JOptionPane.showMessageDialog(null, "El asiento esta libre.");
            return;
        }

        String datos =
                "Asiento: " + s.codigo + "\n" +
                "Nombre: " + s.nombre + "\n" +
                "Pasaporte: " + s.pasaporte + "\n" +
                "Nacionalidad: " + s.nacionalidad + "\n" +
                "Pago: $" + s.precioPagado;

        JOptionPane.showMessageDialog(null, datos);
    }



    public static void resumenVuelo() {

        double total = 0;
        int libres = 0;
        int ocupados = 0;

        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 4; c++) {

                Seat s = avion[f][c];

                if (s.estado.equals("Ocupado")) {
                    total += s.precioPagado;
                    ocupados++;
                } else {
                    libres++;
                }
            }
        }

        double porcentaje = (ocupados * 100.0) / (ocupados + libres);

        String resumen = "Total recaudado: $" + total +
                "\nAsientos libres: " + libres +
                "\nOcupacion: " + porcentaje + "%";

        JOptionPane.showMessageDialog(null, resumen);
    }
}
