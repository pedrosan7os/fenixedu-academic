/*
 * ApagarSala.java
 *
 * Created on 25 de Outubro de 2002, 15:36
 */

package ServidorAplicacao.Servico.sop;

/**
 * Servi�o ApagarSala.
 *
 * @author tfc130
 **/
import DataBeans.RoomKey;
import Dominio.ISala;
import ServidorAplicacao.IServico;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;

public class ApagarSala implements IServico {

  private static ApagarSala _servico = new ApagarSala();
  /**
   * The singleton access method of this class.
   **/
  public static ApagarSala getService() {
    return _servico;
  }

  /**
   * The actor of this class.
   **/
  private ApagarSala() { }

  /**
   * Devolve o nome do servico
   **/
  public final String getNome() {
    return "ApagarSala";
  }

  public Object run(RoomKey keySala) throws Exception {

    ISala sala1 = null;
    boolean result = false;

    try {
      ISuportePersistente sp = SuportePersistenteOJB.getInstance();
      sala1 = sp.getISalaPersistente().readByNome(keySala.getNomeSala());
      if (sala1 != null) {
          sp.getISalaPersistente().delete(sala1);
          result = true;
      }
    } catch (ExcepcaoPersistencia ex) {
    	if (ex.getMessage().equals("N�o � poss�vel apagar salas com aulas associadas")){
    	throw new Exception(ex.getMessage());}
    		
      ex.printStackTrace();
      
    }
    
    return new Boolean (result);
  }

}