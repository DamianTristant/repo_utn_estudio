/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp6_ejercicio_3;

/**
 *
 * @author enfer
 */
public class TP6_Ejercicio_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Instanciamos 3 profesores
        Profesor profesor1= new Profesor("1","Leandro De Angelis","ADO");
        Profesor profesor2= new Profesor("2","Ariel Enferrel","Programacion");
        Profesor profesor3= new Profesor("3","Carlos Frede","Financiera");
        
        //Instanciamos 5 cursos
        Curso programacion1= new Curso("1","Programacion1");
        Curso ado= new Curso("2","ADO");
        Curso financiera= new Curso("3","Financiera 1");
        Curso programacion2= new Curso("4","Programacion 2");
        Curso baseDatos= new Curso("5","Base de Datos");
        
        //Creamos la universidad 
        Universidad uni= new Universidad("UTN");
        
        //Agregamos los profesores a la universidad
        uni.agregarProfesor(profesor3);
        uni.agregarProfesor(profesor2);
        uni.agregarProfesor(profesor1);
        
        //Agregamos los cursos
        uni.agregarCurso(ado);
        uni.agregarCurso(programacion1);
        uni.agregarCurso(financiera);
        uni.agregarCurso(programacion2);
        uni.agregarCurso(baseDatos);
        
     //Asignamos los profesores con los cursos
     uni.asignarProfesorACurso("2", "1");
     uni.asignarProfesorACurso("1", "2");
     uni.asignarProfesorACurso("4", "2");
     uni.asignarProfesorACurso("5", "2");
     uni.asignarProfesorACurso("3", "3");
     
     //Listamos profesores con cursos
     uni.listarProfesor();
     
     //Listamos cursos
     uni.listarCursos();
     
     //Cambiamos un profesor de curso
      System.out.println("///////////////||||||||||||||||||||||||||||");
        System.out.println("Reasignamos el curso de Programacion 1 al docente"
                + "Leandro De Angelis");
     uni.asignarProfesorACurso("1", "1");
     uni.listarCursos();
     uni.listarProfesor();
       
     //Removemos un curso y validamos que no lo tiene al profesor
        System.out.println("***********************************");
        System.out.println("Removemos el curso de id=2 y vemos los profesores");
     uni.eliminarCurso(financiera);
     uni.listarProfesor();
     //Vemos que ahora hay 1 curso menos en la universidad
     uni.listarCursos();
      
     //Removemos un profesor
        System.out.println("||||||||||||||||||||||||||||||||||||||||");
        System.out.println("Removemos al profesor de id=1");
        uni.eliminarProfesor(profesor1);
       
        uni.listarProfesor();
    }
    
}
