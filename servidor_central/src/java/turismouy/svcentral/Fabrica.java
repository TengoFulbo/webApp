package turismouy.svcentral;

import turismouy.svcentral.controladores.ActividadController;
import turismouy.svcentral.controladores.CategoriaController;
import turismouy.svcentral.controladores.DepartamentoController;
import turismouy.svcentral.controladores.InscripcionController;
import turismouy.svcentral.controladores.PaqueteController;
import turismouy.svcentral.controladores.SalidaController;
import turismouy.svcentral.controladores.UsuarioController;
import turismouy.svcentral.interfaces.IActividadController;
import turismouy.svcentral.interfaces.ICategoriaController;
import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.interfaces.IInscripcionController;
import turismouy.svcentral.interfaces.IPaqueteController;
import turismouy.svcentral.interfaces.ISalidaController;
import turismouy.svcentral.interfaces.IUsuarioController;

public class Fabrica {
    private static Fabrica instancia;
    
    private Fabrica(){};

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    // Entrega las interfaces de los controladores.
    public IUsuarioController getIUsuarioController() {
        return new UsuarioController();
    }

    public IPaqueteController getIPaqueteController() {
        return new PaqueteController();
    }

    public IDepartamentoController getIDepartamentoController() {
        return new DepartamentoController();
    }

    public IActividadController getIActividadController() {
        return new ActividadController();
    }
    
    public ISalidaController getISalidaController() {
        return new SalidaController();
    }
   
    public IInscripcionController getIInscripcionController() {
    	return new InscripcionController();
    }

    public ICategoriaController getICategoriaController() {
        return new CategoriaController();
    }
}
