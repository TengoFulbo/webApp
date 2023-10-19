package turismouy.svcentral;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import turismouy.svcentral.interfaces.IDepartamentoController;
import turismouy.svcentral.utilidades.log;

public class test {
    
    @org.junit.Test
    public void testDepartamento() {
        IDepartamentoController IDC = Fabrica.getInstance().getIDepartamentoController();

        try {
            IDC.crearDepartamento("Prueba1", "Prueba1", "Prueba1");
            assertTrue(true);
        } catch (Exception e) {
            log.error(e.toString());
            fail("Está mal");
        }
    }
}
