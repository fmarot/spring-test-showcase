package hello.businesslogic;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/** Exemple a la con car ca ne sert à rien de faire comme ca 
 * à par perdre du temps à initialiser Spring ! */
@RunWith(SpringRunner.class)
public class DWIComputerTestUseless {

	@MockBean
	private VolumeService volumeServiceMock;
		
	@Test
	public void testDWIComputerWithMockedVolumeService() {

		when(volumeServiceMock.fetchVolume())
				.thenReturn(new int[] { 0, 9, 0 });

		DWIComputer dwiComputer = new DWIComputer(volumeServiceMock, new FiberDetector());
		int dwi = dwiComputer.computeDWI().intValue();
		Assert.assertEquals(dwi, 0 + 9 + 0);
	}
	
}
