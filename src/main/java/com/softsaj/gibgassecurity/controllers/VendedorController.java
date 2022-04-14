/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.softsaj.gibgassecurity.controllers;


import com.softsaj.gibgassecurity.models.Vendedor;
import com.softsaj.gibgassecurity.services.VendedorService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;;

/**
 *
 * @author Marcos
 */
@RestController
@RequestMapping("/vendedores")
public class VendedorController {
    
     @Autowired
    private VendedorService vs;
    
    @GetMapping
    public ResponseEntity<List<Vendedor>> getAll() {
        List<Vendedor> movies =  vs.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    
    //GEt Vendedor
     @GetMapping("/vendedor/{id}")
    public ResponseEntity<Vendedor> getCienfiloById (@PathVariable("id") Long id
             ,@RequestParam("token") String token) {
        
        Vendedor vendedor = vs.findVendedorById(id);
        return new ResponseEntity<>(vendedor, HttpStatus.OK);
    }
    
     @GetMapping("/vendedor/usuario/{email}")
    public ResponseEntity<List<Vendedor>> findByEmail (@PathVariable("email") String email
             ,@RequestParam("token") String token) {
        
       
        
        List<Vendedor> movies = vs.findByEmail(email);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    
    
    @PostMapping("/vendedor/add")
    public ResponseEntity<Vendedor> addVendedor(@RequestBody Vendedor movie, @RequestParam("token") String token) {
        
        Vendedor newVendedor = vs.addVendedor(movie);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/vendedor/{id}").buildAndExpand(movie.getId()).toUri();
        
        return new ResponseEntity<>(newVendedor, HttpStatus.CREATED);
    }
    
    //Update nome,telefone,idade,foto;
    @PutMapping("/vendedor/update/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@PathVariable("id") Long id, @RequestBody Vendedor newvendedor
            ,@RequestParam("token") String token) {
        
        
        
        Vendedor vendedor = vs.findVendedorById(id);
        //vendedor.setNome(newvendedor.getNome());
        //c//inefilo.setTelefone(newvendedor.getTelefone());
        //vendedor.setIdade(newvendedor.getIdade());
        //vendedor.setFoto(newvendedor.getFoto());
        Vendedor updateVendedor = vs.updateVendedor(vendedor);//s
        return new ResponseEntity<>(updateVendedor, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVendedor(@PathVariable("id") Long id
            ,@RequestParam("token") String token) {
        
      
        
        vs.deleteVendedor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}


