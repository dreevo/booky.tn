/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.tests;

import com.booky.entities.Client;
import com.booky.entities.Livre;
import com.booky.entities.Panier;
import com.booky.services.ServicesClient;
import com.booky.services.ServicesLivre;
import com.booky.services.ServicesPanier;

/**
 *
 * @author 21655
 */
public class MainProg {
    public static void main(String[] args) {
        Livre l1 = new Livre("alchimiste", 26, "Alchimiste est un conte philosophique de Paulo Coelho paru en 1988. La traduction française, signée Jean Orecchioni, a été publiée en 1994.",1);
        Livre l2 = new Livre("harry potter", 26, "Harry Potter est une série littéraire de low fantasy écrite par l auteure britannique J. K. Rowling dont la suite romanesque s est achevée en 2007. Une pièce de théâtre considérée comme la « huitième histoire » officielle a été jouée et publiée en 2016.",0);
        ServicesLivre sl = new ServicesLivre();
        //sl.ajouter(l1);
        //sl.ajouter(l2);
        //sl.supprimer(new Livre(3,"alchimiste", 26, "Alchimiste est un conte philosophique de Paulo Coelho paru en 1988. La traduction française, signée Jean Orecchioni, a été publiée en 1994.",1));
        Client c1 = new Client("aziz", "gharbi",22, "gharbi@gmail.com","15 rue kortoba","55753337");
        ServicesClient sc = new ServicesClient();
        //sc.AjouterClient(c1);
        Panier p = new Panier();
        ServicesPanier sp = new ServicesPanier();
        sp.ajouterPdtAuPanier(l1, c1.getId(), p);
        sp.ajouterPdtAuPanier(l2, c1.getId(), p);
        sp.ajouterPdtAuPanier(l2, c1.getId(), p);
        System.out.println(p);
    }
}
