package ServidorAplicacao.Servicos.student;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import DataBeans.InfoExecutionCourse;
import DataBeans.InfoDegree;
import DataBeans.InfoExecutionDegree;
import ServidorAplicacao.Servicos.TestCaseServicos;

public class ReadShiftsByTypeFromExecutionCourseServicesTest extends TestCaseServicos {
	public ReadShiftsByTypeFromExecutionCourseServicesTest(java.lang.String testName) {
		super(testName);
	}

	public static void main(java.lang.String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		TestSuite suite =
			new TestSuite(ReadShiftsByTypeFromExecutionCourseServicesTest.class);

		return suite;
	}

	protected void setUp() {
		super.setUp();
	}

	protected void tearDown() {
		super.tearDown();
	}

	// read turnos by unauthorized user
	public void testUnauthorizedReadAulas() {
		Object argsLerTurnos[] =
			{
				new InfoExecutionCourse(
					_disciplinaExecucao1.getNome(),
					_disciplinaExecucao1.getSigla(),
					_disciplinaExecucao1.getPrograma(),
					new InfoExecutionDegree(
						_disciplinaExecucao1
							.getLicenciaturaExecucao()
							.getAnoLectivo(),
						new InfoDegree(
							_disciplinaExecucao1
								.getLicenciaturaExecucao()
								.getCurso()
								.getSigla(),
							_disciplinaExecucao1
								.getLicenciaturaExecucao()
								.getCurso()
								.getNome())),
					_disciplinaExecucao1.getTheoreticalHours(),
					_disciplinaExecucao1.getPraticalHours(),
					_disciplinaExecucao1.getTheoPratHours(),
					_disciplinaExecucao1.getLabHours()),
				_turno1.getTipo()};
		Object result = null;
		try {
			result =
				_gestor.executar(
					_userView2,
					"ReadShiftsByTypeFromExecutionCourse",
					argsLerTurnos);
			fail("testUnauthorizedReadTurnos");
		} catch (Exception ex) {
			assertNull("testUnauthorizedReadTurnos", result);
		}
	}

	// read new existing turnos
	public void testReadExistingTurnos() {
		Object argsLerTurnos[] =
			{
				new InfoExecutionCourse(
					_disciplinaExecucao1.getNome(),
					_disciplinaExecucao1.getSigla(),
					_disciplinaExecucao1.getPrograma(),
					new InfoExecutionDegree(
						_disciplinaExecucao1
							.getLicenciaturaExecucao()
							.getAnoLectivo(),
						new InfoDegree(
							_disciplinaExecucao1
								.getLicenciaturaExecucao()
								.getCurso()
								.getSigla(),
							_disciplinaExecucao1
								.getLicenciaturaExecucao()
								.getCurso()
								.getNome())),
						_disciplinaExecucao1.getTheoreticalHours(),
						_disciplinaExecucao1.getPraticalHours(),
						_disciplinaExecucao1.getTheoPratHours(),
						_disciplinaExecucao1.getLabHours()),

				_turno1.getTipo()};
		Object result = null;
		try {
			
			result =
				_gestor.executar(
					_userView,
					"ReadShiftsByTypeFromExecutionCourse",
					argsLerTurnos);
			
			assertEquals("testLerExistingTurnos", 2, ((List) result).size());
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			fail("testLerExistingTurnos");
		}
	}

	// read new non-existing turnos
	public void testReadNonExistingTurnos() {
		Object argsLerTurnos[] =
			{
				new InfoExecutionCourse(
					_disciplinaExecucao2.getNome(),
					_disciplinaExecucao2.getSigla(),
					_disciplinaExecucao2.getPrograma(),
					new InfoExecutionDegree(
						_disciplinaExecucao2
							.getLicenciaturaExecucao()
							.getAnoLectivo(),
						new InfoDegree(
							_disciplinaExecucao2
								.getLicenciaturaExecucao()
								.getCurso()
								.getSigla(),
							_disciplinaExecucao2
								.getLicenciaturaExecucao()
								.getCurso()
								.getNome())),
						_disciplinaExecucao2.getTheoreticalHours(),
						_disciplinaExecucao2.getPraticalHours(),
						_disciplinaExecucao2.getTheoPratHours(),
						_disciplinaExecucao2.getLabHours()),

				_turno1.getTipo()};
		Object result = null;
		try {
			result =
				_gestor.executar(
					_userView,
					"ReadShiftsByTypeFromExecutionCourse",
					argsLerTurnos);
			assertTrue("testLerNonExistingTurnos", ((List) result).isEmpty());
		} catch (Exception ex) {
			fail("testLerNonExistingTurnos");
		}
	}

}