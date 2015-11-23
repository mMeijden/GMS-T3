package beans;

import org.junit.Before;
import org.junit.Test;
import persist.Car;
import persist.Instruction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 21-11-2015.
 */
public class InstructionProcessBeanTest {

    private InstructionProcessBean instructionProcessBean;
    private InstructionRequestBean instructionRequestBean;
    private CarRequestBean carRequestBean;
    private String email;
    private String license;
    private Instruction instruction;
    private Car car;

    @Before
    public void setUp(){
        instructionRequestBean = mock(InstructionRequestBean.class);
        carRequestBean = mock(CarRequestBean.class);
        instructionProcessBean = new InstructionProcessBean(instructionRequestBean, carRequestBean);

        email = "test@mail.nl";
        license = "test01";
        instruction = new Instruction();
        car = new Car();
    }

    @Test
    public void testExecuteProcessCorrect() throws Exception {
        when(carRequestBean.findByLicense(license)).thenReturn(car);
        when(instructionRequestBean.createInstruction(instruction)).thenReturn(true);
        assertThat(instructionProcessBean.executeProcess(email, license, instruction), is("instructionConfirmed"));
    }

    @Test
    public void testExecuteProcessCarNotExist(){
        when(carRequestBean.findByLicense(license)).thenReturn(null);
        assertThat(instructionProcessBean.executeProcess(email, license, instruction), is("createCar"));
    }
}