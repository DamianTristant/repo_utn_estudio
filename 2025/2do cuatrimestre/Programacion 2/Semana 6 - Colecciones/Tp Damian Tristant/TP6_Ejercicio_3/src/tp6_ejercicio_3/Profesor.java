/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp6_ejercicio_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author enfer
 */
public class Profesor {

    private String id;
    private String nombre;
    private String especialidad;
    private List<Curso> cursos;

    public Profesor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Curso> getCursos() {
        return Collections.unmodifiableList(cursos);
    }

    public void agregarCurso(Curso curso) {
        if (curso != null && !cursos.contains(curso)) {
            cursos.add(curso);
            if (curso.getProfesor() != this) {
                curso.setProfesor(this);
            }
        }
    }

    public void eliminarCurso(Curso curso) {

        if (curso != null && cursos.contains(curso)) {

            cursos.remove(curso);

            if (curso.getProfesor() == this) {
                curso.setProfesor(null);

            }
        }
    }

    public void listarCursos(){
        System.out.println("Los cursos del profesor "+nombre+" son: ");
        for(Curso curso: cursos){
            curso.mostrarInfo();
        }
    }
    
    public void mostrarInfo(){
        System.out.println("El profesor con id: "+id+
                "\nnombre: "+nombre+
                "\nespecialidad: "+especialidad+
                "\nejerse en "+cursos.size()+" cursos.");
    }
}
