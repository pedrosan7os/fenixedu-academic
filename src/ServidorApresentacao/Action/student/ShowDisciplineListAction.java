package ServidorApresentacao.Action.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import DataBeans.InfoCourse;
import DataBeans.InfoStudent;
import ServidorAplicacao.GestorServicos;
import ServidorAplicacao.IUserView;
import ServidorAplicacao.Servico.DataView;
import ServidorApresentacao.Action.sop.utils.ServiceUtils;
import ServidorApresentacao.Action.sop.utils.SessionUtils;

/**
 * @author Ricardo Nortadas & Rui Figueiredo
 */

public class ShowDisciplineListAction extends Action {

	public static String INFO_STUDENT_KEY = "infoStudent";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {


		IUserView userView = SessionUtils.getUserView(request);

		HttpSession session = request.getSession();

		InfoStudent infoStudent = (InfoStudent) session.getAttribute(INFO_STUDENT_KEY);

		System.out.println(infoStudent.getNumber());
		Object[] argsReadDisciplinesByStudent = { infoStudent.getNumber(), infoStudent.getDegreeType() };
		Object[] argsReadCourseByStudent = { infoStudent.getNumber(), infoStudent.getDegreeType() };


		ArrayList DisciplinesList = new ArrayList();
		InfoCourse Course=null;

		System.out.println("iniciarChamada");
		try {
			DisciplinesList =
				(ArrayList) ServiceUtils.executeService(
						userView,
						"ReadDisciplinesByStudent",
						argsReadDisciplinesByStudent);
			if (!DisciplinesList.isEmpty()) {
					session.setAttribute("disciplinesList", DisciplinesList);
			}

			Course =
				(InfoCourse) ServiceUtils.executeService(
						userView,
						"ReadCourseByStudent",
						argsReadCourseByStudent);
			if (Course!=null) {
				System.out.println(Course.toString());
				session.setAttribute("infoCourse", Course);
			} else {
				System.out.println("course esta a chegar null a Action");
			}
					System.out.println("acabeiServico");
				} catch (Exception e) {
					System.out.println("entrei" + e);
					return mapping.getInputForward();
				}

			/*Calendar calendar = Calendar.getInstance();
			//date.DAY_OF_MONTH
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
			String dia= Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
			request.setAttribute("Dia", dia);*/
			Object argumentos[] = {};

			GestorServicos gestor = GestorServicos.manager();
			DataView data = (DataView) gestor.executar(null, "ObterData", argumentos);
			//request.setAttribute("Data", data);
			session.setAttribute("Data", data);
			System.out.println("Acabei de fazer request da data");


		return mapping.findForward("viewDisciplinesList");

	}

}