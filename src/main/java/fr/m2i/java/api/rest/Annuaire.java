package fr.m2i.java.api.rest;

import java.util.ArrayList;
import java.util.List;

public class Annuaire {

    private List<Personne> personnes;
    private Long nextId;

    public Annuaire() {
        this.personnes = new ArrayList();
        this.nextId = 1L;
    }

    public Personne create(Personne personne) {
        personne.setId(nextId);
        personnes.add(personne);

        nextId++;

        return personne;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public Personne getPersonneById(Long id) {

        for (Personne p : personnes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }

        return null;
    }

    public boolean update(Long id, Personne personne) {

        Personne toUpdate = getPersonneById(id);

        if (toUpdate == null) {
            return false;
        }

        personnes.remove(toUpdate);
        personne.setId(id);
        personnes.add(personne);

        return true;
    }
    
    public boolean delete(Long id) {

        Personne toDelete = getPersonneById(id);

        if (toDelete == null) {
            return false;
        }

        personnes.remove(toDelete);
        return true;
    }
}