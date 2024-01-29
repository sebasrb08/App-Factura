package com.campusland.respository.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

   
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String celular;   
    private String documento;


    public String getFullName(){
        return this.nombre+" "+this.apellido;

    }

    public void imprimir(){
        System.out.println("Documento: "+this.getDocumento());
        System.out.println("Nombre: "+this.getFullName());
        System.out.println("Email: "+this.getEmail());
        System.out.println("Celular: "+this.getCelular());
        System.out.println("Dirección: "+this.getDireccion());    
    }



    

    
}
