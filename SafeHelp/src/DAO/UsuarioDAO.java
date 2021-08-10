/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaControles.UsuarioJpaController;
import jpaControles.exceptions.NonexistentEntityException;

/**
 *
 * @author lsilva
 */
public class UsuarioDAO {
    private final UsuarioJpaController objetoJPA;
    private final EntityManagerFactory emf;
    
    public UsuarioDAO(){
        emf=Persistence.createEntityManagerFactory("SafeHelpPU");
        objetoJPA = new UsuarioJpaController(emf);
    }
    
    public void (Usuario objeto) throws Exception{
        objetoJPA.create(objeto);
    }
    
    public void (Usuario objeto) throws Exception{
        objetoJPA.edit(objeto);
    }
    
    public void remove(short id) throws NonexistentEntityException{
        objetoJPA.destroy(id);
    }
    
    public List<Usuario> geAllUsuario(){
        return objetoJPA.findUsuarioEntities();
    }
    
    public void persist (Usuario object){
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
    
}