package tn.iit.services;

import java.util.ArrayList;
import java.util.List;

import tn.iit.entities.Enseignant;
import tn.iit.entities.Matiere;
import tn.iit.persistance.EnseignantsDao;

public class EnseignantService {

    EnseignantsDao enseignantdao = new EnseignantsDao();

    public Enseignant addEnseignant(Enseignant ens) {
        enseignantdao.addEnseignant(ens);
        return ens;
    }

    public void removeEnseignant(String id) {
        // enseignantdao.removeEnseignant(id);
    }

    public Enseignant updateEnseignant(Enseignant ens) {
        return enseignantdao.updateEnseignant(ens);
    }

    public List<Enseignant> getAll() {
        return enseignantdao.getEnseignants();
    }

    public List<Matiere> getEnsMat(String id) {
        return enseignantdao.getEnsMat(id);
    }

    public Enseignant findById(String id) {
        return enseignantdao.findEnsById(id);
    }

    public void deleteEnseignant(String id) {
        enseignantdao.deleteEnseignant(id);
    }
}
