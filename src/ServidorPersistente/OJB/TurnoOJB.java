/*
 * ITurnoOJB.java
 *
 * Created on 17 de Outubro de 2002, 19:35
 */

package ServidorPersistente.OJB;

/**
 *
 * @author  tfc130
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.odmg.QueryException;

import Dominio.IDisciplinaExecucao;
import Dominio.ITurmaTurno;
import Dominio.ITurno;
import Dominio.ITurnoAluno;
import Dominio.ITurnoAula;
import Dominio.TurmaTurno;
import Dominio.Turno;
import Dominio.TurnoAluno;
import Dominio.TurnoAula;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.ITurnoPersistente;
import Util.TipoAula;

public class TurnoOJB extends ObjectFenixOJB implements ITurnoPersistente {

	public ITurno readByNameAndExecutionCourse(
		String shiftName,
		IDisciplinaExecucao executionCourse)
		throws ExcepcaoPersistencia {

		try {
			ITurno shift = null;
			String oqlQuery = "select turnonome from " + Turno.class.getName();
			oqlQuery += " where nome = $1 and disciplinaExecucao.sigla = $2 "
				+ " and  disciplinaExecucao.executionPeriod.name = $3 "
				+ " and disciplinaExecucao.executionPeriod.executionYear.year = $4";
			query.create(oqlQuery);
			
			query.bind(shiftName);
			query.bind(executionCourse.getSigla());
			query.bind(executionCourse.getExecutionPeriod().getName());
			query.bind(executionCourse.getExecutionPeriod().getExecutionYear().getYear());
			
			List result = (List) query.execute();
			lockRead(result);
			if (result.size() != 0)
				shift = (ITurno) result.get(0);
			return shift;
		
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
	}

	public void lockWrite(ITurno turno) throws ExcepcaoPersistencia {
		super.lockWrite(turno);
	}

	public void delete(ITurno turno) throws ExcepcaoPersistencia {

		try { 

			ITurnoAula turnoAula = null;
			TurnoAulaOJB turnoAulaOJB = new TurnoAulaOJB();
			String oqlQuery = "select all from " + TurnoAula.class.getName();
			oqlQuery
				+= " where turno.nome = $1 and turno.disciplinaExecucao.sigla = $2 "
				+ "and  turno.disciplinaExecucao.licenciaturaExecucao.anoLectivo = $3 and "
				+ " turno.disciplinaExecucao.licenciaturaExecucao.curso.sigla = $4";
			query.create(oqlQuery);
			query.bind(turno.getNome());
			query.bind(turno.getDisciplinaExecucao().getSigla());
			query.bind(
				turno
					.getDisciplinaExecucao()
					.getLicenciaturaExecucao()
					.getAnoLectivo());
			query.bind(
				turno
					.getDisciplinaExecucao()
					.getLicenciaturaExecucao()
					.getCurso()
					.getSigla());
			List result = (List) query.execute();
			lockRead(result);
			Iterator iterador = result.iterator();
			while (iterador.hasNext()) {
				turnoAula = (ITurnoAula) iterador.next();
				turnoAulaOJB.delete(turnoAula);
			}
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
		try {
			ITurmaTurno turmaTurno = null;
			TurmaTurnoOJB turmaTurnoOJB = new TurmaTurnoOJB();
			String oqlQuery1 = "select all from " + TurmaTurno.class.getName();
			oqlQuery1
				+= " where turno.nome = $1 and turno.disciplinaExecucao.sigla = $2 "
				+ "and  turno.disciplinaExecucao.licenciaturaExecucao.anoLectivo = $3 and "
				+ " turno.disciplinaExecucao.licenciaturaExecucao.curso.sigla = $4";
			query.create(oqlQuery1);
			query.bind(turno.getNome());
			query.bind(turno.getDisciplinaExecucao().getSigla());
			query.bind(
				turno
					.getDisciplinaExecucao()
					.getLicenciaturaExecucao()
					.getAnoLectivo());
			query.bind(
				turno
					.getDisciplinaExecucao()
					.getLicenciaturaExecucao()
					.getCurso()
					.getSigla());
			List result = (List) query.execute();
			lockRead(result);
			Iterator iterador = result.iterator();
			while (iterador.hasNext()) {
				turmaTurno = (ITurmaTurno) iterador.next();
				turmaTurnoOJB.delete(turmaTurno);
			}
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
		try {
			ITurnoAluno turnoAluno = null;
			TurnoAlunoOJB turnoAlunoOJB = new TurnoAlunoOJB();
			String oqlQuery2 = "select all from " + TurnoAluno.class.getName();
			oqlQuery2
				+= " where turno.nome = $1 and turno.disciplinaExecucao.sigla = $2 "
				+ "and  turno.disciplinaExecucao.licenciaturaExecucao.anoLectivo = $3 and "
				+ " turno.disciplinaExecucao.licenciaturaExecucao.curso.sigla = $4";
			query.create(oqlQuery2);
			query.bind(turno.getNome());
			query.bind(turno.getDisciplinaExecucao().getSigla());
			query.bind(
				turno
					.getDisciplinaExecucao()
					.getLicenciaturaExecucao()
					.getAnoLectivo());
			query.bind(
				turno
					.getDisciplinaExecucao()
					.getLicenciaturaExecucao()
					.getCurso()
					.getSigla());
			List result = (List) query.execute();
			lockRead(result);
			Iterator iterador = result.iterator();
			while (iterador.hasNext()) {
				turnoAluno = (ITurnoAluno) iterador.next();
				turnoAlunoOJB.delete(turnoAluno);
			}
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}

		//		
		//		ITurno turnoCriteria = new Turno();
		//		
		//		turnoCriteria.setNome(turno.getNome());
		//		turnoCriteria.setDisciplinaExecucao(turno.getDisciplinaExecucao());
		//		
		//		ITurmaTurno turmaTurno = new TurmaTurno(); 
		//		turmaTurno.setTurno(turnoCriteria);
		//
		//		ITurnoAula turnoAula = new TurnoAula();
		//		turnoAula.setTurno(turnoCriteria);

		super.delete(turno);

		//		deleteByCriteria(turmaTurno);
		//		deleteByCriteria(turnoAula);

		/*
		TurmaTurnoOJB turmaTurnoOJB = new TurmaTurnoOJB();
		TurnoAulaOJB turnoAulaOJB = new TurnoAulaOJB();
		
		turmaTurnoOJB.delete(turmaTurno);
		turnoAulaOJB.delete(turnoAula);
		*/

	}

	public void deleteAll() throws ExcepcaoPersistencia {
		try {
			String oqlQuery = "select all from " + Turno.class.getName();
			query.create(oqlQuery);
			List result = (List) query.execute();
			Iterator iterator = result.iterator();
			while(iterator.hasNext()){
				delete((ITurno) iterator.next());
			}
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
	}

	public Integer countAllShiftsOfAllClassesAssociatedWithShift(ITurno shift) throws ExcepcaoPersistencia {
		try {
			String oqlQuery = "select all from " + TurmaTurno.class.getName()
							+ " where turno.nome = $1 "
							+ " and turno.disciplinaExecucao.sigla = $2 "
							+ " and turno.disciplinaExecucao.executionPeriod.name = $3 "
							+ " and turno.disciplinaExecucao.executionPeriod.executionYear.year = $4"
							+ " and turno.tipo = $5";
			query.create(oqlQuery);
			
			query.bind(shift.getNome());
			query.bind(shift.getDisciplinaExecucao().getSigla());
			query.bind(shift.getDisciplinaExecucao().getExecutionPeriod().getName());
			query.bind(shift.getDisciplinaExecucao().getExecutionPeriod().getExecutionYear().getYear());
			query.bind(shift.getTipo().getTipo());

			List result = (List) query.execute();
			lockRead(result);

			//return new Integer(result.size());
			List result2 = null;
			for (int i = 0; i != result.size(); i++) {
				try {
					oqlQuery = "select all from " + TurmaTurno.class.getName();
					//oqlQuery += ", " + Turma.class.getName() + ", " + TurmaTurno.class.getName() + ")";
					oqlQuery += " where turno.tipo = $1 and turma.nome = $2";
					query.create(oqlQuery);
					query.bind(new Integer(TipoAula.PRATICA));
					query.bind(
						((TurmaTurno) (result.get(i))).getTurma().getNome());
					if (i == 0) {
						result2 = (List) query.execute();
						lockRead(result2);
					} else {
						List result_tmp = (List) query.execute();
						lockRead(result_tmp);
						for (int j = 0; j != result_tmp.size(); j++)
							if (!result2.contains(result_tmp.get(j)))
								result2.add(result_tmp.get(j));
					}
				} catch (QueryException ex) {
					throw new ExcepcaoPersistencia(
						ExcepcaoPersistencia.QUERY,
						ex);
				}
			}
			return new Integer(result2.size());

		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
	}

	public ArrayList readByDisciplinaExecucao(
		String sigla,
		String anoLectivo,
		String siglaLicenciatura)
		throws ExcepcaoPersistencia {
		try {
			ArrayList turnos = new ArrayList();
			String oqlQuery = "select turnos from " + Turno.class.getName();
			oqlQuery += " where disciplinaExecucao.sigla = $1"
					 + " and disciplinaExecucao.executionPeriod.executionYear.year = $2"
					 + " and disciplinaExecucao.associatedCurricularCourses.degreeCurricularPlan.curso.sigla = $3";
			query.create(oqlQuery);
			query.bind(sigla);
			query.bind(anoLectivo);
			query.bind(siglaLicenciatura);
			List result = (List) query.execute();
			lockRead(result);
			
			System.out.println(result.size());
			
			for (int i = 0; i != result.size(); i++)
				turnos.add((ITurno) (result.get(i)));
			return turnos;
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
	}
	public List readByExecutionCourseAndType(
		IDisciplinaExecucao executionCourse,
		Integer type)
		throws ExcepcaoPersistencia {
		try {
			String oqlQuery = "select turnos from " + Turno.class.getName();
			oqlQuery += " where disciplinaExecucao.sigla = $1";
			oqlQuery
				+= " and disciplinaExecucao.executionPeriod.name = $2";
			oqlQuery
				+= " and disciplinaExecucao.executionPeriod.executionYear.year = $3";
			oqlQuery += " and tipo = $4";
			query.create(oqlQuery);
			query.bind(executionCourse.getSigla());
			query.bind(executionCourse.getExecutionPeriod().getName());
			query.bind(executionCourse.getExecutionPeriod().getExecutionYear().getYear());
			query.bind(type);
			List result = (List) query.execute();
			lockRead(result);
			return result;
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
	}
	/**
	 * @see ServidorPersistente.ITurnoPersistente#readByExecutionCourse(Dominio.IDisciplinaExecucao)
	 */
	public List readByExecutionCourse(IDisciplinaExecucao executionCourse)
		throws ExcepcaoPersistencia {
		try {
			String oqlQuery = "select turnos from " + Turno.class.getName();
			oqlQuery += " where disciplinaExecucao.sigla = $1"
				+ " and disciplinaExecucao.executionPeriod.name = $2"
				+ " and disciplinaExecucao.executionPeriod.executionYear.year = $3";
			query.create(oqlQuery);
			query.bind(executionCourse.getSigla());
			query.bind(executionCourse.getExecutionPeriod().getName());
			query.bind(executionCourse.getExecutionPeriod().getExecutionYear().getYear());
			
			List result = (List) query.execute();
			lockRead(result);
			return result;
		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
	}

}