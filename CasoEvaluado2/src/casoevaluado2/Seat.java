/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package casoevaluado2;
    
public class Seat {
    String codigo;
    String clase;
    double precioBase;
    String estado; // Libre, Ocupado, Reservado

    // Datos del pasajero
    String nombre;
    String pasaporte;
    String nacionalidad;
    double precioPagado;

    public Seat(String codigo, String clase, double precioBase) {
        this.codigo = codigo;
        this.clase = clase;
        this.precioBase = precioBase;
        this.estado = "Libre";
        this.nombre = "";
        this.pasaporte = "";
        this.nacionalidad = "";
        this.precioPagado = 0;
    }
}

