/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.UsuariosJpaController;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class UsuariosDAO {
    private final UsuariosJpaController objetoJPA;
    private final EntityManagerFactory emf;

    public UsuariosDAO(){
        emf=Persistence.createEntityManagerFactory("SafeHelpPU");
        objetoJPA = new UsuariosJpaController(emf);
    }
    
    public void add(Usuarios objeto) throws Exception{
        objetoJPA.create(objeto);
    }
    
    public void edit(Usuarios objeto) throws Exception{
        objetoJPA.edit(objeto);
    }
    
    public void remove(int id) throws NonexistentEntityException{
        objetoJPA.destroy(id);
    }
    
    public List<Usuarios> geAllUsuario(){
        return objetoJPA.findUsuariosEntities();
    }
    
    
    public void persist (Usuarios object){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally{
            em.close();
        }
    }
    
     public Usuarios userLogin(String email, String password){
        Usuarios login = objetoJPA.login(email, password);
        System.out.println( "DAO   -    " + login.getEmail());
        return login;
        }
    
}
   
