/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Academic.
 *
 * FenixEdu Academic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Academic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Academic.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.academic.domain.serviceRequests;

import org.fenixedu.academic.domain.ExecutionSemester;
import org.fenixedu.academic.domain.ExecutionYear;
import org.fenixedu.academic.domain.StudentCurricularPlan;
import org.fenixedu.academic.domain.accounting.EventType;
import org.fenixedu.academic.domain.accounting.events.serviceRequests.PartialRegistrationRegimeRequestEvent;
import org.fenixedu.academic.domain.accounting.postingRules.FixedAmountPR;
import org.fenixedu.academic.domain.curricularRules.MaximumNumberOfCreditsForEnrolmentPeriod;
import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.domain.serviceRequests.documentRequests.AcademicServiceRequestType;
import org.fenixedu.academic.domain.student.Registration;
import org.fenixedu.academic.domain.student.RegistrationRegime;
import org.fenixedu.academic.domain.student.RegistrationRegimeType;
import org.fenixedu.academic.dto.serviceRequests.AcademicServiceRequestBean;
import org.fenixedu.academic.dto.serviceRequests.RegistrationAcademicServiceRequestCreateBean;
import org.fenixedu.academic.util.Money;

public class PartialRegistrationRegimeRequest extends PartialRegistrationRegimeRequest_Base {

    private PartialRegistrationRegimeRequest() {
        super();
    }

    public PartialRegistrationRegimeRequest(final RegistrationAcademicServiceRequestCreateBean bean) {
        this();
        super.init(bean);
    }

    @Override
    protected void checkRulesToChangeState(AcademicServiceRequestSituationType situationType) {
        super.checkRulesToChangeState(situationType);
        if (situationType == AcademicServiceRequestSituationType.PROCESSING) {
            checkEctsCredits(getRegistration(), getExecutionYear());
        }
    }

    private void checkEctsCredits(final Registration registration, final ExecutionYear executionYear) {
        final StudentCurricularPlan studentCurricularPlan = registration.getLastStudentCurricularPlan();

        double enroledEctsCredits = 0d;
        for (final ExecutionSemester semester : executionYear.getExecutionPeriodsSet()) {
            enroledEctsCredits += studentCurricularPlan.getAccumulatedEctsCredits(semester);

        }

        if (enroledEctsCredits > MaximumNumberOfCreditsForEnrolmentPeriod.MAXIMUM_NUMBER_OF_CREDITS_PARTIAL_TIME) {
            throw new DomainException("error.RegistrationRegime.semester.has.more.ects.than.maximum.allowed",
                    String.valueOf(enroledEctsCredits), executionYear.getQualifiedName(),
                    String.valueOf(MaximumNumberOfCreditsForEnrolmentPeriod.MAXIMUM_NUMBER_OF_CREDITS_PARTIAL_TIME));
        }
    }

    @Override
    protected void internalChangeState(final AcademicServiceRequestBean academicServiceRequestBean) {
        if (academicServiceRequestBean.isToCancelOrReject() && getEvent() != null) {
            getEvent().cancel(academicServiceRequestBean.getResponsible());

        } else if (academicServiceRequestBean.isToConclude()) {
            if (isPayable() && !isPayed()) {
                throw new DomainException("AcademicServiceRequest.hasnt.been.payed");
            }
            academicServiceRequestBean.setSituationDate(getActiveSituation().getSituationDate().toYearMonthDay());
        }
    }

    @Override
    protected boolean isPayed() {
        return super.isPayed() || getEvent().isCancelled();
    }

    @Override
    protected void createAcademicServiceRequestSituations(AcademicServiceRequestBean academicServiceRequestBean) {
        super.createAcademicServiceRequestSituations(academicServiceRequestBean);

        if (academicServiceRequestBean.isToProcess() && !isFree()) {
            FixedAmountPR partialRegistrationPostingRule =
                    (FixedAmountPR) getAdministrativeOffice().getServiceAgreementTemplate().findPostingRuleByEventTypeAndDate(
                            EventType.PARTIAL_REGISTRATION_REGIME_REQUEST,
                            getExecutionYear().getBeginDateYearMonthDay().toDateTimeAtMidnight());

            if (partialRegistrationPostingRule.getFixedAmount().greaterThan(Money.ZERO)) {
                new PartialRegistrationRegimeRequestEvent(getAdministrativeOffice(), getPerson(), this);
            }
        } else if (academicServiceRequestBean.isToConclude()) {
            AcademicServiceRequestSituation.create(this, new AcademicServiceRequestBean(
                    AcademicServiceRequestSituationType.DELIVERED, academicServiceRequestBean.getResponsible()));

            if (!getRegistration().isPartialRegime(getExecutionYear())) {
                new RegistrationRegime(getRegistration(), getExecutionYear(), RegistrationRegimeType.PARTIAL_TIME);
            }
        }
    }

    @Override
    public boolean isAvailableForTransitedRegistrations() {
        return false;
    }

    @Override
    public AcademicServiceRequestType getAcademicServiceRequestType() {
        return AcademicServiceRequestType.PARTIAL_REGIME_REQUEST;
    }

    @Override
    public EventType getEventType() {
        /*
         * For 2010/2011 partial registration is not charged
         */
        if (getExecutionYear().isAfterOrEquals(ExecutionYear.readExecutionYearByName("2010/2011"))) {
            return null;
        }

        return EventType.PARTIAL_REGISTRATION_REGIME_REQUEST;
    }

    @Override
    public boolean hasPersonalInfo() {
        return false;
    }

    @Override
    public boolean isPayedUponCreation() {
        return true;
    }

    @Override
    public boolean isPossibleToSendToOtherEntity() {
        return false;
    }

    @Override
    public boolean isManagedWithRectorateSubmissionBatch() {
        return false;
    }

    @Override
    public boolean isToPrint() {
        return false;
    }

}
