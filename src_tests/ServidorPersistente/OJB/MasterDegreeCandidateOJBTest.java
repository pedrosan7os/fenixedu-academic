/*
 * MasterDegreeCandidateOJBTest.java
 * NetBeans JUnit based test
 *
 * Created on 17 de Outubro de 2002, 11:37
 * 
 * Testes :
 *  - 1 : Read existing MasterDegreeCandidate by username
 *  - 2 : Read non-existing MasterDegreeCandidate by username
 *  - 3 : Write existing MasterDegreeCandidate 
 *  - 4 : Write non-existing MasterDegreeCandidate 
 *  - 5 : Delete existing MasterDegreeCandidate 
 *  - 6 : Delete non-existing MasterDegreeCandidate 
 *  - 7 : Delete all MasterDegreeCandidates 
 *  - 8 : Test equal MasterDegreeCandidates
 *  - 9 : Read existing MasterDegreeCandidate by candidateNumber, applicationYear and masterDegreeCode
 *  - 10 : Read non-existing MasterDegreeCandidate by candidateNumber, applicationYear and masterDegreeCode
 */
 
/**
 *
 * Autores :
 *   - Nuno Nunes (nmsn@rnl.ist.utl.pt)
 *   - Joana Mota (jccm@rnl.ist.utl.pt)
 *
 */

package ServidorPersistente.OJB;

import java.util.Calendar;

import junit.framework.Test;
import junit.framework.TestSuite;

import Dominio.ICountry;
import Dominio.ICurso;
import Dominio.IMasterDegreeCandidate;
import Dominio.MasterDegreeCandidate;
import ServidorPersistente.ExcepcaoPersistencia;
import Util.EstadoCivil;
import Util.Sexo;
import Util.Specialization;
import Util.TipoDocumentoIdentificacao;

public class MasterDegreeCandidateOJBTest extends TestCaseOJB {
    
    public MasterDegreeCandidateOJBTest(java.lang.String testName) {
        super(testName);
    }
    
    public static void main(java.lang.String[] args) {
        System.out.println("Beginning of test from class MasterDegreeCandidateOJB" + "\n");
        junit.textui.TestRunner.run(suite());
        System.out.println("End of test from class MasterDegreeCandidateOJB" + "\n");       
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(MasterDegreeCandidateOJBTest.class);
        
        return suite;
    }
    
    protected void setUp(){
        super.setUp();
    }
    
    protected void tearDown(){
//        super.tearDown();
    }
    
    public void testReadExistingMasterDegreeCandidate() {
        System.out.println("- Test 1 : Read Existing Master Degree Candidate");
        IMasterDegreeCandidate masterDegreeCandidateTemp = null;
        
        try {
            _suportePersistente.iniciarTransaccao();
            masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByUsername("Cand1");
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
        // Testing the obtained candidate
        
        assertTrue(masterDegreeCandidateTemp.getIdentificationDocumentNumber().equals("112233"));
        assertEquals(masterDegreeCandidateTemp.getIdentificationDocumentType(), new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE));
        assertTrue(masterDegreeCandidateTemp.getIdentificationDocumentIssuePlace().equals("Lisboa"));
        
        assertTrue(masterDegreeCandidateTemp.getIdentificationDocumentIssueDate().toString().equals("2002-11-17"));
        assertTrue(masterDegreeCandidateTemp.getName().equals("Nuno Nunes"));
        assertEquals(masterDegreeCandidateTemp.getSex(), new Sexo(Sexo.MASCULINO));
        assertEquals(masterDegreeCandidateTemp.getMaritalStatus(), new EstadoCivil(EstadoCivil.SOLTEIRO));
        
        assertTrue(masterDegreeCandidateTemp.getBirth().toString().equals("2000-12-10"));
        assertTrue(masterDegreeCandidateTemp.getFatherName().equals("Manuel"));
        assertTrue(masterDegreeCandidateTemp.getMotherName().equals("Maria"));
        assertTrue(masterDegreeCandidateTemp.getNationality().equals("Portugal"));
        assertTrue(masterDegreeCandidateTemp.getBirthPlaceParish().equals("Oeiras"));
        assertTrue(masterDegreeCandidateTemp.getBirthPlaceMunicipality().equals("Oeiras"));
        assertTrue(masterDegreeCandidateTemp.getBirthPlaceDistrict().equals("Lisboa"));
        assertTrue(masterDegreeCandidateTemp.getAddress().equals("Rua Nuno"));
        assertTrue(masterDegreeCandidateTemp.getPlace().equals("Localidade Nuno"));
        assertTrue(masterDegreeCandidateTemp.getPostCode().equals("2795-833"));
        assertTrue(masterDegreeCandidateTemp.getAddressParish().equals("Queijas"));
        assertTrue(masterDegreeCandidateTemp.getAddressMunicipality().equals("Oeiras"));
        assertTrue(masterDegreeCandidateTemp.getAddressDistrict().equals("Lisboa"));
        assertTrue(masterDegreeCandidateTemp.getTelephone().equals("11111"));
        assertTrue(masterDegreeCandidateTemp.getMobilePhone().equals("33333"));
        assertTrue(masterDegreeCandidateTemp.getEmail().equals("nmsn@rnl.ist.utl.pt"));
        assertTrue(masterDegreeCandidateTemp.getWebSite().equals("www.nuno.com"));
        assertTrue(masterDegreeCandidateTemp.getContributorNumber().equals("11111"));
        assertTrue(masterDegreeCandidateTemp.getOccupation().equals("Estudante"));
        assertTrue(masterDegreeCandidateTemp.getMajorDegree().equals("LEIC"));
        assertTrue(masterDegreeCandidateTemp.getUsername().equals("Cand1"));
        assertTrue(masterDegreeCandidateTemp.getPassword().equals("Pass1"));
        assertTrue(masterDegreeCandidateTemp.getCandidateNumber().equals(new Integer(1)));
        assertTrue(masterDegreeCandidateTemp.getApplicationYear().equals(new Integer(2002)));
        assertEquals(masterDegreeCandidateTemp.getSpecialization(), new Specialization(Specialization.MESTRADO));
        assertTrue(masterDegreeCandidateTemp.getMajorDegreeSchool().equals("IST"));
        assertTrue(masterDegreeCandidateTemp.getMajorDegreeYear().equals(new Integer(2000)));
        assertTrue(masterDegreeCandidateTemp.getAverage().equals(new Double(11)));

        assertEquals(masterDegreeCandidateTemp.getSituations().size(), 1);

    }
    
    public void testReadNonExistingMasterDegreeCandidate() {
        System.out.println("- Test 2 : Read non-existing Master Degree Candidate");
        IMasterDegreeCandidate masterDegreeCandidateTemp = null;
        
        try {
            _suportePersistente.iniciarTransaccao();
            masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByUsername("Cand_Inex");
            assertNull(masterDegreeCandidateTemp);
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
    }
 
    
    public void testWriteExistingMasterDegreeCandidate() {
        System.out.println("- Test 3 : Write Existing Master Degree Candidate");    

        Calendar data = Calendar.getInstance();
        data.set(2002, Calendar.NOVEMBER, 17);

        ICurso masterDegreeTemp = null;
		ICountry countryTemp = null;
        try {
            _suportePersistente.iniciarTransaccao();
	        masterDegreeTemp = cursoPersistente.readBySigla("MEIC");
	        countryTemp = persistentCountry.readCountryByName("Portugal");
            _suportePersistente.confirmarTransaccao();
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }


        IMasterDegreeCandidate candidateTemp = new MasterDegreeCandidate("1", new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE), "1", data.getTime(), "Nome", new Sexo(Sexo.MASCULINO), new EstadoCivil(EstadoCivil.SOLTEIRO), data.getTime(), "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "Cand1", "Pass1", new Integer(1), new Integer(2002), new Specialization(Specialization.MESTRADO), "1", new Integer(1), new Double(1.0), masterDegreeTemp, countryTemp);
        try {
            _suportePersistente.iniciarTransaccao();
            persistentMasterDegreeCandidate.writeMasterDegreeCandidate(candidateTemp);
            _suportePersistente.confirmarTransaccao();
            fail("testWriteExistingMasterDegreeCandidate");
        } catch (ExcepcaoPersistencia ex) {
            // All is OK
        }
    }
    
    
    public void testWriteNonExistingMasterDegreeCandidate() {
        System.out.println("- Test 4 : Write NonExisting Master Degree Candidate");    


        Calendar data = Calendar.getInstance();
        data.set(2002, Calendar.NOVEMBER, 17);

        ICurso masterDegreeTemp = null;
		ICountry countryTemp = null;
        try {
            _suportePersistente.iniciarTransaccao();
	        masterDegreeTemp = cursoPersistente.readBySigla("MIC");
	        countryTemp = persistentCountry.readCountryByName("Inglaterra");
            _suportePersistente.confirmarTransaccao();
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
		assertNotNull(masterDegreeTemp);
		assertNotNull(countryTemp);

        IMasterDegreeCandidate candidateTemp = new MasterDegreeCandidate("3", new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE), "2", data.getTime(), "Nome2", new Sexo(Sexo.MASCULINO), new EstadoCivil(EstadoCivil.SOLTEIRO), data.getTime(), "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "Cand3", "Pass12", new Integer(12), new Integer(2002), new Specialization(Specialization.MESTRADO), "12", new Integer(12), new Double(2.0), masterDegreeTemp, countryTemp);
        
        try {
            _suportePersistente.iniciarTransaccao();
            persistentMasterDegreeCandidate.writeMasterDegreeCandidate(candidateTemp);
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
        
        candidateTemp = null;
        
        try {
            _suportePersistente.iniciarTransaccao();
            candidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByUsername("Cand3");
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
        // Testing the obtained Candidate
        assertTrue(candidateTemp.getIdentificationDocumentNumber().equals("3"));
        assertEquals(candidateTemp.getIdentificationDocumentType(), new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE));
        assertTrue(candidateTemp.getIdentificationDocumentIssuePlace().equals("2"));
        
        assertTrue(candidateTemp.getIdentificationDocumentIssueDate().toString().equals("2002-11-17"));
        assertTrue(candidateTemp.getName().equals("Nome2"));
        assertEquals(candidateTemp.getSex(), new Sexo(Sexo.MASCULINO));
        assertEquals(candidateTemp.getMaritalStatus(), new EstadoCivil(EstadoCivil.SOLTEIRO));
        assertTrue(candidateTemp.getBirth().toString().equals("2002-11-17"));
        assertTrue(candidateTemp.getFatherName().equals("12"));
        assertTrue(candidateTemp.getMotherName().equals("12"));
        assertTrue(candidateTemp.getNationality().equals("12"));
        assertTrue(candidateTemp.getBirthPlaceParish().equals("12"));
        assertTrue(candidateTemp.getBirthPlaceMunicipality().equals("12"));
        assertTrue(candidateTemp.getBirthPlaceDistrict().equals("12"));
        assertTrue(candidateTemp.getAddress().equals("12"));
        assertTrue(candidateTemp.getPlace().equals("12"));
        assertTrue(candidateTemp.getPostCode().equals("12"));
        assertTrue(candidateTemp.getAddressParish().equals("12"));
        assertTrue(candidateTemp.getAddressMunicipality().equals("12"));
        assertTrue(candidateTemp.getAddressDistrict().equals("12"));
        assertTrue(candidateTemp.getTelephone().equals("12"));
        assertTrue(candidateTemp.getMobilePhone().equals("12"));
        assertTrue(candidateTemp.getEmail().equals("12"));
        assertTrue(candidateTemp.getWebSite().equals("12"));
        assertTrue(candidateTemp.getContributorNumber().equals("12"));
        assertTrue(candidateTemp.getOccupation().equals("12"));
        assertTrue(candidateTemp.getMajorDegree().equals("12"));
        assertTrue(candidateTemp.getUsername().equals("Cand3"));
        assertTrue(candidateTemp.getPassword().equals("Pass12"));
        assertTrue(candidateTemp.getCandidateNumber().equals(new Integer(12)));
        assertTrue(candidateTemp.getApplicationYear().equals(new Integer(2002)));
        assertEquals(candidateTemp.getSpecialization(), new Specialization(Specialization.MESTRADO));
        assertTrue(candidateTemp.getMajorDegreeSchool().equals("12"));
        assertTrue(candidateTemp.getMajorDegreeYear().equals(new Integer(12)));
        assertTrue(candidateTemp.getAverage().equals(new Double(2.0)));

    } 
    
    
    public void testDeleteExistingMasterDegreeCandidate() {
        System.out.println("- Test 5 : Delete Existing Master Degree Candidate");
        IMasterDegreeCandidate masterDegreeCandidateTemp = null;
        try {
            _suportePersistente.iniciarTransaccao();
			masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByUsername("Cand1");
            persistentMasterDegreeCandidate.delete(masterDegreeCandidateTemp);
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
        
        masterDegreeCandidateTemp = null;
        
        // test if it was really deleted
        try {
            _suportePersistente.iniciarTransaccao();
            masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByUsername("Cand1");
            assertNull(masterDegreeCandidateTemp);
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
    } 
    
    public void testDeleteNonExistingMasterDegreeCandidate() {
        System.out.println("- Test 6 : Delete NonExisting Master Degree Candidate");

        IMasterDegreeCandidate masterDegreeCandidateTemp = null;
        try {
            _suportePersistente.iniciarTransaccao();
			masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByUsername("Cand1");
            _suportePersistente.confirmarTransaccao();
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }


        // Clean all
        try {
            _suportePersistente.iniciarTransaccao();
            persistentMasterDegreeCandidate.deleteAll();
            _suportePersistente.confirmarTransaccao();
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
        
        try {
            _suportePersistente.iniciarTransaccao();
            persistentMasterDegreeCandidate.delete(masterDegreeCandidateTemp);
            _suportePersistente.confirmarTransaccao();
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
    } 
    
   
    public void testDeleteAllMasterDegreeCandidates() {
        System.out.println("- Test 7 : Delete All Master Degree Candidates");
        IMasterDegreeCandidate masterDegreeCandidateTemp = null;
        try {
            _suportePersistente.iniciarTransaccao();
            persistentMasterDegreeCandidate.deleteAll();
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
        
        // Test if it was really deleted
        try {
            _suportePersistente.iniciarTransaccao();
            masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByUsername("Cand1");
            assertNull(masterDegreeCandidateTemp);
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
    } 
    
    public  void testEqualMasterDegreeCandidates() {
        System.out.println("- Test 8 : Test if two Master Degree Candidates are equal");
        IMasterDegreeCandidate masterDegreeCandidateTemp1 = null;
        IMasterDegreeCandidate masterDegreeCandidateTemp2 = null;

        Calendar data = Calendar.getInstance();
        data.set(2002, Calendar.NOVEMBER, 17);

        Calendar data2 = Calendar.getInstance();
        data2.set(2002, 12, 18);

        ICurso masterDegreeTemp1 = null;
        ICountry countryTemp = null;
        try {
            _suportePersistente.iniciarTransaccao();
	        masterDegreeTemp1 = cursoPersistente.readBySigla("MEIC");
			countryTemp = persistentCountry.readCountryByName("Portugal");
            _suportePersistente.confirmarTransaccao();
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }


        
        masterDegreeCandidateTemp1 = new MasterDegreeCandidate("2", new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE), "2", data.getTime(), "Nome2", new Sexo(Sexo.MASCULINO), new EstadoCivil(EstadoCivil.SOLTEIRO), data.getTime(), "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "Cand2", "Pass12", new Integer(12), new Integer(2002), new Specialization(Specialization.MESTRADO), "12", new Integer(12), new Double(2.0), masterDegreeTemp1, countryTemp);
        masterDegreeCandidateTemp2 = new MasterDegreeCandidate("2", new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE), "2", data.getTime(), "Nome2", new Sexo(Sexo.MASCULINO), new EstadoCivil(EstadoCivil.SOLTEIRO), data.getTime(), "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "Cand2", "Pass12", new Integer(12), new Integer(2002), new Specialization(Specialization.MESTRADO), "12", new Integer(12), new Double(2.0), masterDegreeTemp1, countryTemp);
        
        assertTrue(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2));  
        
        masterDegreeCandidateTemp1.setIdentificationDocumentNumber("3");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setIdentificationDocumentNumber("2");
        
        masterDegreeCandidateTemp1.setIdentificationDocumentType(new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.PASSAPORTE));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setIdentificationDocumentType(new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE));
        
        masterDegreeCandidateTemp1.setIdentificationDocumentIssuePlace("3");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setIdentificationDocumentIssuePlace("2");
        
        masterDegreeCandidateTemp1.setIdentificationDocumentIssueDate(data2.getTime());
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setIdentificationDocumentIssueDate(data.getTime());

        masterDegreeCandidateTemp1.setName("OLA");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setName("Nome2");

        masterDegreeCandidateTemp1.setSex(new Sexo(Sexo.FEMININO));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setSex(new Sexo(Sexo.MASCULINO));

        masterDegreeCandidateTemp1.setMaritalStatus(new EstadoCivil(EstadoCivil.CASADO));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setMaritalStatus(new EstadoCivil(EstadoCivil.SOLTEIRO));
        
        masterDegreeCandidateTemp1.setBirth(data2.getTime());
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);
        masterDegreeCandidateTemp1.setBirth(data.getTime());

        masterDegreeCandidateTemp1.setFatherName("pai");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setFatherName("1");

        masterDegreeCandidateTemp1.setMotherName("mae");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setMotherName("1");
        
        masterDegreeCandidateTemp1.setNationality("tuga");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setNationality("1");

        masterDegreeCandidateTemp1.setBirthPlaceParish("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setBirthPlaceParish("1");
        
        masterDegreeCandidateTemp1.setBirthPlaceMunicipality("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setBirthPlaceMunicipality("1");
        
        masterDegreeCandidateTemp1.setBirthPlaceDistrict("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setBirthPlaceDistrict("1");

        masterDegreeCandidateTemp1.setAddress("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setAddress("1");
        
        masterDegreeCandidateTemp1.setPlace("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setPlace("1");
        
        masterDegreeCandidateTemp1.setPostCode("1234-567");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setPostCode("1");
        
        masterDegreeCandidateTemp1.setAddressParish("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setAddressParish("1");
        
        masterDegreeCandidateTemp1.setAddressMunicipality("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setAddressMunicipality("1");
        
        masterDegreeCandidateTemp1.setAddressDistrict("lisboa");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setAddressDistrict("1");

        masterDegreeCandidateTemp1.setTelephone("123456789");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setTelephone("1");
        
        masterDegreeCandidateTemp1.setMobilePhone("123456789");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setMobilePhone("1");
        
        masterDegreeCandidateTemp1.setEmail("mail@mail");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setEmail("1");
        
        masterDegreeCandidateTemp1.setWebSite("www.ola.pt");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setWebSite("1");
        
        masterDegreeCandidateTemp1.setContributorNumber("123456789");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setContributorNumber("1");
        
        masterDegreeCandidateTemp1.setOccupation("estudante");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setOccupation("1");
        
        masterDegreeCandidateTemp1.setMajorDegree("LEIC");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setMajorDegree("1");
        
        masterDegreeCandidateTemp1.setUsername("ola");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setUsername("Cand1");
        
        masterDegreeCandidateTemp1.setPassword("njvifmcvkd");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setPassword("Pass1");
        
        masterDegreeCandidateTemp1.setCandidateNumber(new Integer(123456789));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setCandidateNumber(new Integer(1));
        
        masterDegreeCandidateTemp1.setApplicationYear(new Integer(2000));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setApplicationYear(new Integer(2002));

        masterDegreeCandidateTemp1.setSpecialization(new Specialization(Specialization.ESPECIALIZACAO));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setSpecialization(new Specialization(Specialization.MESTRADO));
        
        masterDegreeCandidateTemp1.setMajorDegreeSchool("IST");
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setMajorDegreeSchool("1");
        
        masterDegreeCandidateTemp1.setMajorDegreeYear(new Integer(2000));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setMajorDegreeYear(new Integer(2002));
        
        masterDegreeCandidateTemp1.setAverage(new Double(20));
        assertEquals(masterDegreeCandidateTemp1.equals(masterDegreeCandidateTemp2), false);  
        masterDegreeCandidateTemp1.setAverage(new Double(1.0));
    }

    
    public void testReadExistingMasterDegreeCandidate2() {
        System.out.println("- Test 9 : Read existing Master Degree Candidate by candidateNumber, applicationYear and masterDegreeCode");
        IMasterDegreeCandidate masterDegreeCandidateTemp = null;
        
        try {
            _suportePersistente.iniciarTransaccao();
            masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByNumberAndApplicationYearAndDegreeCode(new Integer(1), new Integer(2002), "MIC");
            _suportePersistente.confirmarTransaccao();
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
        
        
        
        // Testing the obtained Candidate 
        assertTrue(masterDegreeCandidateTemp.getIdentificationDocumentNumber().equals("112233"));
        assertEquals(masterDegreeCandidateTemp.getIdentificationDocumentType(), new TipoDocumentoIdentificacao(TipoDocumentoIdentificacao.BILHETE_DE_IDENTIDADE));
        assertTrue(masterDegreeCandidateTemp.getIdentificationDocumentIssuePlace().equals("Lisboa"));
        
        assertTrue(masterDegreeCandidateTemp.getIdentificationDocumentIssueDate().toString().equals("2002-11-17"));
        assertTrue(masterDegreeCandidateTemp.getName().equals("Nuno Nunes"));
        assertEquals(masterDegreeCandidateTemp.getSex(), new Sexo(Sexo.MASCULINO));
        assertEquals(masterDegreeCandidateTemp.getMaritalStatus(), new EstadoCivil(EstadoCivil.SOLTEIRO));
        
        assertTrue(masterDegreeCandidateTemp.getBirth().toString().equals("2000-12-10"));
        assertTrue(masterDegreeCandidateTemp.getFatherName().equals("Manuel"));
        assertTrue(masterDegreeCandidateTemp.getMotherName().equals("Maria"));
        assertTrue(masterDegreeCandidateTemp.getNationality().equals("Portugal"));
        assertTrue(masterDegreeCandidateTemp.getBirthPlaceParish().equals("Oeiras"));
        assertTrue(masterDegreeCandidateTemp.getBirthPlaceMunicipality().equals("Oeiras"));
        assertTrue(masterDegreeCandidateTemp.getBirthPlaceDistrict().equals("Lisboa"));
        assertTrue(masterDegreeCandidateTemp.getAddress().equals("Rua Nuno"));
        assertTrue(masterDegreeCandidateTemp.getPlace().equals("Localidade Nuno"));
        assertTrue(masterDegreeCandidateTemp.getPostCode().equals("2795-833"));
        assertTrue(masterDegreeCandidateTemp.getAddressParish().equals("Queijas"));
        assertTrue(masterDegreeCandidateTemp.getAddressMunicipality().equals("Oeiras"));
        assertTrue(masterDegreeCandidateTemp.getAddressDistrict().equals("Lisboa"));
        assertTrue(masterDegreeCandidateTemp.getTelephone().equals("11111"));
        assertTrue(masterDegreeCandidateTemp.getMobilePhone().equals("33333"));
        assertTrue(masterDegreeCandidateTemp.getEmail().equals("nmsn@rnl.ist.utl.pt"));
        assertTrue(masterDegreeCandidateTemp.getWebSite().equals("www.nuno.com"));
        assertTrue(masterDegreeCandidateTemp.getContributorNumber().equals("11111"));
        assertTrue(masterDegreeCandidateTemp.getOccupation().equals("Estudante"));
        assertTrue(masterDegreeCandidateTemp.getMajorDegree().equals("LEIC"));
        assertTrue(masterDegreeCandidateTemp.getUsername().equals("Cand1"));
        assertTrue(masterDegreeCandidateTemp.getPassword().equals("Pass1"));
        assertTrue(masterDegreeCandidateTemp.getCandidateNumber().equals(new Integer(1)));
        assertTrue(masterDegreeCandidateTemp.getApplicationYear().equals(new Integer(2002)));
        assertEquals(masterDegreeCandidateTemp.getSpecialization(), new Specialization(Specialization.MESTRADO));
        assertTrue(masterDegreeCandidateTemp.getMajorDegreeSchool().equals("IST"));
        assertTrue(masterDegreeCandidateTemp.getMajorDegreeYear().equals(new Integer(2000)));
        assertTrue(masterDegreeCandidateTemp.getAverage().equals(new Double(11)));

    }

    public void testReadNonExistingMasterDegreeCandidate2() {
        System.out.println("- Test 10 : Read non existing MasterDegreeCandidate by candidateNumber, applicationYear and masterDegreeCode");
        IMasterDegreeCandidate masterDegreeCandidateTemp = null;
        
        try {
            _suportePersistente.iniciarTransaccao();
            masterDegreeCandidateTemp = persistentMasterDegreeCandidate.readMasterDegreeCandidateByNumberAndApplicationYearAndDegreeCode(new Integer(999), new Integer(95486), "555");
            assertNull(masterDegreeCandidateTemp);
            _suportePersistente.confirmarTransaccao();
            
        } catch (ExcepcaoPersistencia ex) {
            fail("    -> Error on test");
        }
    }
    
    
} // End of test from Class MasterDegreeCandidateOJB
