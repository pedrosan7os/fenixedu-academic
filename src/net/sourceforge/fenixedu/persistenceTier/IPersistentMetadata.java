/*
 * Created on 23/Jul/2003
 *  
 */

package net.sourceforge.fenixedu.persistenceTier;

import java.util.List;

import net.sourceforge.fenixedu.domain.IDistributedTest;
import net.sourceforge.fenixedu.domain.IExecutionCourse;
import net.sourceforge.fenixedu.domain.IMetadata;
import net.sourceforge.fenixedu.domain.ITest;

/**
 * @author Susana Fernandes
 */
public interface IPersistentMetadata extends IPersistentObject {

    public abstract List readByExecutionCourse(IExecutionCourse executionCourse)
            throws ExcepcaoPersistencia;

    public abstract List readByExecutionCourseAndVisibility(IExecutionCourse executionCourse)
            throws ExcepcaoPersistencia;

    public abstract List readByExecutionCourseAndVisibilityAndOrder(IExecutionCourse executionCourse,
            String order, String asc) throws ExcepcaoPersistencia;

    public abstract List readByExecutionCourseAndNotTest(IExecutionCourse executionCourse, ITest test,
            String order, String asc) throws ExcepcaoPersistencia;

    public List readByExecutionCourseAndNotDistributedTest(IDistributedTest distributedTest)
            throws ExcepcaoPersistencia;

    public abstract int getNumberOfQuestions(IMetadata metadata) throws ExcepcaoPersistencia;

    public abstract int countByExecutionCourse(IExecutionCourse executionCourse)
            throws ExcepcaoPersistencia;

    public void cleanMetadatas() throws ExcepcaoPersistencia;

    public void delete(IMetadata metadata) throws ExcepcaoPersistencia;
}