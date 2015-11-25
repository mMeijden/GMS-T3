package beans;

import javax.faces.context.FacesContext;

import example.ApkCaller;
import org.junit.Before;
import org.junit.Test;
import persist.Car;
import persist.Instruction;
import util.InstructionStatus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 21-11-2015.
 */
public class InstructionProcessBeanTest {

    private InstructionProcessBean instructionProcessBean;
    private InstructionRequestBean instructionRequestBean;
    private ApkCaller apkCaller;
    private CarRequestBean carRequestBean;
    private String email;
    private String license;
    private Instruction instruction;
    private Car car;

    @Before
    public void setUp() {
        instructionRequestBean = mock(InstructionRequestBean.class);
        carRequestBean = mock(CarRequestBean.class);
        apkCaller = mock(ApkCaller.class);
        instructionProcessBean = new InstructionProcessBean();
        instructionProcessBean.setInstructionRequestBean(instructionRequestBean);
        instructionProcessBean.setCarRequestBean(carRequestBean);
        instructionProcessBean.setApkCaller(apkCaller);

        email = "test@mail.nl";
        license = "test01";
        instruction = new Instruction();
        car = new Car();
    }

    @Test
    public void testExecuteProcessCorrect() throws Exception {
        when(carRequestBean.findByLicense(license)).thenReturn(car);
        when(instructionRequestBean.createInstruction(instruction)).thenReturn(true);
        assertThat(instructionProcessBean.executeProcess(email, license, instruction), is("instructionOverview"));
    }

    @Test
    public void testExecuteProcessCarNotExist() {
        when(carRequestBean.findByLicense(license)).thenReturn(null);
        assertThat(instructionProcessBean.executeProcess(email, license, instruction), is("createCar"));
    }

    @Test
    public void testEndInstructionNoAPK(){
        instruction.setApk(false);
        InstructionStatus status = InstructionStatus.DONE;
        doNothing().when(instructionRequestBean).alterInstructionStatus(instruction, status);
        System.out.println("Method executed succesfully");
    }

    @Test
    public void testEndInstructionAPKNoSample(){
        instruction.setApk(true);
        InstructionStatus status = InstructionStatus.DONE;

        when(apkCaller.markReadyForSample(car)).thenReturn(false);
        doNothing().when(instructionRequestBean).alterInstructionStatus(instruction, status);
        System.out.println("Method executed succesfully");
    }

    @Test
    public void testEndInstructionAPKSample(){
        instruction.setApk(true);
        InstructionStatus status = InstructionStatus.SAMPLE;

        when(apkCaller.markReadyForSample(car)).thenReturn(true);
        doNothing().when(instructionRequestBean).alterInstructionStatus(instruction, status);
        System.out.println("Method executed succesfully");
    }
}