package middleware;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import Dominio.Curso;
import Dominio.CursoExecucao;
import Dominio.ICurso;
import Dominio.ICursoExecucao;
import Dominio.IPlanoCurricularCurso;
import Dominio.PlanoCurricularCurso;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.ICursoExecucaoPersistente;
import ServidorPersistente.ICursoPersistente;
import ServidorPersistente.IPlanoCurricularCursoPersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;
import Util.TipoCurso;

public class LoadDegrees extends DataFileLoader{
	

	private LoadDegrees() throws IOException{
		super();
	}

	public static void main(String[] args) {
	
		try {
			new LoadDegrees();
		} catch (IOException e) {
			e.printStackTrace(System.out);
			System.out.println("Erro a carregar as propriedades");
			System.exit(1);
		}
		try {

			// Obter as interfaces para o suporte persistente.
			ICursoPersistente iLicenciaturaPersistente =
				SuportePersistenteOJB.getInstance().getICursoPersistente();
			ICursoExecucaoPersistente iLicenciaturaExecucaoPersistente =
				SuportePersistenteOJB
					.getInstance()
					.getICursoExecucaoPersistente();

			// Ler o ficheiro de entrada.
			BufferedReader bufferedReader =
				new BufferedReader(new FileReader(getDataFilePath()));
			String line = bufferedReader.readLine();
			while (line != null) {
				carregarLicenciatura(line);
				line = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("CarregarLicenciatura: " + e.toString());
		}
	}

	private static void carregarLicenciatura(String line)
		throws ExcepcaoPersistencia {
		StringTokenizer stringTokenizer = new StringTokenizer(line, getFieldSeparator());
		// Obter os dados da licenciatura.
		String codigo = stringTokenizer.nextToken();
		String nome = stringTokenizer.nextToken();

		SuportePersistenteOJB sp = SuportePersistenteOJB.getInstance();

		// Obter as interfaces para o suporte persistente.
		ICursoPersistente iLicenciaturaPersistente =
			sp.getICursoPersistente();
		ICursoExecucaoPersistente iLicenciaturaExecucaoPersistente =
			sp.getICursoExecucaoPersistente();
		IPlanoCurricularCursoPersistente degreeCurricularPlanDAO = sp.getIPlanoCurricularCursoPersistente(); 
		
		//IRamoPersistente iRamoPersistente = SuportePersistente.getInstance().iRamoPersistente();       

		if (codigo.endsWith("0")) { 

			// Obter c�digo da licenciatura.
			int codigoLicenciatura = Integer.parseInt(codigo) / 10;

			// Criar licenciatura.
			ICurso degree =
				new Curso(
					MiddlewareUtil.gerarSiglaDeLicenciatura(codigoLicenciatura),
					nome,
					new TipoCurso(TipoCurso.LICENCIATURA));

			IPlanoCurricularCurso degreeCurricularPlan = new PlanoCurricularCurso(nome, degree.getSigla(), degree);
			sp.iniciarTransaccao();

			//iLicenciaturaPersistente.lockWrite(degree);
			degreeCurricularPlanDAO.lockWrite(degreeCurricularPlan);

			// Criar licenciaturas execu��o.

			ICursoExecucao executionDegree =
				new CursoExecucao("2002/2003", degree);

			iLicenciaturaExecucaoPersistente.lockWrite(executionDegree);

			sp.confirmarTransaccao();
		} else {
			/* Ignoramos para j� os Ramos */
			// Obter o c�digo da licenciatura.
			//            int codigoLicenciatura = Integer.parseInt(codigo) / 10;
			//            
			//            // Obter o c�digo do ramo.
			//            int codigoRamo = Integer.parseInt(codigo) % 10;
			//            
			//            // Criar ramo.
			//            Ramo ramo = new Ramo(codigoRamo, nome, codigoLicenciatura);
			//            iRamoPersistente.escreverRamo(ramo);
		}

	}

}